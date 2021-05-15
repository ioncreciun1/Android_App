package com.example.app.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.Model.Ingredient;
import com.example.app.R;
import com.example.app.View.CreateRecipeActivity;
import com.example.app.View.IngredientDialog;
import com.example.app.View.RecipeActivity;

import java.util.ArrayList;
import java.util.List;

public class IngredientListAdapter extends RecyclerView.Adapter<IngredientListAdapter.ViewHolder>{
    private List<Ingredient> items;
    private final CreateRecipeActivity activity;
    private final RecipeActivity activity1;
    public IngredientListAdapter(CreateRecipeActivity activity)
    {
        this.activity = activity;
        items = new ArrayList<>();
        activity1 = null;
    }
    public IngredientListAdapter(RecipeActivity activity1)
    {
        this.activity1 = activity1;
        items = new ArrayList<>();
        activity = null;
    }


    public CreateRecipeActivity getCreateRecipeActivity()
    {
        return activity;
    }

    public Context createRecipeActivityContext(){
        return activity.getApplicationContext();
    }


    public RecipeActivity getRecipeActivity()
    {
        return activity1;
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

    public void deleteItem(int position)
    {
        activity.deleteIngredient(position);

    }

    public void editItem(int position)
    {
        Ingredient ingredient = items.get(position);
        IngredientDialog dialog = new IngredientDialog();
        Bundle bundle = new Bundle();
        bundle.putString("IngredientName",ingredient.getName());
        bundle.putInt("IngredientPosition",position);
        dialog.setArguments(bundle);
        dialog.show(activity.getSupportFragmentManager(),"Ingredient Dialog");
        notifyItemChanged(position);
    }

    public void AddToShoppingList(int position)
    {
        Ingredient ingredient = items.get(position);
        activity1.addToShoppingList(ingredient.getName());
        Context context = getRecipeActivity().getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast.makeText(context, "Ingredient added to shopping list", duration).show();
    }

    public Context getRecipeContext() {
        return activity1.getApplicationContext();
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
