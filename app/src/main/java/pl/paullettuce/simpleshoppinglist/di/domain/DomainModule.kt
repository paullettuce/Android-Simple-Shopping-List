package pl.paullettuce.simpleshoppinglist.di.domain

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import pl.paullettuce.simpleshoppinglist.domain.mapper.ShoppingListEntityToDetailsListMapper
import pl.paullettuce.simpleshoppinglist.domain.model.ShoppingListDetailsDiffCallback

@InstallIn(ApplicationComponent::class)
@Module
object DomainModule {

    @Provides
    fun provideShoppingListsDiffCallback() = ShoppingListDetailsDiffCallback()

    @Provides
    fun provideShoppingListEntityToDetailsListMapper() = ShoppingListEntityToDetailsListMapper()
}