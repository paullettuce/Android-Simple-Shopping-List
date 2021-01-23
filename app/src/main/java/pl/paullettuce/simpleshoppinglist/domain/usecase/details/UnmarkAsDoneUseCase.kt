package pl.paullettuce.simpleshoppinglist.domain.usecase.details

import io.reactivex.rxjava3.core.Completable
import pl.paullettuce.simpleshoppinglist.domain.repository.ShoppingListItemsRepository

interface UnmarkAsDoneUseCase {
    operator fun invoke(id: Long): Completable
}

class UnmarkAsDoneUseCaseImpl(
    private val itemsRepository: ShoppingListItemsRepository
) : UnmarkAsDoneUseCase {
    override fun invoke(id: Long) = itemsRepository.unmarkAsDone(id)
}