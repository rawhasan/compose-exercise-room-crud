package com.example.roomcrud

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.roomcrud.ui.theme.RoomCRUDTheme

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
    Text("Room Crud")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RoomCrudApp()
}