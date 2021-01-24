package pl.paullettuce.simpleshoppinglist.di.presentation

import android.app.Activity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import pl.paullettuce.simpleshoppinglist.domain.usecase.details.*
import pl.paullettuce.simpleshoppinglist.presentation.diff_callbacks.ShoppingListItemDiffCallback
import pl.paullettuce.simpleshoppinglist.presentation.shopping_list_details.ShoppingListDetailsActivity
import pl.paullettuce.simpleshoppinglist.presentation.shopping_list_details.ShoppingListDetailsContract
import pl.paullettuce.simpleshoppinglist.presentation.shopping_list_details.ShoppingListDetailsPresenter
import pl.paullettuce.simpleshoppinglist.presentation.shopping_list_details.ShoppingListItemsAdapter

@InstallIn(ActivityComponent::class)
@Module
object ShoppingListDetailsActivityModule {

    @Provides
    fun providePresenter(
        getShoppingListDetailsUseCase: GetShoppingListDetailsUseCase,
        addShoppingListItemUseCase: AddShoppingListItemUseCase,
        markAsDoneUseCase: MarkAsDoneUseCase,
        unmarkAsDoneUseCase: UnmarkAsDoneUseCase,
        deleteListItemUseCase: DeleteListItemUseCase
    ): ShoppingListDetailsContract.Presenter {
        return ShoppingListDetailsPresenter(
            getShoppingListDetailsUseCase,
            addShoppingListItemUseCase,
            markAsDoneUseCase,
            unmarkAsDoneUseCase,
            deleteListItemUseCase
        )
    }

    @Provides
    fun provideShoppingListDetailsActivity(
        activity: Activity
    ): ShoppingListDetailsActivity = activity as ShoppingListDetailsActivity

    @Provides
    fun provideListInteraction(
        activity: ShoppingListDetailsActivity
    ): ShoppingListDetailsContract.ListInteraction =
        activity as ShoppingListDetailsContract.ListInteraction

    @Provides
    fun provideListItemsAdapter(
        interaction: ShoppingListDetailsContract.ListInteraction,
        diffCallback: ShoppingListItemDiffCallback
    ): ShoppingListItemsAdapter = ShoppingListItemsAdapter(interaction, diffCallback)
}