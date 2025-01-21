package com.example.hershield.Pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SafetyTipsScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Safety Tips for Being Aware and Secure",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.White // Setting the font color to white
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color.Black // Setting the background color to black
                ),
                modifier = Modifier.fillMaxWidth()
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(bottom = 16.dp) // Add padding at the bottom
                ) {
                    items(tipsList) { tip ->
                        SafetyTipCard(tip)
                    }
                }
            }
        }
    )
}

@Composable
fun SafetyTipCard(tip: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp), // Increased space between cards
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = tip,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

val tipsList = listOf(
    "Always let someone know your whereabouts and expected arrival time.",
    "Trust your instincts. If something doesn’t feel right, remove yourself from the situation.",
    "Avoid using your phone or wearing headphones in unfamiliar or dark areas.",
    "Stick to well-lit, populated paths when walking alone at night.",
    "Keep your phone fully charged and carry a portable charger.",
    "If you're in danger, make noise or create a scene to attract attention.",
    "Consider using a personal safety app to send your location to loved ones.",
    "Always be cautious when accepting rides from strangers or using ride-sharing apps.",
    "Be aware of your surroundings and avoid distractions like texting or browsing.",
    "Keep your valuables out of sight and be cautious of pickpockets."
)

@Preview(showBackground = true)
@Composable
fun PreviewSafetyTipsScreen() {
    SafetyTipsScreen()
}