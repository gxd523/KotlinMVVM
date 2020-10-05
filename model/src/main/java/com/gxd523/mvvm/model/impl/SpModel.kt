package com.gxd523.mvvm.model.impl

import com.gxd523.mvvm.model.AbsModel

/**
 * Created by guoxiaodong on 2020/10/5 16:08
 */
class SpModel(name: String? = null) : AbsModel(name) {
    inline fun <reified T> get(key: String, t: T): T {
        return t
    }
}