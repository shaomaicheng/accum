package shaomai.app.translate.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.text.TextUtils
import android.util.Log
import kotlinx.coroutines.experimental.async
import shaomai.app.translate.network.TranslateService
import shaomai.app.translate.network.response.TranslateResponse
import shaomai.app.middle.Result
import shaomai.app.middle.await
import shaomai.app.middle.network.createService
import shaomai.app.middle.network.mergeParams

/**
 *  created by chenglei on 2017/10/5.
 *  @describe
 */
class TranslateViewModel : ViewModel() {

    private val TAG:String = "TranslateViewModel"

    val mObservableTranslated = MutableLiveData<String>()
    var mTranslated = ObservableField<String>()
    var mResult = ObservableField<String>()
    private val service: TranslateService by lazy { createService(TranslateService::class.java) }

    /**
     * 翻译
     */
    fun translate(content: String?) {
        if (TextUtils.isEmpty(content)) {
            mResult.set("")
            return
        }
        async {
            var params = HashMap<String,String>()
            var result = service.translate(mergeParams(params, content)).await()
            when (result) {
                is Result.OK<TranslateResponse> -> {
                    var response: TranslateResponse = result.value
                    if (!TextUtils.isEmpty(response.errorCode)) {
                        Log.e(TAG, response.errorMsg)
                        mResult.set("暂无结果")
                    } else {
                        var translateRes = response.transResult[0].dst
                        Log.e(TAG, translateRes)
                        mResult.set(translateRes)
                    }
                }
                is Result.Exception<TranslateResponse> -> {
                    var response: Throwable = result.exception
                    Log.e(TAG, response.message)
                    mResult.set("暂无结果")
                }
                is Result.Error<TranslateResponse> -> {
                    var errorResponse: okhttp3.Response = result.response
                    Log.e(TAG, errorResponse.message())
                }
            }
        }
    }
}