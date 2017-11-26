package shaomai.app.main.ui

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.avos.avoscloud.AVUser
import shaomai.app.R
import shaomai.app.account.SignupActivity
import shaomai.app.middle.*

/**
 * main Activity
 * create by chenglei
 */
class MainActivity : AppCompatActivity() {

    private lateinit var mainTab: TabLayout

    val home = HomeFragment()
    val collection = CollectionFragment()
    val mine = MineFragment()
    val frags = listOf(home, collection, mine)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var avuser = AVUser.getCurrentUser()
        if (avuser == null) {
            var intent = Intent(this, SignupActivity::class.java)
            //登录
            intent.putExtra("sign_type", false)
            startActivity(intent)
            finish()
        }
//        if (!isColorDark(R.color.colorPrimaryDark)){
//            statusBarLightMode(lightStatusBar())
//        } else{
//            statusBarDarkMode(darkStatusBar())
//        }
        statusBarLightMode(lightStatusBar())
        setContentView(R.layout.activity_main)

        mainTab = findViewById(R.id.main_tab)
        initViewsAndEvents()
    }

    private fun initViewsAndEvents() {
        supportFragmentManager.beginTransaction().add(R.id.main_frag, frags[0], "0").commit()
        mainTab.apply {
            addTab(newTab().apply { setText(R.string.home) }.setIcon(R.mipmap.home))
            addTab(newTab().apply { setText(R.string.collection) }.setIcon(R.mipmap.collection_gray))
            addTab(newTab().apply { setText(R.string.mine) }.setIcon(R.mipmap.mine_gray))
        }.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabUnselected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        tab.setIcon(R.mipmap.home_gray)
                    }
                    1 -> {
                        tab.setIcon(R.mipmap.collection_gray)
                    }
                    2 -> {
                        tab.setIcon(R.mipmap.mine_gray)
                    }
                }
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        tab.setIcon(R.mipmap.home)
                    }
                    1 -> {
                        tab.setIcon(R.mipmap.collection)
                    }
                    2 -> {
                        tab.setIcon(R.mipmap.mine)
                    }
                }
                frags.all {
                    supportFragmentManager.beginTransaction().hide(it).commit()
                    true
                }
                if (supportFragmentManager.findFragmentByTag((tab?.position as Int).toString()) == null) {
                    supportFragmentManager
                            .beginTransaction()
                            .add(R.id.main_frag, frags[tab.position], tab.position.toString())
                            .show(frags[tab.position])
                            .commit()
                } else {
                    supportFragmentManager
                            .beginTransaction()
                            .show(frags[tab.position])
                            .commit()
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }
}
