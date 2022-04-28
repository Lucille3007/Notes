package com.example.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        supportActionBar?.hide()

        val signup = findViewById<Button>(R.id.signupbutton)

        signup.setOnClickListener {
            val intent = Intent(this@SignUpActivity, HomeActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }
}