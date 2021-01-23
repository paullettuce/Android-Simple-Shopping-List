package pl.paullettuce.simpleshoppinglist.storage.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import io.reactivex.rxjava3.core.Completable
import pl.paullettuce.simpleshoppinglist.storage.entity.ShoppingListEntity

@Dao
interface ShoppingListsDao {

    @Insert(onConflict = REPLACE)
    fun insert(shoppingListEntity: ShoppingListEntity): Completable

    @Query(
        """
        SELECT * FROM shopping_list_entity 
        WHERE is_active=:active
        ORDER BY creation_timestamp DESC
        """
    )
    fun getShoppingLists(active: Boolean): LiveData<List<ShoppingListEntity>>
}