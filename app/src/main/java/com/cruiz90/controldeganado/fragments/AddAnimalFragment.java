package com.cruiz90.controldeganado.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.cruiz90.controldeganado.R;
import com.cruiz90.controldeganado.activities.MainActivity;
import com.cruiz90.controldeganado.entities.Animal;
import com.cruiz90.controldeganado.entities.AnimalType;
import com.cruiz90.controldeganado.util.DBConnection;

import org.joda.time.DateTime;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddAnimalFragment extends Fragment {

    private Spinner animalTypes;
    private EditText et_name, et_buyPrice, et_birthDate, et_birthWeight, et_color, et_weaningDate, et_weaningWeight, et_soldDate, et_soldWeight, et_soldPrice;
    private RadioButton rb_male, rb_female;
    private Button b_save;
    private AnimalType selectedType;

    public AddAnimalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_add_animal, container, false);

        selectedType = null;

        Spinner spinner = (Spinner) root.findViewById(R.id.s_animalType);
        //Definiendo los valores a mostrar en el spinner
        List<AnimalType> animalTypes = DBConnection.getInstance().loadAll(AnimalType.class);
        ArrayAdapter<AnimalType> adapter = new ArrayAdapter<>(getActivity(), R.layout.support_simple_spinner_dropdown_item, animalTypes);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedType = (AnimalType) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        et_name = (EditText) root.findViewById(R.id.et_name);
        et_buyPrice = (EditText) root.findViewById(R.id.et_buyPrice);
        et_birthDate = (EditText) root.findViewById(R.id.et_birthDate);
        et_birthWeight = (EditText) root.findViewById(R.id.et_birthWeight);
        et_color = (EditText) root.findViewById(R.id.et_color);
        et_weaningDate = (EditText) root.findViewById(R.id.et_weaningDate);
        et_weaningWeight = (EditText) root.findViewById(R.id.et_weaningWeight);
        et_soldDate = (EditText) root.findViewById(R.id.et_soldDate);
        et_soldPrice = (EditText) root.findViewById(R.id.et_soldPrice);
        et_soldWeight = (EditText) root.findViewById(R.id.et_soldWeight);
        rb_male = (RadioButton) root.findViewById(R.id.rb_male);
        rb_female = (RadioButton) root.findViewById(R.id.rb_female);
        b_save = (Button) root.findViewById(R.id.b_save);

        b_save.setOnClickListener(save());

        return root;
    }

    public View.OnClickListener save(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String errors = "";

                String name, color;
                Float buyPrice, birthWeight, weaningWeight, soldPrice, soldWeight;
                DateTime birthDate, weaningDate, soldDate;
                Boolean isMale;

                if(et_name.getText().length() < 1){
                    errors += "Debe escribir el nombre del animal.\n";
                }
                if (et_color.getText().length()<3){
                    errors += "Debe escribir el color del animal.\n";
                }

                name = et_name.getText().toString();
                color = et_color.getText().toString();
                buyPrice = (et_buyPrice.getText().length()>0)?Float.parseFloat(et_buyPrice.getText().toString()):null;
                birthWeight = (et_birthWeight.getText().length()>0)?Float.parseFloat(et_birthWeight.getText().toString()):null;
                weaningWeight = (et_weaningWeight.getText().length()>0)?Float.parseFloat(et_weaningWeight.getText().toString()):null;
                soldPrice = (et_soldPrice.getText().length()>0)?Float.parseFloat(et_soldPrice.getText().toString()):null;
                soldWeight = (et_soldWeight.getText().length()>0)?Float.parseFloat(et_soldWeight.getText().toString()):null;

                birthDate=weaningDate=soldDate=null;

                isMale = rb_male.isChecked();

                if (errors.length()>0){
                    Toast.makeText(getContext(), errors, Toast.LENGTH_SHORT).show();
                }else{
                    Animal animal = new Animal(selectedType, name, buyPrice, birthDate, birthWeight, color, isMale, weaningDate, weaningWeight, soldDate, soldWeight, soldPrice);
                    DBConnection.getInstance().insert(animal);
                    Toast.makeText(getContext(), "Animal guardado correctamente!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            }
        };
    }

}
