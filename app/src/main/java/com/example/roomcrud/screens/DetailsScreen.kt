package com.example.roomcrud.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun DetailsScreen(navController: NavController, appTitle: (String) -> Unit) {
    appTitle("Item Details")

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Item Details", modifier = Modifier.padding(bottom = 16.dp))

        Button(onClick = {
            navController.navigate("home") {
                popUpTo("home") { inclusive = true }
            }
        }) {
            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
            Text(text = "Back to Inventory", modifier = Modifier.padding(start = 8.dp))
        }
    }
}