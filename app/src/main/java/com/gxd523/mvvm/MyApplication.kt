package com.gxd523.mvvm

import android.app.Application
import android.content.Context
import com.gxd523.mvvm.model.registerAllModel

class MyApplication : Application() {
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
    }

    override fun onCreate() {
        super.onCreate()
        registerAllModel()
    }
}