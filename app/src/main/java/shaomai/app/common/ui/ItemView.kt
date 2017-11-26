package shaomai.app.common.ui

import android.content.Context
import android.databinding.BindingAdapter
import android.graphics.Color
import android.util.AttributeSet
import android.view.Gravity
import android.widget.RelativeLayout
import android.widget.TextView
import org.jetbrains.anko.backgroundColor
import shaomai.app.R

/**
 * Created by chenglei on 2017/11/26.
 */
class ItemView : RelativeLayout {

    private lateinit var content: String
    private lateinit var title: String

    private lateinit var titleTv: TextView
    private lateinit var contentTv: TextView

    constructor(context: Context?) : super(context) {
        init(context, null)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    private fun init(context: Context?, attrs: AttributeSet?) {
        titleTv = TextView(context)
        addView(titleTv)

        (titleTv.layoutParams as LayoutParams).apply {
            width = LayoutParams.WRAP_CONTENT
            height = LayoutParams.MATCH_PARENT
            addRule(ALIGN_PARENT_LEFT)
            leftMargin = 16
            gravity = CENTER_IN_PARENT
        }
        titleTv.apply {
            gravity = Gravity.CENTER
            textSize = 16f
            setTextColor(Color.BLACK)
        }

        contentTv = TextView(context)
        addView(contentTv)

        (contentTv.layoutParams as LayoutParams).apply {
            width = LayoutParams.WRAP_CONTENT
            height = LayoutParams.MATCH_PARENT
            addRule(ALIGN_PARENT_RIGHT)
            rightMargin = 16
            gravity = CENTER_IN_PARENT
        }
        contentTv.apply {
            gravity = Gravity.CENTER
            textSize = 12f
        }


        context?.let {
            var typeArr = context.obtainStyledAttributes(attrs, R.styleable.ItemView)
            typeArr?.let {
                if (typeArr.hasValue(R.styleable.ItemView_title)) {
                    title = typeArr.getString(R.styleable.ItemView_title)
                    titleTv.text = title
                }
                if (typeArr.hasValue(R.styleable.ItemView_content)) {
                    content = typeArr.getString(R.styleable.ItemView_content)
                    contentTv.text = content
                }
                typeArr.recycle()
            }
        }

        backgroundColor = Color.WHITE
    }

    companion object {
        @BindingAdapter("title")
        @JvmStatic
        fun setItemViewTitle(view: ItemView, title: String?) {
            title?.let {
                view.title = title
            }
        }

        @BindingAdapter("content")
        @JvmStatic
        fun setItemViewContent(view: ItemView, content: String?) {
            content?.let {
                view.content = content
            }
        }
    }
}