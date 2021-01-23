package pl.paullettuce.simpleshoppinglist.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import io.reactivex.rxjava3.core.Completable
import pl.paullettuce.simpleshoppinglist.storage.entity.ShoppingListItemEntity

@Dao
interface ShoppingListItemsDao {

    @Insert(onConflict = REPLACE)
    fun insert(shoppingListItemEntity: ShoppingListItemEntity): Completable

    @Query("UPDATE shopping_list_item_entity SET isDone=1 WHERE id=:id")
    fun markAsDone(id: Long): Completable

    @Query("UPDATE shopping_list_item_entity SET isDone=0 WHERE id=:id")
    fun unmarkAsDone(id: Long): Completable
}