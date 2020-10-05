package com.gxd523.mvvm

import com.gxd523.mvvm.model.DatabaseModel
import com.gxd523.mvvm.model.NetworkModel
import com.gxd523.mvvm.model.modelOf

/**
 * Created by guoxiaodong on 2020/10/5 15:04
 */
class MainViewModel {
    val networkModel by modelOf<NetworkModel>()
    val databaseModel by modelOf<DatabaseModel>()
}