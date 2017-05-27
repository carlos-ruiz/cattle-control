package com.cruiz90.controldeganado.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.cruiz90.controldeganado.R;
import com.cruiz90.controldeganado.entities.AnimalType;
import com.cruiz90.controldeganado.util.DBConnection;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddAnimalFragment extends Fragment {

    private Spinner animalTypes;
    private EditText et_name, et_buyPrice, et_bithDate, et_birthWeight, et_color, et_weaningDate, et_weaningWeight, et_soldDate, et_soldWeight, et_soldPrice;
    private RadioButton rb_male, rb_female;

    public AddAnimalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_add_animal, container, false);

        Spinner spinner = (Spinner) root.findViewById(R.id.s_animalType);
        List<AnimalType> animalTypes = DBConnection.getInstance().loadAll(AnimalType.class);
        ArrayAdapter<AnimalType> adapter = new ArrayAdapter<>(getActivity(), R.layout.support_simple_spinner_dropdown_item, animalTypes);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), "Pos: " + position + " - Id: " + id, Toast.LENGTH_SHORT).show();
                Object item = parent.getItemAtPosition(position);
                Long idSeleccionado = ((AnimalType) item).getAnimalTypeId();
                String nombre = ((AnimalType) item).getName();
                Toast.makeText(getContext(), nombre, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return root;
    }

}
