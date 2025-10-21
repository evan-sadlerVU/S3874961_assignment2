package com.example.s3874961_assignment2.data

import com.example.s3874961_assignment2.networking.Nit3213ApiService
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class DashboardRepository @Inject constructor(
    private val apiService: Nit3213ApiService
){
    @Suppress("UNCHECKED_CAST")
    suspend fun getItems(key: String): List<DashboardResponseItem> {
        val raw = apiService.getDashboardRawMap(key)
        val entities = (raw["entities"] as? List<Map<String, Any?>>).orEmpty()
        return entities.map { DashboardResponseItem(LinkedHashMap(it)) }
    }
}