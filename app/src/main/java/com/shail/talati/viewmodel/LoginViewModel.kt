package com.shail.talati.viewmodel

import android.app.Application
import android.content.Context
import android.content.Intent
import android.text.Editable
import android.view.View
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.AndroidViewModel
import com.shail.talati.R
import com.shail.talati.ui.HomeActivity
import com.shail.talati.ui.LoginActivity
import com.shail.talati.utils.SharedPreferance
import kotlinx.android.synthetic.main.activity_main.*
import java.util.regex.Matcher
import java.util.regex.Pattern

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    var context: Context? = null
    var activity:LoginActivity? = null
    var view: View? = null

    var email = ObservableField<String>("")
    var password = ObservableField<String>("")
    var emailErrorVisibility = ObservableInt(View.GONE)
    var emailError = ObservableField<String>("")
    var passwordErrorVisibility = ObservableInt(View.GONE)
    var passwordError = ObservableField<String>("")



    fun setEmail(text : Editable){
        this.email.set(text.toString())
    }

    fun setPassword(text: Editable){
        this.password.set(text.toString())
    }
    fun loginOnClick(){
        SharedPreferance.setValue("email", email.get()!!.trim())
        SharedPreferance.setValue("password", password.get()!!.trim())
        val intent = Intent(context, HomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        context!!.startActivity(intent)
    }

    fun onTextChangedEmail(s:CharSequence, start:Int, befor:Int,count:Int){

    }

    private fun isValid(): Boolean {
        var isValid = true
        if(email.get()!!.trim().equals("")){
            isValid = false
            emailError.set(context!!.resources.getString(R.string.str_hint_email_id))
            emailErrorVisibility.set(View.VISIBLE)
        } else if(email.get()!!.trim().length < 10){
            isValid = false
            emailError.set(context!!.resources.getString(R.string.str_hint_email_id))
            emailErrorVisibility.set(View.VISIBLE)
        } else {
            emailError.set("")
            emailErrorVisibility.set(View.GONE)
        }

        if(password.get()!!.trim().equals("")){
            isValid = false
            passwordError.set(context!!.resources.getString(R.string.str_hint_password))
            passwordErrorVisibility.set(View.VISIBLE)
        } else {
            passwordError.set("")
            passwordErrorVisibility.set(View.GONE)
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