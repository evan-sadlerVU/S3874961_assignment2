package com.example.s3874961_assignment2.ui.dashboard.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.s3874961_assignment2.R

class DashboardRecyclerViewAdaptor(
    private val onItemClick: ((Map<String, Any?>) -> Unit)? = null
) : RecyclerView.Adapter<DashboardRecyclerViewHolder>() {

    private val items = mutableListOf<Map<String, Any?>>()

    fun setData(data: List<Map<String, Any?>>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardRecyclerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.dashboard_response_item, parent, false)
        return DashboardRecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: DashboardRecyclerViewHolder, position: Int) {
        holder.bind(items[position], onItemClick)
    }

    override fun getItemCount(): Int = items.size

}