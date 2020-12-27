package com.demo.mvvm.model

import com.demo.mvvm.model.ModelCache.getModel
import java.util.*
import kotlin.reflect.KProperty

/**
 * Created by guoxiaodong on 2020/10/5 15:28
 */
object ModelDelegate {
    operator fun <T : AbsModel> getValue(thisRef: Any?, property: KProperty<*>): T {
        return property.name.capitalize(Locale.getDefault()).getModel()
    }
}