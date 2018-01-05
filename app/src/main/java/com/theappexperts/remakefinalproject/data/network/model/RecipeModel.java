package com.theappexperts.remakefinalproject.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.theappexperts.remakefinalproject.data.network.model.Recipes;

public class RecipeModel {

    @SerializedName("recipe")
    @Expose
    private Recipes recipes;

    public Recipes getRecipes() {
        return recipes;
    }

    public void setRecipe(Recipes recipes) {
        this.recipes = recipes;
    }

}
