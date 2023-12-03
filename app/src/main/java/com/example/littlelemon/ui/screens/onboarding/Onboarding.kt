package com.example.littlelemon.ui.screens.onboarding

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.littlelemon.R
import com.example.littlelemon.getNullString
import com.example.littlelemon.ui.theme.LittleLemonColor


@Composable
fun OnboardingScreen(
    navHostController: NavHostController?,
    sharedPreferences: SharedPreferences?,
    context: Context?,
    onboardingViewModel: OnboardingViewModel = viewModel()
) {
    val onboardingUiState = onboardingViewModel.uiState.collectAsState()

    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Image(
            painter = painterResource(id = R.drawable.top_logo_image),
            contentDescription = "Logo with text",
            modifier = Modifier
                .padding(top = 5.dp)
                .width(150.dp)
        )
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 10.dp)
                .height(50.dp)
                .background(color = LittleLemonColor.green),
        ) {
            Text(
                text = "Let's get to know you",
                color = LittleLemonColor.cloud,
                fontSize = 24.sp
            )
        }
        Box(
            modifier = Modifier
                .padding(top = 25.dp, bottom = 25.dp)
                .fillMaxWidth()
                .padding(start = 10.dp)
        ) {
            Text(
                text = "Personal information",
                color = LittleLemonColor.charcoal,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {


                OnboardingTextField(
                    value = onboardingViewModel.firstName,
                    textFieldLabel = "First Name",
                    onboardingViewModel = onboardingViewModel,
                    fieldName = "First name",
                )
                Text(
                    text = context?.getNullString(onboardingUiState.value.firstNamError)!!,
                    color = Color.Red,
                    fontSize = 16.sp
                )
                OnboardingTextField(
                    value = onboardingViewModel.lastName,
                    textFieldLabel = "Last Name",
                    onboardingViewModel = onboardingViewModel,
                    fieldName = "Last name",
                )
                Text(
                    text = context.getNullString(onboardingUiState.value.lastNameError),
                    color = Color.Red,
                    fontSize = 16.sp
                )
                OnboardingTextField(
                    value = onboardingViewModel.email,
                    textFieldLabel = "Email",
                    onboardingViewModel = onboardingViewModel,
                    fieldName = "Email"
                )
                Text(
                    text = context.getNullString(onboardingUiState.value.emailError),
                    color = Color.Red,
                    fontSize = 16.sp
                )
                Button(
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(7.dp),
                    onClick = {onboardingViewModel
                        .goToHomeScreen(navHostController,sharedPreferences)},
                    colors = ButtonDefaults.buttonColors(LittleLemonColor.yellow),
                    border = BorderStroke(2.dp, LittleLemonColor.pink),

                ) {
                    Text(
                        text = "Register",
                        color = LittleLemonColor.charcoal
                    )
                }
            }
        }
    }
}



@Composable
fun OnboardingTextField(
    value: String,
    textFieldLabel: String,
    onboardingViewModel: OnboardingViewModel,
    fieldName: String
) {
    Text(
        text = textFieldLabel,
        modifier = Modifier.padding(bottom = 5.dp)
    )
    BasicTextField(
        value = value,
        onValueChange = {
            onboardingViewModel.setTextFieldValue(fieldName, it)
        },
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = LittleLemonColor.charcoal,
                        shape = RoundedCornerShape(size = 5.dp)
                    )
                    .padding(horizontal = 16.dp, vertical = 12.dp), // inner padding
            ) {
                if (value.isEmpty()) {
                    Text(
                        text = "",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.LightGray
                    )
                }
                innerTextField()
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun OnboardingPreview() {
    OnboardingScreen(null, null, null)
}