package com.example.s3874961_assignment2.ui.dashboard

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.s3874961_assignment2.data.DashboardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val dashboardRepository: DashboardRepository
) : ViewModel() {
    data class UiState(
        val loading: Boolean = false,
        val items: List<Map<String, Any?>> = emptyList(),
        val error: String? = null
    )
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState

    fun fetchDashboardData(keypass: String){
        viewModelScope.launch {
            _uiState.update { it.copy(loading = true, error = null) }
            try {
                //call the dashboard repository to get the dashboard data
                val dashboardResponse = dashboardRepository.getDashboard(keypass)
                Log.d("DashboardViewModel", "Dashboard response: $dashboardResponse")
                _uiState.update { it.copy(loading = false, items = dashboardResponse.entities) }
            } catch (e: Exception) {
                Log.e("DashboardViewModel", "Dashboard failed", e)
            }
        }
    }
}