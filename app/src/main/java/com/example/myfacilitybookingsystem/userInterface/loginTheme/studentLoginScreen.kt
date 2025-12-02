package com.example.myfacilitybookingsystem.userInterface.loginTheme

import android.R.attr.onClick
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfacilitybookingsystem.R
import com.example.myfacilitybookingsystem.ui.theme.Background
import com.example.myfacilitybookingsystem.ui.theme.BlueMain
import com.example.myfacilitybookingsystem.ui.theme.CheckGreen
import com.example.myfacilitybookingsystem.ui.theme.ErrorRed
import com.example.myfacilitybookingsystem.ui.theme.ForgotBlue
import com.example.myfacilitybookingsystem.ui.theme.TARRed
import com.example.myfacilitybookingsystem.ui.theme.UMTBlue
import com.example.myfacilitybookingsystem.userInterface.HomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun studentLoginScreen(
    onLoginSuccess: () -> Unit = {},
    onForgotPassClick: () -> Unit = {},
    bottomBar: @Composable () -> Unit = {}
) {
    var studentId by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }

    var idValid by remember { mutableStateOf<Boolean?>(null) }   // null = default, true = check, false = x
    var showLoginError by remember { mutableStateOf(false) }

    // Regex Example (you可换自己的)
    val idRegex = Regex("^[A-Z]{8}\\d{5}\$")

    fun validateId(input: String) {
        idValid = idRegex.matches(input)
    }

    Column(
        modifier = Modifier
            .background(Background)
            .padding(24.dp)
    ) {

        Image(
            painter = painterResource(id = R.drawable.tarumt), // 图片资源
            contentDescription = "Logo",
            modifier = Modifier
                .size(400.dp)
                .align(Alignment.CenterHorizontally)
        )

        // Student ID TextField
        OutlinedTextField(
            value = studentId,
            onValueChange = {
                studentId = it
                validateId(it)
                showLoginError = false
            },
            leadingIcon = {
                // Default user icon
                val iconTint = when (idValid) {
                    true -> CheckGreen
                    false -> ErrorRed
                    else -> Color.Gray
                }

                Icon(
                    imageVector = Icons.Outlined.Person,
                    contentDescription = null,
                    tint = iconTint
                )
            },
            trailingIcon = {
                when (idValid) {
                    true -> Icon(
                        painterResource(id = R.drawable.ic_checked_user),
                        contentDescription = null
                    )
                    false -> Icon(
                        painterResource(id = R.drawable.ic_denied),
                        contentDescription = null
                    )
                    else -> {}
                }
            },
            isError = showLoginError,
            placeholder = { Text("Student ID") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Password TextField
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                showLoginError = false
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Lock,
                    contentDescription = "Lock Outline",
                    tint = Color.Gray,
                    modifier = Modifier.size(24.dp)
                )
            },
            trailingIcon = {
                val icon = if (showPassword) R.drawable.ic_visibilityoff else R.drawable.ic_visibility
                IconButton(onClick = { showPassword = !showPassword }) {
                    Icon(
                        painter = painterResource(id = icon),
                        contentDescription = "Toggle Password Visibility"
                    )

                }
            },
            visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
            placeholder = { Text("Password") },
            isError = showLoginError,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )

        if (showLoginError) {
            Text(
                text = "Invalid login ID or password.",
                color = ErrorRed,
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 6.dp)
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        // LOGIN BUTTON
        Button(
            onClick = {
                if (idValid == true && password.length >= 8) {
                    onLoginSuccess()
                } else {
                    showLoginError = true
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = BlueMain),
            modifier = Modifier
                .fillMaxWidth()
                .height(57.dp),
            shape = RoundedCornerShape(20.dp)
        ) {
            Text("Login", fontSize = 18.sp, color = Color.White)
        }

        Spacer(modifier = Modifier.height(15.dp))

        Text(
            text = "Forgot Password?",
            color = ForgotBlue,
            fontSize = 14.sp,
            fontStyle = FontStyle.Italic,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .clickable { onForgotPassClick() }

        )

        Spacer(modifier = Modifier.weight(1f))

        // Bottom bar placeholder
        bottomBar()
    }
}
@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    studentLoginScreen(
        onLoginSuccess = {},
        bottomBar = {}
    )
}