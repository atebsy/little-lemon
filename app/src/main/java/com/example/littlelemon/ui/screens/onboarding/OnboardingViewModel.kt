package com.example.littlelemon.ui.screens.onboarding


import android.content.SharedPreferences
import android.provider.SyncStateContract.Helpers.update
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.edit
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.littlelemon.Home
import com.example.littlelemon.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class OnboardingViewModel : ViewModel() {

    private var _uiState = MutableStateFlow(OnboardingUiState())
    val uiState: StateFlow<OnboardingUiState> = _uiState.asStateFlow()

    var firstName by mutableStateOf("")
        private set
    var lastName by mutableStateOf("")
        private set
    var email by mutableStateOf("")
        private set


    fun setTextFieldValue(fieldName: String, value: String) {
        when (fieldName) {
            "Email" -> {
                if (value.isEmpty()) {
                    _uiState.update { currentState ->
                        currentState.copy(
                            emailError = R.string.email_err_msg
                        )
                    }
                } else {
                    _uiState.update { currentState ->
                        currentState.copy(
                            emailError = null
                        )
                    }
                }
                email = value
            }

            "First name" -> {
                if (value.isEmpty()) {
                    _uiState.update { currentState ->
                        currentState.copy(
                            firstNamError = R.string.first_name_err_msg
                        )
                    }
                } else {
                    _uiState.update { currentState ->
                        currentState.copy(
                            firstNamError = null
                        )
                    }
                }
                firstName = value
            }

            "Last name" -> {
                if (value.isEmpty()) {
                    _uiState.update { currentState ->
                        currentState.copy(
                            lastNameError = R.string.last_name_err_msg
                        )
                    }
                } else {
                    _uiState.update { currentState ->
                        currentState.copy(
                            lastNameError = null
                        )
                    }
                }
                lastName = value
            }

            else -> {}
        }
    }

    fun goToHomeScreen(
        navHostController: NavHostController?,
        sharedPreferences: SharedPreferences?
    ) {
        if (firstName.isNotBlank()
            && lastName.isNotBlank()
            && email.isNotBlank()
        ) {
            sharedPreferences?.edit(commit = true) {
                putString("first_name", firstName)
            }

            sharedPreferences?.edit(commit = true) {
                putString("last_name", lastName)
            }

            sharedPreferences?.edit(commit = true) {
                putString("email", email)
            }

            navHostController?.navigate(Home.route)
        } else {
            checkInvalidField(firstName, lastName, email)
        }
    }

    fun checkInvalidField(
        firstName: String,
        lastName: String,
        email: String
    ) {
        if (firstName.isEmpty()) {

            _uiState.update { currentState ->
                currentState.copy(
                    firstNamError = R.string.first_name_err_msg
                )
            }
        }

        if (lastName.isEmpty()) {

            _uiState.update { currentState ->
                currentState.copy(
                    lastNameError = R.string.last_name_err_msg
                )
            }
        }

        if (email.isEmpty()) {

            _uiState.update { currentState ->
                currentState.copy(
                    emailError = R.string.email_err_msg
                )
            }
        }
    }

}