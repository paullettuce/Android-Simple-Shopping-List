package pl.paullettuce.simpleshoppinglist.storage.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopping_list_item_entity")
data class ShoppingListItemEntity(
    @ColumnInfo(name = "shopping_list_id") val shoppingListId: Long,
    val name: String,
    val quantity: Int,
    val creationTimestamp: Long,
    val isDone: Boolean = false,
    var isListActive: Boolean = true
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}