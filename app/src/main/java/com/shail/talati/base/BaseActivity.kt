package com.shail.talati.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

abstract class BaseActivity<M : ViewModel,B : ViewDataBinding> :AppCompatActivity(){
    var viewModelFactory: ViewModelProvider.Factory? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ViewDataBinding = DataBindingUtil.setContentView(this,getLayResId())
        val viewModel = ViewModelProviders.of(this,viewModelFactory).get(getViewModel())
        onCreate(savedInstanceState,viewModel as M, binding as B)
    }

    protected abstract fun getViewModel(): Class<M>

    protected abstract fun onCreate(instance: Bundle?, viewModel: M, binding: B)

    protected abstract fun getLayResId(): Int
}