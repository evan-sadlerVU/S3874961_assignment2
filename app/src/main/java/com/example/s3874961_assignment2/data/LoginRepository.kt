// repository class to handle login operations.
package com.example.s3874961_assignment2.data

import com.example.s3874961_assignment2.networking.Nit3213ApiService
import javax.inject.Inject
import javax.inject.Singleton

// hm, this is a singleton, but is only used once, so its probably not necessary.
@Singleton
class LoginRepository @Inject constructor(
    private val apiService: Nit3213ApiService
) {
    // performs the login request, uses the api service to do so.
    suspend fun login(loginRequest: LoginRequest): LoginResponse {
        return apiService.login(loginRequest)
    }
}