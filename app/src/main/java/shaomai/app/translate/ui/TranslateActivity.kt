package shaomai.app.translate.ui

import android.support.v4.app.Fragment
import shaomai.app.R
import shaomai.app.common.ui.ToolbarActivity

/**
 *  created by chenglei on 2017/9/24.
 *  page to input your want to translate text
 *  @describe
 */

class TranslateActivity : ToolbarActivity() {

    override fun replaceFragment(): Fragment = TranslateFragment()

    override fun initViewsAndEvent() {
        title = getString(R.string.translate_content_want)
    }

}