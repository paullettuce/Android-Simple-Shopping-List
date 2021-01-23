package pl.paullettuce.simpleshoppinglist.domain.usecase.details

import androidx.lifecycle.LiveData
import pl.paullettuce.simpleshoppinglist.domain.model.ShoppingListDetails
import pl.paullettuce.simpleshoppinglist.domain.model.ShoppingListDetailsWithItems
import pl.paullettuce.simpleshoppinglist.domain.repository.ShoppingListsRepository

interface GetShoppingListDetailsUseCase {
    operator fun invoke(shoppingListId: Long): LiveData<ShoppingListDetailsWithItems>
}

class GetShoppingListDetailsUseCaseImpl(
    private val detailsRepository: ShoppingListsRepository
) : GetShoppingListDetailsUseCase {
    override fun invoke(shoppingListId: Long) =
        detailsRepository.getShoppingListWithItemsDetails(shoppingListId)
}