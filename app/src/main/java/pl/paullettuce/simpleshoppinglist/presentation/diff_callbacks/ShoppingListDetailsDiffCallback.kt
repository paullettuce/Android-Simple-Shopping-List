package pl.paullettuce.simpleshoppinglist.presentation.diff_callbacks

import androidx.recyclerview.widget.DiffUtil
import pl.paullettuce.simpleshoppinglist.domain.model.ShoppingListDetails

class ShoppingListDetailsDiffCallback: DiffUtil.ItemCallback<ShoppingListDetails>() {
    override fun areItemsTheSame(
        oldItem: ShoppingListDetails,
        newItem: ShoppingListDetails
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: ShoppingListDetails,
        newItem: ShoppingListDetails
    ): Boolean {
        return oldItem == newItem
    }
}