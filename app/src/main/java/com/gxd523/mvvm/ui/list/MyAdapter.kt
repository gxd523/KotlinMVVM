package com.gxd523.mvvm.ui.list

import android.content.res.Resources
import android.graphics.Color
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MyAdapter : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBindViewHolder(position)
    }

    override fun getItemCount(): Int {
        return 3
    }

    class MyViewHolder(
        parent: ViewGroup,
        view: RecyclerView = MyRecyclerView("inner", parent.context),
    ) : RecyclerView.ViewHolder(view) {
        init {
            (itemView as RecyclerView).layoutManager = LinearLayoutManager(
                parent.context,
                LinearLayoutManager.VERTICAL,
                false
            )
            itemView.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                Resources.getSystem().displayMetrics.heightPixels / 2
            )
        }

        fun onBindViewHolder(position: Int) {
            (itemView as RecyclerView).adapter = InnerAdapter()
            itemView.setBackgroundColor(
                if (position % 2 == 0) Color.WHITE else {
                    Color.BLACK
                }
            )
        }
    }
}