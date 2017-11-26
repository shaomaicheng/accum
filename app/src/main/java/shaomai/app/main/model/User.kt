package shaomai.app.main.model

import android.databinding.ObservableField

/**
 * Created by chenglei on 2017/11/26.
 */
class User
(
        var userId:ObservableField<String>,
        var username:ObservableField<String>,
        var avtarUrl:ObservableField<String>,
        var favorite:ObservableField<String>,
        var provice:ObservableField<String>,
        var city:ObservableField<String>,
        var country:ObservableField<String>,
        var address:ObservableField<String>
)