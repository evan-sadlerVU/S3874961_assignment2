package com.example.s3874961_assignment2.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.s3874961_assignment2.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val titleView = view.findViewById<TextView>(R.id.detailsTitle)
        val bodyView = view.findViewById<TextView>(R.id.detailsBody)

        val fields = args.responseitem.fields  // LinkedHashMap<String, Any?>


        val firstEntry = fields.entries.firstOrNull()
        titleView.text = firstEntry?.value?.toString() ?: "(details)"


        var bodyText = ""
        fields.entries.drop(1).forEach { (key, value) ->
            bodyText += "$key: ${value?.toString() ?: ""}\n"
        }

        bodyView.text = bodyText
    }
}
