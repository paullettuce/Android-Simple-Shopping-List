package pl.paullettuce.simpleshoppinglist.domain.repository

import androidx.lifecycle.LiveData
import io.reactivex.rxjava3.core.Completable
import pl.paullettuce.simpleshoppinglist.domain.model.ShoppingListDetails
import pl.paullettuce.simpleshoppinglist.domain.model.ShoppingListDetailsWithItems

interface ShoppingListsRepository {
    fun createShoppingList(name: String): Completable
    fun getShoppingLists(active: Boolean): LiveData<List<ShoppingListDetails>>
    fun getShoppingListWithItemsDetails(id: Long): LiveData<ShoppingListDetailsWithItems>
}