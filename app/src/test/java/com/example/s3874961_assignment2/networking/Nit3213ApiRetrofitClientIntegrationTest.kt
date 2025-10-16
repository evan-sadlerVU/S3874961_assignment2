package com.example.s3874961_assignment2.networking
// !! This is not in the right package, but is a useful test nonetheless !!
import com.example.s3874961_assignment2.data.LoginRequest
import org.junit.Test
import org.junit.Assert.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest

@OptIn(ExperimentalCoroutinesApi::class)
class Nit3213ApiRetrofitClientIntegrationTest {
    @Test
    fun `Login responds with correct Key`() = runTest() {
        // Test case that checks that the retrofit client is working
        // and that the login response is correct.
        val loginResponse = Nit3213ApiRetrofitClient.nit3213ApiService.login(LoginRequest("evan", "3874961"))
        assertEquals("animals", loginResponse.keypass)
    }
}