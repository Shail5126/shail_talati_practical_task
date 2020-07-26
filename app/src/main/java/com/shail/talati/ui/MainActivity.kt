package com.shail.talati.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.shail.talati.R
import com.shail.talati.utils.SharedPreferance
import kotlinx.android.synthetic.main.activity_main.*
import java.util.regex.Matcher
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setInitValues()
    }

    private fun setInitValues() {

        btnLogin.setOnClickListener {
            if (isValid()) {
                tiEmail.isErrorEnabled = false
                tiPassword.isErrorEnabled = false
                SharedPreferance.setValue("email", etEmailId.text.toString().trim())
                SharedPreferance.setValue("password", etPassword.text.toString().trim())
                val intent = Intent(this, HomeActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }
    }

    private fun isValid(): Boolean {
        var isValid = true
        var isValidEmail = true
        var isValidPassword = true
        if (!emailValidator(
                etEmailId.text.toString().trim()
            ) || !etEmailId.text.toString().trim().equals("hello@yopmail.com")
        ) {
            isValid = false
            isValidEmail = false
            if(isValidPassword)
                tiPassword.isErrorEnabled = false
            tiEmail.error = getString(R.string.str_invalid_email)
        }
        if (!isValidPassword(
                etPassword.text.toString().trim()
            ) || !etPassword.text.toString().trim().equals("Password@123")
        ) {
            tiPassword.error = getString(R.string.str_validate_password)
            isValid = false
            isValidPassword = false
            if(isValidEmail)
                tiEmail.isErrorEnabled = false
        }
        return isValid
    }

    fun emailValidator(email: String?): Boolean {
        val pattern: Pattern
        val matcher: Matcher
        val EMAIL_PATTERN =
            "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
        pattern = Pattern.compile(EMAIL_PATTERN)
        matcher = pattern.matcher(email)
        return matcher.matches()
    }

    fun isValidPassword(password: String?): Boolean {
        val pattern: Pattern
        val matcher: Matcher
        val PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$"
        pattern = Pattern.compile(PASSWORD_PATTERN)
        matcher = pattern.matcher(password)
        return matcher.matches()
    }
}
