package com.example.s3874961_assignment2.ui.dashboard

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.s3874961_assignment2.R
import com.example.s3874961_assignment2.ui.dashboard.recyclerview.DashboardRecyclerViewAdaptor
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class DashboardFragment : Fragment() {
    //Get the arguments passed when navigating (the keypass)
    private val args: DashboardFragmentArgs by navArgs()
    //Associate the appropriate viewmodel with this fragment
    private val viewModel: DashboardViewModel by viewModels()

    private lateinit var adapter: DashboardRecyclerViewAdaptor



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

        // Setup the recycler
        val recycler = view.findViewById<RecyclerView>(R.id.recyclerDashboard)
        recycler.layoutManager = LinearLayoutManager(requireContext())
        adapter = DashboardRecyclerViewAdaptor(
            onItemClick = { item ->
                // Handle item click here
                val action = DashboardFragmentDirections.actionDashboardFragmentToDetailsFragment(item)
                findNavController().navigate(action)
            }
        )
        recycler.adapter = adapter

        // get the dashboard data with the keypass
        viewModel.fetchDashboardData(args.keypass)

        // submit the data to the recycler
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    adapter.setData(state.items)
                }
            }
        }
    }
}