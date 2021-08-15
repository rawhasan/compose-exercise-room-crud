package com.example.roomcrud

import android.app.Application
import com.example.roomcrud.data.ItemRoomDatabase

class ItemApplication : Application() {
    private val database by lazy { ItemRoomDatabase.getDatabase(this) }
    val repository by lazy { ItemRepository(database.itemDao()) }
}