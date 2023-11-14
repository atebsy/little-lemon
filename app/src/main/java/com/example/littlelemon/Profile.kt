package com.example.littlelemon

import android.content.SharedPreferences
import androidx.activity.ComponentActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.navigation.NavHostController
import com.example.littlelemon.ui.theme.LittleLemonColor

@Composable
fun ProfileScreen(
    navHostController: NavHostController?,
    sharedPreferences: SharedPreferences?
){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = R.drawable.top_logo_image),
            contentDescription = "Logo with text",
            modifier = Modifier
                .padding(top = 5.dp, bottom = 70.dp)
                .width(150.dp)
        )

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
            val firstName by remember {
                mutableStateOf(sharedPreferences?.getString("first_name","").toString())
            }

            val lastName by remember {
                mutableStateOf(sharedPreferences?.getString("last_name","").toString())
            }

            val email by remember {
                mutableStateOf(sharedPreferences?.getString("email","").toString())
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                ProfileTextField(
                    value = firstName,
                    textFieldLabel = "First Name"
                )

                ProfileTextField(
                    value = lastName,
                    textFieldLabel = "Last Name"
                )

                ProfileTextField(
                    value = email,
                    textFieldLabel = "Email"
                )

                Button(
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(7.dp),
                    onClick = {
                        sharedPreferences?.edit()?.clear()?.commit()
                        navHostController?.navigate(Onboarding.route)
                    },
                    colors = ButtonDefaults.buttonColors(LittleLemonColor.yellow),
                    border = BorderStroke(2.dp, LittleLemonColor.pink)
                ) {
                    Text(
                        text = "Logout",
                        color = LittleLemonColor.charcoal
                    )
                }
            }
        }
    }
}


@Composable
fun ProfileTextField(
    value: String,
    textFieldLabel: String
) {
    Text(
        text = textFieldLabel,
        modifier = Modifier.padding(bottom = 5.dp)
    )
    BasicTextField(
        value = value,
        onValueChange = {},
        readOnly = true,
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
fun ProfilePreview() {
    ProfileScreen(null, null)
}