package com.theappexperts.remakefinalproject.views.search;

import com.theappexperts.remakefinalproject.data.IDataManager;
import com.theappexperts.remakefinalproject.data.network.model.RecipeListModel;
import com.theappexperts.remakefinalproject.data.network.model.RecipeModel;
import com.theappexperts.remakefinalproject.views.ui.base.BasePresenter;
import com.theappexperts.remakefinalproject.views.ui.utils.rx.SchedulerProvider;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

/**
 * Created by TheAppExperts on 08/01/2018.
 */

public class SearchPresenter <V extends ISearchMvpView>
        extends BasePresenter<V>
        implements ISearchMVPPresenter<V>{

    public SearchPresenter(IDataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onCallRecipeModelList(String key, String keyword) {

        getCompositeDisposable().add(
                getDataManager().getFromApi_RecipeList(key, keyword)
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
}
