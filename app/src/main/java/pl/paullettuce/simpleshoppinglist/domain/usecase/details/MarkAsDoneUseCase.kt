package pl.paullettuce.simpleshoppinglist.domain.usecase.details

import io.reactivex.rxjava3.core.Completable
import pl.paullettuce.simpleshoppinglist.domain.repository.ShoppingListItemsRepository

interface MarkAsDoneUseCase {
    operator fun invoke(id: Long): Completable
}

class MarkAsDoneUseCaseImpl(
    private val itemsRepository: ShoppingListItemsRepository
) : MarkAsDoneUseCase {
    override fun invoke(id: Long) = itemsRepository.markAsDone(id)

}