package com.gxd523.mvvm.model

/**
 * Created by guoxiaodong on 2020/10/5 15:13
 */
abstract class AbsModel {
    init {
        ModelCache.run { register() }
    }
}