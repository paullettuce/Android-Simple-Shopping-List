package pl.paullettuce.simpleshoppinglist.storage.repository;


import androidx.lifecycle.LiveData;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Function;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import pl.paullettuce.simpleshoppinglist.domain.extensions.LiveDataExKt;
import pl.paullettuce.simpleshoppinglist.domain.mapper.ShoppingListEntityToDetailsListMapper;
import pl.paullettuce.simpleshoppinglist.domain.mapper.ShoppingListItemsIsArchivedMapper;
import pl.paullettuce.simpleshoppinglist.domain.model.ShoppingListDetails;
import pl.paullettuce.simpleshoppinglist.domain.model.ShoppingListDetailsWithItems;
import pl.paullettuce.simpleshoppinglist.domain.repository.ShoppingListsRepository;
import pl.paullettuce.simpleshoppinglist.storage.dao.ShoppingListsDao;
import pl.paullettuce.simpleshoppinglist.storage.entity.ShoppingListEntity;

public class ShoppingListsRepositoryImpl implements ShoppingListsRepository {
    ShoppingListsDao shoppingListsDao;
    ShoppingListEntityToDetailsListMapper shoppingListMapper;
    ShoppingListItemsIsArchivedMapper isArchivedMapper;

    public ShoppingListsRepositoryImpl(
            ShoppingListsDao shoppingListsDao,
            ShoppingListEntityToDetailsListMapper shoppingListMapper,
            ShoppingListItemsIsArchivedMapper isArchivedMapper) {
        this.shoppingListsDao = shoppingListsDao;
        this.shoppingListMapper = shoppingListMapper;
        this.isArchivedMapper = isArchivedMapper;
    }

    @NotNull
    @Override
    public Completable createShoppingList(@NotNull String name) {
        ShoppingListEntity shoppingListEntity = new ShoppingListEntity(
                name,
                System.currentTimeMillis(),
                true
        );
        return shoppingListsDao.insert(shoppingListEntity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @NotNull
    @Override
    public LiveData<List<ShoppingListDetails>> getShoppingLists(boolean active) {
        return LiveDataExKt.mapNotNull(
                shoppingListsDao.getShoppingLists(active),
                shoppingListMapper::map);
    }

    @NotNull
    @Override
    public LiveData<ShoppingListDetailsWithItems> getShoppingListWithItemsDetails(long id) {
        return LiveDataExKt.mapNotNull(
                shoppingListsDao.getShoppingListWithItemsDetails(id),
                isArchivedMapper::map);
    }

    @NotNull
    @Override
    public Completable archiveList(long id) {
        return shoppingListsDao.archiveList(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}