/* View Model For Login Fragment */
package com.example.s3874961_assignment2.ui.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.s3874961_assignment2.data.LoginRepository
import com.example.s3874961_assignment2.data.LoginRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
) : ViewModel(){

    // function to login when button is clicked.
    fun onLoginClicked(username: String, password: String) {
        Log.d("LoginViewModel", "Login attempt for user $username")

        //attempt login with viewModelScope
        viewModelScope.launch {
            try {
                val loginResponse = loginRepository.login(LoginRequest(username, password))
                Log.d("LoginViewModel", "Login response: $loginResponse")
            } catch (e: Exception) {
                Log.e("LoginViewModel", "Login failed", e)
            }
        }
    }
}