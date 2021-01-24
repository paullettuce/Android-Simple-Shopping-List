package pl.paullettuce.simpleshoppinglist.presentation.shopping_list_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import pl.paullettuce.simpleshoppinglist.domain.extensions.switchMap
import pl.paullettuce.simpleshoppinglist.domain.model.ShoppingListDetails
import pl.paullettuce.simpleshoppinglist.domain.model.ShoppingListDetailsWithItems
import pl.paullettuce.simpleshoppinglist.domain.usecase.details.*
import pl.paullettuce.simpleshoppinglist.storage.entity.ShoppingListItemEntity

class ShoppingListDetailsPresenter(
    private val getShoppingListDetailsUseCase: GetShoppingListDetailsUseCase,
    private val addShoppingListItemUseCase: AddShoppingListItemUseCase,
    private val markAsDoneUseCase: MarkAsDoneUseCase,
    private val unmarkAsDoneUseCase: UnmarkAsDoneUseCase,
    private val deleteListItemUseCase: DeleteListItemUseCase
) : ShoppingListDetailsContract.Presenter {

    override val shoppingListDetailsLiveData: LiveData<ShoppingListDetailsWithItems>
        get() = _shoppingListIdLiveData.switchMap {
            getShoppingListDetailsUseCase(it)
        }

    private val _shoppingListIdLiveData = MutableLiveData<Long>()
    private val compositeDisposable = CompositeDisposable()

    override fun setShoppingListId(id: Long) {
        _shoppingListIdLiveData.postValue(id)
    }

    override fun addShoppingListItem(name: String, quantity: Int) {
        _shoppingListIdLiveData.value?.let {
            addShoppingListItemUseCase(it, name, quantity)
                .subscribe()
                .addTo(compositeDisposable)
        }
    }

    override fun markAsDone(listItemEntity: ShoppingListItemEntity) {
        markAsDoneUseCase(listItemEntity.id)
            .subscribe()
            .addTo(compositeDisposable)
    }

    override fun unmarkAsDone(listItemEntity: ShoppingListItemEntity) {
        unmarkAsDoneUseCase(listItemEntity.id)
            .subscribe()
            .addTo(compositeDisposable)
    }

    override fun delete(item: ShoppingListItemEntity) {
        deleteListItemUseCase(item)
            .subscribe()
            .addTo(compositeDisposable)
    }
}