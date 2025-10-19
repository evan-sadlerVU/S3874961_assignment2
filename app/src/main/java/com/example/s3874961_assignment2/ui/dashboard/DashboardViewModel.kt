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
    fun fetchDashboardData(){
        viewModelScope.launch {
            try {
                // TODO: REMOVE TEMP KEYPASS USE ACTUAL KEYPASS FROM LOGIN RESPONSE
                val dashboardResponse = dashboardRepository.getDashboard("animals")
                Log.d("DashboardViewModel", "Dashboard response: $dashboardResponse")
            } catch (e: Exception) {
                Log.e("DashboardViewModel", "Dashboard failed", e)
            }
        }
    }
}