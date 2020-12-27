package com.gxd523.mvvm.ui.list

import android.graphics.Color
import android.view.Gravity
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.gxd523.mvvm.util.dp

class InnerAdapter : RecyclerView.Adapter<InnerAdapter.InnerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerViewHolder {
        return InnerViewHolder(parent)
    }

    override fun onBindViewHolder(holder: InnerViewHolder, position: Int) {
        holder.onBindViewHolder(position)
    }

    override fun getItemCount(): Int {
        return 6
    }

    class InnerViewHolder(
        parent: ViewGroup,
        view: TextView = MyTextView(parent.context),
    ) : RecyclerView.ViewHolder(view) {
        init {
            (itemView as TextView).textSize = 20f.dp
            itemView.gravity = Gravity.CENTER
            itemView.layoutParams = ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                150f.dp.toInt()
            ).also {
                it.leftMargin = 10f.dp.toInt()
                it.topMargin = 10f.dp.toInt()
                it.rightMargin = 10f.dp.toInt()
                it.bottomMargin = 10f.dp.toInt()
            }
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
                    Color.CYAN
                } else {
                    Color.YELLOW
                }
            )
        }
    }
}