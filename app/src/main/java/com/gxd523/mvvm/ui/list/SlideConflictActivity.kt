package com.gxd523.mvvm.ui.list

import android.app.Activity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager

class SlideConflictActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val recyclerView = MyRecyclerView("outer", this)
        setContentView(recyclerView)

        recyclerView.adapter = MyAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }
}