package pl.paullettuce.simpleshoppinglist.di.domain

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import pl.paullettuce.simpleshoppinglist.domain.mapper.ShoppingListEntityToDetailsListMapper
import pl.paullettuce.simpleshoppinglist.presentation.diff_callbacks.ShoppingListDetailsDiffCallback
import pl.paullettuce.simpleshoppinglist.presentation.diff_callbacks.ShoppingListItemDiffCallback

@InstallIn(ApplicationComponent::class)
@Module
object DomainModule {

    @Provides
    fun provideShoppingListsDiffCallback() =
        ShoppingListDetailsDiffCallback()

    @Provides
    fun provideShoppingListItemDiffCallback() =
        ShoppingListItemDiffCallback()

    @Provides
    fun provideShoppingListEntityToDetailsListMapper() = ShoppingListEntityToDetailsListMapper()
}