package shaomai.app.account.ui

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import shaomai.app.R
import shaomai.app.account.viewmodel.SignupViewModel
import shaomai.app.databinding.ActivitySignupBinding
import shaomai.app.middle.lightStatusBar
import shaomai.app.middle.statusBarLightMode

/**
 * Created by chenglei on 2017/11/26.
 */

class SignupActivity : AppCompatActivity() {


    lateinit var binding: ActivitySignupBinding

    lateinit var viewModel: SignupViewModel

    var typeSign:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        statusBarLightMode(lightStatusBar())
        viewModel = ViewModelProviders.of(this).get(SignupViewModel::class.java)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup)
        binding.viewmodel = viewModel

        typeSign = intent.getBooleanExtra("sign_type", false)
        binding.visible = View.VISIBLE
        binding.gone = View.GONE
        binding.phone = ""
        binding.password = ""
        binding.interval = 60
        binding.code = ""
    }

}
