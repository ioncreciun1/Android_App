package com.example.app.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.Model.RecipeCard;
import com.example.app.R;
import com.example.app.View.CreateRecipeActivity;
import com.example.app.View.RecipeActivity;
import com.example.app.View.RecipeFragment;

import java.util.ArrayList;
import java.util.List;

public class RecipeCardListAdapter extends RecyclerView.Adapter<RecipeCardListAdapter.ViewHolder> {
    private List<RecipeCard> items;
    private final RecipeFragment activity;
    public RecipeCardListAdapter(RecipeFragment activity)
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
            holder.label.setText(items.get(position).getTitle());
            holder.description.setText(items.get(position).getDescription());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openItem(position);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    RecipeCard card = items.get(position);
                    openDialog(card.getID_recipe());
                    return false;
                }
            });
    }

    private void openDialog(int id) {
            activity.openWeekDaysDialog(id);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public Context getContext()
    {
        return activity.getContext();
    }

    public void deleteItem(int position)
    {
        RecipeCard card = items.get(position);
        try
        {
            activity.deleteItem(card.getID_recipe());
        }catch (NullPointerException ignored)
        {
            System.out.println("Item not Deleted\n");
        }
        items.remove(position);

    }
    public void editItem(int position)
    {
        RecipeCard card = items.get(position);
        Context context = activity.getContext();
        Class destination = CreateRecipeActivity.class;
        Intent intent = new Intent(context, destination);
        intent.putExtra("RecipeID",card.getID_recipe());
        notifyItemChanged(position);

        try
        {
            activity.startActivity(intent);
        }catch (NullPointerException e)
        {
            System.out.println("Activity Not Started\n");
        }

    }
    public void openItem(int position)
    {
        RecipeCard card = items.get(position);
        Context context = activity.getContext();
        Class destination = RecipeActivity.class;
        Intent intent = new Intent(context, destination);
        intent.putExtra("RecipeID",card.getID_recipe());

        try
        {
                activity.startActivity(intent);
        }catch (NullPointerException e)
        {
            System.out.println("Activity Not started  on open\n");
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder
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
