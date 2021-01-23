package pl.paullettuce.simpleshoppinglist.di.storage

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import pl.paullettuce.simpleshoppinglist.domain.mapper.ShoppingListEntityToDetailsListMapper
import pl.paullettuce.simpleshoppinglist.domain.repository.ShoppingListItemsRepository
import pl.paullettuce.simpleshoppinglist.domain.repository.ShoppingListsRepository
import pl.paullettuce.simpleshoppinglist.storage.dao.ShoppingListItemsDao
import pl.paullettuce.simpleshoppinglist.storage.dao.ShoppingListsDao
import pl.paullettuce.simpleshoppinglist.storage.repository.ShoppingListItemsRepositoryImpl
import pl.paullettuce.simpleshoppinglist.storage.repository.ShoppingListsRepositoryImpl
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun provideShoppingListsRepository(
        shoppingListsDao: ShoppingListsDao,
        mapper: ShoppingListEntityToDetailsListMapper
    ): ShoppingListsRepository {
        return ShoppingListsRepositoryImpl(shoppingListsDao, mapper)
    }

    @Provides
    @Singleton
    fun provideShoppingListItemsRepository(
        shoppingListItemsDao: ShoppingListItemsDao
    ): ShoppingListItemsRepository {
        return ShoppingListItemsRepositoryImpl(shoppingListItemsDao)
    }
}