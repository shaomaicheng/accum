package shaomai.app.common.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import shaomai.app.R
import shaomai.app.middle.lightStatusBar
import shaomai.app.middle.statusBarLightMode

/**
 * created by chenglei on 2017/9/24.

 * @describe
 */

open abstract class ToolbarActivity : AppCompatActivity() {
    private var mToolbar: Toolbar? = null

    var title: String
        get(): String = mToolbar?.title.toString()
        set(value) {
            mToolbar?.title = value
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initWindow()
        setContentView(R.layout.activity_toolbar)
        mToolbar = findViewById(R.id.toolbar)
        setSupportActionBar(mToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_toolbar_activity, replaceFragment())
                .commit()
        initViewsAndEvent()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home ->
                finish()
        }
        return false
    }

    private fun initWindow() {
        statusBarLightMode(lightStatusBar())
    }

    abstract fun replaceFragment(): Fragment
    abstract fun initViewsAndEvent(): Unit

}
