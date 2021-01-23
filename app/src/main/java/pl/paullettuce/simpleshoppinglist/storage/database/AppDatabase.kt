package pl.paullettuce.simpleshoppinglist.storage.database

import androidx.room.Database
import androidx.room.RoomDatabase
import pl.paullettuce.simpleshoppinglist.storage.dao.ShoppingListsDao
import pl.paullettuce.simpleshoppinglist.storage.entity.ShoppingListEntity
import pl.paullettuce.simpleshoppinglist.storage.entity.ShoppingListItemEntity

@Database(entities = [ShoppingListEntity::class, ShoppingListItemEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun shoppingListsDao(): ShoppingListsDao
}