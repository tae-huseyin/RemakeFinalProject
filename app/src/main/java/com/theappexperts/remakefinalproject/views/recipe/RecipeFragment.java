package com.theappexperts.remakefinalproject.views.recipe;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.theappexperts.remakefinalproject.R;
import com.theappexperts.remakefinalproject.data.AppDataManager;
import com.theappexperts.remakefinalproject.data.network.constant.API_List;
import com.theappexperts.remakefinalproject.data.network.model.RecipeListModel;
import com.theappexperts.remakefinalproject.data.network.model.RecipeModel;
import com.theappexperts.remakefinalproject.data.network.model.Recipes;
import com.theappexperts.remakefinalproject.views.map.MapFragment;
import com.theappexperts.remakefinalproject.views.ui.utils.rx.AppSchedulerProvider;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;

public class RecipeFragment extends Fragment implements IRecipeMvpView{

    RecipePresenter<RecipeFragment> recipePresenter;

    Recipes recipe;
    String rID;

    Unbinder unbinder;

    @BindView(R.id.ivRecipePicture)
    ImageView ivRecipePicture;

    @BindView(R.id.tbRecipeTitle)
    Toolbar tbRecipeTitle;

    @BindView(R.id.tvRecipeList)
    TextView tvRecipeList;

    @OnClick(R.id.fbtnWebsite)
    public void goToSite(){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(recipe.getPublisherUrl()));
        startActivity(intent);
    }

    @OnClick(R.id.fbtnMap)
    public void goToMap(){
        FragmentManager fragmentManager;
        fragmentManager = getActivity().getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .add(R.id.frag_container, new MapFragment())
                .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .addToBackStack(null)
                .commit();
    }

    public RecipeFragment() {
        // Required empty public constructor
    }

    public void initData()
    {
        recipePresenter = new RecipePresenter<>(new AppDataManager(), new AppSchedulerProvider(), new CompositeDisposable());
        recipePresenter.onAttach(this);
    }

    private void showSnackbar(final String text) {
        View container = getView();
        if (container != null) {
            Snackbar.make(container, text, Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        initData();

        if(savedInstanceState == null) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(tbRecipeTitle);
            tbRecipeTitle.setTitle("");
        }

        //2734 rid testing
        //recipePresenter.onCallRecipeList(API_List.API_KEY, "2734");
        recipePresenter.onCallRecipeList(API_List.API_KEY, rID);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            rID = getArguments().getString("recipe_ID");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipe, container, false);
    }

    @Override
    public void onDestroyView() {
        recipePresenter.onDetach();
        unbinder.unbind();
        super.onDestroyView();
    }

    @Override
    public void onFetchDataSuccess(RecipeListModel recipeListModel) {

    }

    @Override
    public void onFetchDataSuccess(RecipeModel recipeModel) {
        recipe = recipeModel.getRecipes();

        ivRecipePicture.setImageURI(Uri.parse(recipe.getImageUrl()));
        //tbRecipeTitle.setTitle(recipe.getTitle());

        tvRecipeList.append("\n\n");
        for(String x: recipe.getIngredients())
        {
            tvRecipeList.append("- " + x + "\n\n");
        }
        tbRecipeTitle.setTitle(recipe.getTitle());
    }

    @Override
    public void onFetchDataError(String message) {
        showSnackbar(message);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void openActivityOnTokenExpire() {

    }

    @Override
    public void onError(int resId) {
        showSnackbar(Integer.toString(resId));
    }

    @Override
    public void onError(String message) {
        showSnackbar(message);
    }

    @Override
    public void showMessage(String message) {
        showSnackbar(message);
    }

    @Override
    public void showMessage(int resId) {
        showSnackbar(Integer.toString(resId));
    }

    @Override
    public boolean isNetworkConnected() {
        return false;
    }

    @Override
    public void hideKeyboard() {

    }
}
