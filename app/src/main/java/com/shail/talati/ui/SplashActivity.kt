package com.shail.talati.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.shail.talati.R
import com.shail.talati.utils.SharedPreferance


class SplashActivity : AppCompatActivity() {

    private val SPLASH_DISPLAY_LENGTH = 1000


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed(Runnable {
            /* Create an Intent that will start the Menu-Activity. */
            val email =
                SharedPreferance.getValue("email", "").toString()
            if (email.equals("hello@yopmail.com")) {
                val intent = Intent(applicationContext, HomeActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                startActivity(Intent(applicationContext, LoginActivity::class.java))
                finish()
            }
        }, SPLASH_DISPLAY_LENGTH.toLong())
    }

}
