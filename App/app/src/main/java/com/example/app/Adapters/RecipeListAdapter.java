package com.example.app.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.Model.RecipeCard;
import com.example.app.R;
import com.example.app.View.RecipeFragment;

import java.util.ArrayList;
import java.util.List;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.ViewHolder> {
    private List<RecipeCard> items;
    private RecipeFragment activity;
    public RecipeListAdapter(RecipeFragment activity)
    {
        this.activity = activity;
        items = new ArrayList<>();
    }


    public List<RecipeCard> getItems() {
        return items;
    }

    public void setItems(List<RecipeCard> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recipe_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.label.setText(items.get(position).getName());
            holder.description.setText(items.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }




    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView label;
        TextView description;
        ViewHolder(View view)
        {
            super(view);
            label = view.findViewById(R.id.recipe_card_label);
            description = view.findViewById(R.id.recipe_card_description);
        }

    }

}
