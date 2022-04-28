package com.example.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.button.MaterialButton

//import kotlin.android.synthetic.main.activity_main.*


class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        supportActionBar?.hide()

        val moveToSignup = findViewById<TextView>(R.id.moveToSignup)
        val login = findViewById<Button>(R.id.loginbutton)
        val username = findViewById<TextView>(R.id.username)


        moveToSignup.setOnClickListener {
            val intent = Intent(this@HomeActivity, SignUpActivity::class.java)
            startActivity(intent)
            this.finish()
        }

        login.setOnClickListener {
            val intent = Intent(this@HomeActivity, MainScreenActivity::class.java)
            startActivity(intent)
            this.finish()
        }

    }
}