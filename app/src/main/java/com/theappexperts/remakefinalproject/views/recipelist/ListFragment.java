package com.theappexperts.remakefinalproject.views.recipelist;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.theappexperts.remakefinalproject.R;
import com.theappexperts.remakefinalproject.data.AppDataManager;
import com.theappexperts.remakefinalproject.data.network.constant.API_List;
import com.theappexperts.remakefinalproject.data.network.model.Recipe;
import com.theappexperts.remakefinalproject.data.network.model.RecipeListModel;
import com.theappexperts.remakefinalproject.data.network.model.RecipeModel;
import com.theappexperts.remakefinalproject.views.recipe.RecipeFragment;
import com.theappexperts.remakefinalproject.views.ui.utils.rx.AppSchedulerProvider;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.CompositeDisposable;

public class ListFragment extends Fragment implements IRecipeListMvpView{

    //pagination stuff
    private int PAGE = 1;
    List<Recipe> savedList = new ArrayList<>();
    boolean callApi = false;
    //end of

    RecipeListPresenter<ListFragment> recipeListPresenter;

    @BindView(R.id.rvListOfRecipes)
    RecyclerView recyclerView;

    public ListFragment() {
        // Required empty public constructor
    }

    public void initData()
    {
        recipeListPresenter = new RecipeListPresenter<>(new AppDataManager(), new AppSchedulerProvider(), new CompositeDisposable());
        attach();
    }

    public void attach()
    {
        recipeListPresenter.onAttach(this);
    }

    @Override
    public void onDestroyView() {
        recipeListPresenter.onDetach();
        super.onDestroyView();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        initData();

        if(savedInstanceState == null){
            EventBus.getDefault().register(this);
            recipeListPresenter.onCallRecipeModelList(API_List.API_KEY, PAGE);

        }else{
            recyclerView.setAdapter(new RecipeListModelAdapter(savedList, getContext()));
        }

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1) && callApi) {
                    PAGE += 1;
                    recipeListPresenter.onCallRecipeModelList(API_List.API_KEY, PAGE);
                    callApi = false;
                }
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    private void showSnackbar(final String text) {
        View container = getView();
        if (container != null) {
            Snackbar.make(container, text, Snackbar.LENGTH_LONG).show();
        }
    }

    /**
     * eventbus that listens to when a user presses get recipe and pings the API to get the recipe
     * @param event
     */
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
    public void onFetchDataSuccess(RecipeListModel recipeListModel) {
        recyclerView.setAdapter(new RecipeListModelAdapter(recipeListModel.getRecipes(), getContext()));

        if(PAGE == 1) {
            recyclerView.setAdapter(new RecipeListModelAdapter(recipeListModel.getRecipes(), getContext()));
            for(Recipe x : recipeListModel.getRecipes())
            {
                savedList.add(x);
                callApi = true;
            }
        }else{//change this
            int nowPos = savedList.size()-1;
            for(Recipe x : recipeListModel.getRecipes())
            {
                savedList.add(x);
                callApi = true;
            }
            recyclerView.swapAdapter(new RecipeListModelAdapter(savedList, getContext()), true);
            recyclerView.scrollToPosition(nowPos);
        }

    }

    @Override
    public void onFetchDataSuccess(RecipeModel recipeModel) {

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
