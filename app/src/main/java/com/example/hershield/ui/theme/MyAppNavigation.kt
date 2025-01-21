package com.example.hershield.ui.theme

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hershield.Pages.HomePage
import com.example.hershield.Pages.LoginPage
import com.example.hershield.Pages.SafetyTipsScreen
import com.example.hershield.Pages.SignupPage

import com.example.hershield.navigation.NavGraph

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun MyAppNavigation(modifier: Modifier = Modifier, authViewModel: AuthViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login", builder = {
        composable("login") {
            LoginPage(modifier, navController, authViewModel)
        }
        composable("signup") {
            SignupPage(modifier, navController, authViewModel)
        }
        composable("home") {
            HomePage(modifier, navController, authViewModel)
        }
        composable("videoCall") {
            // Navigate to the video call screen represented by NavGraph
            NavGraph()  // Replace with your video call composable
        }
        composable("safetyTipsPage") {
            // Your Safety Tips Page
          SafetyTipsScreen()
        }
    })
}
