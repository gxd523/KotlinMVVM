package com.gxd523.mvvm

import android.app.Activity
import android.os.Bundle
import com.gxd523.mvvm.model.impl.DatabaseModel
import com.gxd523.mvvm.model.impl.NetworkModel

class MainActivity : Activity() {
    private val mainViewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        NetworkModel()
        DatabaseModel()
        mainViewModel.networkModel.get("http://www.baidu.com/").let(::println)
        mainViewModel.databaseModel.query("select * from user").let(::println)
    }
}