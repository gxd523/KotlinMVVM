package com.demo.mvvm.ui.viewpager2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.demo.mvvm.databinding.LayoutPagerBinding
import com.demo.mvvm.ui.base.BindingFragment

/**
 * Created by guoxiaodong on 3/19/21 10:01
 */
class PagerFragment : BindingFragment<LayoutPagerBinding>() {
    private var color: Int? = null
    private var position: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {
            color = arguments?.getInt(COLOR)
            position = arguments?.getInt(POSITION)
        }
    }

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): LayoutPagerBinding = LayoutPagerBinding.inflate(inflater, container, false)

    override fun LayoutPagerBinding.onViewCreated(view: View, savedInstanceState: Bundle?) {
        color?.let(root::setBackgroundColor)
        position?.let { it -> root.text = it.toString() }
    }

    companion object {
        const val COLOR = "COLOR"
        const val POSITION = "POSITION"
    }
}