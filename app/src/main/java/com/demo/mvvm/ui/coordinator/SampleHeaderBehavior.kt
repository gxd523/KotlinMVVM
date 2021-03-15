package com.demo.mvvm.ui.coordinator

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SampleHeaderBehavior(context: Context?, attrs: AttributeSet?) : CoordinatorLayout.Behavior<TextView>(context, attrs) {
    /**
     * 界面整体向上滑动，达到列表可滑动的临界点
     */
    private var upReach = false

    /**
     * 列表向上滑动后，再向下滑动，达到界面整体可滑动的临界点
     */
    private var downReach = false

    /**
     * 列表上一个全部可见的item位置
     */
    private var lastPosition = -1

    override fun onInterceptTouchEvent(parent: CoordinatorLayout, child: TextView, ev: MotionEvent): Boolean {
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                downReach = false
                upReach = false
            }
        }
        return super.onInterceptTouchEvent(parent, child, ev)
    }

    /**
     * 嵌套滑动进行中，要监听的子View将要滑动，滑动事件即将被消费（但最终被谁消费，可以通过代码控制）
     * 处理监听到的滑动事件，实现整体滑动和列表单独滑动（header是否完全隐藏是滑动的临界点）
     */
    override fun onNestedPreScroll(
        coordinatorLayout: CoordinatorLayout,
        child: TextView,
        target: View,
        dx: Int,
        dy: Int,
        consumed: IntArray,
        type: Int,
    ) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type)
        if (target is RecyclerView) {
            val pos = (target.layoutManager as LinearLayoutManager?)!!.findFirstCompletelyVisibleItemPosition()
            if (pos == 0 && pos < lastPosition) {// 列表第0个Item全部可见，且往下滑
                downReach = true
            }
            if (canScroll(child, dy) && pos == 0) {// 整体可以滑动，否则RecyclerView消费滑动事件
                var finalY = child.translationY - dy
                if (finalY < -child.height) {
                    finalY = -child.height.toFloat()
                    upReach = true
                } else if (finalY > 0) {
                    finalY = 0f
                }
                child.translationY = finalY
                consumed[1] = dy// 让CoordinatorLayout消费滑动事件
            }
            lastPosition = pos
        }
    }

    /**
     * 往上滑，且没有到达列表滑动的临界点
     * @return true：整体滑动，false：列表滑动
     */
    private fun canScroll(child: View, scrollY: Int): Boolean =
        if (scrollY > 0 && child.translationY == -child.height.toFloat() && !upReach) false else !downReach


    /**
     * 嵌套滑动开始（ACTION_DOWN），确定Behavior是否要监听此次事件
     * 这里我们只监听其垂直方向的滑动事件
     */
    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: TextView,
        directTargetChild: View,
        target: View,
        axes: Int,
        type: Int,
    ): Boolean = axes and ViewCompat.SCROLL_AXIS_VERTICAL != 0
}