package com.example.s3874961_assignment2.data

import com.squareup.moshi.Json

data class DashboardResponseItem(
    @Json(name = "id") val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "data") val data: Map<String, String?>
)

