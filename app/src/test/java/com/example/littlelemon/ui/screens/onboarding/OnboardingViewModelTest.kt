package com.example.littlelemon.ui.screens.onboarding

import com.example.littlelemon.R
import org.junit.Assert.*

import org.junit.Test

class OnboardingViewModelTest {

    private val onboardingViewModel = OnboardingViewModel()

    @Test
    fun onboardingViewModel_setTextFieldValue_showEmptyEmailErrorMsg() {
        val fieldName = "Email"
        val emailValue = ""

        onboardingViewModel.setTextFieldValue(fieldName, emailValue)

        assertEquals(R.string.email_err_msg, onboardingViewModel.uiState.value.emailError)

    }

    @Test
    fun onboardingViewModel_setTextFieldValue_showEmptyFirstNameErrorMsg() {
        val fieldName = "First name"
        val firstNameValue = ""

        onboardingViewModel.setTextFieldValue(fieldName, firstNameValue)

        assertEquals(R.string.first_name_err_msg, onboardingViewModel.uiState.value.firstNamError)

    }

    @Test
    fun onboardingViewModel_checkInvalidField_shouldDisplayAllErrorMsg(){
        val firstNameValue = ""
        val lastNameValue = ""
        val emailValue = ""

        onboardingViewModel.checkInvalidField(firstNameValue,lastNameValue,emailValue)

        assertEquals(R.string.first_name_err_msg, onboardingViewModel.uiState.value.firstNamError)
        assertEquals(R.string.last_name_err_msg, onboardingViewModel.uiState.value.lastNameError)
        assertEquals(R.string.email_err_msg, onboardingViewModel.uiState.value.emailError)
    }

    @Test
    fun onboardingViewModel_checkInvalidField_allErrorMsgShouldBeNUll(){
        val firstNameValue = "First name"
        val lastNameValue = "Last name"
        val emailValue = "atebajoel@gmail.com"

        onboardingViewModel.checkInvalidField(firstNameValue,lastNameValue,emailValue)

        assertEquals(null, onboardingViewModel.uiState.value.firstNamError)
        assertEquals(null, onboardingViewModel.uiState.value.lastNameError)
        assertEquals(null, onboardingViewModel.uiState.value.emailError)
    }
}