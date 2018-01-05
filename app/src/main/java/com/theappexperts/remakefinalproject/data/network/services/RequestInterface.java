package com.theappexperts.remakefinalproject.data.network.services;

import com.theappexperts.remakefinalproject.data.network.constant.API_List;
import com.theappexperts.remakefinalproject.data.network.model.RecipeListModel;
import com.theappexperts.remakefinalproject.data.network.model.RecipeModel;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by TheAppExperts on 02/01/2018.
 */

public interface RequestInterface {

    @GET(API_List.SEARCH)
    Observable<RecipeListModel> getPopular(@Query("key") String key);

    @GET(API_List.SEARCH)
    Observable<RecipeListModel> getPopular(@Query("key") String key, @Query("page") int page);

    @GET(API_List.RECIPE)
    Observable<RecipeModel> getRecipe(@Query("key") String key, @Query("rId") String rId);

}
