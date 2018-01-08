package com.theappexperts.remakefinalproject.views.search;

import com.theappexperts.remakefinalproject.data.network.model.RecipeListModel;
import com.theappexperts.remakefinalproject.views.ui.base.MvpView;

/**
 * Created by TheAppExperts on 08/01/2018.
 */

public interface ISearchMvpView extends MvpView {

    void onFetchDataSuccess(RecipeListModel recipeListModel);
    void onFetchDataError(String message);

}
