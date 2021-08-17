package com.example.roomcrud

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.roomcrud.data.Item
import com.example.roomcrud.screens.AddScreen
import com.example.roomcrud.screens.DetailsScreen
import com.example.roomcrud.screens.EditScreen
import com.example.roomcrud.screens.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val itemViewModel by viewModels<ItemViewModel> {
            ItemViewModelFactory((application as ItemApplication).repository)
        }

        setContent {
            RoomCrudApp(
                itemViewModel,
                onAddItem = { itemViewModel.addItem(it) },
                onEditItem = { itemViewModel.updateItem(it) },
                onDeleteItem = { itemViewModel.deleteItem(it) })
        }
    }
}

/***************
 * App Entrance
 ***************/
@Composable
fun RoomCrudApp(
    itemViewModel: ItemViewModel,
    onAddItem: (Item) -> Unit,
    onEditItem: (Item) -> Unit,
    onDeleteItem: (Item) -> Unit
) {
    val navController = rememberNavController()
    var canPop by remember { mutableStateOf(false) }

    var appTitle by remember { mutableStateOf("") }
    var showFab by remember { mutableStateOf(false) }

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
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            if (showFab) { // display FAB based on the even from screens
                FloatingActionButton(onClick = { navController.navigate("add") }) {
                    Icon(imageVector = Icons.Filled.Add, contentDescription = "Add Item")
                }
            }
        },
        content = {
            NavHost(navController = navController, startDestination = "home") {
                composable("home") {
                    HomeScreen(
                        navController,
                        itemViewModel,
                        onSetAppTitle = { appTitle = it },
                        onShowFab = { showFab = it }
                    )
                }
                composable("details/{itemId}") { backStackEntry ->
                    DetailsScreen(
                        backStackEntry.arguments?.getString("itemId"),
                        navController,
                        itemViewModel,
                        onSetAppTitle = { appTitle = it },
                        onShowFab = { showFab = it },
                        onItemDeleted = { onDeleteItem(it) }
                    )
                }
                composable("add") {
                    AddScreen(
                        navController,
                        itemViewModel,
                        onSetAppTitle = { appTitle = it },
                        onShowFab = { showFab = it },
                        onItemAdded = { onAddItem(it) }
                    )
                }
                composable("edit/{itemId}") { backStackEntry ->
                    EditScreen(
                        backStackEntry.arguments?.getString("itemId"),
                        navController,
                        itemViewModel,
                        onSetAppTitle = { appTitle = it },
                        onShowFab = { showFab = it },
                        onItemEdited = { onEditItem(it) }
                    )
                }
            }
        }
    )
}