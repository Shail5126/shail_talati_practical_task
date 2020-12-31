package com.shail.talati.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.shail.talati.R
import com.shail.talati.base.BaseActivity
import com.shail.talati.databinding.ActivityMainBinding
import com.shail.talati.utils.SharedPreferance
import com.shail.talati.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.util.regex.Matcher
import java.util.regex.Pattern

class LoginActivity : BaseActivity<LoginViewModel,ActivityMainBinding>() {

    lateinit var dataBinding:ActivityMainBinding
    override fun getViewModel(): Class<LoginViewModel> {
        return LoginViewModel::class.java
    }

    override fun onCreate(
        instance: Bundle?,
        viewModel: LoginViewModel,
        binding: ActivityMainBinding
    ) {
        binding.viewModel = viewModel
        viewModel.activity = this
        dataBinding = binding
    }

    override fun getLayResId(): Int {
        return R.layout.activity_main
    }


}
