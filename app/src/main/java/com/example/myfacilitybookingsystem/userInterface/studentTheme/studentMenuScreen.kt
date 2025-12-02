package com.example.myfacilitybookingsystem.userInterface.studentTheme

import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfacilitybookingsystem.ui.theme.BlueMain
import com.example.myfacilitybookingsystem.ui.theme.LogoutRed
import com.example.myfacilitybookingsystem.userInterface.loginTheme.studentLoginScreen

@Composable
fun studentMenuScreen(
    onBookFacility: () -> Unit = {},
    onMyBookings: () -> Unit = {},
    onProfile: () -> Unit = {},
    onLogout: () -> Unit = {},
    bottomBar: @Composable () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp, vertical = 20.dp)
    ) {
        Spacer(modifier = Modifier.height(80.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(BlueMain)
                .padding(20.dp),
            contentAlignment = Alignment.Center
        ){
            Row {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Doctor Avatar",
                    modifier = Modifier.size(100.dp)
                )
                Column {
                    Text(
                        text = "NAME",//"Dr. " + doctor?.name
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                    Text(
                        text = "NAME",//"Dr. " + doctor?.name
                        fontWeight = FontWeight.Light,
                        fontSize = 20.sp
                    )
                    Text(
                        text = "NAME",//"Dr. " + doctor?.name
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )

                }
            }
        }

        Spacer(modifier = Modifier.height(45.dp))

        // ---- BUTTON TEMPLATE ----
        MenuButton("Book Facility", onBookFacility)

        Spacer(modifier = Modifier.height(35.dp))

        MenuButton("My Bookings", onMyBookings)

        Spacer(modifier = Modifier.height(35.dp))

        MenuButton("Profile", onProfile)

        Spacer(modifier = Modifier.height(35.dp))

        Button(
            onClick = onLogout,
            modifier = Modifier
                .fillMaxWidth()
                .height(57.dp),
            colors = ButtonDefaults.buttonColors(containerColor = LogoutRed),
            shape = RoundedCornerShape(20.dp)
        ) {
            Text("Logout", fontSize = 18.sp, color = Color.White)
        }

        Spacer(modifier = Modifier.weight(1f))

        bottomBar()
    }
}


@Composable
fun MenuButton(
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(57.dp),
        shape = RoundedCornerShape(20.dp)
    ) {
        Text(
            text = text,
            fontSize = 18.sp,
            color = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    studentMenuScreen(
        onBookFacility = {},
        onMyBookings = {},
        onProfile = {},
        onLogout = {},
        bottomBar = {}
    )
}
