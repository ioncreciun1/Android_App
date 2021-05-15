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

import com.example.app.Adapters.MealPlanerRecipeCardListAdapter;
import com.example.app.R;

public class MealPlanerRecycleItemTouchHelper extends ItemTouchHelper.SimpleCallback {


    private final MealPlanerRecipeCardListAdapter mealPlanerRecipeCardListAdapter;

    public MealPlanerRecycleItemTouchHelper(MealPlanerRecipeCardListAdapter mealPlanerRecipeCardListAdapter)
    {
        super(0,ItemTouchHelper.LEFT);
        this.mealPlanerRecipeCardListAdapter = mealPlanerRecipeCardListAdapter;

    }


    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        swipeMealPlanerRecipe(mealPlanerRecipeCardListAdapter,viewHolder,direction);
    }

    private void swipeMealPlanerRecipe(MealPlanerRecipeCardListAdapter mealPlanerRecipeCardListAdapter, RecyclerView.ViewHolder viewHolder, int direction) {
        final int position = viewHolder.getAdapterPosition();
        if(direction == ItemTouchHelper.LEFT)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(mealPlanerRecipeCardListAdapter.getContext());
            builder.setTitle("Delete Task");
            builder.setMessage("Are you sure you want to delete this shopping Item");
            builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mealPlanerRecipeCardListAdapter.deleteItem(position);
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mealPlanerRecipeCardListAdapter.notifyItemChanged(viewHolder.getAdapterPosition());
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        Drawable icon = null;
        ColorDrawable background = null;
        View ItemView = viewHolder.itemView;
        int backgroundCornerOffset = 20;

        if(dX<0)
        {


                icon = ContextCompat.getDrawable(mealPlanerRecipeCardListAdapter.getContext(), R.drawable.ic_baseline_delete_forever_24);
                background = new ColorDrawable(ContextCompat.getColor(mealPlanerRecipeCardListAdapter.getContext(),R.color.red));
            int iconMargin = (ItemView.getHeight() - icon.getIntrinsicHeight()) / 2;
            int iconTop = ItemView.getTop() + iconMargin;
            int iconBottom = iconTop + icon.getIntrinsicHeight();
            if (dX < 0) { // Swiping to the left
                int iconLeft = ItemView.getRight() - iconMargin - icon.getIntrinsicWidth();
                int iconRight = ItemView.getRight() - iconMargin;
                icon.setBounds(iconLeft, iconTop, iconRight, iconBottom);

                background.setBounds(ItemView.getRight() + ((int) dX) - backgroundCornerOffset,
                        ItemView.getTop(), ItemView.getRight(), ItemView.getBottom());
            } else { // view is unSwiped
                System.out.println("I am here\n");
                background.setBounds(0, 0, 0, 0);
            }
            background.draw(c);
            icon.draw(c);

        }
        else {
            System.out.println("WTF IS HAPPENING");
        }

        }
}
