package pl.paullettuce.simpleshoppinglist.presentation.shopping_list_details

import androidx.lifecycle.LiveData
import pl.paullettuce.simpleshoppinglist.domain.model.ShoppingListDetails
import pl.paullettuce.simpleshoppinglist.domain.model.ShoppingListDetailsWithItems
import pl.paullettuce.simpleshoppinglist.storage.entity.ShoppingListItemEntity

interface ShoppingListDetailsContract {
    interface View {

    }

    interface Presenter {
        val shoppingListDetailsLiveData: LiveData<ShoppingListDetailsWithItems>

        fun setShoppingListId(id: Long)
        fun addShoppingListItem(name: String, quantity: Int)
        fun markAsDone(listItemEntity: ShoppingListItemEntity)
        fun unmarkAsDone(listItemEntity: ShoppingListItemEntity)
        fun delete(item: ShoppingListItemEntity)
    }

    interface ListInteraction {
        fun markAsDone(listItemEntity: ShoppingListItemEntity)
        fun unmarkAsDone(listItemEntity: ShoppingListItemEntity)
        fun delete(item: ShoppingListItemEntity)
    }
}