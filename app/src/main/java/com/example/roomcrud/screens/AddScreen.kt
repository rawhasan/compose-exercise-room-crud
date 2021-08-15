package com.example.roomcrud.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.roomcrud.ItemViewModel
import com.example.roomcrud.data.Item

@Composable
fun AddScreen(
    navController: NavController,
    itemViewModel: ItemViewModel,
    onSetAppTitle: (String) -> Unit,
    onShowFab: (Boolean) -> Unit
) {
    var itemName by remember { mutableStateOf("") }
    var itemPrice by remember { mutableStateOf("") }
    var itemQuantity by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        onSetAppTitle("Add Item")
        onShowFab(false)
    }

    Column(
        modifier = Modifier
            .padding(horizontal = 32.dp, vertical = 16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        OutlinedTextField(
            value = itemName,
            onValueChange = { itemName = it },
            label = { Text("Item name") },
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
        )

        OutlinedTextField(
            value = itemPrice,
            onValueChange = { itemPrice = it },
            label = { Text("Item price") },
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
        )

        OutlinedTextField(
            value = itemQuantity,
            onValueChange = { itemQuantity = it },
            label = { Text("Item quantity") },
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
        )

        Button(
            onClick = {
                if (itemViewModel.isItemValid(itemName, itemPrice, itemQuantity)) {
                    itemViewModel.addItem(
                        Item(
                            0,
                            itemName.trim(),
                            itemPrice.trim().toDouble(),
                            itemQuantity.trim().toInt()
                        )
                    )
                    navController.navigate("home") {
                        popUpTo("home") { inclusive = true }
                    }
                }
            }, modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Add item")
        }
    }
}