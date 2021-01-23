package pl.paullettuce.simpleshoppinglist.presentation.diff_callbacks

import androidx.recyclerview.widget.DiffUtil
import pl.paullettuce.simpleshoppinglist.storage.entity.ShoppingListItemEntity

class ShoppingListItemDiffCallback: DiffUtil.ItemCallback<ShoppingListItemEntity>() {
    override fun areItemsTheSame(
        oldItem: ShoppingListItemEntity,
        newItem: ShoppingListItemEntity
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: ShoppingListItemEntity,
        newItem: ShoppingListItemEntity
    ): Boolean {
        return oldItem == newItem
    }
}