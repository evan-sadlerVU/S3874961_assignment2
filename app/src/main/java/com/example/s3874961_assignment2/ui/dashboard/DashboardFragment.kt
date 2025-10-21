package com.example.s3874961_assignment2.ui.dashboard

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.s3874961_assignment2.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment() {
    //Get the arguments passed when navigating (the keypass)
    private val args: DashboardFragmentArgs by navArgs()
    //Associate the appropriate viewmodel with this fragment
    private val viewModel: DashboardViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Call the function to fetch the dashboard data
        viewModel.fetchDashboardData(args.keypass)
        Log.d("DashboardFragment", "Keypass received: ${args.keypass}")

    }
}