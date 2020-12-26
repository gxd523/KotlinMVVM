package com.gxd523.mvvm.service

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import com.gxd523.mvvm.ui.user.UserActivity
import com.gxd523.mvvm.util.NotificationUtil

/**
 * Created by guoxiaodong on 12/26/20 16:58
 */
class BackgroundService : Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Android9.0开始，前台服务需要FOREGROUND_SERVICE权限
        startForeground(111, NotificationUtil.createNotification(this, "这是一个测试前台服务"))

        Handler(Looper.getMainLooper()).postDelayed({
            // 后台进程启动Activity，需要SYSTEM_ALERT_WINDOW权限
            startActivity(Intent(this, UserActivity::class.java).also {
                it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            })
        }, 5000)
        return super.onStartCommand(intent, flags, startId)
    }
}