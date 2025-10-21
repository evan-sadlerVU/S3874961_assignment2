package com.example.s3874961_assignment2.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import java.util.LinkedHashMap

// generic dashboard response class, used to parse the JSON response from the API
@Parcelize
data class DashboardResponseItem(
    val fields: @RawValue LinkedHashMap<String, Any?>
) : Parcelable