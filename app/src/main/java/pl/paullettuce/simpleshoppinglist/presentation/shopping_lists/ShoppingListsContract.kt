package pl.paullettuce.simpleshoppinglist.presentation.shopping_lists

import androidx.lifecycle.LiveData
import pl.paullettuce.simpleshoppinglist.domain.model.ShoppingListDetails

interface ShoppingListsContract {

    interface View {

    }

    interface Presenter {
        val shoppingLists: LiveData<List<ShoppingListDetails>>

        fun setFetchActiveLists(shouldFetchActive: Boolean)
        fun createShoppingList(name: String)
        fun archiveList(listDetails: ShoppingListDetails)
    }

    interface ListInteraction {
        fun archive(item: ShoppingListDetails)
        fun onClick(item: ShoppingListDetails)
    }

    interface FABInteraction {
        fun onAddFABClick()
    }
}