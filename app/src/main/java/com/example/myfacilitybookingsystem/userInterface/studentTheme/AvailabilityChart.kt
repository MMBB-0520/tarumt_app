package com.example.myfacilitybookingsystem.userInterface.studentTheme

import android.R.attr.label
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AvailabilityChartScreen(
    navController: NavController,
    selectedVenue: String,
    onBack: () -> Unit
) {

    // date follow actual date
    val formatter = DateTimeFormatter.ofPattern("dd / MMM / yyyy (EEE)", Locale.ENGLISH)
    val today = LocalDate.now()
    val dateOptions = (0..2).map { today.plusDays(it.toLong()).format(formatter) }
    var selectedDate by remember { mutableStateOf(dateOptions[0]) }

    // venue
    var selectedVenue by remember { mutableStateOf("") }
    val venueOptions = listOf(
        "Cyber Centre Discussion Room",
        "Library Discussion Room",
        "Sports Facilities"
    )

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.padding(start = 8.dp).clickable{onBack()}
                    )
                },
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Availability Chart", color = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF0D47A1),
                    titleContentColor = Color.White
                )
            )
        }
    ) { padding ->

        Column(Modifier.padding(padding)) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Button(onClick = {
                    if (selectedDate.isNotEmpty() && selectedVenue.isNotEmpty()) {
                        navController.navigate("BookingConfirm/$selectedVenue/$selectedDate")
                    }
                }) {
                    Text("Book Now")
                }
            }

            Column(
                Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
            ) {
                Text("Date")
                UnderlinedFloatingLabelDropdown(
                    label = "Date",
                    value = selectedDate,
                    items = dateOptions
                )
                { selectedDate = it }

                Spacer(Modifier.height(14.dp))

                Text("Venue Type")
                UnderlinedFloatingLabelDropdown(
                    label = "Venue",
                    value = selectedVenue,
                    items = venueOptions
                )
                { selectedVenue = it }

                Spacer(Modifier.height(20.dp))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnderlinedFloatingLabelDropdown(
    label: String,
    value: String,
    items: List<String>,
    onValueChange: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth()) {

        if (value.isNotEmpty()){
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                color = Color.Gray,
                modifier = Modifier.padding(start = 4.dp, bottom = 2.dp)
            )
        }
        TextField(
            value = value,
            onValueChange = {},
            readOnly = true,
            placeholder = {if(value.isEmpty()) Text(label)},
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = true },
            trailingIcon = {
                Icon(Icons.Default.ArrowDropDown, contentDescription = null)
            },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,

                focusedIndicatorColor = Color.Black,
                unfocusedIndicatorColor = Color.Gray,
            )
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    text = { Text(item) },
                    onClick = {
                        onValueChange(item)
                        expanded = false
                    }
                )
            }
        }
    }
}