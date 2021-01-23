package pl.paullettuce.simpleshoppinglist.di.presentation

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import pl.paullettuce.simpleshoppinglist.presentation.shopping_lists.ShoppingListsFragment
import javax.inject.Named

@InstallIn(ActivityComponent::class)
@Module
object ShoppingListsActivityModule {

    @Provides
    @Named("active")
    fun provideActiveListsFragment(): ShoppingListsFragment {
        return ShoppingListsFragment.getActiveShoppingListsFragment()
    }

    @Provides
    @Named("archived")
    fun provideArchivedListsFragment(): ShoppingListsFragment {
        return ShoppingListsFragment.getArchivedShoppingListsFragment()
    }
}

