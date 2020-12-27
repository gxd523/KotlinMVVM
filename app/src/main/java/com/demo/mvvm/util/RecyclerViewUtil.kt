package com.demo.mvvm.util

import androidx.recyclerview.widget.RecyclerView

object RecyclerViewUtil {
    fun isScrollToBottom(recyclerView: RecyclerView): Boolean {
        return recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset() >= recyclerView.computeVerticalScrollRange()
    }

    fun isScrollToTop(recyclerView: RecyclerView): Boolean {
        return recyclerView.computeVerticalScrollOffset() == 0
    }
}