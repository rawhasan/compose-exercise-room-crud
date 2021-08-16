package com.example.roomcrud.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.roomcrud.ItemViewModel

@Composable
fun DetailsScreen(
    itemId: String?,
    navController: NavController,
    itemViewModel: ItemViewModel,
    onSetAppTitle: (String) -> Unit,
    onShowFab: (Boolean) -> Unit,
) {
    // set the events only once during recompositions
    LaunchedEffect(Unit) {
        onSetAppTitle("Item Details")
        onShowFab(false)
        Log.d("DetailsScreen", "Title & FAB events set.")
    }

    val item = itemViewModel.getItem(itemId!!.toInt()).observeAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "${item.value?.itemName}",
            modifier = Modifier.padding(bottom = 8.dp),
            fontSize = 20.sp
        )
        Text("$ ${item.value?.itemPrice}", modifier = Modifier.padding(bottom = 8.dp))
        Text(
            "Quantity in Stock: ${item.value?.quantityInStock}",
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Button(
            onClick = {
                navController.navigate("home") {
                    popUpTo("home") { inclusive = true }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Filled.ShoppingCart,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(16.dp)
            )
            Text(text = "Sell", modifier = Modifier.padding(start = 8.dp))
        }

        Spacer(modifier = Modifier.weight(1f))
        Button(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth()) {
            Icon(
                imageVector = Icons.Filled.Edit,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(16.dp)

            )
            Text("Edit Item", modifier = Modifier.padding(start = 8.dp))
        }
        OutlinedButton(
            onClick = { /*TODO*/ }, modifier = Modifier
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