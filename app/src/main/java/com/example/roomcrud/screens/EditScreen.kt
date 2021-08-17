package com.example.roomcrud.screens

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
    onShowFab: (Boolean) -> Unit,
    onItemEdited: (Item) -> Unit,
) {
    LaunchedEffect(Unit) {
        onSetAppTitle("Edit Item")
        onShowFab(false)
    }

    // gets an empty object initially, and gets the actual data after several recompositions
    val receivedItem: Item by itemViewModel.getItem(itemId!!.toInt())
        .observeAsState(Item(0, "", 0.0, 0))

    // initially remembering the empty data
    var itemName by remember { mutableStateOf(receivedItem.itemName) }
    var itemPrice by remember { mutableStateOf(receivedItem.itemPrice.toString()) }
    var itemQuantity by remember { mutableStateOf(receivedItem.quantityInStock.toString()) }

    // updating the values just once when the actual data arrives from the viewModel
    // so that they are not overwritten over and over again
    // and disables editing the TextFields.
    if (receivedItem.id != 0) {
        LaunchedEffect(Unit) {
            itemName = receivedItem.itemName
            itemPrice = receivedItem.itemPrice.toString()
            itemQuantity = receivedItem.quantityInStock.toString()
        }
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

        // Save (Edits) Button
        Button(
            onClick = {
                if (itemViewModel.isItemValid(itemName, itemPrice, itemQuantity)) {
                    val updatedItem = receivedItem.copy(
                        itemName = itemName.trim(),
                        itemPrice = itemPrice.trim().toDouble(),
                        quantityInStock = itemQuantity.trim().toInt()
                    )

                    // raise event to save the edits by the caller function
                    onItemEdited(updatedItem)
                    //itemViewModel.updateItem(updatedItem)

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