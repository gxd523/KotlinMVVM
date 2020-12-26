package com.gxd523.mvvm.ui.user

import android.app.Activity
import android.os.Bundle
import com.gxd523.mvvm.R
import kotlinx.android.synthetic.main.activity_user.*

/**
 * Created by guoxiaodong on 12/26/20 16:59
 */
class UserActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        activity_user_title_tv.text = localClassName
    }
}