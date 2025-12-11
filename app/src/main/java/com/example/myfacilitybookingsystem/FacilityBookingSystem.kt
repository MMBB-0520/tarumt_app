package com.example.myfacilitybookingsystem

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import bottomChooseBar
import com.example.myfacilitybookingsystem.rooms.repo.UsersRepo
import com.example.myfacilitybookingsystem.ui.theme.StaffRed
import com.example.myfacilitybookingsystem.ui.theme.StudentBlue
import com.example.myfacilitybookingsystem.userInterface.HomeScreen
import com.example.myfacilitybookingsystem.userInterface.loginTheme.StaffLoginScreen
import com.example.myfacilitybookingsystem.userInterface.loginTheme.studentLoginScreen
import com.example.myfacilitybookingsystem.userInterface.staffTheme.StaffMenuScreen
import com.example.myfacilitybookingsystem.userInterface.studentTheme.FacilityBookScreen
import com.example.myfacilitybookingsystem.userInterface.studentTheme.StudentMenuScreen
import com.example.myfacilitybookingsystem.viewModel.UsersViewModel


class UsersViewModelFactory(private val usersRepo: UsersRepo): ViewModelProvider.Factory {
    override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UsersViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UsersViewModel(usersRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
enum class AppScreen {
    // Main container
    MainSystem,

    // Tabs
    HomeScreen,
    StudentLoginScreen,
    StaffLoginScreen,
    AdminLoginScreen,

    StudentScreen,
    StaffScreen,
    AdminScreen,

    ForgotPassword,

    // Details under Home tab
    AnnouncementDetail,

    // Student Screens
    StudentBooking,
    StudentBookingDetail,

    // Staff Screens
    StaffCheckBooking,
    StaffApprove,

    // Admin Screens
    AdminAddFacility,
    AdminManageUsers,

    // Wei Qi Review
    AdminViewReview,
    AdminReviewDetail,

    StudentViewReview,
    StudentAddReview,

    StaffViewReview,
    StaffAddReview,


    // Regina booking
    CITCBooking,
    LibraryBooking,
    SportsBooking,

    CITCTimetable,
    LibraryTimetable,
    SportsTimetable,

    // KK check in
    CheckInManual,
    CheckInBarcode,
    CheckInSuccess,

    CheckOutManual,
    CheckOutBarcode,
    CheckOutSuccess,

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarScreen(
    currentScreen: AppScreen,
    hasPopBack: () -> Unit

    ) {
    when(currentScreen) {
        AppScreen.StudentLoginScreen -> {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.CenterHorizontally)
                    ) {
                        Text(
                            text = "Student Login",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 24.sp
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = StudentBlue,
                    titleContentColor = Color.White
                )
            )
        }

        AppScreen.StaffLoginScreen -> {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.CenterHorizontally)
                    ) {
                        Text(
                            text = "Staff Login",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 24.sp
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = StaffRed,
                    titleContentColor = Color.White
                )
            )
        }

        AppScreen.AdminLoginScreen -> {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.CenterHorizontally)
                    ) {
                        Text(
                            text = "Admin Login",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 24.sp
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black,
                    titleContentColor = Color.White
                )
            )
        }
        AppScreen.StudentBooking ->{
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = hasPopBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier
                                .padding(start = 8.dp)

                        )
                    }
                },                
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.CenterHorizontally)
                    ) {
                        Text(
                            text = "Facility Booking",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 24.sp
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Blue,
                    titleContentColor = Color.White
                )
            )
        }
        AppScreen.CheckInManual,AppScreen.CheckInBarcode,AppScreen.CheckInSuccess -> {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = hasPopBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier
                                .padding(start = 8.dp)

                        )
                    }
                },
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.CenterHorizontally)
                    ) {
                        Text(
                            text = "Check-In",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 24.sp
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black,
                    titleContentColor = Color.White
                )
            )
        }
        AppScreen.CheckOutManual,AppScreen.CheckOutBarcode,AppScreen.CheckOutSuccess -> {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = hasPopBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier
                                .padding(start = 8.dp)

                        )
                    }
                },
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.CenterHorizontally)
                    ) {
                        Text(
                            text = "Check-Out",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 24.sp
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black,
                    titleContentColor = Color.White
                )
            )
        }
        else -> {}
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FBSApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val context = LocalContext.current

    // App Database
    val db = remember { AppDatabase.getInstance(context) }
    // Repository
    val usersRepo = remember { UsersRepo(db.usersDao()) }
    // ViewModel
    val usersViewModel: UsersViewModel = viewModel(
        factory = UsersViewModelFactory(usersRepo)
    )

    // Compose 状态
    var studentId by remember { mutableStateOf("") }
    var staffId by remember { mutableStateOf("") }
    var studentPwd by remember { mutableStateOf("") }
    var staffPwd by remember { mutableStateOf("") }

    val staffIdValid by usersViewModel.staffIdValid.collectAsState()
    val studentIdValid by usersViewModel.studentIdValid.collectAsState()
    val showLoginError by usersViewModel.showLoginError.collectAsState()
    val currentUser by usersViewModel.currentUser.collectAsState()
    val users by usersViewModel.users.collectAsState()

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = try {
        AppScreen.valueOf(backStackEntry?.destination?.route ?: "")
    } catch (e: Exception) {
        AppScreen.MainSystem
    }

    Scaffold(
        topBar = { TopBarScreen(
            currentScreen = currentScreen,
            hasPopBack = {navController.popBackStack()}
        ) },
        bottomBar = {
            val showBottomBar = currentScreen in listOf(
                AppScreen.MainSystem,
                AppScreen.HomeScreen,
                AppScreen.StudentLoginScreen,
                AppScreen.StaffLoginScreen,
                AppScreen.AdminLoginScreen
            )

            if (showBottomBar) {
                bottomChooseBar(
                    selectedIndex = when (currentScreen) {
                        AppScreen.MainSystem, AppScreen.HomeScreen -> 0
                        AppScreen.StudentLoginScreen -> 1
                        AppScreen.StaffLoginScreen -> 2
                        AppScreen.AdminLoginScreen -> 3
                        else -> 0
                    },
                    onItemSelected = { index ->
                        when (index) {
                            0 -> navController.navigate(AppScreen.HomeScreen.name)
                            1 -> navController.navigate(AppScreen.StudentLoginScreen.name)
                            2 -> navController.navigate(AppScreen.StaffLoginScreen.name)
                            3 -> navController.navigate(AppScreen.AdminLoginScreen.name)
                        }
                    }
                )
            }
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = AppScreen.MainSystem.name,
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            navigation(
                startDestination = AppScreen.HomeScreen.name,
                route = AppScreen.MainSystem.name
            ) {
                composable(route = AppScreen.HomeScreen.name) {
                    HomeScreen(
                        onAnnouncementClick = {
                            navController.navigate(AppScreen.AnnouncementDetail.name)
                        }
                    )
                }

                // Student Login Screen
                composable(route = AppScreen.StudentLoginScreen.name) {
                    studentLoginScreen(
                        studentId = studentId,
                        onStudIdChange = {
                            studentId = it
                            usersViewModel.checkUserId(it, role = "Student")
                        },
                        idValid = studentIdValid,
                        password = studentPwd,
                        onPasswordChange = { studentPwd = it },
                        showLoginError = showLoginError,
                        onLoginClick = {
                            usersViewModel.login(studentId, studentPwd ,"Student") { success ->
                                if (success) {
                                    navController.navigate(AppScreen.StudentScreen.name) {
                                        popUpTo(AppScreen.StudentLoginScreen.name) {
                                            inclusive = true
                                        }
                                    }
                                }
                            }
                        },
                        onForgotPassClick = {
                            navController.navigate(AppScreen.ForgotPassword.name)
                        }
                    )
                }

                // Student Main Screen
                composable(route = AppScreen.StudentScreen.name) {
                    var logOutConfirm by rememberSaveable { mutableStateOf(false) }

                    if (logOutConfirm) {
                        AlertDialog(
                            onDismissRequest = { logOutConfirm = false },
                            title = { Text("Confirm Logout") },
                            text = { Text("Are you sure you want to logout?") },
                            confirmButton = {
                                TextButton(onClick = {
                                    logOutConfirm = false
                                    usersViewModel.logout()
                                    navController.navigate(AppScreen.MainSystem.name)
                                }) { Text("Yes") }
                            },
                            dismissButton = {
                                TextButton(onClick = { logOutConfirm = false }) { Text("No") }
                            },
                            properties = DialogProperties(dismissOnClickOutside = false)
                        )
                    }

                    StudentMenuScreen(
                        name = currentUser?.username ?: "",
                        studentId = currentUser?.loginId ?: "",
                        email = currentUser?.email ?: "",
                        onLogoutClick = { logOutConfirm = true },
                        onMyBookingClick = {
                            navController.navigate(AppScreen.StudentBookingDetail.name)
                        },
                        onFacilityBookingClick = {
                            navController.navigate(AppScreen.StudentBooking.name)
                        },
                        onFeedbackClick = {
                            navController.navigate(AppScreen.StudentBooking.name)
                        },
                        onSettingsClick = {
                            navController.navigate(AppScreen.StudentBooking.name)
                        }
                    )
                }
                // Staff Login Screen
                composable(route = AppScreen.StaffLoginScreen.name) {

                    StaffLoginScreen(
                        staffId = staffId,
                        onStaffIdChange = {
                            staffId = it
                            usersViewModel.checkUserId(it, role = "Staff")
                        },
                        idValid = staffIdValid,
                        password = staffPwd,
                        onPasswordChange = { staffPwd = it },
                        showLoginError = showLoginError,
                        onLoginClick = {
                            usersViewModel.login(staffId, staffPwd,"Staff") { success ->
                                if (success) {
                                    navController.navigate(AppScreen.StaffScreen.name) {
                                        popUpTo(AppScreen.StaffLoginScreen.name) {
                                            inclusive = true
                                        }
                                    }
                                }
                            }
                        },
                        onForgotPassClick = {
                            navController.navigate(AppScreen.ForgotPassword.name)
                        }
                    )
                }
                // Staff Main Screen
                composable(route = AppScreen.StaffScreen.name) {
                    var logOutConfirm by rememberSaveable { mutableStateOf(false) }

                    if (logOutConfirm) {
                        AlertDialog(
                            onDismissRequest = { logOutConfirm = false },
                            title = { Text("Confirm Logout") },
                            text = { Text("Are you sure you want to logout?") },
                            confirmButton = {
                                TextButton(onClick = {
                                    logOutConfirm = false
                                    usersViewModel.logout()
                                    navController.navigate(AppScreen.MainSystem.name)
                                }) { Text("Yes") }
                            },
                            dismissButton = {
                                TextButton(onClick = { logOutConfirm = false }) { Text("No") }
                            },
                            properties = DialogProperties(dismissOnClickOutside = false)
                        )
                    }

                    StaffMenuScreen(
                        name = currentUser?.username ?: "",
                        staffId = currentUser?.loginId ?: "",
                        email = currentUser?.email ?: "",
                        onLogoutClick = { logOutConfirm = true },
                        onMyBookingClick = {
                            navController.navigate(AppScreen.StudentBookingDetail.name)
                        },
                        onFacilityBookingClick = {
                            navController.navigate(AppScreen.StudentBooking.name)
                        },
                        onFeedbackClick = {
                            navController.navigate(AppScreen.StudentBooking.name)
                        },
                        onSettingsClick = {
                            navController.navigate(AppScreen.StudentBooking.name)
                        }
                    )

                }
                composable(route = AppScreen.StudentBooking.name) {

                    FacilityBookScreen(
                        onCITCABClick = {
                            navController.navigate(AppScreen.CITCBooking.name)
                        },
                        onLibraryABClick = {
                            navController.navigate(AppScreen.LibraryBooking.name)
                        },
                        onSportsABClick = {
                            navController.navigate(AppScreen.SportsBooking.name)
                        },
                        onCITCTTClick = {
                            navController.navigate(AppScreen.CITCTimetable.name)
                        },
                        onLibraryTTClick = {
                            navController.navigate(AppScreen.LibraryTimetable.name)
                        },
                        onSportsTTClick = {
                            navController.navigate(AppScreen.SportsTimetable.name)
                        }
                    )
                }
                // Forgot Password Screen
                composable(route = AppScreen.ForgotPassword.name) {
                }

                // Admin Login Screen
                composable(route = AppScreen.AdminLoginScreen.name) {
                }

                // For Jayla
                // Admin Main Screen
                composable(route = AppScreen.AdminScreen.name) {
                }
                composable(route = AppScreen.AnnouncementDetail.name) {
                }
                composable(route = AppScreen.StudentBookingDetail.name) {
                }
                composable(route = AppScreen.StaffCheckBooking.name) {
                }
                composable(route = AppScreen.StaffApprove.name) {
                }
                composable(route = AppScreen.AdminAddFacility.name) {
                }
                composable(route = AppScreen.AdminManageUsers.name) {
                }

                // For Regina
                composable(route = AppScreen.CITCBooking.name) {
                }
                composable(route = AppScreen.LibraryBooking.name) {
                }
                composable(route = AppScreen.SportsBooking.name) {
                }
                composable(route = AppScreen.CITCTimetable.name) {
                }
                composable(route = AppScreen.LibraryTimetable.name) {
                }
                composable(route = AppScreen.SportsTimetable.name) {
                }

                // For Vicky
                composable(route = AppScreen.AdminViewReview.name) {
                }
                composable(route = AppScreen.AdminReviewDetail.name) {
                }
                composable(route = AppScreen.StudentViewReview.name) {
                }
                composable(route = AppScreen.StudentAddReview.name) {
                }
                composable(route = AppScreen.StaffViewReview.name) {
                }
                composable(route = AppScreen.StaffAddReview.name) {
                }

                // For Kaken
                composable(route = AppScreen.CheckInManual.name) {
                }
                composable(route = AppScreen.CheckInBarcode.name){
                }
                composable(route = AppScreen.CheckInSuccess.name) {
                }
                composable(route = AppScreen.CheckOutManual.name) {
                }
                composable(route = AppScreen.CheckOutBarcode.name) {
                }
                composable(route = AppScreen.CheckOutSuccess.name) {
                }
            }
        }

    }
}


