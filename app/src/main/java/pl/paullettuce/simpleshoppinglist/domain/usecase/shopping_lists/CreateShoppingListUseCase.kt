package pl.paullettuce.simpleshoppinglist.domain.usecase.shopping_lists

import io.reactivex.rxjava3.core.Completable
import pl.paullettuce.simpleshoppinglist.domain.repository.ShoppingListsRepository

interface CreateShoppingListUseCase {
    operator fun invoke(name: String): Completable
}

class CreateShoppingListUseCaseImpl(
    private val shoppingListsRepository: ShoppingListsRepository
): CreateShoppingListUseCase {
    override fun invoke(name: String): Completable {
        return shoppingListsRepository.createShoppingList(name)
    }
}