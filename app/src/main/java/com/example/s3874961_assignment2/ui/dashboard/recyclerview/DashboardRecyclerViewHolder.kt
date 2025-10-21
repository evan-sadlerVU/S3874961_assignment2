package com.example.s3874961_assignment2.ui.dashboard.recyclerview

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.s3874961_assignment2.R
import com.example.s3874961_assignment2.data.DashboardResponseItem

class DashboardRecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val titleText: TextView = view.findViewById(R.id.titleText)
    private val bodyText: TextView = view.findViewById(R.id.bodyText)
    // reference for clicks later.
    private var currentItem: DashboardResponseItem? = null

    fun bind(
        item: DashboardResponseItem,
        onClick: ((DashboardResponseItem) -> Unit)?
    ) {
        currentItem = item

        // designate the first field in an object as the title
        val title = item.fields.entries.first()
        titleText.text = title.value.toString()

        // show all other fields as "key: value" pairs except the one we used as the title and the description field.
        val body = item.fields.entries
            .filter { it.key != title.key && it.key != "description" }
            .joinToString("\n") { "${it.key}: ${it.value}" }
        bodyText.text = body

        //basic click support
        // TODO: add proper navigation to details pane.
        itemView.setOnClickListener {
            currentItem?.let {onClick?.invoke(it)}
        }






    }


}