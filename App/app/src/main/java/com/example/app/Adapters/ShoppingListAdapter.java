package com.example.app.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.recyclerview.widget.RecyclerView;

import com.example.app.Model.ShoppingItem;
import com.example.app.R;
import com.example.app.View.ShoppingDialog;
import com.example.app.View.ShoppingListFragment;

import java.util.ArrayList;
import java.util.List;

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ViewHolder> {

    private List<ShoppingItem> items;
    private ShoppingListFragment activity;
   // private ShoppingListFragmentViewModel viewModel;

    public ShoppingListAdapter(ShoppingListFragment activity)
    {
        this.activity = activity;
        items = new ArrayList<>();
        //viewModel   = new ViewModelProvider(activity).get(ShoppingListFragmentViewModel.class);
    }

    public void onBindViewHolder(ViewHolder holder, int position)
    {
        ShoppingItem item = items.get(position);
        holder.item.setText(item.getName());
        holder.item.setChecked(item.getStatus()!=0);
        holder.item.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
/*
                    if(isChecked)
                        activity.updateStatus(1,item.getId());
                    else
                    {
                        activity.updateStatus(0,item.getId());
                    }*/
            }
        });
    }

    public List<ShoppingItem> getItems() {
        return items;
    }
    public Context getContext()
    {
        return activity.getContext();
    }

    public void setItems(List<ShoppingItem> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void deleteItem(int position)
    {
        ShoppingItem item = items.get(position);
        activity.remove(item);
        items.remove(item);
        notifyItemRemoved(position);
    }
    public void EditItem(int position)
    {
        ShoppingItem item = items.get(position);
        ShoppingDialog dialog = new ShoppingDialog();
        Bundle bundle = new Bundle();
        bundle.putString("ItemName",item.getName());
        bundle.putString("ItemID",Integer.toString(item.getId()));
        bundle.putString("ItemStatus",Integer.toString(item.getStatus()));
        dialog.setArguments(bundle);
        dialog.show(activity.getParentFragmentManager(),"Text");
        notifyItemChanged(position);
    }
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.shopping_item, parent, false);
        return new ViewHolder(view);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        CheckBox item;
        ViewHolder(View view)
        {
            super(view);
            item = view.findViewById(R.id.shopping_item_checkbox);

        }

    }
}
