package com.example.notes.Activity

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.notes.R
import com.example.notes.Utills.PrefConstant

class SplashActivity : AppCompatActivity() {
    lateinit var sharedPreference: SharedPreferences
    lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        handler = Handler(Looper.getMainLooper())
        setupSharedPreference()
        checkLoginStatus()
    }

    private fun setupSharedPreference() {

        sharedPreference = getSharedPreferences(PrefConstant.SHARED_PREFERENCES_NAME, MODE_PRIVATE)
    }

    private fun checkLoginStatus() {
        val isLoggedIn = sharedPreference.getBoolean(PrefConstant.IS_LOGGED_IN, false)
        if (isLoggedIn) {
            handler.postDelayed({
                val intent = Intent(this@SplashActivity, MyNotesActivity::class.java)
                startActivity(intent)
                finish()
            }, 1000)
        } else {
            handler.postDelayed({
                val intent = Intent(this@SplashActivity, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }, 1000)
        }

    }
}

