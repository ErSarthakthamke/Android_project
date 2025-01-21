package com.example.hershield.ui.theme

import android.annotation.SuppressLint
import android.content.Context
import android.media.MediaPlayer
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hershield.R

@RequiresApi(Build.VERSION_CODES.S)
@SuppressLint("RememberReturnType")
@Composable
fun VibrationButton() {
    val context = LocalContext.current
    var mediaPlayer by remember { mutableStateOf<MediaPlayer?>(null) }
    var isPlaying by remember { mutableStateOf(false) } // Track if the song is playing

    // Ensure MediaPlayer and Vibrator are released properly
    DisposableEffect(Unit) {
        onDispose {
            mediaPlayer?.release()
            stopVibration(context)
        }
    }

    Button(onClick = {
        if (isPlaying) {
            // Stop the song and stop vibration
            mediaPlayer?.stop()
            mediaPlayer?.release()
            mediaPlayer = null
            stopVibration(context)
            isPlaying = false // Update state
        } else {
            // Start the song and start vibration
            mediaPlayer = mediaPlayer ?: MediaPlayer.create(context, R.raw.sample_song) // Adjust song resource if needed
            mediaPlayer?.start()
            startVibration(context)
            mediaPlayer?.setOnCompletionListener {
                stopVibration(context) // Stop vibration when the song ends
                isPlaying = false // Update state
            }
            isPlaying = true // Update state
        }
    },
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .height(50.dp), // Adjust height as needed
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF472B6))) {
        Text(text = if (isPlaying) "End Fake Call" else "Fake Call", fontSize = 18.sp) // Button text changes based on state
    }
}

@RequiresApi(Build.VERSION_CODES.S)
fun startVibration(context: Context) {
    val vibratorManager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as? VibratorManager
    val vibrator = vibratorManager?.defaultVibrator ?: (context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator)

    if (vibrator != null) {
        val pattern = longArrayOf(0, 500, 500) // Vibrate for 500ms, pause for 500ms, repeat
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createWaveform(pattern, 0)) // Loop vibration
        } else {
            @Suppress("DEPRECATION")
            vibrator.vibrate(pattern, 0) // Legacy method for older devices
        }
    }
}

@RequiresApi(Build.VERSION_CODES.S)
fun stopVibration(context: Context) {
    val vibratorManager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as? VibratorManager
    val vibrator = vibratorManager?.defaultVibrator ?: (context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator)

    vibrator?.cancel() // Stop the vibration
}
