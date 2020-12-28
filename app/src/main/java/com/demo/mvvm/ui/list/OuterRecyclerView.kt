package com.demo.mvvm.ui.list

import android.content.Context
import android.util.Log
import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView
import com.demo.mvvm.util.MotionEventUtil
import com.demo.mvvm.util.RecyclerViewUtil

class OuterRecyclerView(context: Context) : RecyclerView(context) {
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        Log.d("gxd", "OuterRecyclerView.dispatchTouchEvent...${MotionEventUtil.getAction(ev)}")
        val result = super.dispatchTouchEvent(ev)
        Log.d("gxd", "OuterRecyclerView.dispatchTouchEvent...return...$result")
        return result
    }

    override fun onInterceptTouchEvent(e: MotionEvent?): Boolean {
        Log.d("gxd", "OuterRecyclerView.onInterceptTouchEvent...${MotionEventUtil.getAction(e)}")
        val result = if (handleInterceptTouchEvent(e)) false else super.onInterceptTouchEvent(e)
        Log.d("gxd", "OuterRecyclerView.onInterceptTouchEvent...return...$result")
        return result
    }

    private var previousY = 0
    private var childIndex = 0

    private fun handleInterceptTouchEvent(e: MotionEvent?): Boolean {
        when (e?.action) {
            MotionEvent.ACTION_DOWN -> {
                previousY = e.y.toInt()
                for (i in 0 until childCount) {
                    val location = IntArray(2)
                    val child = getChildAt(i)
                    child.getLocationOnScreen(location)

                    if (e.rawY > location[1]) {
                        childIndex = i
                    } else {
                        break
                    }
                }
            }
            MotionEvent.ACTION_MOVE -> {
                val innerRecyclerView = getChildAt(childIndex) as RecyclerView
                val offsetY = e.y - previousY
                previousY = e.y.toInt()
                if (offsetY < 0 && !RecyclerViewUtil.isScrollToBottom(innerRecyclerView) ||
                    offsetY > 0 && !RecyclerViewUtil.isScrollToTop(innerRecyclerView)
                ) {// 上滑且内部Recyclerview未到底，或者下滑且内部Recyclerview未到头
                    return true
                }
            }
        }
        return false
    }

    override fun onTouchEvent(e: MotionEvent?): Boolean {
        Log.d("gxd", "OuterRecyclerView.onTouchEvent...${MotionEventUtil.getAction(e)}")
        val result = super.onTouchEvent(e)
        Log.d("gxd", "OuterRecyclerView.onTouchEvent...return...$result")
        return result
    }
}