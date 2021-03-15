package com.demo.mvvm.ui.coordinator

import android.app.Activity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.mvvm.R
import com.demo.mvvm.ui.material.scroll.ScrollingAdapter
import kotlinx.android.synthetic.main.activity_nested_scrolling.*

/**
 * Created by guoxiaodong on 3/15/21 11:20
 */
class NestedScrollingActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_nested_scrolling)

        nestedRecyclerView.layoutManager = LinearLayoutManager(this)
        nestedRecyclerView.adapter = ScrollingAdapter(40)
    }
}