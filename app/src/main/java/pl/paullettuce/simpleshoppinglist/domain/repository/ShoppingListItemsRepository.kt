package pl.paullettuce.simpleshoppinglist.domain.repository

import io.reactivex.rxjava3.core.Completable
import pl.paullettuce.simpleshoppinglist.storage.entity.ShoppingListItemEntity

interface ShoppingListItemsRepository {
    fun addShoppingListItem(shoppingListId: Long, name: String, quantity: Int): Completable
    fun markAsDone(id: Long): Completable
    fun unmarkAsDone(id: Long): Completable
    fun delete(item: ShoppingListItemEntity): Completable
}