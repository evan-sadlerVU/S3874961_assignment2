package com.example.s3874961_assignment2.ui.dashboard

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.s3874961_assignment2.data.DashboardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val dashboardRepository: DashboardRepository
) : ViewModel() {
    fun fetchDashboardData(keypass: String){
        viewModelScope.launch {
            try {
                //call the dashboard repository to get the dashboard data
                val dashboardResponse = dashboardRepository.getDashboard(keypass)
                Log.d("DashboardViewModel", "Dashboard response: $dashboardResponse")
                // TODO: remove this test code, just prints all the responses as individual items
                for (item in dashboardResponse.entities) {
                    for ((key, value) in item) {
                        Log.d("DashboardViewModel", "Key: $key, Value: $value")
                    }
                }
            } catch (e: Exception) {
                Log.e("DashboardViewModel", "Dashboard failed", e)
            }
        }
    }
}