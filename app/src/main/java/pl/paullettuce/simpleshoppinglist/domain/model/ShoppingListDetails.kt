package pl.paullettuce.simpleshoppinglist.domain.model

import androidx.recyclerview.widget.DiffUtil
import pl.paullettuce.simpleshoppinglist.storage.entity.ShoppingListItemEntity

data class ShoppingListDetails(
    val id: Long,
    val name: String,
    val creationDate: String,
    val items: List<ShoppingListItemEntity> = emptyList()
)

class ShoppingListDetailsDiffCallback: DiffUtil.ItemCallback<ShoppingListDetails>() {
    override fun areItemsTheSame(
        oldItem: ShoppingListDetails,
        newItem: ShoppingListDetails
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun areContentsTheSame(
        oldItem: ShoppingListDetails,
        newItem: ShoppingListDetails
    ): Boolean {
        TODO("Not yet implemented")
    }
}