package com.gxd523.mvvm.model

/**
 * Created by guoxiaodong on 2020/10/5 15:20
 */
class NetworkModel : AbsModel() {
    fun get(url: String): String = """{"code":0}"""
}