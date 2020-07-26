package com.shail.talati


/*
* Application loader first class load when app start
* initialize required which used in entire app
*/
class ApplicationLoader : BaseApplication() {

    companion object {
        lateinit var appInstance: ApplicationLoader
    }


    override fun onCreate() {
        super.onCreate()
        appInstance = this

    }

}