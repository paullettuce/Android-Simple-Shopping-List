package pl.paullettuce.simpleshoppinglist.storage.repository

import androidx.lifecycle.LiveData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers
import pl.paullettuce.simpleshoppinglist.domain.extensions.mapNotNull
import pl.paullettuce.simpleshoppinglist.domain.mapper.ShoppingListEntityToDetailsListMapper
import pl.paullettuce.simpleshoppinglist.domain.model.ShoppingListDetails
import pl.paullettuce.simpleshoppinglist.domain.repository.ShoppingListsRepository
import pl.paullettuce.simpleshoppinglist.storage.dao.ShoppingListsDao
import pl.paullettuce.simpleshoppinglist.storage.entity.ShoppingListEntity

class ShoppingListsRepositoryImpl(
    private val shoppingListsDao: ShoppingListsDao,
    private val shoppingListMapper: ShoppingListEntityToDetailsListMapper
) : ShoppingListsRepository {
    override fun getShoppingLists(active: Boolean): LiveData<List<ShoppingListDetails>> {
        return shoppingListsDao.getShoppingLists(active)
            .mapNotNull {
                shoppingListMapper.map(it)
            }
    }

    override fun createShoppingList(name: String): Completable {
        val shoppingListEntity = ShoppingListEntity(
            name,
            System.currentTimeMillis()
        )
        return shoppingListsDao.insert(shoppingListEntity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}