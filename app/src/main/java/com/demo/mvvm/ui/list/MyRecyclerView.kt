package com.demo.mvvm.ui.list

import android.content.Context
import android.util.Log
import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView
import com.demo.mvvm.util.MotionEventUtil

class MyRecyclerView(private val name: String, context: Context) : RecyclerView(context) {
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        Log.d("gxd", "$name.dispatchTouchEvent...${MotionEventUtil.getAction(ev)}")
        val result = super.dispatchTouchEvent(ev)
        Log.d("gxd", "$name.dispatchTouchEvent...return...$result")
        return result
    }

    override fun onInterceptTouchEvent(e: MotionEvent?): Boolean {
        Log.d("gxd", "$name.onInterceptTouchEvent...${MotionEventUtil.getAction(e)}")
        val result = super.onInterceptTouchEvent(e)
        Log.d("gxd", "$name.onInterceptTouchEvent...return...$result")
        return result
    }

    override fun onTouchEvent(e: MotionEvent?): Boolean {
        Log.d("gxd", "$name.onTouchEvent...${MotionEventUtil.getAction(e)}")
        val result = super.onTouchEvent(e)
        Log.d("gxd", "$name.onTouchEvent...return...$result")
        return result
    }
}