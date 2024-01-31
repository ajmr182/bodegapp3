package com.ajmr182.bodegaap3

import android.app.Application
import di.initKoin

class BodegaApp3 : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}