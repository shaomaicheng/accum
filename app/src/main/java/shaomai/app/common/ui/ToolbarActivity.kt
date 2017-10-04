package shaomai.app.common.ui

import android.annotation.TargetApi
import android.app.Fragment
import android.os.Build
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import shaomai.app.R
import shaomai.app.middle.lightStatusBar
import shaomai.app.middle.statusBarLightMode

/**
 * created by chenglei on 2017/9/24.

 * @describe
 */

open abstract class ToolbarActivity : AppCompatActivity() {
    lateinit var mToolbar: Toolbar

    var title:String
        get(): String {
            return mToolbar.title.toString()
        }
        set(value) {
            mToolbar.title = value
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initWindow()
        setContentView(R.layout.activity_toolbar)
        fragmentManager
                .beginTransaction()
                .replace(R.id.fragment_toolbar_activity, replaceFragment())
                .commit()
    }

    private fun initWindow() {
        statusBarLightMode(lightStatusBar())
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun setStatusBarColor() {
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimaryDark)
    }

    abstract fun replaceFragment(): Fragment
    abstract fun initViewsAndEvent(): Unit

}
