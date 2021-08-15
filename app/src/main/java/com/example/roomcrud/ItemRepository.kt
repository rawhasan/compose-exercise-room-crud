package com.example.roomcrud

import androidx.annotation.WorkerThread
import com.example.roomcrud.data.Item
import com.example.roomcrud.data.ItemDao
import kotlinx.coroutines.flow.Flow

class ItemRepository(private val itemDao: ItemDao) {
    val allItems: Flow<List<Item>> = itemDao.getAllItems()

    fun getItem(id: Int): Flow<Item> {
        return itemDao.getItem(id)
    }

    @Suppress("RedundantSuppressModifier")
    @WorkerThread
    suspend fun insertItem(item: Item) {
        itemDao.insert(item)
    }

    @Suppress("RedundantSuppressModifier")
    @WorkerThread
    suspend fun addItem(item: Item) {
        itemDao.insert(item)
    }

    @Suppress("RedundantSuppressModifier")
    @WorkerThread
    suspend fun updateItem(item: Item) {
        itemDao.update(item)


    }

    @Suppress("RedundantSuppressModifier")
    @WorkerThread
    suspend fun deleteItem(item: Item) {
        itemDao.delete(item)
    }
}