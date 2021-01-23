package pl.paullettuce.simpleshoppinglist.domain.repository

import androidx.lifecycle.LiveData
import io.reactivex.rxjava3.core.Completable
import pl.paullettuce.simpleshoppinglist.domain.model.ShoppingListDetails

interface ShoppingListsRepository {
    fun getShoppingLists(active: Boolean): LiveData<List<ShoppingListDetails>>
    fun createShoppingList(name: String): Completable
}