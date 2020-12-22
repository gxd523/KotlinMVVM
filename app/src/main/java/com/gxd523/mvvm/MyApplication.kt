package com.gxd523.mvvm

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.gxd523.mvvm.model.registerAllModel

class MyApplication : Application() {
    val viewModelProvider by lazy {
        ViewModelProvider.AndroidViewModelFactory.getInstance(this)
    }

    override fun onCreate() {
        super.onCreate()
        registerAllModel()
    }
}