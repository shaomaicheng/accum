package shaomai.app.account.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import com.avos.avoscloud.*

/**
 * Created by chenglei on 2017/12/17.
 */

class SignupViewModel : ViewModel() {
    var signupType: ObservableField<Boolean> = ObservableField(false)
    var countDownStart: ObservableField<Boolean> = ObservableField(false)


    var codeError = MutableLiveData<Boolean>()
    var signUpError = MutableLiveData<String>()
    var signUpSuccess = MutableLiveData<Boolean>()

    fun clickSwitchSign() {
        signupType.set(!signupType.get())
    }

    /**
     * 注册
     * @param phone 手机号
     * @param password 密码
     * @param username 用户名
     * @param code 验证码
     *
     *
     * 测试leancloud 短信验证码
     * 手机 18216728528
     * 验证码 779903
     */
    fun signUp(phone: String?, password: String?, username: String?, code: String?) {
        AVSMS.verifySMSCodeInBackground(code, phone, object : AVMobilePhoneVerifyCallback() {
            override fun done(e: AVException?) {
                if (e != null) {
                    codeError.value = true
                } else {
                    val user = AVUser()
                    user.username = username
                    user.mobilePhoneNumber = phone
                    user.setPassword(password)
                    user.signUpInBackground(object : SignUpCallback() {
                        override fun done(e: AVException?) {
                            if (e != null) {
                                signUpError.value = e.message
                            } else {
                                signUpSuccess.value = true
                            }
                        }
                    })
                }
            }
        })
    }
}
