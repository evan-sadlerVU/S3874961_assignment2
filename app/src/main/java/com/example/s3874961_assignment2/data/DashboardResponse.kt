package com.example.s3874961_assignment2.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

// generic dashboard response class, used to parse the JSON response from the API
@JsonClass(generateAdapter = true)
data class DashboardResponse(
    @Json(name = "entities") val entities: List<Map<String, Any?>>,
    @Json(name = "entityTotal") val entityTotal: Int
)
