package pl.paullettuce.simpleshoppinglist.di.presentation

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import pl.paullettuce.simpleshoppinglist.domain.model.ShoppingListDetailsDiffCallback
import pl.paullettuce.simpleshoppinglist.domain.usecase.shopping_lists.CreateShoppingListUseCase
import pl.paullettuce.simpleshoppinglist.domain.usecase.shopping_lists.GetShoppingListsUseCase
import pl.paullettuce.simpleshoppinglist.presentation.shopping_lists.ShoppingListsAdapter
import pl.paullettuce.simpleshoppinglist.presentation.shopping_lists.ShoppingListsContract
import pl.paullettuce.simpleshoppinglist.presentation.shopping_lists.ShoppingListsPresenter

@Module
@InstallIn(FragmentComponent::class)
object ShoppingListsFragmentModule {

    @Provides
    fun providePresenter(
        getShoppingListsUseCase: GetShoppingListsUseCase,
        createShoppingListUseCase: CreateShoppingListUseCase
    ): ShoppingListsContract.Presenter {
        return ShoppingListsPresenter(getShoppingListsUseCase, createShoppingListUseCase)
    }

    @Provides
    fun provideShoppingListsAdapter(
        diffCallback: ShoppingListDetailsDiffCallback
    ): ShoppingListsAdapter = ShoppingListsAdapter(diffCallback)
}