package com.demo.mvvm.ui.viewpager2

import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.demo.mvvm.databinding.ActivityViewpager2Binding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ViewPager2Activity : FragmentActivity() {
    private val binding by lazy {
        ActivityViewpager2Binding.inflate(layoutInflater)
    }
    private val tabLayoutMediator by lazy {
        TabLayoutMediator(
            binding.tabLayout, binding.viewPager2, false, true
        ) { tab, position ->
            with(tab) {
                setIcon(android.R.mipmap.sym_def_app_icon)
                text = "Tab-$position"
            }
        }
    }
    private val colorList by lazy {
        listOf(
            android.R.color.holo_orange_dark,
            android.R.color.holo_purple,
            android.R.color.holo_blue_dark,
            android.R.color.holo_green_light,
            android.R.color.holo_blue_light,
        ).map { ContextCompat.getColor(this, it) }.toMutableList()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val fragmentStateAdapter = MyFragmentStateAdapter(colorList, this@ViewPager2Activity)
        binding.viewPager2.apply {
            adapter = fragmentStateAdapter
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    Log.d("gxd", "onPageSelected-->$position")
                }
            })
            offscreenPageLimit = ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT// 禁用预加载
        }
        binding.tabLayout.apply {
            repeat(fragmentStateAdapter.itemCount) {
                addTab(newTab().apply {
                    setIcon(android.R.mipmap.sym_def_app_icon)
                    text = "Tab-$position"
                })
            }
            addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                // 测试添加删除功能
                override fun onTabSelected(tab: TabLayout.Tab) {
                    binding.viewPager2.currentItem = tab.position

                    if (tab.position == 4) {
                        addOrRemoveTab(fragmentStateAdapter)
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                }

                override fun onTabReselected(tab: TabLayout.Tab) {
                    if (tab.position == 4) {
                        addOrRemoveTab(fragmentStateAdapter)
                    }
                }
            })
        }

        tabLayoutMediator.attach()
    }

    override fun onDestroy() {
        tabLayoutMediator.detach()
//        binding.viewPager2.unregisterOnPageChangeCallback()
        super.onDestroy()
    }

    private fun addOrRemoveTab(fragmentStateAdapter: MyFragmentStateAdapter) {
        if (colorList.size > 5) {
            colorList.removeLast()
            binding.tabLayout.removeTabAt(colorList.size)
            fragmentStateAdapter.notifyItemRemoved(colorList.size)
        } else {
            binding.tabLayout.apply {
                addTab(newTab().apply {
                    setIcon(android.R.mipmap.sym_def_app_icon)
                    text = "Tab-${colorList.size}"
                })
            }
            colorList += android.R.color.holo_red_light.let { ContextCompat.getColor(this@ViewPager2Activity, it) }
            fragmentStateAdapter.notifyItemInserted(colorList.size - 1)
        }
    }
}