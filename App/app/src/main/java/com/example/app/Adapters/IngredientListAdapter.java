package com.example.app.Adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.View.CreateRecipeActivity;
import com.example.app.Model.Ingredient;
import com.example.app.R;
import com.example.app.View.RecipeActivity;

import java.util.ArrayList;
import java.util.List;

public class IngredientListAdapter extends RecyclerView.Adapter<IngredientListAdapter.ViewHolder>{
    private List<Ingredient> items;
    private CreateRecipeActivity activity;
    private RecipeActivity activity1;
    public IngredientListAdapter(CreateRecipeActivity activity)
    {
        this.activity = activity;
        items = new ArrayList<>();
    }
    public IngredientListAdapter(RecipeActivity activity1)
    {
        this.activity1 = activity1;
        items = new ArrayList<>();
    }


    public List<Ingredient> getItems() {
        return items;
    }

    public void setItems(List<Ingredient> items) {
        this.items = items;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.ingredient_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.ingredient.setText(items.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }




    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView ingredient;
        ViewHolder(View view)
        {
            super(view);
            ingredient = view.findViewById(R.id.ingredient_card_text);
        }

    }
}
