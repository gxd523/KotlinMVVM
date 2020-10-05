package com.gxd523.mvvm.model

import java.util.concurrent.ConcurrentHashMap
import kotlin.reflect.KClass

/**
 * Created by guoxiaodong on 2020/10/5 15:14
 */
object ModelCache {
    private val modelMap = ConcurrentHashMap<KClass<out AbsModel>, AbsModel>()

    fun AbsModel.register() {
        modelMap[this::class] = this
    }

    fun <T : AbsModel> KClass<T>.get(): T {
        return modelMap[this::class] as T
    }
}