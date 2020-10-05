package com.gxd523.mvvm.model

import java.util.concurrent.ConcurrentHashMap

/**
 * Created by guoxiaodong on 2020/10/5 15:14
 */
object ModelCache {
    private val modelMap = ConcurrentHashMap<String, AbsModel>()

    fun AbsModel.register(name: String) {
        modelMap[name] = this
    }

    fun <T : AbsModel> String.getModel(): T {
        return modelMap[this] as T
    }
}