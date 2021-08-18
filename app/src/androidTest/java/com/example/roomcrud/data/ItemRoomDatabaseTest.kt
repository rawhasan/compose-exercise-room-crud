package com.example.roomcrud.data

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class ItemRoomDatabaseTest {

    private lateinit var itemDao: ItemDao
    private lateinit var db: ItemRoomDatabase

    @Before
    fun createDb() {
        //val context = InstrumentationRegistry.getInstrumentation().targetContext
        val context = ApplicationProvider.getApplicationContext<Context>()

        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, ItemRoomDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()

        itemDao = db.itemDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @get:Rule val instantExecutorRule = InstantTaskExecutorRule()
    @ExperimentalCoroutinesApi
    @Test
    @Throws(Exception::class)
    fun insertAndGetItem() = runBlockingTest {
        val boira = Item(0, "Boira", 0.35, 36)
        val apple = Item(0, "Apple", 0.60, 50)
        val mango = Item(0, "Mango", 2.10, 43)

        itemDao.insert(boira)
        itemDao.insert(apple)
        itemDao.insert(mango)

        val receivedItem = itemDao.getAllItems().asLiveData()

        assertThat(receivedItem.value?.size, equalTo(3))
    }
}