package shaomai.app.translate.network

import retrofit2.Call
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import shaomai.app.translate.network.response.TranslateResponse

/**
 * Created by chenglei on 2017/11/7.
 */
interface TranslateService {


    @FormUrlEncoded
    @POST("/api/trans/vip/translate")
    fun translate(@FieldMap params:Map<String,String>):Call<TranslateResponse>
}