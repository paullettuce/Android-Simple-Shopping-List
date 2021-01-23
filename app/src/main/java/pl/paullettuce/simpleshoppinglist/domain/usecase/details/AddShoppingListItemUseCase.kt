package pl.paullettuce.simpleshoppinglist.domain.usecase.details

import io.reactivex.rxjava3.core.Completable
import pl.paullettuce.simpleshoppinglist.domain.repository.ShoppingListItemsRepository

interface AddShoppingListItemUseCase {
    operator fun invoke(shoppingListId: Long, name: String, quantity: Int): Completable
}

class AddShoppingListItemUseCaseImpl(
    private val itemsRepository: ShoppingListItemsRepository
) : AddShoppingListItemUseCase {
    override fun invoke(shoppingListId: Long, name: String, quantity: Int): Completable {
        return itemsRepository.addShoppingListItem(shoppingListId, name, quantity)
    }
}