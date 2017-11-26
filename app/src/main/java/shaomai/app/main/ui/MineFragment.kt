package shaomai.app.main.ui

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.request.RequestOptions
import shaomai.app.GlideApp
import shaomai.app.R
import shaomai.app.databinding.FragMineBinding

/**
 *  created by chenglei on 2017/10/3.
 *  @describe 我的  tab页
 */
class MineFragment : Fragment() {
    private lateinit var binding: FragMineBinding
    private lateinit var avatarImg: ImageView

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View
    {
        //可能会报错
        // 所以先把父view remove掉
        var view = DataBindingUtil.inflate<FragMineBinding>(inflater, R.layout.frag_mine, container, true)
                .root
        if (view.parent != null) {(view.parent as ViewGroup).removeView(view)}
        return view
    }
//    = inflater!!.inflate(R.layout.frag_mine, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        avatarImg = activity.findViewById(R.id.avatar)
        GlideApp.with(this)
                .load("")
                .placeholder(R.drawable.default_avatar)
                .apply(RequestOptions().circleCrop())
                .into(avatarImg)
    }
}