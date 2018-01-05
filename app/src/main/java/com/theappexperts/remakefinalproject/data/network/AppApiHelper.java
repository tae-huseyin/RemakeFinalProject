package com.theappexperts.remakefinalproject.data.network;

import com.theappexperts.remakefinalproject.data.network.model.RecipeListModel;
import com.theappexperts.remakefinalproject.data.network.model.RecipeModel;
import com.theappexperts.remakefinalproject.data.network.services.RequestInterface;
import com.theappexperts.remakefinalproject.data.network.services.ServerConnection;

import io.reactivex.Observable;

/**
 * Created by TheAppExperts on 02/01/2018.
 */

public class AppApiHelper implements ApiHelper{

    private RequestInterface requestInterface;

    public AppApiHelper() {
        requestInterface = ServerConnection.BackendService();
    }

    @Override
    public Observable<RecipeListModel> getFromApi_RecipeList(String key) {
        return requestInterface.getPopular(key);
    }

    @Override
    public Observable<RecipeListModel> getFromApi_RecipeList(String key, int page) {
        return requestInterface.getPopular(key, page);
    }

    @Override
    public Observable<RecipeModel> getFromApi_Recipe(String key, String rId) {
        return requestInterface.getRecipe(key ,rId);
    }
}
