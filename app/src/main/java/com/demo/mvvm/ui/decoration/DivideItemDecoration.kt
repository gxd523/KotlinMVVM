package com.demo.mvvm.ui.decoration

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class DivideItemDecoration(
    private val tagList: List<String>,
    private val divideHeight: Int = 1,
    divideColor: Int = 0x44333333,
) : ItemDecoration() {
    private val mPaint by lazy {
        Paint().apply {
            isAntiAlias = true
            color = divideColor
        }
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        if (tagList.isEmpty()) {
            return
        }
        val manager = parent.layoutManager
        if (manager is LinearLayoutManager && LinearLayoutManager.VERTICAL != manager.orientation) {
            return
        }
        val position = parent.getChildAdapterPosition(view)
        if (position + 1 < tagList.size && tagList[position] == tagList[position + 1]) {
            outRect.set(0, 0, 0, divideHeight)
        }
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        if (tagList.isEmpty()) {
            return
        }
        for (i in 0 until parent.childCount) {
            val child = parent.getChildAt(i)
            val position = parent.getChildAdapterPosition(child)
            if (position + 1 < tagList.size && tagList[position] == tagList[position + 1]) {
                drawDivide(c, parent, child)
            }
        }
    }

    private fun drawDivide(canvas: Canvas, parent: RecyclerView, child: View) {
        val params = child.layoutParams as RecyclerView.LayoutParams
        val left = parent.paddingLeft
        val right = parent.width
        val top = child.bottom + params.bottomMargin
        val bottom = top + divideHeight
        canvas.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), mPaint)
    }
}