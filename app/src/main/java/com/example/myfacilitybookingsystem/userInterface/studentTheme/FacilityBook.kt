package com.example.myfacilitybookingsystem.userInterface.studentTheme

import android.R.attr.name
import android.R.attr.onClick
import com.example.myfacilitybookingsystem.R
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FacilityBookScreen(
    onCITCABClick: () -> Unit,
    onLibraryABClick: () -> Unit,
    onSportsABClick: () -> Unit,
    onCITCTTClick: () -> Unit,
    onLibraryTTClick: () -> Unit,
    onSportsTTClick: () -> Unit
) {

    Column(
        Modifier
            .padding(16.dp)
    ) {
        TabRow(
            selectedTabIndex = 0,
            contentColor = Color.White
        ) {
            Tab(true, onClick = {}) { Text("Facility Booking") }
            Tab(false, onClick = {}) { Text("My Bookings") }
        }

        Spacer(Modifier.height(16.dp))

        FacilityItemUI("Cyber Centre Discussion Room", onCITCABClick, onCITCTTClick)
        FacilityItemUI("Library Discussion Room", onLibraryABClick, onLibraryTTClick)
        FacilityItemUI("Sports Facilities", onSportsABClick, onSportsTTClick)
    }
}

@Composable
fun FacilityItemUI(name: String, onAddBookingClick: () -> Unit, onViewTTClick: () -> Unit) {
    Card(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)

    ) {
        Row(
            Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(name, modifier = Modifier.weight(1f))
            Icon(
                painter = painterResource(id = R.drawable.add),
                contentDescription = "Add",
                tint = Color.Unspecified,
                modifier = Modifier
                    .size(24.dp)
                    .clickable { onAddBookingClick() }
            )
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                painter = painterResource(id = R.drawable.time),
                contentDescription = "Time",
                tint = Color.Unspecified,
                modifier = Modifier
                    .size(32.dp)
                    .clickable { onViewTTClick() }
            )
        }
    }
}


@Composable
fun FacilityBookingDetailScreen(facilityName: String, onBack: () -> Unit) {
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("< Back", Modifier.clickable { onBack() }, color = Color.Blue)
        Spacer(Modifier.height(16.dp))
        Text("Booking details for: $facilityName", fontWeight = FontWeight.Bold, fontSize = 20.sp)
    }
}


