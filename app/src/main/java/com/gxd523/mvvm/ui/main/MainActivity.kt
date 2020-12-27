package com.gxd523.mvvm.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.gxd523.mvvm.R
import com.gxd523.mvvm.bean.LoadState
import com.gxd523.mvvm.service.BackgroundService
import com.gxd523.mvvm.ui.list.SlideConflictActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.loadState.observe(this, {
            when (it) {
                is LoadState.Success -> {
                    button.isEnabled = true
                }
                is LoadState.Fail -> {
                    button.isEnabled = true
                }
                is LoadState.Loading -> {
                    button.isEnabled = false
                }
            }
            it.msg.takeIf { msg ->
                msg.trim() != ""
            }?.let { msg ->
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.imageList.observe(this, {
            Glide.with(this)
                .load(it[0])
                .into(imageView1)
            Glide.with(this)
                .load(it[1])
                .into(imageView2)
            Glide.with(this)
                .load(it[2])
                .into(imageView3)
        })

        button.setOnClickListener {
            viewModel.getImageList()
        }
        launchServiceBtn.setOnClickListener {
            startService(Intent(this, BackgroundService::class.java))
        }
        slideConflictBtn.setOnClickListener {
            startActivity(Intent(this, SlideConflictActivity::class.java))
        }
        launchServiceBtn.post { launchServiceBtn.performClick() }
    }
}
