package pl.paullettuce.simpleshoppinglist.domain.repository

import androidx.lifecycle.LiveData
import pl.paullettuce.simpleshoppinglist.storage.entity.ShoppingListEntity

interface ShoppingListRepository {
    fun getActiveShoppingLists(): LiveData<List<ShoppingListEntity>>
    fun getArchivedShoppingLists(): LiveData<List<ShoppingListEntity>>
}