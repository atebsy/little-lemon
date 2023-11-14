package com.example.littlelemon

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.edit
import androidx.navigation.NavHostController
import com.example.littlelemon.ui.theme.LittleLemonColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnboardingScreen(
    navHostController: NavHostController?,
    sharedPreferences: SharedPreferences?,
    context: Context?
) {
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
            var firstName by remember {
                mutableStateOf("")
            }

            var lastName by remember {
                mutableStateOf("")
            }

            var email by remember {
                mutableStateOf("")
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                OnboardingTextField(
                    value = firstName,
                    textFieldLabel = "First Name"
                ) {
                    firstName = it
                }

                OnboardingTextField(
                    value = lastName,
                    textFieldLabel = "Last Name"
                ) {
                    lastName = it
                }

                OnboardingTextField(
                    value = email,
                    textFieldLabel = "Email"
                ) {
                    email = it
                }

                Button(
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(7.dp),
                    onClick = {
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
                            Toast.makeText(context, "All field are required!!", Toast.LENGTH_LONG)
                                .show()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(LittleLemonColor.yellow),
                    border = BorderStroke(2.dp, LittleLemonColor.pink)
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
    onTextFieldChange: (String) -> Unit
) {
    Text(
        text = textFieldLabel,
        modifier = Modifier.padding(bottom = 5.dp)
    )
    BasicTextField(
        value = value,
        onValueChange = onTextFieldChange,
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .padding(bottom = 20.dp) //margin bottom
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