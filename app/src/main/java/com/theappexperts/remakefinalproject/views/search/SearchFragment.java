package com.theappexperts.remakefinalproject.views.search;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.theappexperts.remakefinalproject.R;
import com.theappexperts.remakefinalproject.data.AppDataManager;
import com.theappexperts.remakefinalproject.data.network.constant.API_List;
import com.theappexperts.remakefinalproject.data.network.model.RecipeListModel;
import com.theappexperts.remakefinalproject.views.map.MapFragment;
import com.theappexperts.remakefinalproject.views.recipe.RecipeFragment;
import com.theappexperts.remakefinalproject.views.recipelist.GetRecipeEvent;
import com.theappexperts.remakefinalproject.views.recipelist.RecipeListModelAdapter;
import com.theappexperts.remakefinalproject.views.ui.utils.rx.AppSchedulerProvider;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.CompositeDisposable;

public class SearchFragment extends Fragment implements ISearchMvpView{

    SearchPresenter<SearchFragment> searchPresenter;

    @BindView(R.id.rvListOfRecipesSearch)
    RecyclerView recyclerViewSearch;

    @BindView(R.id.search_q)
    EditText tvSearch;

    //@BindView(R.id.btn_search)
    //Button btnSearch;

    @OnClick(R.id.btn_search)
    public void toSearch(Button button) {
        if(!tvSearch.getText().toString().isEmpty() && tvSearch.getText().toString().length() >= 3) {
            searchPresenter.onCallRecipeModelList(API_List.API_KEY, tvSearch.getText().toString());
        }
    }

    void initPresenter(){
        searchPresenter = new SearchPresenter<>(new AppDataManager(), new AppSchedulerProvider(), new CompositeDisposable());
        searchPresenter.onAttach(this);
    }

    public SearchFragment() {
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        recyclerViewSearch.setLayoutManager(new LinearLayoutManager(getContext()));
        initPresenter();

        tvSearch.requestFocus();

        EventBus.getDefault().register(this);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetRecipeEvent(GetRecipeEvent event) {
        //recipeListPresenter.onCallRecipeList(Constants.API_KEY, event.rId);
        //showSnackbar(event.rId);
        //recipeListPresenter.onDetach();
        Bundle bundle = new Bundle();
        bundle.putString("recipe_ID", event.rId);

        FragmentManager fragmentManager;
        fragmentManager = getActivity().getSupportFragmentManager();

        RecipeFragment recipeFragment = new RecipeFragment();
        recipeFragment.setArguments(bundle);

        fragmentManager.beginTransaction()
                .add(R.id.frag_container, recipeFragment)
                .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    public void onFetchDataSuccess(RecipeListModel recipeListModel) {
        recyclerViewSearch.setAdapter(new RecipeListModelAdapter(recipeListModel.getRecipes(), getContext()));
    }

    @Override
    public void onFetchDataError(String message) {

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

    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showMessage(int resId) {

    }

    @Override
    public boolean isNetworkConnected() {
        return false;
    }

    @Override
    public void hideKeyboard() {

    }
}
