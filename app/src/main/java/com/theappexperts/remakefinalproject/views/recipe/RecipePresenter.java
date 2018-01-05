package com.theappexperts.remakefinalproject.views.recipe;

import com.theappexperts.remakefinalproject.data.IDataManager;
import com.theappexperts.remakefinalproject.data.network.model.RecipeListModel;
import com.theappexperts.remakefinalproject.data.network.model.RecipeModel;
import com.theappexperts.remakefinalproject.views.ui.base.BasePresenter;
import com.theappexperts.remakefinalproject.views.ui.utils.rx.SchedulerProvider;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

/**
 * Created by TheAppExperts on 02/01/2018.
 */

public class RecipePresenter <V extends IRecipeMvpView>
        extends BasePresenter<V>
        implements IRecipeMvpPresenter<V> {

    public RecipePresenter(IDataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onCallRecipeModelList(String key) {

        getCompositeDisposable().add(
                getDataManager().getFromApi_RecipeList(key)
                        .observeOn(getSchedulerProvider().ui())
                        .subscribeOn(getSchedulerProvider().io())
                        .subscribe(new Consumer<RecipeListModel>() {
                                       @Override
                                       public void accept(RecipeListModel recipeListModel) throws Exception {
                                           getMvpView().onFetchDataSuccess(recipeListModel);
                                       }
                                   },
                                new Consumer<Throwable>() {
                                    @Override
                                    public void accept(Throwable throwable) throws Exception {
                                        getMvpView().onFetchDataError(throwable.getMessage());
                                    }
                                })
        );

    }

    @Override
    public void onCallRecipeModelList(String key, int page) {

        getCompositeDisposable().add(
                getDataManager().getFromApi_RecipeList(key, page)
                        .observeOn(getSchedulerProvider().ui())
                        .subscribeOn(getSchedulerProvider().io())
                        .subscribe(new Consumer<RecipeListModel>() {
                                       @Override
                                       public void accept(RecipeListModel recipeListModel) throws Exception {
                                           getMvpView().onFetchDataSuccess(recipeListModel);
                                       }
                                   },
                                new Consumer<Throwable>() {
                                    @Override
                                    public void accept(Throwable throwable) throws Exception {
                                        getMvpView().onFetchDataError(throwable.getMessage());
                                    }
                                })
        );
    }

    @Override
    public void onCallRecipeList(String key, String rId) {
        getCompositeDisposable().add(
                getDataManager().getFromApi_Recipe(key, rId)
                        .observeOn(getSchedulerProvider().ui())
                        .subscribeOn(getSchedulerProvider().io())
                        .subscribe(new Consumer<RecipeModel>() {
                                       @Override
                                       public void accept(RecipeModel recipeModel) throws Exception {
                                           getMvpView().onFetchDataSuccess(recipeModel);
                                       }
                                   },
                                new Consumer<Throwable>() {
                                    @Override
                                    public void accept(Throwable throwable) throws Exception {
                                        getMvpView().onFetchDataError(throwable.getMessage());
                                    }
                                })
        );
    }

}
