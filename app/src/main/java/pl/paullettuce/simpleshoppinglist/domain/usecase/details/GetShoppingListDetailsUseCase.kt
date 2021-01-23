package pl.paullettuce.simpleshoppinglist.domain.usecase.details

import androidx.lifecycle.LiveData
import pl.paullettuce.simpleshoppinglist.domain.model.ShoppingListDetails

interface GetShoppingListDetailsUseCase {
    operator fun invoke(shoppingListId: Long): LiveData<ShoppingListDetails>
}