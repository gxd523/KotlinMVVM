package com.demo.mvvm.ui.coordinator

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.max

class SimpleBehavior(context: Context, attrs: AttributeSet?) : CoordinatorLayout.Behavior<View>(context, attrs) {
    /**
     * 列表顶部和title底部重合时，列表的滑动距离
     */
    private var deltaY = 0f

    /**
     * 确定使用Behavior的View要依赖的View的类型
     * @child 使用该Behavior的View
     * @dependency 依赖的View，也就是要监听的View，即RecyclerView
     */
    override fun layoutDependsOn(parent: CoordinatorLayout, child: View, dependency: View): Boolean = dependency is RecyclerView

    /**
     * 当被依赖的View状态改变时回调
     */
    override fun onDependentViewChanged(parent: CoordinatorLayout, child: View, dependency: View): Boolean {
        if (deltaY == 0f) {
            deltaY = dependency.y - child.height
        }

        val dy = max(dependency.y - child.height, 0f)

        val percent = dy / deltaY

        child.translationY = -percent * child.height
        child.alpha = 1 - percent

        return true
    }
}