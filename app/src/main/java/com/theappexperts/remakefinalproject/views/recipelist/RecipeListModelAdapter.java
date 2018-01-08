package com.theappexperts.remakefinalproject.views.recipelist;

import android.content.Context;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.theappexperts.remakefinalproject.MainActivity;
import com.theappexperts.remakefinalproject.R;
import com.theappexperts.remakefinalproject.data.network.model.Recipe;
import com.theappexperts.remakefinalproject.views.search.SearchFragment;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by TheAppExperts on 02/01/2018.
 */

public class RecipeListModelAdapter extends RecyclerView.Adapter<RecipeListModelAdapter.MyViewHolder> {

    private List<Recipe> recipesList;
    private Context context;

    public RecipeListModelAdapter(List<Recipe> recipesList, Context context) {
        this.recipesList = recipesList;
        this.context = context;
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
    }

    @Override
    public RecipeListModelAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recipe_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipeListModelAdapter.MyViewHolder holder, int position) {
        holder.tvTitle.setText(recipesList.get(position).getTitle());
        holder.tvPublisher.setText(recipesList.get(position).getPublisher());
        holder.ivFood.setImageURI(Uri.parse(recipesList.get(position).getImageUrl()));
        holder.btnGetRecipes.setTag(recipesList.get(position).getRecipeId());
    }

    @Override
    public int getItemCount() {
        return recipesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        Unbinder unbinder;

        @BindView(R.id.tvTitle)
        TextView tvTitle;

        @BindView(R.id.tvPublisher)
        TextView tvPublisher;

        @BindView(R.id.ivFood)
        SimpleDraweeView ivFood;

        @BindView(R.id.btn_GetRecipes)
        Button btnGetRecipes;

        @OnClick(R.id.btn_GetRecipes)
        public void toRecipeScreen(Button button) {
            EventBus.getDefault().post(new GetRecipeEvent(""+button.getTag()));
        }

        public MyViewHolder(View itemView) {
            super(itemView);
            unbinder = ButterKnife.bind(this, itemView);
        }
    }
}
