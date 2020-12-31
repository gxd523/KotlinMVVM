package com.demo.mvvm.ui.material.scroll

import android.graphics.Color
import android.view.Gravity
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.demo.mvvm.util.dp

/**
 * Created by guoxiaodong on 12/31/20 15:15
 */
class ScrollingAdapter : RecyclerView.Adapter<ScrollingAdapter.ScrollingViewHolder>() {
    class ScrollingViewHolder(
        parent: ViewGroup,
        view: TextView = TextView(parent.context),
    ) : RecyclerView.ViewHolder(view) {
        init {
            (itemView as TextView).textSize = 12f.dp
            itemView.gravity = Gravity.CENTER
            itemView.layoutParams = ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                50f.dp.toInt()
            )
            itemView.setOnClickListener {
                Toast.makeText(parent.context, "点击第${adapterPosition}个Item", Toast.LENGTH_SHORT).show()
            }
        }

        fun onBindViewHolder(position: Int) {
            if (itemView !is TextView) {
                return
            }
            itemView.text = "第${position}个元素"

            itemView.setBackgroundColor(
                if (position % 2 == 0) {
                    0x66666666
                } else {
                    0x66ffffff
                }
            )
            itemView.setTextColor(
                if (position % 2 == 0) {
                    Color.WHITE
                } else {
                    Color.BLACK
                }
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScrollingViewHolder {
        return ScrollingViewHolder(parent)
    }

    override fun onBindViewHolder(holder: ScrollingViewHolder, position: Int) {
        holder.onBindViewHolder(position)
    }

    override fun getItemCount(): Int {
        return 20
    }
}