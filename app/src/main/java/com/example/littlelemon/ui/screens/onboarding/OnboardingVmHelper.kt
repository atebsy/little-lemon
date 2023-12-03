package com.example.littlelemon.ui.screens.onboarding

import com.example.littlelemon.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class OnboardingVmHelper {
    companion object {
        fun checkInvalidField(
            onboardingUiState: MutableStateFlow<OnboardingUiState>,
            firstName: String,
            lastName: String,
            email: String
        ) {
            if (firstName.isEmpty()) {

                onboardingUiState.update { currentState ->
                    currentState.copy(
                        firstNamError = R.string.first_name_err_msg
                    )
                }
            }

            if (lastName.isEmpty()) {

                onboardingUiState.update { currentState ->
                    currentState.copy(
                        lastNameError = R.string.last_name_err_msg
                    )
                }
            }

            if (email.isEmpty()) {

                onboardingUiState.update { currentState ->
                    currentState.copy(
                        emailError = R.string.email_err_msg
                    )
                }
            }
        }
    }
}