package shaomai.app.home.viewmodel

import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField

/**
 *  created by chenglei on 2017/10/5.
 *  @describe
 */
class TranslateViewModel : ViewModel() {
    val mObservableTranslated = MutableLiveData<String>()
    var mTranslated = ObservableField<String>()
    var mResult = ObservableField<String>()

    fun translate(content: String?): String {
        return content ?: ""
    }
}