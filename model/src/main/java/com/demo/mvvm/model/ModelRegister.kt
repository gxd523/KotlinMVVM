package com.demo.mvvm.model

import com.demo.mvvm.model.impl.DatabaseModel
import com.demo.mvvm.model.impl.NetworkModel
import com.demo.mvvm.model.impl.SpModel

/**
 * Created by guoxiaodong on 2020/10/5 16:35
 */
fun registerAllModel() {
    NetworkModel()
    DatabaseModel()
    SpModel()
    SpModel("SpModel2")
}