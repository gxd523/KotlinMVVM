package com.demo.mvvm.ui.viewpager2

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * Created by guoxiaodong on 3/19/21 09:59
 */
class MyFragmentStateAdapter(
    private val colorList: List<Int>,
    fragmentActivity: FragmentActivity
) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = colorList.size

    override fun createFragment(position: Int): Fragment = PagerFragment().apply {
        arguments = Bundle().apply {
            putInt(PagerFragment.POSITION, position)
            putInt(PagerFragment.COLOR, colorList[position])
        }
    }
}