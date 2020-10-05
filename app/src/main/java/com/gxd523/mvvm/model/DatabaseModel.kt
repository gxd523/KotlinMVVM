package com.gxd523.mvvm.model

/**
 * Created by guoxiaodong on 2020/10/5 15:24
 */
class DatabaseModel : AbsModel() {
    fun query(sql: String): String = """{"name":"gxd"}"""
}