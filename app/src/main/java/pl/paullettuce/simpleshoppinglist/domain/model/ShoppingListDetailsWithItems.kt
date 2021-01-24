package pl.paullettuce.simpleshoppinglist.domain.model

import androidx.room.Embedded
import androidx.room.Relation
import pl.paullettuce.simpleshoppinglist.storage.entity.ShoppingListEntity
import pl.paullettuce.simpleshoppinglist.storage.entity.ShoppingListItemEntity

data class ShoppingListDetailsWithItems(
    @Embedded val details: ShoppingListEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "shopping_list_id"
    )
    var items: List<ShoppingListItemEntity>
) {
    init {
        items = items.sortedByDescending { it.creationTimestamp }
    }
}