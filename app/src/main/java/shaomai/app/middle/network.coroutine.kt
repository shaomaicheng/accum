package shaomai.app.middle

import kotlinx.coroutines.experimental.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import shaomai.app.middle.network.BaseResponse

/**
 * Created by chenglei on 2017/11/7.
 */

suspend fun <T : BaseResponse> Call<T>.await(): Result<T> {
    return suspendCancellableCoroutine { continuation ->
        enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>?, response: Response<T>) {
                continuation.resume(
                        if (response.isSuccessful) {
                            val body = response.body()
                            if (body == null) {
                                Result.Exception(NullPointerException("Response is null"))
                            } else {
                                Result.OK(body, response.raw())
                            }
                        } else {
                            Result.Error(HttpException(response), response.raw())
                        }
                )
            }

            override fun onFailure(call: Call<T>?, t: Throwable) {
                if (continuation.isCancelled) return
                continuation.resume(Result.Exception(t))
            }

        })

        continuation.invokeOnCompletion {
            if (continuation.isCancelled) {
                cancel()
            }
        }
    }
}