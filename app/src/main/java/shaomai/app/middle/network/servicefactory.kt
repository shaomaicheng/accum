package shaomai.app.middle.network

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by chenglei on 2017/11/7.
 */

private fun createRetrofit(): Retrofit {
    return Retrofit.Builder()
            .baseUrl("http://fanyi-api.baidu.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}

fun <T> createService(clazz: Class<T>): T {
    return createRetrofit().create(clazz)
}