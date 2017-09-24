package shaomai.app.main.ui

import android.app.Fragment
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity
import shaomai.app.R

/**
 * main Activity
 * create by chenglei
 */
class MainActivity : AppCompatActivity() {

    private lateinit var mainTab: TabLayout

    val home = HomeFragment()
    val collection = Fragment()
    val mine = Fragment()

    val frags = listOf<Fragment>(home, collection, mine)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainTab = findViewById(R.id.main_tab)
        initViewsAndEvents()
    }

    fun initViewsAndEvents() {
        fragmentManager.beginTransaction().replace(R.id.main_frag, frags[0]).commit()
        mainTab
                .apply { addTab(newTab().apply { setText(R.string.home) }.setIcon(R.mipmap.home)) }
                .apply { addTab(newTab().apply { setText(R.string.collection) }.setIcon(R.mipmap.collection_gray)) }
                .apply { addTab(newTab().apply { setText(R.string.mine) }.setIcon(R.mipmap.mine_gray)) }
                .addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                    override fun onTabUnselected(tab: TabLayout.Tab?) {
                        when (tab?.position) {
                            0->{
                                tab.setIcon(R.mipmap.home_gray)
                            }
                            1->{
                                tab.setIcon(R.mipmap.collection_gray)
                            }
                            2->{
                                tab.setIcon(R.mipmap.mine_gray)
                            }
                        }
                        fragmentManager
                                .popBackStackImmediate();
                    }

                    override fun onTabSelected(tab: TabLayout.Tab?) {
                        when(tab?.position) {
                            0-> {
                                tab.setIcon(R.mipmap.home)
                            }
                            1-> {
                                tab.setIcon(R.mipmap.collection)
                            }
                            2->{
                                tab.setIcon(R.mipmap.mine)
                            }
                        }
                        fragmentManager
                                .beginTransaction()
                                .replace(R.id.main_frag, frags[tab?.position as Int])
                                .commit()

                    }

                    override fun onTabReselected(tab: TabLayout.Tab?) {
                    }
                })
    }
}
