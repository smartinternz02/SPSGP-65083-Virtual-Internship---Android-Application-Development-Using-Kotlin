package com.example.groceryapp

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface GroceryDao {

    // Insert function is used to
    // insert data in database.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: GroceryItems)

    // Delete function is used to
    // delete data in database.
    @Delete
    suspend fun delete(item: GroceryItems)

    // getAllGroceryItems function is used to get
    // all the data of database.
    @Query("SELECT * FROM grocery_items")
    fun getAllGroceryItems(): LiveData<List<GroceryItems>>
}