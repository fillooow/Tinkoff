package com.example.su.tinkoff

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        Log.d("test", "login")

        var enterButton = findViewById<Button>(R.id.enter_button)

        enterButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NO_HISTORY
            setResult(Activity.RESULT_OK, intent)
            Log.d("test", "finish")
            finish()
        }
        Log.d("test", "loginw")

    }
}
