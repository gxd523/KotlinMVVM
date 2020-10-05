package com.gxd523.mvvm

import android.app.Activity
import android.os.Bundle

class MainActivity : Activity() {
    private val mainViewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel.networkModel.get("http://www.baidu.com/").let(::println)
        mainViewModel.databaseModel.query("select * from user").let(::println)
        mainViewModel.spModel.get("a", false).let(::println)
        mainViewModel.spModel2.get("b", 33).let(::println)
    }
}