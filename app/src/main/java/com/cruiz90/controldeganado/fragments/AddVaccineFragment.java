package com.cruiz90.controldeganado.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.cruiz90.controldeganado.R;
import com.cruiz90.controldeganado.entities.Vaccine;
import com.cruiz90.controldeganado.util.DBConnection;
import com.cruiz90.controldeganado.util.Utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddVaccineFragment extends Fragment {
    private int monthsSelected;

    public AddVaccineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_add_vaccine, container, false);

        final EditText et_name = (EditText) root.findViewById(R.id.et_name);
        final EditText et_dosePerKg = (EditText) root.findViewById(R.id.et_dosePerKg);
        final Switch s_isPregnantAllowed = (Switch) root.findViewById(R.id.s_isPregnantAllowed);
        Button b_save = (Button) root.findViewById(R.id.b_save);
        monthsSelected = 0;

        b_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String errors = "";
                if (et_name.getText().length() < 3) {
                    errors += getString(R.string.errorNameVaccineNull) + "\n";
                }
                if (et_dosePerKg.getText().length() < 1) {
                    errors += getString(R.string.errorDoseVaccineNull);
                }

                if (errors.isEmpty()) {
                    Vaccine vaccine = new Vaccine(et_name.getText().toString(), Float.parseFloat(et_dosePerKg.getText().toString()), s_isPregnantAllowed.isChecked(), monthsSelected > 0 ? monthsSelected : null);
                    DBConnection.getInstance().insert(vaccine);
                    Toast.makeText(getContext(), getString(R.string.msgSaved), Toast.LENGTH_SHORT).show();
                    getFragmentManager().beginTransaction().replace(R.id.content_frame, new ListVaccinesFragment()).commit();
                    Utils.hideKeyboard(getContext());
                }
            }
        });

        Switch s_isPeriodic = (Switch) root.findViewById(R.id.s_isPeriodic);
        final TextView tv_periodicity = (TextView) root.findViewById(R.id.tv_periodicity);

        final NumberPicker np_periodicity = (NumberPicker) root.findViewById(R.id.np_periodicity);
        np_periodicity.setMaxValue(24);
        np_periodicity.setMinValue(1);
        np_periodicity.setWrapSelectorWheel(true);

        s_isPeriodic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    tv_periodicity.setVisibility(TextView.VISIBLE);
                    np_periodicity.setVisibility(NumberPicker.VISIBLE);
                } else {
                    tv_periodicity.setVisibility(TextView.GONE);
                    np_periodicity.setVisibility(NumberPicker.GONE);
                }
            }
        });

        np_periodicity.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                monthsSelected = newVal;
            }
        });

        return root;
    }

}
