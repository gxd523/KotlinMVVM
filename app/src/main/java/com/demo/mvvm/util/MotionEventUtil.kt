package com.demo.mvvm.util

import android.view.MotionEvent

object MotionEventUtil {
    fun getAction(event: MotionEvent?): String {
        return when (event?.action) {
            MotionEvent.ACTION_DOWN -> "ACTION_DOWN"
            MotionEvent.ACTION_MOVE -> "ACTION_MOVE"
            MotionEvent.ACTION_UP -> "ACTION_UP"
            MotionEvent.ACTION_CANCEL -> "ACTION_CANCEL"
            else -> "UNKNOW"
        }
    }
}