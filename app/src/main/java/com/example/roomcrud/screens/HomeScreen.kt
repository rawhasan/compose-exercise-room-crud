package com.example.roomcrud.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.roomcrud.R

@Composable
fun HomeScreen(
    navController: NavController,
    onSetAppTitle: (String) -> Unit,
    onShowFab: (Boolean) -> Unit
) {
    val appTitle = stringResource(id = R.string.app_name)

    // set the events only once during recompositions
    LaunchedEffect(Unit) {
        onSetAppTitle(appTitle)
        onShowFab(true)
        Log.d("HomeScreen", "Title & FAB events set.")
    }

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