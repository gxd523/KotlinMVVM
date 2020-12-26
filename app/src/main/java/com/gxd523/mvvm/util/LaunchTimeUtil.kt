package com.gxd523.mvvm.util

import android.os.SystemClock

object LaunchTimeUtil {
    private val map = mutableMapOf<String, Long>()

    fun start(tag: String) {
        map[tag] = SystemClock.uptimeMillis()
    }

    fun end(tag: String): Long {
        return map.remove(tag)?.let {
            SystemClock.uptimeMillis() - it
        } ?: -1
    }
}