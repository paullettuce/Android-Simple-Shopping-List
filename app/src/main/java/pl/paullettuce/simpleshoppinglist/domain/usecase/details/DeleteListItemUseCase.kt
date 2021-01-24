package pl.paullettuce.simpleshoppinglist.domain.usecase.details

import io.reactivex.rxjava3.core.Completable
import pl.paullettuce.simpleshoppinglist.domain.repository.ShoppingListItemsRepository
import pl.paullettuce.simpleshoppinglist.storage.entity.ShoppingListItemEntity

interface DeleteListItemUseCase {
    operator fun invoke(item: ShoppingListItemEntity): Completable
}

class DeleteListItemUseCaseImpl(
    private val repository: ShoppingListItemsRepository
): DeleteListItemUseCase {
    override fun invoke(item: ShoppingListItemEntity) = repository.delete(item)
}