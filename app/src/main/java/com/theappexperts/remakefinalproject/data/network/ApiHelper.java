package com.theappexperts.remakefinalproject.data.network;

import com.theappexperts.remakefinalproject.data.network.model.RecipeListModel;
import com.theappexperts.remakefinalproject.data.network.model.RecipeModel;

import io.reactivex.Observable;

/**
 * Created by TheAppExperts on 02/01/2018.
 */

public interface ApiHelper {
    Observable<RecipeListModel> getFromApi_RecipeList(String key);

    Observable<RecipeListModel> getFromApi_RecipeList(String key, int page);
    //search
    Observable<RecipeListModel> getFromApi_RecipeList(String key, String keyword);
    //get the clicked recipe
    Observable<RecipeModel> getFromApi_Recipe(String key, String rId);
}
