package com.example.app.View;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.Adapters.RecipeCardListAdapter;
import com.example.app.Adapters.ShoppingListAdapter;
import com.example.app.R;

public class RecycleItemTouchHelper extends ItemTouchHelper.SimpleCallback {

    private final ShoppingListAdapter shoppingListAdapter;
    private final RecipeCardListAdapter recipeCardListAdapter;
    public RecycleItemTouchHelper(ShoppingListAdapter adapter)
    {
        super(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT);
        this.shoppingListAdapter = adapter;
        recipeCardListAdapter = null;
    }
    public RecycleItemTouchHelper(RecipeCardListAdapter adapter)
    {
        super(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT);
        this.shoppingListAdapter = null;
        recipeCardListAdapter = adapter;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    public void swipeRecipeCardList(RecipeCardListAdapter adapter,RecyclerView.ViewHolder viewHolder,int direction)
    {
        final int position = viewHolder.getAdapterPosition();
        if(direction == ItemTouchHelper.LEFT)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(adapter.getContext());
            builder.setTitle("Delete Recipe");
            builder.setMessage("Are you sure you want to delete this Recipe");
            builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    adapter.deleteItem(position);
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            adapter.notifyItemChanged(viewHolder.getAdapterPosition());
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        else
        {
            adapter.editItem(position);
        }

    }
    public void swipeShoppingItem(ShoppingListAdapter adapter, RecyclerView.ViewHolder viewHolder,int direction)
    {
        final int position = viewHolder.getAdapterPosition();
        if(direction == ItemTouchHelper.LEFT)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(shoppingListAdapter.getContext());
            builder.setTitle("Delete Shopping Item");

            builder.setMessage("Are you sure you want to delete " +  shoppingListAdapter.getItems().get(position).getName() + " ?");
            builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    shoppingListAdapter.deleteItem(position);
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    shoppingListAdapter.notifyItemChanged(viewHolder.getAdapterPosition());
                }
            });
            shoppingListAdapter.notifyItemChanged(viewHolder.getAdapterPosition());
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        else
        {
            shoppingListAdapter.EditItem(position);
        }


    }
    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
       if(recipeCardListAdapter!=null)
       {
           swipeRecipeCardList(recipeCardListAdapter,viewHolder,direction);
       }
       else if(shoppingListAdapter!=null) {
           swipeShoppingItem(shoppingListAdapter, viewHolder, direction);
       }

    }



    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        Drawable icon = null;
        ColorDrawable background = null;
        View ItemView = viewHolder.itemView;
        int backgroundCornerOffset = 20;
        if (dX > 0
        ) {
            if(recipeCardListAdapter != null)
            {
                icon = ContextCompat.getDrawable(recipeCardListAdapter.getContext(), R.drawable.ic_baseline_edit_24);
                background = new ColorDrawable(ContextCompat.getColor(recipeCardListAdapter.getContext(),R.color.CaribbeanGreen));

            }
            else if(shoppingListAdapter!=null)
            {
                icon = ContextCompat.getDrawable(shoppingListAdapter.getContext(), R.drawable.ic_baseline_edit_24);
                background = new ColorDrawable(ContextCompat.getColor(shoppingListAdapter.getContext(),R.color.CaribbeanGreen));
            }

        }
        else
        {
            if(recipeCardListAdapter != null)
            {
                icon = ContextCompat.getDrawable(recipeCardListAdapter.getContext(), R.drawable.ic_baseline_delete_forever_24);
                background = new ColorDrawable(ContextCompat.getColor(recipeCardListAdapter.getContext(),R.color.red));

            }
            else if (shoppingListAdapter != null)
            {
                icon = ContextCompat.getDrawable(shoppingListAdapter.getContext(), R.drawable.ic_baseline_delete_forever_24);
                background = new ColorDrawable(ContextCompat.getColor(shoppingListAdapter.getContext(),R.color.red));

            }


        }
        if(icon!=null) {
            int iconMargin = (ItemView.getHeight() - icon.getIntrinsicHeight()) / 2;
            int iconTop = ItemView.getTop() + iconMargin;
            int iconBottom = iconTop + icon.getIntrinsicHeight();
            if (dX > 0) { // Swiping to the right
                int iconLeft = ItemView.getLeft() + iconMargin;
                int iconRight = ItemView.getLeft() + iconMargin + icon.getIntrinsicWidth();
                icon.setBounds(iconLeft, iconTop, iconRight, iconBottom);

                background.setBounds(ItemView.getLeft(), ItemView.getTop(),
                        ItemView.getLeft() + ((int) dX) + backgroundCornerOffset, ItemView.getBottom());
            } else if (dX < 0) { // Swiping to the left
                int iconLeft = ItemView.getRight() - iconMargin - icon.getIntrinsicWidth();
                int iconRight = ItemView.getRight() - iconMargin;
                icon.setBounds(iconLeft, iconTop, iconRight, iconBottom);

                background.setBounds(ItemView.getRight() + ((int) dX) - backgroundCornerOffset,
                        ItemView.getTop(), ItemView.getRight(), ItemView.getBottom());
            } else { // view is unSwiped
                background.setBounds(0, 0, 0, 0);
            }

            background.draw(c);
            icon.draw(c);
        }
    }
}
