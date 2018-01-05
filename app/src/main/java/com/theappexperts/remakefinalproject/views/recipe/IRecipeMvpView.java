package com.theappexperts.remakefinalproject.views.recipe;

import com.theappexperts.remakefinalproject.data.network.model.RecipeListModel;
import com.theappexperts.remakefinalproject.data.network.model.RecipeModel;
import com.theappexperts.remakefinalproject.views.ui.base.MvpView;

/**
 * Created by TheAppExperts on 02/01/2018.
 */

public interface IRecipeMvpView extends MvpView {

    void onFetchDataSuccess(RecipeListModel recipeListModel);
    void onFetchDataSuccess(RecipeModel recipeModel);
    void onFetchDataError(String message);

}
