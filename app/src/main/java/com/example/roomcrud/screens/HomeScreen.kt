package com.example.roomcrud.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.roomcrud.ItemViewModel
import com.example.roomcrud.R
import com.example.roomcrud.data.Item

@Composable
fun HomeScreen(
    navController: NavController,
    itemViewModel: ItemViewModel,
    onSetAppTitle: (String) -> Unit,
    onShowFab: (Boolean) -> Unit
) {
    val appTitle = stringResource(id = R.string.app_name)
    val items: List<Item> by itemViewModel.allItems.observeAsState(listOf())

    // set the events only once during recompositions
    LaunchedEffect(Unit) {
        onSetAppTitle(appTitle)
        onShowFab(true)
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        LazyColumn(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            item { ItemHeaderLayout() }

            items(items) { item ->
                ItemLayout(item, navController)
            }
        }
    }
}

// Header inside the LazyColumn
@Composable
fun ItemHeaderLayout() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.primaryVariant)
            .padding(vertical = 8.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            "Items",
            modifier = Modifier.weight(1f),
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        Text(
            "Price",
            modifier = Modifier
                .width(100.dp)
                .padding(horizontal = 16.dp),
            textAlign = TextAlign.End,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        Text(
            "Quantity in Stock",
            modifier = Modifier.width(60.dp),
            textAlign = TextAlign.End,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}

// Layout of a single item row in the LazyColumn
@Composable
fun ItemLayout(item: Item, navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.primaryVariant)
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .clickable { navController.navigate("details/${item.id}") }

    ) {
        Text(item.itemName, modifier = Modifier.weight(1f), color = Color.White)
        Text(
            "$ ${item.itemPrice}",
            modifier = Modifier
                .width(100.dp)
                .padding(horizontal = 16.dp),
            textAlign = TextAlign.End,
            color = Color.White
        )
        Text(
            item.quantityInStock.toString(),
            modifier = Modifier.width(60.dp),
            textAlign = TextAlign.End,
            color = Color.White
        )
    }
}
