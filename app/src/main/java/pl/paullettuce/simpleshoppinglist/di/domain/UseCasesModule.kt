package pl.paullettuce.simpleshoppinglist.di.domain

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import pl.paullettuce.simpleshoppinglist.domain.repository.ShoppingListsRepository
import pl.paullettuce.simpleshoppinglist.domain.usecase.shopping_lists.CreateShoppingListUseCase
import pl.paullettuce.simpleshoppinglist.domain.usecase.shopping_lists.CreateShoppingListUseCaseImpl
import pl.paullettuce.simpleshoppinglist.domain.usecase.shopping_lists.GetShoppingListsUseCase
import pl.paullettuce.simpleshoppinglist.domain.usecase.shopping_lists.GetShoppingListsUseCaseImpl

@InstallIn(ApplicationComponent::class)
@Module
object UseCasesModule {

    @Provides
    fun provideGetShoppingListsUseCase(
        shoppingListsRepository: ShoppingListsRepository
    ): GetShoppingListsUseCase {
        return GetShoppingListsUseCaseImpl(shoppingListsRepository)
    }

    @Provides
    fun provideCreateShoppingListUseCase(
        shoppingListsRepository: ShoppingListsRepository
    ): CreateShoppingListUseCase {
        return CreateShoppingListUseCaseImpl(shoppingListsRepository)
    }
}