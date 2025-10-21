package com.example.s3874961_assignment2.ui.dashboard.recyclerview

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.s3874961_assignment2.R

class DashboardRecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val titleText: TextView = view.findViewById(R.id.titleText)
    private val bodyText: TextView = view.findViewById(R.id.bodyText)
    // reference for clicks later.
    private var currentItem: Map<String, Any?>? = null

    fun bind(
        item: Map<String, Any?>,
        onClick: ((Map<String, Any?>) -> Unit)?
    ) {
        currentItem = item

        // designate the first field in an object as the title
        val title = item.entries.first()
        titleText.text = title.value.toString()

        // show all other fields as "key: value" pairs except the one we used as the title.
        // TODO: remove details field.
        val body = item.entries
            .filter { it.key != title.key }
            .joinToString("\n") { "${it.key}: ${it.value}" }
        bodyText.text = body

        //basic click support
        // TODO: add proper navigation to details pane.
        itemView.setOnClickListener {
            currentItem?.let {onClick?.invoke(it)}
        }






    }


}