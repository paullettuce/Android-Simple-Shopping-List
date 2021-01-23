package pl.paullettuce.simpleshoppinglist.storage.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Transaction
import io.reactivex.rxjava3.core.Completable
import pl.paullettuce.simpleshoppinglist.domain.model.ShoppingListDetails
import pl.paullettuce.simpleshoppinglist.domain.model.ShoppingListDetailsWithItems
import pl.paullettuce.simpleshoppinglist.storage.entity.ShoppingListEntity

@Dao
interface ShoppingListsDao {

    @Insert(onConflict = REPLACE)
    fun insert(shoppingListEntity: ShoppingListEntity): Completable

    @Query(
        """
        SELECT * FROM shopping_list_entity 
        WHERE isActive=:active
        ORDER BY creationTimestamp DESC
        """
    )
    fun getShoppingLists(active: Boolean): LiveData<List<ShoppingListEntity>>

    @Transaction
    @Query(
        """
            SELECT * FROM shopping_list_entity 
            WHERE id=:id
            ORDER BY creationTimestamp DESC"""
    )
    fun getShoppingListWithItemsDetails(id: Long): LiveData<ShoppingListDetailsWithItems>

    @Query(
        """
            UPDATE shopping_list_entity 
            SET isActive = 0 
            WHERE id=:id
        """
    )
    fun archiveList(id: Long): Completable
}