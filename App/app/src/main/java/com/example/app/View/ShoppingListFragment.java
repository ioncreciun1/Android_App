package com.example.app.View;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.Adapters.ShoppingListAdapter;
import com.example.app.Model.ShoppingItem;
import com.example.app.R;
import com.example.app.ViewModel.ShoppingListFragmentViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;



public class ShoppingListFragment extends Fragment {

    private RecyclerView shoppingList;
    private ShoppingListAdapter adapter;
    private FloatingActionButton fabButton;
    private ShoppingListFragmentViewModel viewModel;
    public ShoppingListFragment() {

    }

    public void updateStatus(int status,int id)
    {
        viewModel.updateStatus(status, id);
    }

    public void remove(ShoppingItem item)
    {
        viewModel.remove(item);
    }



    public void init(View root)
    {
        shoppingList = (RecyclerView)root.findViewById(R.id.shoppingRecycleView);
        shoppingList.hasFixedSize();
        shoppingList.setLayoutManager(new LinearLayoutManager(root.getContext()));
        adapter = new ShoppingListAdapter(this);
        shoppingList.setAdapter(adapter);
        viewModel  = new ViewModelProvider(this).get(ShoppingListFragmentViewModel.class);
        viewModel.getAllShoppingItems().observe(getViewLifecycleOwner(),shoppingItems -> {
            adapter.setItems(shoppingItems);
        });

        //FLOATING BUTTON
        fabButton = root.findViewById(R.id.fab);
        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShoppingDialog dialog = new ShoppingDialog();
                dialog.show(getChildFragmentManager(),"Add Item");
            }
        });


        //Used for swipe
        ItemTouchHelper helper = new ItemTouchHelper(new RecycleItemTouchHelper(adapter));
        helper.attachToRecyclerView(shoppingList);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_shopping_list, container,false);
        init(root);
        return  root;
    }
}