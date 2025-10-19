package com.example.s3874961_assignment2.data

import com.example.s3874961_assignment2.networking.Nit3213ApiService
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class DashboardRepository @Inject constructor(
    private val apiService: Nit3213ApiService
){
    suspend fun getDashboard(keypass: String): List <DashboardResponseItem> {
        return apiService.getDashboard(keypass)
    }

}