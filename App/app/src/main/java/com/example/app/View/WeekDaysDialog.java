package com.example.app.View;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.app.Model.WeekDayRecipes;
import com.example.app.R;
import com.example.app.ViewModel.MealPlanerRecipesFragmentViewModel;

public class WeekDaysDialog extends AppCompatDialogFragment {

    private MealPlanerRecipesFragmentViewModel viewModel;
    private RadioGroup group;
    private RadioButton selectedButton;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.week_days_dialog,null);
        group = view.findViewById(R.id.weekDayRadioGroup);

        viewModel = new ViewModelProvider(this).get(MealPlanerRecipesFragmentViewModel.class);

        Bundle bundle = getArguments();

        dialog.setView(view).setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int id = group.getCheckedRadioButtonId();
                selectedButton = view.findViewById(id);
                WeekDayRecipes weekDayRecipes = new WeekDayRecipes();
                weekDayRecipes.setFK_ID_recipe(bundle.getInt("Recipe_ID"));
                weekDayRecipes.setWeekDay(selectedButton.getText().toString());
                viewModel.insertWeekDayRecipe(weekDayRecipes);
            }
        });
        dialog.setView(view).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });


        return dialog.create();
    }
}
