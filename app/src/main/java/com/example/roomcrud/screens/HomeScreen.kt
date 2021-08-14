package com.example.roomcrud.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.roomcrud.R

@Composable
fun HomeScreen(navController: NavController, appTitle: (String) -> Unit) {
    appTitle(stringResource(id = R.string.app_name))

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Inventory", modifier = Modifier.padding(bottom = 16.dp))

        Button(onClick = { navController.navigate("details") }) {
            Text(text = "Item Details")
        }
    }
}