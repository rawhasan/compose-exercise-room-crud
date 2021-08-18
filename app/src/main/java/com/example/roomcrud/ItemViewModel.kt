package com.example.roomcrud

import androidx.lifecycle.*
import com.example.roomcrud.data.Item
import kotlinx.coroutines.launch

class ItemViewModel(private val repository: ItemRepository) : ViewModel() {
    val allItems: LiveData<List<Item>> = repository.allItems.asLiveData()

    fun getItem(id: Int): LiveData<Item> = repository.getItem(id).asLiveData()

    fun addItem(item: Item) = viewModelScope.launch {
        repository.insertItem(item)
    }

    fun updateItem(item: Item) = viewModelScope.launch {
        repository.updateItem(item)
    }

    fun deleteItem(item: Item) = viewModelScope.launch {
        repository.deleteItem(item)
    }

    // check input validity before adding or editing an item
    fun isItemValid(itemName: String, itemPrice: String, itemQuantityInStock: String): Boolean {
        if (itemName.trim().isBlank()
            || itemPrice.trim().isBlank() || (itemPrice.trim().toDoubleOrNull() ?: 0.0) <= 0.0
            || itemQuantityInStock.trim().isBlank() || (itemQuantityInStock.trim().toIntOrNull()
                ?: 0) <= 0
        ) {
            return false
        }
        return true
    }
}

class ItemViewModelFactory(private val repository: ItemRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ItemViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ItemViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}