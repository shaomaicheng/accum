package shaomai.app

import android.app.Application
import com.avos.avoscloud.AVOSCloud
import com.facebook.stetho.Stetho

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
        Stetho.initializeWithDefaults(this)
        AVOSCloud.initialize(this, "C5GwCBeeMdNjUau8JsQge7FB-gzGzoHsz",
                "xYJaKw4eDTIkWGzMMK30zKwq")
        AVOSCloud.setDebugLogEnabled(true)

    }
}