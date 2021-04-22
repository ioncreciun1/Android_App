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

import com.example.app.Model.Ingredient;
import com.example.app.R;
import com.example.app.ViewModel.CreateRecipeActivityViewModel;

public class IngredientDialog  extends AppCompatDialogFragment {
    private CreateRecipeActivityViewModel viewModel;
    private EditText ingredientName;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(CreateRecipeActivityViewModel.class);
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_ingredient,null);
        ingredientName = view.findViewById(R.id.edit_add_ingredient);
        dialog.setView(view).setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Ingredient ing = new Ingredient();
                ing.setName(ingredientName.getText().toString());
                viewModel.addIngredient(ing);

            }
        });
        return dialog.create();
    }
}
