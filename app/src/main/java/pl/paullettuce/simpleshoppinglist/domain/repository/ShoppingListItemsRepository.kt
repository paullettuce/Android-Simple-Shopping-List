package pl.paullettuce.simpleshoppinglist.domain.repository

import io.reactivex.rxjava3.core.Completable

interface ShoppingListItemsRepository {
    fun addShoppingListItem(shoppingListId: Long, name: String, quantity: Int): Completable
    fun markAsDone(id: Long): Completable
    fun unmarkAsDone(id: Long): Completable
}