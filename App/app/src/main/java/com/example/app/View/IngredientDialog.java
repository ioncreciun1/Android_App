package com.example.app.View;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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
        Bundle bundle = getArguments();

        if(bundle!=null)
            ingredientName.setText(bundle.getString("IngredientName"));

        dialog.setView(view).setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Ingredient ing = new Ingredient();
                ing.setName(ingredientName.getText().toString());
                if(ingredientName.getText().length()>0)
                {
                    if (bundle != null) {
                        viewModel.updateIngredient(bundle.getInt("IngredientPosition"), ingredientName.getText().toString());
                    } else {
                        System.out.println("Ingredient Added");
                        viewModel.addIngredient(ing);
                    }
                }
                else
                {
                        Context context = getContext();
                        int duration = Toast.LENGTH_LONG;
                        Toast.makeText(context, "No Ingredient Added", duration).show();
                }

            }
        });
        return dialog.create();
    }
}
