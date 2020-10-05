package com.gxd523.mvvm

import android.app.Application
import com.gxd523.mvvm.model.registerAllModel

/**
 * Created by guoxiaodong on 2020/10/5 16:37
 */
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        registerAllModel()
    }
}