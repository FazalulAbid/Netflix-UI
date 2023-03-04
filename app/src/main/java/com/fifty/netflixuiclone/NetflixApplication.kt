package com.fifty.netflixuiclone

import android.app.Application

class NetflixApplication : Application() {
    companion object {
        @get:Synchronized
        lateinit var initializer: NetflixApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        initializer = this
    }
}