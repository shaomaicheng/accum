package shaomai.app.account

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import shaomai.app.R
import shaomai.app.databinding.ActivitySignupBinding
import shaomai.app.middle.lightStatusBar
import shaomai.app.middle.statusBarLightMode

/**
 * Created by chenglei on 2017/11/26.
 */

class SignupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        statusBarLightMode(lightStatusBar())
        var binding:ActivitySignupBinding = DataBindingUtil.setContentView(this,R.layout.activity_signup)
        binding.typeSign = intent.getBooleanExtra("sign_type", false)
        binding.visible = View.VISIBLE
        binding.gone = View.GONE
        binding.phone = ""
        binding.password = ""
        binding.interval = 60
        binding.code = ""
    }
}
