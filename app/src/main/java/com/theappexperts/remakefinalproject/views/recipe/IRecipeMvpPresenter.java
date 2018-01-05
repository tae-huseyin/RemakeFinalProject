package com.theappexperts.remakefinalproject.views.recipe;

import com.theappexperts.remakefinalproject.views.ui.base.MvpPresenter;

/**
 * Created by TheAppExperts on 02/01/2018.
 */

public interface IRecipeMvpPresenter <V extends IRecipeMvpView> extends MvpPresenter<V> {

    void onCallRecipeModelList(String key);

    void onCallRecipeModelList(String key, int page);

    void onCallRecipeList(String key, String rId);

}
