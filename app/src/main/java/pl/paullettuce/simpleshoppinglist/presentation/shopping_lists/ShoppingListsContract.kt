package pl.paullettuce.simpleshoppinglist.presentation.shopping_lists

import androidx.lifecycle.LiveData
import pl.paullettuce.simpleshoppinglist.domain.model.ShoppingListDetails

interface ShoppingListsContract {

    interface View {

    }

    interface Presenter {
        val shoppingLists: LiveData<List<ShoppingListDetails>>

        fun setShouldFetchActiveLists(shouldFetchActive: Boolean)
        fun saveScrollPosition(position: Int)
        fun createShoppingList(name: String)
    }

    interface ListInteraction {
        fun archive(item: ShoppingListDetails)
        fun unarchive(item: ShoppingListDetails)
        fun onClick(item: ShoppingListDetails)
    }
}