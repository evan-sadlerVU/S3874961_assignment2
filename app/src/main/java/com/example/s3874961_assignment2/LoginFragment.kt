package com.example.s3874961_assignment2

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.s3874961_assignment2.data.LoginRequest
import com.example.s3874961_assignment2.networking.Nit3213ApiRetrofitClient
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState:
    Bundle?): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //TODO: replace test login thingo (BAD AND NEEDS TO BE REPLACED LOLOL)
        val loginButton = view.findViewById<MaterialButton>(R.id.loginButton)
        loginButton.setOnClickListener {
            val username = view.findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.usernameField).text.toString()
            val password = view.findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.passwordField).text.toString()
            Log.d("LoginFragment", "Submitted: Username=\"$username\", Password=\"$password\"")
            //TODO: V-BAD, Replace
            lifecycleScope.launch {
                // set user and pass permanently to test login
                val username = "evan"
                val password = "3874961"
                val loginResponse = withContext(Dispatchers.IO) {
                    Nit3213ApiRetrofitClient.nit3213ApiService.login(
                        LoginRequest(
                            username,
                            password
                        )
                    )
                }
                Log.d("LoginFragment", "Login response: $loginResponse")
            }
        }



    }
}