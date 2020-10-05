package com.gxd523.mvvm.model.impl

import com.gxd523.mvvm.model.AbsModel

/**
 * Created by guoxiaodong on 2020/10/5 15:24
 */
class DatabaseModel : AbsModel() {
    fun query(sql: String): String = """{"name":"gxd"}"""
}