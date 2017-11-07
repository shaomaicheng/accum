package shaomai.app.middle

import android.view.View
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.channels.actor

/**
 * Created by chenglei on 2017/11/6.
 */


/**
 * 防止多次点击
 */
fun View.onClick(action:suspend ()->Unit) {

    val eventActor = actor<Unit>(UI) {
        for (event in channel) {
            action()
        }
    }

    setOnClickListener {
        eventActor.offer(Unit)
    }

}