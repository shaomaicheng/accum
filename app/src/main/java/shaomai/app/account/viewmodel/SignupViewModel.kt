package shaomai.app.account.viewmodel

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField

/**
 * Created by chenglei on 2017/12/17.
 */

class SignupViewModel : ViewModel() {
    var signupType: ObservableField<Boolean> = ObservableField(false)
    var countDownStart: ObservableField<Boolean> = ObservableField(false)

    fun clickSwitchSign() {
        signupType.set(!signupType.get())
    }
}
