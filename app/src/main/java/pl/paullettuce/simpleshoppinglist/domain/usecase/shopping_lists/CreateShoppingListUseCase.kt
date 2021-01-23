package pl.paullettuce.simpleshoppinglist.domain.usecase.shopping_lists

interface CreateShoppingListUseCase {
    operator fun invoke(name: String)
}