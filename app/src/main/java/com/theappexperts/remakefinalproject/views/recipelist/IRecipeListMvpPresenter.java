package com.theappexperts.remakefinalproject.views.recipelist;

import com.theappexperts.remakefinalproject.views.ui.base.MvpPresenter;

/**
 * Created by TheAppExperts on 02/01/2018.
 */

public interface IRecipeListMvpPresenter <V extends IRecipeListMvpView> extends MvpPresenter<V> {
    void onCallRecipeModelList(String key);

    void onCallRecipeModelList(String key, int page);

    void onCallRecipeList(String key, String rId);
}
