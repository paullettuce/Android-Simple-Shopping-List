package pl.paullettuce.simpleshoppinglist.presentation.shopping_lists

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import pl.paullettuce.simpleshoppinglist.domain.extensions.switchMap
import pl.paullettuce.simpleshoppinglist.domain.model.ShoppingListDetails
import pl.paullettuce.simpleshoppinglist.domain.usecase.shopping_lists.CreateShoppingListUseCase
import pl.paullettuce.simpleshoppinglist.domain.usecase.shopping_lists.GetShoppingListsUseCase

class ShoppingListsPresenter(
    private val getShoppingListsUseCase: GetShoppingListsUseCase,
    private val createShoppingListUseCase: CreateShoppingListUseCase
) : ShoppingListsContract.Presenter {
    override val shoppingLists: LiveData<List<ShoppingListDetails>>
        get() = _shouldFetchActiveLists.switchMap {
            getShoppingListsUseCase(it)
        }

    private val _shouldFetchActiveLists = MutableLiveData<Boolean>()
    private val compositeDisposable = CompositeDisposable()

    override fun setFetchActiveLists(shouldFetchActive: Boolean) {
        _shouldFetchActiveLists.postValue(shouldFetchActive)
    }

    override fun createShoppingList(name: String) {
        createShoppingListUseCase(name)
            .subscribe()
            .addTo(compositeDisposable)
    }
}