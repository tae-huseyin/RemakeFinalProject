package com.theappexperts.remakefinalproject.views.search;

import com.theappexperts.remakefinalproject.views.ui.base.MvpPresenter;

/**
 * Created by TheAppExperts on 08/01/2018.
 */

public interface ISearchMVPPresenter <V extends ISearchMvpView> extends MvpPresenter<V> {

    void onCallRecipeModelList(String key, String keyword);
    
}
