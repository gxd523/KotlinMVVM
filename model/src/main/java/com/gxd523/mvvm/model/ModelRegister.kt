package com.gxd523.mvvm.model

import com.gxd523.mvvm.model.impl.DatabaseModel
import com.gxd523.mvvm.model.impl.NetworkModel
import com.gxd523.mvvm.model.impl.SpModel

/**
 * Created by guoxiaodong on 2020/10/5 16:35
 */
fun registerAllModel() {
    NetworkModel()
    DatabaseModel()
    SpModel()
    SpModel("SpModel2")
}