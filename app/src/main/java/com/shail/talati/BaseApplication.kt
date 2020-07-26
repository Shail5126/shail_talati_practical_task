package com.shail.talati

import android.app.Application

abstract class BaseApplication : Application() {

    //any written in this companion object is static you can access this veriable using ApplicationLoader.REQUEST_TIMEOUT
    companion object {

        lateinit var appInstance: BaseApplication
        var byteArray: ByteArray? = null

        @Synchronized
        fun getInstance(): BaseApplication {
            return appInstance
        }
    }

    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }
}