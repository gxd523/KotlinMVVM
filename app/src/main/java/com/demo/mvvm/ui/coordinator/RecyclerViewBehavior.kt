package com.demo.mvvm.ui.coordinator

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.max

class RecyclerViewBehavior(context: Context?, attrs: AttributeSet?) : CoordinatorLayout.Behavior<RecyclerView>(context, attrs) {
    override fun layoutDependsOn(parent: CoordinatorLayout, child: RecyclerView, dependency: View): Boolean =
        dependency is TextView

    override fun onDependentViewChanged(parent: CoordinatorLayout, child: RecyclerView, dependency: View): Boolean {
        child.y = max(dependency.height + dependency.translationY, 0f)
        return true
    }
}