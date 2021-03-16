package com.demo.mvvm.ui.decoration

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.text.TextPaint
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.demo.mvvm.util.dp

class GroupHeaderItemDecoration(
    private val tagList: List<String>,
    private val groupHeaderHeight: Int = 40f.dp.toInt(),
    private val groupHeaderLeftPadding: Int = 20f.dp.toInt(),
    textColor: Int = 0xFF999999.toInt(),
    textSize: Float = 14f.dp,
    headerColor: Int = 0xFFEEEEEE.toInt(),
    private val drawItemDecorationListener: OnDrawItemDecorationListener? = null,
) : ItemDecoration() {
    private val mPaint by lazy {
        Paint().apply {
            isAntiAlias = true
            color = headerColor
        }
    }
    private val mTextPaint by lazy {
        TextPaint().apply {
            isAntiAlias = true
            color = textColor
            this.textSize = textSize
        }
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        if (tagList.isEmpty()) return

        val manager = parent.layoutManager
        if (manager is LinearLayoutManager && LinearLayoutManager.VERTICAL != manager.orientation) return

        val position = parent.getChildAdapterPosition(view)
        // ItemView的position==0 或者 当前itemView的data的tag和上一个ItemView的不相等，则为当前itemView设置top 偏移量
        if (position == 0 || tagList[position] != tagList[position - 1]) {
            outRect.set(0, groupHeaderHeight, 0, 0)
        }
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        if (tagList.isEmpty()) {
            return
        }
        for (i in 0 until parent.childCount) {
            val view = parent.getChildAt(i)
            val position = parent.getChildAdapterPosition(view)
            val tag = tagList[position]
            //和getItemOffsets()里的条件判断类似，开始绘制分组的GroupHeader
            if (position == 0 || tag != tagList[position - 1]) {
                if (drawItemDecorationListener == null) {
                    drawGroupHeader(c, parent, view, tag)
                } else {
                    drawItemDecorationListener.onDrawGroupHeader(c,
                        mPaint,
                        mTextPaint,
                        getGroupHeaderCoordinate(parent, view),
                        position)
                }
            }
        }
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        if (tagList.isEmpty()) return

        //列表第一个可见的ItemView位置
        val position = (parent.layoutManager as LinearLayoutManager?)!!.findFirstVisibleItemPosition()
        val tag = tagList[position]
        //第一个可见的ItemView
        val view = parent.findViewHolderForAdapterPosition(position)!!.itemView
        //当前ItemView的data的tag和下一个ItemView的不相等，则代表将要重新绘制悬停的GroupHeader
        var flag = false
        if (position + 1 < tagList.size && tag != tagList[position + 1]) {
            //如果第一个可见ItemView的底部坐标小于groupHeaderHeight，则执行Canvas垂直位移操作
            if (view.bottom <= groupHeaderHeight) {
                c.save()
                flag = true
                c.translate(0f, (view.height + view.top - groupHeaderHeight).toFloat())
            }
        }
        if (drawItemDecorationListener == null) {
            drawSuspensionGroupHeader(c, parent, tag)
        } else {
            drawItemDecorationListener.onDrawSuspensionGroupHeader(c,
                mPaint,
                mTextPaint,
                getSuspensionGroupHeaderCoordinate(parent),
                position)
        }
        if (flag) {
            c.restore()
        }
    }

    private fun getGroupHeaderCoordinate(parent: RecyclerView, view: View): IntArray {
        val params = view.layoutParams as RecyclerView.LayoutParams
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight
        val bottom = view.top - params.topMargin
        val top = bottom - groupHeaderHeight
        return intArrayOf(left, top, right, bottom)
    }

    private fun drawGroupHeader(c: Canvas, parent: RecyclerView, view: View, tag: String) {
        val params = getGroupHeaderCoordinate(parent, view)
        c.drawRect(params[0].toFloat(), params[1].toFloat(), params[2].toFloat(), params[3].toFloat(), mPaint)
        val x = params[0] + groupHeaderLeftPadding
        val bounds = Rect()
        mTextPaint.getTextBounds(tag, 0, tag.length, bounds)
        val y = params[1] + (groupHeaderHeight + bounds.height()) / 2
        c.drawText(tag, x.toFloat(), y.toFloat(), mTextPaint)
    }

    private fun getSuspensionGroupHeaderCoordinate(parent: RecyclerView): IntArray {
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight
        val bottom = groupHeaderHeight
        val top = 0
        return intArrayOf(left, top, right, bottom)
    }

    private fun drawSuspensionGroupHeader(c: Canvas, parent: RecyclerView, tag: String) {
        val params = getSuspensionGroupHeaderCoordinate(parent)
        c.drawRect(params[0].toFloat(), params[1].toFloat(), params[2].toFloat(), params[3].toFloat(), mPaint)
        val x = params[0] + groupHeaderLeftPadding
        val bounds = Rect()
        mTextPaint.getTextBounds(tag, 0, tag.length, bounds)
        val y = params[1] + (groupHeaderHeight + bounds.height()) / 2
        c.drawText(tag, x.toFloat(), y.toFloat(), mTextPaint)
    }
}