package com.shail.talati.ui

import android.os.Bundle
import com.shail.talati.R
import com.shail.talati.base.BaseActivity
import com.shail.talati.databinding.ActivityMainBinding
import com.shail.talati.viewmodel.LoginViewModel

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
