package shaomai.app.main.ui

import android.app.Fragment
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import shaomai.app.R
import shaomai.app.translate.ui.TranslateActivity

/**
 *  created by chenglei on 2017/9/24.
 *  @describe
 */
class HomeFragment : Fragment() {

    lateinit var edit : EditText

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater?.inflate(R.layout.frag_home, container, false) as View
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        edit = activity.findViewById(R.id.home_goto_edit)
        initViews()
    }

    fun initViews() {
        hideInputSoftBroad()
        edit.requestFocus()
        edit.setOnClickListener { startActivity(Intent(activity, TranslateActivity::class.java)) }
    }


    fun hideInputSoftBroad() {
        (activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(edit.windowToken, 0)
    }
}