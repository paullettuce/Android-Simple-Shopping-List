package pl.paullettuce.simpleshoppinglist.storage.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ShoppingListItemEntity(
    val shoppingListId: Long,
    val name: String,
    var isDone: Boolean
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}
