package shaomai.app.account.ui

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.TextView
import org.jetbrains.anko.toast
import shaomai.app.R
import shaomai.app.account.constant.KEY_SIGN_TYPE
import shaomai.app.account.viewmodel.SignupViewModel
import shaomai.app.databinding.ActivitySignupBinding
import shaomai.app.middle.lightStatusBar
import shaomai.app.middle.statusBarLightMode

/**
 * Created by chenglei on 2017/11/26.
 */

class SignupActivity : AppCompatActivity() {

    private val TAG = "SignupActivity"
    lateinit var binding: ActivitySignupBinding

    lateinit var viewModel: SignupViewModel

    var typeSign:Boolean = false

    companion object {
        val COUNT_DOWN_MAX = 60
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        statusBarLightMode(lightStatusBar())

        typeSign = intent.getBooleanExtra(KEY_SIGN_TYPE, false)


        viewModel = ViewModelProviders.of(this).get(SignupViewModel::class.java)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup)
        binding.viewmodel = viewModel
        viewModel.signupType.set(typeSign)

        binding.visible = View.VISIBLE
        binding.gone = View.GONE
        binding.phone = ""
        binding.password = ""
        binding.interval = COUNT_DOWN_MAX
        binding.code = ""

    }

    fun clickSign(view :View) {


        Log.i(TAG, "click sign button")

        if (TextUtils.isEmpty(binding.phone)
                || TextUtils.isEmpty(binding.password)) {
            // input is empty, please input !!!
            toast("请输入用户名或者密码")
        }

        when (viewModel.signupType.get()) {
            true-> {
                signUp()
            }
            false -> {
                signIn()
            }
        }
    }

    fun sendVerifyCode(view: View) {
        if ((view as TextView).text.toString() == getString(R.string.send_verify_code)) {
            viewModel.countDownStart.set(true)

            val countDowner = object : Runnable {
                override fun run() {
                    if (binding.interval == 1) {
                        binding.interval = COUNT_DOWN_MAX
                        viewModel.countDownStart.set(false)
                    } else {
                        binding.interval--
                        Handler().postDelayed(this, 1000)
                    }
                }
            }

            countDowner.run()
        }
    }


    fun signUp() {

    }


    fun signIn() {

    }

}
