package com.example.su.tinkoff

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        val API_KEY = "845e2478-1fa7-4253-9580-111b2c959e50"
        val REQUEST_CODE_LOGIN: Int = 1
        var flagLogin: Boolean = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (!flagLogin) {
            val testIntent = Intent(this, LoginActivity::class.java)
            testIntent.flags = Intent.FLAG_ACTIVITY_NO_HISTORY
            startActivityForResult(testIntent, REQUEST_CODE_LOGIN)
        }

        //TODO: запускать после логин активити
        openFragment(OrdersFragment())
        navigation_view.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_orders -> {
                //toolbar_main.title = resources.getString(R.string.translate_icon)
                openFragment(OrdersFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_map -> {
                //toolbar_main.title = resources.getString(R.string.settings)
                //loadFragment(SettingsFragment(), false)
                openFragment(MapsFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_profile -> {
                //toolbar_main.title = resources.getString(R.string.bookmarks_history_navigation)
                openFragment(ProfileFragment())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }


        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            if (resultCode == RESULT_OK) {
                when (requestCode) {
                    REQUEST_CODE_LOGIN -> {
                        flagLogin = true
                        Log.d("test", "get")
                    }
                }
            }
        }

        private fun openFragment(fragment: Fragment) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frame_container, fragment)
            transaction.commit()
        }
}
