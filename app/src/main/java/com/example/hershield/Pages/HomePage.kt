package com.example.hershield.Pages

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hershield.ui.theme.AuthState
import com.example.hershield.ui.theme.AuthViewModel
import com.example.hershield.ui.theme.VibrationButton

// HomePage composable
@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun HomePage(modifier: Modifier = Modifier, navController: NavController, authViewModel: AuthViewModel) {

    val authState = authViewModel.authState.observeAsState()

    LaunchedEffect(authState.value) {
        when (authState.value) {
            is AuthState.Unauthenticated -> navController.navigate("login")
            else -> Unit
        }
    }

    Scaffold(
        topBar = {
            CenterTopBar() // Add custom top bar
        },
        modifier = modifier.fillMaxSize()
    ) { paddingValues ->

        // Main content goes here
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Vibrating Button (if needed)
            VibrationButton()

            Spacer(modifier = Modifier.height(30.dp))

            // Start Video Call Button
            StartVideoCallButton(navController = navController)

            Spacer(modifier = Modifier.height(20.dp))




            Spacer(modifier = Modifier.height(20.dp))

            // Safety Tips Button
            SafetyTipsButton(navController = navController)

            Spacer(modifier = Modifier.height(40.dp))

            // Sign Out Button at the bottom
            SignOutButton(authViewModel = authViewModel)
        }
    }
}

// Centered TopBar with pink background and white font color
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CenterTopBar() {
    TopAppBar(
        title = {
            Text(
                text = "Her-shield",
                color = Color.White,
                fontSize = 42.sp, // Increased font size
                fontWeight = FontWeight.Bold, // Set font to bold
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontStyle = FontStyle.Italic// Center align the text
            )
        },
        modifier = Modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color(0xFFEC4899) // Pink shade
        )
    )
}

// StartVideoCallButton composable
@Composable
fun StartVideoCallButton(navController: NavController) {
    Button(
        onClick = {
            // Navigate to the video call screen
            navController.navigate("videoCall")
        },
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .height(50.dp), // Adjust height as needed
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF472B6)) // Another shade of pink
    ) {
        Text(
            text = "Start Video Call",
            fontSize = 18.sp,
            color = Color.White
        )
    }
}




// SafetyTipsButton composable
@Composable
fun SafetyTipsButton(navController: NavController) {
    Button(
        onClick = {
            // Navigate to the Safety Tips Page
            navController.navigate("safetyTipsPage")
        },
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .height(50.dp), // Adjust height as needed
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF472B6)) // Another shade of pink
    ) {
        Text(
            text = "Safety Tips",
            fontSize = 18.sp,
            color = Color.White
        )
    }
}

// SignOutButton composable
@Composable
fun SignOutButton(authViewModel: AuthViewModel) {
    TextButton(onClick = {
        authViewModel.signout()
    }) {
        Text(
            text = "Sign out",
            color = Color(0xFFEC4899), // Pink color
            fontSize = 16.sp
        )
    }
}
