package shaomai.app.main.ui

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import shaomai.app.R

/**
 *  created by chenglei on 2017/10/3.
 *  @describe
 */
class CollectionFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater?.inflate(R.layout.frag_collection, container, false) as View
    }
}