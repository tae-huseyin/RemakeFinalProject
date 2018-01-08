package com.theappexperts.remakefinalproject.data;

import com.theappexperts.remakefinalproject.data.network.ApiHelper;
import com.theappexperts.remakefinalproject.data.network.AppApiHelper;
import com.theappexperts.remakefinalproject.data.network.model.RecipeListModel;
import com.theappexperts.remakefinalproject.data.network.model.RecipeModel;

import io.reactivex.Observable;

/**
 * Created by TheAppExperts on 02/01/2018.
 */

public class AppDataManager implements IDataManager {

    private ApiHelper apiHelper;

    public AppDataManager(){
        apiHelper = new AppApiHelper();
    }

    @Override
    public Observable<RecipeListModel> getFromApi_RecipeList(String key) {
        return apiHelper.getFromApi_RecipeList(key);
    }

    @Override
    public Observable<RecipeListModel> getFromApi_RecipeList(String key, int page) {
        return apiHelper.getFromApi_RecipeList(key, page);
    }

    @Override
    public Observable<RecipeListModel> getFromApi_RecipeList(String key, String keyword) {
        return apiHelper.getFromApi_RecipeList(key, keyword);
    }

    @Override
    public Observable<RecipeModel> getFromApi_Recipe(String key, String rId) {
        return apiHelper.getFromApi_Recipe(key, rId);
    }
}
