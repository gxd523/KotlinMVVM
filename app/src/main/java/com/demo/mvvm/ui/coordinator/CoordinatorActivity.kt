package com.demo.mvvm.ui.coordinator

import android.app.Activity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.mvvm.R
import com.demo.mvvm.ui.material.scroll.ScrollingAdapter
import kotlinx.android.synthetic.main.activity_coordinator.*

/**
 * Created by guoxiaodong on 3/15/21 09:44
 */
class CoordinatorActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_coordinator)

        my_list.layoutManager = LinearLayoutManager(this)
        my_list.adapter = ScrollingAdapter()
    }
}