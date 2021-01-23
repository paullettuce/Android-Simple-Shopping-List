package pl.paullettuce.simpleshoppinglist.domain.usecase.shopping_lists

import io.reactivex.rxjava3.core.Completable
import pl.paullettuce.simpleshoppinglist.domain.repository.ShoppingListsRepository

interface ArchiveListUseCase {
    operator fun invoke(id: Long): Completable
}

class ArchiveListUseCaseImpl(
    private val repository: ShoppingListsRepository
): ArchiveListUseCase {
    override fun invoke(id: Long) = repository.archiveList(id)
}