package shaomai.app.home.ui

import android.arch.lifecycle.LifecycleFragment
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import shaomai.app.R
import shaomai.app.databinding.FragTranslationBinding
import shaomai.app.home.viewmodel.TranslateViewModel

/**
 *  created by chenglei on 2017/9/24.
 *  @describe
 */
class TranslateFragment : LifecycleFragment() {
    private lateinit var mBinding: FragTranslationBinding
    private lateinit var viewModel: TranslateViewModel
    private lateinit var translateInputView:EditText

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mBinding = DataBindingUtil.inflate(inflater,R.layout.frag_translation,container,false)
        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(TranslateViewModel::class.java)
        mBinding.translateModel = viewModel

        translateInputView = activity.findViewById(R.id.translate_input_view)

        translateInputView.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
            override fun afterTextChanged(s: Editable?) {
                viewModel.mObservableTranslated.value = s.toString()
            }
        })

        subscribeUI()
    }

    private fun subscribeUI() {
        viewModel.mObservableTranslated.observe(this, Observer { content->
            viewModel.translate(content)
        })
    }
}