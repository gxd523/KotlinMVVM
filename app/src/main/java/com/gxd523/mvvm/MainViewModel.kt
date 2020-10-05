package com.gxd523.mvvm

import com.gxd523.mvvm.model.ModelDelegate
import com.gxd523.mvvm.model.impl.DatabaseModel
import com.gxd523.mvvm.model.impl.NetworkModel
import com.gxd523.mvvm.model.impl.SpModel

/**
 * Created by guoxiaodong on 2020/10/5 15:04
 */
class MainViewModel {
    val networkModel: NetworkModel by ModelDelegate
    val databaseModel: DatabaseModel by ModelDelegate
    val spModel: SpModel by ModelDelegate
    val spModel2: SpModel by ModelDelegate
}