package com.example.littlelemon.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase

@Entity(tableName = "menu_item")
data class MenuItem(
    @PrimaryKey
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val image: String, val category: String
)


@Dao
interface MenuDao {
    @Insert()
    fun saveMenuItem(vararg menuItem: MenuItem)

    @Query("SELECT * FROM menu_item")
    fun getAllMenuItems(): LiveData<List<MenuItem>>

    @Query("SELECT (SELECT COUNT(*) FROM menu_item) == 0 ")
    fun isMenuEmpty(): Boolean
}

@Database(entities = [MenuItem::class], version = 1)
abstract class MenuDatabase : RoomDatabase() {
    abstract fun menuDao(): MenuDao
}