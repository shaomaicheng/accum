package shaomai.app

import android.app.Application

/**
 *  created by chenglei on 2017/10/6.
 *  @describe
 */
class AccumApplication: Application() {

    companion object {
        val APP_ID = "20170924000084933"
        val SECRET = "EfymFZ1DEK_NF37oXJ7y"
    }

    override fun onCreate() {
        super.onCreate()
    }
}