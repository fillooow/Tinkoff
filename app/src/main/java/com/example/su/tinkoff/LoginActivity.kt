package com.example.su.tinkoff

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        Log.d("test", "login")

        enter_button.setOnClickListener {
            val intent: Intent = Intent(this, MainActivity::class.java)
            setResult(Activity.RESULT_OK, intent)
            Log.d("test", "finish")
            finish()
        }
        Log.d("test", "loginw")
    }
}
