package shaomai.app.home.network

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Query
import shaomai.app.middle.network.TranslateResponse

/**
 * Created by chenglei on 2017/11/7.
 */
interface TranslateService {


    @FormUrlEncoded
    @POST("/api/trans/vip/translate")
    fun translate(@Field("q")content:String?):Call<TranslateResponse>
}