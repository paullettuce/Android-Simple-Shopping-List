package pl.paullettuce.simpleshoppinglist.domain.usecase.shopping_lists

import androidx.lifecycle.LiveData
import pl.paullettuce.simpleshoppinglist.domain.model.ShoppingListDetails
import pl.paullettuce.simpleshoppinglist.domain.repository.ShoppingListsRepository

interface GetShoppingListsUseCase {
    operator fun invoke(active: Boolean): LiveData<List<ShoppingListDetails>>
}

class GetShoppingListsUseCaseImpl(
    private val shoppingListsRepository: ShoppingListsRepository
): GetShoppingListsUseCase {
    override fun invoke(active: Boolean): LiveData<List<ShoppingListDetails>> {
        return shoppingListsRepository.getShoppingLists(active)
    }
}