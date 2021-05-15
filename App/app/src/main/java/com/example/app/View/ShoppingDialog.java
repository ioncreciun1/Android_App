package com.example.app.View;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.app.Model.ShoppingItem;
import com.example.app.R;
import com.example.app.ViewModel.ShoppingListFragmentViewModel;

public class ShoppingDialog  extends AppCompatDialogFragment {
    private ShoppingListFragmentViewModel viewModel;
    private EditText editText;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog_shopping_item,null);
        editText = view.findViewById(R.id.edit_item_shopping_add);

        Bundle bundle = getArguments();

        viewModel = new ViewModelProvider(this).get(ShoppingListFragmentViewModel.class);

        //For Editing Shopping Item
        if(bundle!=null)
            editText.setText(bundle.getString("ItemName"));

        dialog.setView(view).setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ShoppingItem item = new ShoppingItem(editText.getText().toString());
                if(bundle!=null)
                {
                    item.setStatus(Integer.parseInt(bundle.getString("ItemStatus")));
                    item.setId(Integer.parseInt(bundle.getString("ItemID")));
                    item.setName(editText.getText().toString());
                    viewModel.update(item);
                }
                else {
                    viewModel.insert(item);
                }
            }
        });


        return dialog.create();
    }
}

