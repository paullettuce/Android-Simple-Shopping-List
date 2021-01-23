package pl.paullettuce.simpleshoppinglist.storage.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ShoppingListEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val creationTimestamp: Long,
    var isArchived: Boolean
)