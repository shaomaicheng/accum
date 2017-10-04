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
 * 修改状态栏为全透明
 */
inline fun AppCompatActivity.transparencyBar() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        window
                .apply { clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS) }
                .apply { decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE }
                .apply { addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS) }
                .apply { statusBarColor = Color.TRANSPARENT }
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
inline fun AppCompatActivity.setStatusBarColor(color:Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        window
                .apply { addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS) }
                .apply { statusBarColor = ContextCompat.getColor(this as Context, color) }
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
inline fun AppCompatActivity.MIUISetStatusBarLightMode(dark: Boolean): Boolean {
    var res = false
    window?.let {
        try {
            var clazz: Class<Window> = window.javaClass
            var darkModeFlag = 0
            var lp = Class.forName("android.view.MiuiWindowManager\$LayoutParams")
            var field = lp.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE")
            darkModeFlag = field.getInt(lp)
            var extraFlagField = clazz.getMethod("setExtraFlags", Int::class.java, Int::class.java)
            if (dark) {
                extraFlagField.invoke(window, darkModeFlag, darkModeFlag)
            } else {
                extraFlagField.invoke(window, 0, darkModeFlag)
            }
            res = true
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                window.decorView.systemUiVisibility = if (dark) View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR else View.SYSTEM_UI_FLAG_VISIBLE
            }else{}
        } catch (e: Exception) {
        }
    }
    return res
}

/**
 * 如果是魅族的flyme系统，是否是亮色状态栏
 */
inline fun AppCompatActivity.flymeSetStatusBarLightMode(dark: Boolean): Boolean {
    var res = false
    window?.let {
        try {
            var lp = window.attributes
            var darkFlag = WindowManager.LayoutParams::class.java.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON")
            var meizuFlags = WindowManager.LayoutParams::class.java.getDeclaredField("meizuFlags")
            darkFlag.isAccessible = true
            meizuFlags.isAccessible = true
            var bit = darkFlag.getInt(null)
            var value = meizuFlags.getInt(lp)
            value = if (dark) value or bit else value and bit.inv()
            meizuFlags.setInt(lp, value)
            window.attributes = lp
            res = true
        } catch (e: Exception) {
        }
    }
    return res
}


inline fun AppCompatActivity.statusBarDarkMode(type: Int) {
    when(type) {
        1->{MIUISetStatusBarLightMode(false)}
        2->{flymeSetStatusBarLightMode(false)}
        3->{window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE}
    }
}

inline fun AppCompatActivity.statusBarLightMode(type: Int) {
    when(type){
        1->{MIUISetStatusBarLightMode(true)}
        2->{flymeSetStatusBarLightMode(true)}
        3->{window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR}
    }
}



inline fun AppCompatActivity.lightStatusBar(): Int {
    var res = 0
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
        if (MIUISetStatusBarLightMode(true)){
            res = 1
        } else if (flymeSetStatusBarLightMode(true)) {
            res = 2
        } else if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            res = 3
        }
    }
    return res
}

