package pl.paullettuce.simpleshoppinglist.storage.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopping_list_entity")
data class ShoppingListEntity(
    val name: String,
    @ColumnInfo(name= "creation_timestamp") val creationTimestamp: Long,
    @ColumnInfo(name= "is_active") var isActive: Boolean = true
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}
