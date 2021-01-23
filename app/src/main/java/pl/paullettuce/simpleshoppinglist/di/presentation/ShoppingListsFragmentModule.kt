package pl.paullettuce.simpleshoppinglist.di.presentation

import androidx.fragment.app.Fragment
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import pl.paullettuce.simpleshoppinglist.domain.usecase.shopping_lists.CreateShoppingListUseCase
import pl.paullettuce.simpleshoppinglist.domain.usecase.shopping_lists.GetShoppingListsUseCase
import pl.paullettuce.simpleshoppinglist.presentation.diff_callbacks.ShoppingListDetailsDiffCallback
import pl.paullettuce.simpleshoppinglist.presentation.shopping_lists.ShoppingListsAdapter
import pl.paullettuce.simpleshoppinglist.presentation.shopping_lists.ShoppingListsContract
import pl.paullettuce.simpleshoppinglist.presentation.shopping_lists.ShoppingListsFragment
import pl.paullettuce.simpleshoppinglist.presentation.shopping_lists.ShoppingListsPresenter

@Module
@InstallIn(FragmentComponent::class)
object ShoppingListsFragmentModule {

    @Provides
    fun provideShoppingListsFragment(
        fragment: Fragment
    ): ShoppingListsFragment = fragment as ShoppingListsFragment

    @Provides
    fun provideListInteraction(
        fragment: ShoppingListsFragment
    ): ShoppingListsContract.ListInteraction = fragment as ShoppingListsContract.ListInteraction

    @Provides
    fun providePresenter(
        getShoppingListsUseCase: GetShoppingListsUseCase,
        createShoppingListUseCase: CreateShoppingListUseCase
    ): ShoppingListsContract.Presenter {
        return ShoppingListsPresenter(getShoppingListsUseCase, createShoppingListUseCase)
    }

    @Provides
    fun provideShoppingListsAdapter(
        interaction: ShoppingListsContract.ListInteraction,
        diffCallback: ShoppingListDetailsDiffCallback
    ): ShoppingListsAdapter = ShoppingListsAdapter(interaction, diffCallback)
}