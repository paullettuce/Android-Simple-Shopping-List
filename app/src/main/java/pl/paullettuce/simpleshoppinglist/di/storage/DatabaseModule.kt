package pl.paullettuce.simpleshoppinglist.di.storage

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import pl.paullettuce.simpleshoppinglist.storage.dao.ShoppingListItemsDao
import pl.paullettuce.simpleshoppinglist.storage.dao.ShoppingListsDao
import pl.paullettuce.simpleshoppinglist.storage.database.AppDatabase
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext appContext: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "shopping_lists_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideShoppingListsDao(
        db: AppDatabase
    ): ShoppingListsDao = db.shoppingListsDao()

    @Provides
    @Singleton
    fun provideShoppingListItemsDao(
        db: AppDatabase
    ): ShoppingListItemsDao = db.shoppingListItemsDao()
}