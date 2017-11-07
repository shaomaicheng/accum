package shaomai.app.home.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.text.TextUtils
import android.util.Log
import kotlinx.coroutines.experimental.async
import shaomai.app.home.network.TranslateService
import shaomai.app.middle.Result
import shaomai.app.middle.await
import shaomai.app.middle.network.TranslateResponse
import shaomai.app.middle.network.createService

/**
 *  created by chenglei on 2017/10/5.
 *  @describe
 */
class TranslateViewModel : ViewModel() {

    val TAG:String = TranslateViewModel::class.java.simpleName

    val mObservableTranslated = MutableLiveData<String>()
    var mTranslated = ObservableField<String>()
    var mResult = ObservableField<String>()
    private val service: TranslateService by lazy { createService(TranslateService::class.java) }

    /**
     * 翻译
     */
    fun translate(content: String?) {
        async {
            var result = service.translate(content).await()
            when (result) {
                is Result.OK -> {
                    var response: TranslateResponse = result.value
                    if (!TextUtils.isEmpty(response.errorCode)) {
                        Log.e(TAG, response.errorMsg)
                    } else {
                        Log.e(TAG, response.transResult[0].dst)
                    }
                }
                is Result.Exception -> {
                    var response: Throwable = result.exception
                    Log.e(TAG, response.message)
                }
                is Result.Error -> {
                    var errorResponse: okhttp3.Response = result.response
                    Log.e(TAG, errorResponse.message())
                }
            }
        }
    }
}