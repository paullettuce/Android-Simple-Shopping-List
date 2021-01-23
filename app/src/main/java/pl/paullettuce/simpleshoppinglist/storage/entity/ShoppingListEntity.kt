package pl.paullettuce.simpleshoppinglist.storage.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopping_list_entity")
data class ShoppingListEntity(
    val name: String,
    val creationTimestamp: Long,
    var isActive: Boolean = true
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}
