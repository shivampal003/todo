package com.example.notes.Activity

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.notes.R
import com.example.notes.Utills.PrefConstant


class LoginActivity : AppCompatActivity() {
    lateinit var FullName: EditText
    lateinit var UserName: EditText
    lateinit var btnlogin: Button
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        bindview()
        setupSharedpreferences()
    }


    private fun setupSharedpreferences() {
        sharedPreferences = getSharedPreferences(PrefConstant.SHARED_PREFERENCES_NAME, MODE_PRIVATE)
    }

    private fun bindview() {
        FullName = findViewById(R.id.FullName)
        UserName = findViewById(R.id.UserName)
        btnlogin = findViewById(R.id.btnlogin)
        val clickAction: View.OnClickListener = object : View.OnClickListener {
            override fun onClick(view: View) {
                val fullName = FullName.text.toString()
                val userName = UserName.text.toString()
                if (fullName.isNotEmpty() && userName.isNotEmpty()) {

                    val intent = Intent(this@LoginActivity, MyNotesActivity::class.java)
                    intent.putExtra(Appconstant.FULL_NAME, fullName)
                    startActivity(intent)
                    saveFullName(fullName)
                    saveLoginState()
                }else{

                }
            }

        }
        btnlogin.setOnClickListener(clickAction)
    }


    private fun saveLoginState() {
        editor = sharedPreferences.edit()
        editor.putBoolean(PrefConstant.IS_LOGGED_IN, true)
        editor.apply()
    }

    private fun saveFullName(fullName: String) {
        editor = sharedPreferences.edit()
        editor.putString(PrefConstant.FULL_NAME, fullName)
        editor.apply()

    }

}



