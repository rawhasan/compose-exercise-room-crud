package com.example.roomcrud.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.roomcrud.ItemViewModel
import com.example.roomcrud.data.Item

@Composable
fun DetailsScreen(
    itemId: String?,
    navController: NavController,
    itemViewModel: ItemViewModel,
    onSetAppTitle: (String) -> Unit,
    onShowFab: (Boolean) -> Unit,
    onItemDeleted: (Item) -> Unit
) {
    // set the events only once during recompositions
    LaunchedEffect(Unit) {
        onSetAppTitle("Item Details")
        onShowFab(false)
    }

    val context = LocalContext.current

    val receivedItem = itemViewModel.getItem(itemId!!.toInt()).observeAsState()
    val item = receivedItem.value ?: Item(0, "", 0.0, 0)

    var showSellForm by remember { mutableStateOf(false) }
    var enteredQty by remember { mutableStateOf("") }
    var sellErrorMessage by remember { mutableStateOf("") }

    val showDialog = remember { mutableStateOf(false) }
    val deleteConfirmed = remember { mutableStateOf(false) }

    if (showDialog.value) {
        ShowConfirmationDialog(
            title = "Delete Item?",
            text = "Are you sure you want to delete this item? You will not be able to reverse it.",
            onResponse = {
                deleteConfirmed.value = it
                showDialog.value = false
            }
        )
    }

    // Delete item only if confirmed by the user
    if (deleteConfirmed.value) {

        // raise event to delete the item by the caller function
        onItemDeleted(item)

        navController.navigate("home") {
            popUpTo("home") { inclusive = true }
        }

        // resetting the user confirmation from the alert dialog
        deleteConfirmed.value = false
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            item.itemName,
            modifier = Modifier.padding(bottom = 8.dp),
            fontSize = 20.sp
        )
        Text("$ ${item.itemPrice}", modifier = Modifier.padding(bottom = 8.dp))
        Text(
            "Quantity in Stock: ${item.quantityInStock}",
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Sell Button
        Button(
            onClick = { showSellForm = true },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.ShoppingCart,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(16.dp)
            )
            Text(text = "Sell", modifier = Modifier.padding(start = 8.dp))
        }

        // Sell form
        if (showSellForm) {
            Row(verticalAlignment = Alignment.Bottom) {
                TextField(
                    value = enteredQty,
                    onValueChange = { enteredQty = it },
                    label = { Text("Sell Quantity") },
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 16.dp),
                    colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White), // TextField background color
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number) // Numeric keyboard
                )

                // Save the sell button
                Button(onClick = {
                    val sellQty = enteredQty.trim().toIntOrNull()

                    if (sellQty != null && sellQty > 0 && sellQty <= item.quantityInStock) {
                        val sellItem = item.copy(quantityInStock = item.quantityInStock - sellQty)
                        itemViewModel.updateItem(sellItem)
                        showSellForm = false
                        enteredQty = ""
                        sellErrorMessage = ""
                        Toast.makeText(context, "Item(s) sold", Toast.LENGTH_SHORT).show()
                    } else if (sellQty != null && sellQty > item.quantityInStock) {
                        sellErrorMessage = "Not enough items in stock"
                    } else {
                        sellErrorMessage = "Enter a valid quantity to sell"
                    }
                }) {
                    Icon(
                        imageVector = Icons.Filled.Done,
                        contentDescription = "Sell Item",
                        tint = Color.White
                    )
                }
            }

        }

        // Show error message if any error on sell
        if (sellErrorMessage != "") {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Text(sellErrorMessage, color = MaterialTheme.colors.error)
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Edit Button
        Button(
            onClick = { navController.navigate("edit/${item.id}") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Filled.Edit,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(16.dp)

            )
            Text("Edit Item", modifier = Modifier.padding(start = 8.dp))
        }

        // Delete Button
        OutlinedButton(
            onClick = { showDialog.value = true },
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Filled.Delete,
                contentDescription = null,
                tint = MaterialTheme.colors.primary,
                modifier = Modifier.size(16.dp)
            )
            Text("Delete Item", modifier = Modifier.padding(start = 8.dp))
        }

    }
}

// Stateless function to show an alert dialog box (Yes/No)
@Composable
fun ShowConfirmationDialog(title: String, text: String, onResponse: (Boolean) -> Unit) {
    AlertDialog(
        onDismissRequest = { onResponse(false) },
        title = { Text(title) },
        text = { Text(text) },
        confirmButton = {
            TextButton(onClick = { onResponse(true) }) {
                Text("Yes")
            }
        },
        dismissButton = {
            TextButton(onClick = { onResponse(false) }) {
                Text("No")
            }
        }
    )
}