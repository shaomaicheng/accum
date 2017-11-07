package shaomai.app.middle

import okhttp3.Response
import retrofit2.HttpException
import shaomai.app.middle.network.BaseResponse

/**
 * Created by chenglei on 2017/11/7.
 */

sealed class Result<T: BaseResponse> {



    class OK<T: BaseResponse>(
        val value: T,
        val response: Response
    ) : Result<T>()

    class Error<T:BaseResponse> (
        val exception: HttpException,
        val response: Response
    ): Result<T>()

    class Exception<T:BaseResponse>(val exception:Throwable)
        :Result<T>()
}
