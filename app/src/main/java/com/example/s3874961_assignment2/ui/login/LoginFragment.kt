//Fragment for login screen
package com.example.s3874961_assignment2.ui.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.s3874961_assignment2.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState:
    Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // find the Ui components using findViewById
        val loginButton = view.findViewById<MaterialButton>(R.id.loginButton)
        val usernameField = view.findViewById<TextInputEditText>(R.id.usernameField)
        val passwordField = view.findViewById<TextInputEditText>(R.id.passwordField)
        val errorTextView = view.findViewById<TextView>(R.id.errorTextView)


        loginButton.setOnClickListener {
            // get the text from the fields
            val username = usernameField.text.toString()
            val password = passwordField.text.toString()
            Log.d("LoginFragment", "login button clicked for user: $username")
            errorTextView.visibility = View.GONE
            // call the login function in the view model
            viewModel.onLoginClicked(username, password)
        }
        // observe state flow to handle navigation and error messages.
        observeUiEvents(errorTextView)
    }
    private fun observeUiEvents(errorTextView: TextView) {

        //launch a coroutine in the lifecycle scope
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                //collect the data from the state flow
                viewModel.navigateToDashboard.collect { loginResponse ->
                    // if the response is not null, navigate to the dashboard.
                    loginResponse?.let {
                        Log.d("LoginFragment", "Navigating to dashboard with keypass: ${loginResponse.keypass}")
                        val action = LoginFragmentDirections.actionLoginFragmentToDashboardFragment(loginResponse.keypass)
                        findNavController().navigate(action)
                        viewModel.onNavigationComplete()
                    }
                }
            }

        }
        // handle error messages
        lifecycleScope.launch {
            viewModel.loginError.collect { msg ->
                if (msg != null) {
                    errorTextView.text = "Invalid username or Password"
                    errorTextView.visibility = View.VISIBLE
                    viewModel.onErrorShown()
                }
            }
        }

    }
}