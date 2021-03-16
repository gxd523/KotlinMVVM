package com.demo.mvvm.ui.decoration

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.text.TextPaint

interface OnDrawItemDecorationListener {
    /**
     * 自定义的 GroupHeader 绘制接口
     *
     * @param rect   共四个值left、top、right、bottom 代表GroupHeader所在区域的四个坐标值
     * @param position 原始数据源中的position
     */
    fun onDrawGroupHeader(canvas: Canvas, paint: Paint, textPaint: TextPaint, rect: Rect, position: Int)
    fun onDrawSuspensionGroupHeader(canvas: Canvas, paint: Paint, textPaint: TextPaint, rect: Rect, position: Int)
}