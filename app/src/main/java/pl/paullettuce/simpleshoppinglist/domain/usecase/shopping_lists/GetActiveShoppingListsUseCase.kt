package pl.paullettuce.simpleshoppinglist.domain.usecase.shopping_lists

import androidx.lifecycle.LiveData
import pl.paullettuce.simpleshoppinglist.domain.model.ShoppingListDetails

interface GetActiveShoppingListsUseCase {
    operator fun invoke(): LiveData<List<ShoppingListDetails>>
}