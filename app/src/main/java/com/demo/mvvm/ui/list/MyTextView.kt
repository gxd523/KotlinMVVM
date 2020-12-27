package com.demo.mvvm.ui.list

import android.content.Context
import android.util.Log
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatTextView
import com.demo.mvvm.util.MotionEventUtil

class MyTextView(context: Context) : AppCompatTextView(context) {
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        Log.d("gxd", "TextView.dispatchTouchEvent...${MotionEventUtil.getAction(ev)}")
        val result = super.dispatchTouchEvent(ev)
        Log.d("gxd", "TextView.dispatchTouchEvent...return...$result")
        return result
    }

    override fun onTouchEvent(e: MotionEvent?): Boolean {
        Log.d("gxd", "TextView.onTouchEvent...${MotionEventUtil.getAction(e)}")
        val result = super.onTouchEvent(e)
        Log.d("gxd", "TextView.onTouchEvent...return...$result")
        return result
    }
}