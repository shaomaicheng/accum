package shaomai.app.main.ui

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import shaomai.app.R

/**
 *  created by chenglei on 2017/10/3.
 *  @describe 我的
 */
class MineFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater?.inflate(R.layout.frag_mine, container, false) as View
    }
}