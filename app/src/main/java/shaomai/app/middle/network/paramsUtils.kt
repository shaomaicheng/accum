package shaomai.app.middle.network

import shaomai.app.AccumApplication
import java.lang.Exception
import java.security.MessageDigest
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.experimental.and

/**
 * Created by chenglei on 2017/11/23.
 */

/**
 * 拼接网络请求
 * @param q 请求的参数
 */
fun mergeParams(params: HashMap<String, String>, q: String?):HashMap<String,String> {

    params["q"] = q!!
    params["from"] = "en"
    params["to"] = "zh"
    params["appid"] = AccumApplication.APP_ID
    params["salt"] =
            "1435660288"
//            randomSalt()
    params["sign"] =
//            "48ca1ae8a96f85c4dc2cb33e93297aa3"
            getMd5(AccumApplication.APP_ID + q + params["salt"] + AccumApplication.SECRET)

    return params
}


fun getMd5(str: String): String {
    try {
        val messageDigest = MessageDigest.getInstance("md5")
        var hexString = ""
        val bs = messageDigest.digest(str.toByteArray())
        bs.asSequence()
                .map { it.toInt() and 255 }
                .forEach {
                    hexString += if (it in 0..15) {
                        "0" + Integer.toHexString(it)
                    } else {
                        Integer.toHexString(it)
                    }
                }
        return hexString
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return ""
}


fun randomSalt(): String {
    var res = ""
    for (i in 1..10) {
        res += Random().nextInt(10)
    }
    return res
}