package pl.paullettuce.simpleshoppinglist.storage.entity

import androidx.room.PrimaryKey

data class ShoppingListItemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val shoppingListId: Long,
    val name: String,
    var isDone: Boolean
)