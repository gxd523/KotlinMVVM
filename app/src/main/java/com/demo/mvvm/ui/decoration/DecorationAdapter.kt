package com.demo.mvvm.ui.decoration

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.demo.mvvm.R
import com.demo.mvvm.ui.decoration.DecorationAdapter.DecorationViewHolder

class DecorationAdapter(private val dataList: List<String>) : RecyclerView.Adapter<DecorationViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DecorationViewHolder = DecorationViewHolder(parent)

    override fun onBindViewHolder(holder: DecorationViewHolder, position: Int) = holder.onBindViewHolder(dataList[position])

    override fun getItemCount(): Int = dataList.size

    class DecorationViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.layout_decoration, parent, false)
    ) {
        private val titleTv by lazy {
            itemView.findViewById(R.id.title) as TextView
        }

        fun onBindViewHolder(data: String?) {
            titleTv.text = data
        }
    }
}