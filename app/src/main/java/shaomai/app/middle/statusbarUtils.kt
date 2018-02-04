package shaomai.app.middle

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.Window
import android.view.WindowManager
import com.readystatesoftware.systembartint.SystemBarTintManager


/**
 *  created by chenglei on 2017/10/4.
 *  @describe 代码参考 http://www.jianshu.com/p/7f5a9969be53
 */

/**
 * 是否是深颜色
 */
fun isColorDark(color: Int): Boolean {
    if (Color.red(color) == 0xF
            && Color.green(color) == 0xF
            && Color.blue(color) == 0xF) {
        return false
    }
    return 1 - (0.299 * Color.red(color) + 0.587 * Color.green(color) + 0.114 * Color.blue(color)) / 255 >= 0.5
}

/**
 * 修改状态栏为全透明
 */
fun AppCompatActivity.transparencyBar() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        window
                .apply {
                    clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                    decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                    statusBarColor = Color.TRANSPARENT
                }
    } else {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
    }
}

/**
 * 设置状态栏颜色
 */
fun AppCompatActivity.setStatusBarColor(color: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        window.apply {
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            statusBarColor = ContextCompat.getColor(this as Context, color)
        }
    } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
        transparencyBar()
        val tintManager = SystemBarTintManager(this)
        tintManager.apply { isStatusBarTintEnabled = true }
                .apply { setStatusBarTintResource(color) }
    }
}

/**
 * 如果是小米系统，是否为亮色的状态栏
 * @param dark true代表是，false代表不是
 */
fun AppCompatActivity.MIUISetStatusBarLightMode(dark: Boolean): Boolean {
    var res = false
    window?.let {
        val clazz: Class<Window> = window.javaClass
        var darkModeFlag = 0
        val lp = Class.forName("android.view.MiuiWindowManager\$LayoutParams")
        val field = lp.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE")
        darkModeFlag = field.getInt(lp)
        val extraFlagField = clazz.getMethod("setExtraFlags", Int::class.java, Int::class.java)
        extraFlagField.invoke(window, if (dark) 0 else darkModeFlag, darkModeFlag)
        res = true
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility =
                    if (dark) View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR else View.SYSTEM_UI_FLAG_VISIBLE
        }
    }
    return res
}

/**
 * 如果是魅族的flyme系统，是否是亮色状态栏
 */
fun AppCompatActivity.flymeSetStatusBarLightMode(dark: Boolean): Boolean {
    var res = false
    window?.let {
        val lp = window.attributes
        val darkFlag = WindowManager.LayoutParams::class.java.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON")
        val meizuFlags = WindowManager.LayoutParams::class.java.getDeclaredField("meizuFlags")
        darkFlag.isAccessible = true
        meizuFlags.isAccessible = true
        val bit = darkFlag.getInt(null)
        var value = meizuFlags.getInt(lp)
        value = if (dark) value or bit else value and bit.inv()
        meizuFlags.setInt(lp, value)
        window.attributes = lp
        res = true
    }
    return res
}

fun AppCompatActivity.statusBarDarkMode(type: Int) {
    when (type) {
        1 -> {
            MIUISetStatusBarLightMode(false)
        }
        2 -> {
            flymeSetStatusBarLightMode(false)
        }
        3 -> {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
        }
    }
}

fun AppCompatActivity.statusBarLightMode(type: Int) {
    when (type) {
        1 -> {
            MIUISetStatusBarLightMode(true)
        }
        2 -> {
            flymeSetStatusBarLightMode(true)
        }
        3 -> {
            window.decorView.systemUiVisibility =
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                    else View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
    }
}


fun AppCompatActivity.lightStatusBar(): Int {
    var res = 0
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
        when {
            MIUISetStatusBarLightMode(true) -> res = 1
            flymeSetStatusBarLightMode(true) -> res = 2
            Build.VERSION.SDK_INT > Build.VERSION_CODES.M -> {
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                res = 3
            }
        }
    }
    return res
}

fun AppCompatActivity.darkStatusBar(): Int {
    var res = 0
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
        when {
            MIUISetStatusBarLightMode(false) -> res = 1
            flymeSetStatusBarLightMode(false) -> res = 2
            Build.VERSION.SDK_INT > Build.VERSION_CODES.M -> {
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
                res = 3
            }
        }
    }
    return res
}

