package com.example.roomcrud.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.roomcrud.ItemViewModel
import com.example.roomcrud.data.Item

@Composable
fun EditScreen(
    itemId: String?,
    navController: NavController,
    itemViewModel: ItemViewModel,
    onSetAppTitle: (String) -> Unit,
    onShowFab: (Boolean) -> Unit
) {
    LaunchedEffect(Unit) {
        onSetAppTitle("Edit Item")
        onShowFab(false)
    }

    Log.d("EditScreen", "Item id: $itemId")

    val receivedItem by itemViewModel.getItem(itemId!!.toInt()).observeAsState()
    val item = receivedItem ?: Item(0, "", 0.0, 0)

    Log.d("EditScreen", "Editing item: $item")

//    var itemName by remember { mutableStateOf("") }
//    var itemPrice by remember { mutableStateOf("") }
//    var itemQuantity by remember { mutableStateOf("") }

    var itemName by remember { mutableStateOf(item.itemName) }
    var itemPrice by remember { mutableStateOf(item.itemPrice.toString()) }
    var itemQuantity by remember { mutableStateOf(item.quantityInStock.toString()) }

//    itemName = item.itemName
//    itemPrice = item.itemPrice.toString()
//    itemQuantity = item.quantityInStock.toString()

    Log.d("EditScreen", "Editing values: $itemName $itemPrice $itemQuantity")

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
                    itemViewModel.updateItem(
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
            Text(text = "Save")
        }
    }
}