package com.gxd523.mvvm.model

import com.gxd523.mvvm.model.ModelCache.get
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

/**
 * Created by guoxiaodong on 2020/10/5 15:28
 */
class ModelDelegate<T : AbsModel>(private val kClass: KClass<T>) : ReadOnlyProperty<Any?, T> {
    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return kClass.get()
    }
}

inline fun <reified T : AbsModel> modelOf(): ModelDelegate<T> {
    return ModelDelegate(T::class)
}