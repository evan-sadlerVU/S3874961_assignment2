/* View Model For Login Fragment */
package com.example.s3874961_assignment2.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.s3874961_assignment2.data.LoginRepository
import com.example.s3874961_assignment2.data.LoginRequest
import com.example.s3874961_assignment2.data.LoginResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
) : ViewModel(){
    // stateflow to hold navigation event, containing the loginresponse (has keypass inside).
    private val _navigateToDashboard = MutableStateFlow<LoginResponse?>(null)
    val navigateToDashboard = _navigateToDashboard.asStateFlow()

    // stateflow to show an error message when login fails.
    private val _loginError = MutableStateFlow<String?>(null)
    val loginError = _loginError.asStateFlow()


    // function to login when button is clicked.
    fun onLoginClicked(username: String, password: String) {

        //attempt login with viewModelScope
        viewModelScope.launch {
            try {
                val loginResponse = loginRepository.login(LoginRequest(username, password))
                // post response to the livedata which will trigger navigation
                _navigateToDashboard.value = loginResponse
            } catch (e: Exception) {
                // post error message to the livedata which will trigger an error message
                _loginError.value = "Login failed: ${e.message}"
            }
        }
    }
    // call after navigation is handled to prevent retrigger on screen rotation.
    fun onNavigationComplete(){
        _navigateToDashboard.value = null
    }
    // call after error message is handled to prevent retrigger on screen rotation.
    fun onErrorShown(){
        _loginError.value = null
    }

}