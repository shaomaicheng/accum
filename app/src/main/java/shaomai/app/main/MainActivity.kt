package shaomai.app.main

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity
import android.widget.FrameLayout
import shaomai.app.R

class MainActivity : AppCompatActivity() {

    private lateinit var mainTab: TabLayout
    private lateinit var mainFrag: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainTab = findViewById(R.id.main_tab)
        initViewsAndEvents()
    }

    fun initViewsAndEvents() {
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
                    }

                    override fun onTabReselected(tab: TabLayout.Tab?) {
                    }
                })
    }
}
