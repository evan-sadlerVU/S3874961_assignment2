/* essentially a list of functions that can be used with the retrofit client to access data from the API */

package com.example.s3874961_assignment2.networking
import com.example.s3874961_assignment2.data.*
import retrofit2.http.*

interface Nit3213ApiService {
    @POST("footscray/auth")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse
}
