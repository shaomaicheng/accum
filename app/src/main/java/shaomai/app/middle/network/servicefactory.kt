package shaomai.app.middle.network

import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by chenglei on 2017/11/7.
 */

private fun createRetrofit(): Retrofit
    = Retrofit.Builder()
            .client(OkHttpClient.Builder().addNetworkInterceptor(StethoInterceptor()).build())
            .baseUrl("http://fanyi-api.baidu.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

fun <T> createService(clazz: Class<T>): T = createRetrofit().create(clazz)