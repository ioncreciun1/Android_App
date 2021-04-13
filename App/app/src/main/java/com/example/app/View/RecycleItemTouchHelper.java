package com.example.app.View;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.Adapters.ShoppingListAdapter;
import com.example.app.R;

public class RecycleItemTouchHelper extends ItemTouchHelper.SimpleCallback {

    private ShoppingListAdapter adapter;
    public RecycleItemTouchHelper(ShoppingListAdapter adapter)
    {
        super(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT);
        this.adapter = adapter;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        final int position = viewHolder.getAdapterPosition();
        if(direction == ItemTouchHelper.LEFT)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(adapter.getContext());
            builder.setTitle("Delete Task");
            builder.setMessage("Are you sure you want to delete this shopping Item");
            builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    adapter.deleteItem(position);
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    adapter.notifyItemChanged(viewHolder.getAdapterPosition());
                }
            });
            AlertDialog dialog = builder.create();
            builder.show();
        }
        else
        {
        adapter.EditItem(position);
        }

    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        Drawable icon;
        ColorDrawable background;
        View ItemView = viewHolder.itemView;
        int backgroundCornerOffset = 20;
        if (dX > 0
        ) {
            icon = ContextCompat.getDrawable(adapter.getContext(), R.drawable.ic_baseline_edit_24);
            background = new ColorDrawable(ContextCompat.getColor(adapter.getContext(),R.color.CaribbeanGreen));
        }
        else
        {
            icon = ContextCompat.getDrawable(adapter.getContext(), R.drawable.ic_baseline_delete_forever_24);
            background = new ColorDrawable(ContextCompat.getColor(adapter.getContext(),R.color.red));
        }
        int iconMargin = (ItemView.getHeight()-icon.getIntrinsicHeight())/2;
        int iconTop = ItemView.getTop()+ iconMargin;
        int iconBottom = iconTop+icon.getIntrinsicHeight();
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
