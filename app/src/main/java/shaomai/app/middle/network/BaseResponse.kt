package shaomai.app.middle.network

import com.google.gson.annotations.SerializedName

/**
 * Created by chenglei on 2017/11/7.
 */

open class BaseResponse

class TranslateResponse(
        @SerializedName("from")
        var from:String,

        @SerializedName("to")
        var to:String,

        @SerializedName("trans_result")
        var transResult: List<TranslateResult> = ArrayList(),

        @SerializedName("error_code")
        var errorCode:String,
        @SerializedName("error_msg")
        var errorMsg:String

): BaseResponse()


class TranslateResult(
        @SerializedName("src")
        var src: String,

        @SerializedName("dst")
        var dst:String
)