package com.example.s3874961_assignment2.networking

import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class Nit3213ApiRetrofitClientTest {
    @Test
    fun `Test Retrofit Client`() {
        val retrofit = Nit3213ApiRetrofitClient.retrofit
        assertNotNull(retrofit)
    }
}