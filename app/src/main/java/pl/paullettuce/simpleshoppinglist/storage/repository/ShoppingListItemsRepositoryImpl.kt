package pl.paullettuce.simpleshoppinglist.storage.repository

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers
import pl.paullettuce.simpleshoppinglist.domain.repository.ShoppingListItemsRepository
import pl.paullettuce.simpleshoppinglist.storage.dao.ShoppingListItemsDao
import pl.paullettuce.simpleshoppinglist.storage.entity.ShoppingListItemEntity

class ShoppingListItemsRepositoryImpl(
    private val shoppingListItemsDao: ShoppingListItemsDao
) : ShoppingListItemsRepository {
    override fun addShoppingListItem(
        shoppingListId: Long,
        name: String,
        quantity: Int
    ): Completable {
        val item = ShoppingListItemEntity(
            shoppingListId, name, quantity, System.currentTimeMillis()
        )
        return shoppingListItemsDao.insert(item)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun markAsDone(id: Long): Completable {
        return shoppingListItemsDao.markAsDone(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun unmarkAsDone(id: Long): Completable {
        return shoppingListItemsDao.unmarkAsDone(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}