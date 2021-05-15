

package com.example.app.View;

        import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.Adapters.IngredientListAdapter;
import com.example.app.R;

public class IngredientsViewRecipeRecycleItemTouchHelper extends ItemTouchHelper.SimpleCallback {

    private final IngredientListAdapter adapter;
    public IngredientsViewRecipeRecycleItemTouchHelper(IngredientListAdapter adapter)
    {

        super(0,ItemTouchHelper.RIGHT);
        this.adapter = adapter;
    }


    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    public void swipeCreateRecipeIngredientListAdapter(IngredientListAdapter adapter, RecyclerView.ViewHolder viewHolder, int direction)
    {
        final int position = viewHolder.getAdapterPosition();
        if(direction == ItemTouchHelper.RIGHT)
        {
           adapter.AddToShoppingList(position);
           adapter.notifyDataSetChanged();
        }


    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        swipeCreateRecipeIngredientListAdapter(adapter,viewHolder,direction);

    }



    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        Drawable icon ;
        ColorDrawable background ;
        View ItemView = viewHolder.itemView;
        int backgroundCornerOffset = 20;
        if (dX > 0
        ) {
            icon = ContextCompat.getDrawable(adapter.getRecipeContext(), R.drawable.ic_baseline_add_shopping_cart_24);
            background = new ColorDrawable(ContextCompat.getColor(adapter.getRecipeContext(),R.color.CaribbeanGreen));
            int iconMargin = (ItemView.getHeight() - icon.getIntrinsicHeight()) / 2;
            int iconTop = ItemView.getTop() + iconMargin;
            int iconBottom = iconTop + icon.getIntrinsicHeight();


                int iconLeft = ItemView.getLeft() + iconMargin;
                int iconRight = ItemView.getLeft() + iconMargin + icon.getIntrinsicWidth();
                icon.setBounds(iconLeft, iconTop, iconRight, iconBottom);

                background.setBounds(ItemView.getLeft(), ItemView.getTop(),
                        ItemView.getLeft() + ((int) dX) + backgroundCornerOffset, ItemView.getBottom());


            background.draw(c);
            icon.draw(c);
        }




    }
}