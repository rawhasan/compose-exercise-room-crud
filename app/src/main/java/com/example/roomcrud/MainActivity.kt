package com.example.roomcrud

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.roomcrud.screens.DetailsScreen
import com.example.roomcrud.screens.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RoomCrudApp()
        }
    }
}

@Composable
fun RoomCrudApp() {
    val navController = rememberNavController()
    var canPop by remember { mutableStateOf(false) }

    var appTitle by remember { mutableStateOf("") }

    navController.addOnDestinationChangedListener { controller, _, _ ->
        canPop = controller.previousBackStackEntry != null
    }

    val navigationIcon: (@Composable () -> Unit)? =
        if (canPop) {
            {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
                }
            }
        } else {
            null
        }

    Scaffold(
        topBar = { TopAppBar(title = { Text(appTitle) }, navigationIcon = navigationIcon) },
        content = {
            NavHost(navController = navController, startDestination = "home") {
                composable("home") { HomeScreen(navController, appTitle = { appTitle = it }) }
                composable("details") { DetailsScreen(navController, appTitle = { appTitle = it }) }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RoomCrudApp()
}