package com.example.hershield.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.Modifier
import com.example.hershield.Video.VideoCallScreen
import com.example.hershield.Video.VideoCallViewModel
import com.example.hershield.Video.CallState
import com.example.hershield.connect.ConnectScreen
import com.example.hershield.connect.ConnectViewModel
import io.getstream.video.android.compose.theme.VideoTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun NavGraph() {
    // Define routes as constants within the NavGraph
    val ConnectRoute = "connect"
    val VideoCallRoute = "video_call"

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = ConnectRoute, // Use the route string directly
    ) {
        composable(route = ConnectRoute) { // Use the route string directly
            val viewModel = koinViewModel<ConnectViewModel>()
            val state = viewModel.state

            LaunchedEffect(key1 = state.isConnected) {
                if (state.isConnected) {
                    navController.navigate(VideoCallRoute) { // Use the route string directly
                        popUpTo(ConnectRoute) {
                            inclusive = true
                        }
                    }
                }
            }

            ConnectScreen(state = state, onAction = viewModel::onAction)
        }
        composable(route = VideoCallRoute) { // Use the route string directly
            val viewModel = koinViewModel<VideoCallViewModel>()
            val state = viewModel.state

            LaunchedEffect(key1 = state.callState) {
                if (state.callState == CallState.ENDED) {
                    navController.navigate(ConnectRoute) { // Use the route string directly
                        popUpTo(VideoCallRoute) {
                            inclusive = true
                        }
                    }
                }
            }

            VideoTheme {
                VideoCallScreen(state = state, onAction = viewModel::onAction)
            }
        }
    }
}
