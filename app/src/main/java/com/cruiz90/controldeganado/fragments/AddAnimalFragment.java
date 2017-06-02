package com.cruiz90.controldeganado.fragments;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.cruiz90.controldeganado.R;
import com.cruiz90.controldeganado.activities.MainActivity;
import com.cruiz90.controldeganado.entities.Animal;
import com.cruiz90.controldeganado.entities.AnimalDao;
import com.cruiz90.controldeganado.entities.AnimalType;
import com.cruiz90.controldeganado.util.DBConnection;

import org.greenrobot.greendao.query.QueryBuilder;
import org.joda.time.DateTime;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddAnimalFragment extends Fragment {

    private Spinner animalTypes;
    private EditText et_name, et_buyPrice, et_birthDate, et_birthWeight, et_color, et_weaningDate, et_weaningWeight, et_soldDate, et_soldWeight, et_soldPrice;
    private RadioButton rb_male;
    private Button b_save;
    private AnimalType selectedType;
    private Animal selectedMother;
    private Animal selectedFather;
    private List<Animal> females, males;

    public AddAnimalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_add_animal, container, false);

        //Definiendo los valores a mostrar en el spinner
        List<AnimalType> animalTypes = DBConnection.getInstance().loadAll(AnimalType.class);
        selectedType = animalTypes.get(0);

        //Animal temporal para madre o padre desconocidos
        final Animal unknown = new Animal();
        unknown.setName(getString(R.string.unknown));

        females = getPossibleParents(false, selectedType);
        females.add(0, unknown);

        males = getPossibleParents(true, selectedType);
        males.add(0, unknown);

        Spinner spinner = (Spinner) root.findViewById(R.id.s_animalType);

        final ArrayAdapter<AnimalType> adapter = new ArrayAdapter<>(getActivity(), R.layout.support_simple_spinner_dropdown_item, animalTypes);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //Spinner para obtener la madre
        Spinner motherSpinner = (Spinner) root.findViewById(R.id.s_mother);

        final ArrayAdapter<Animal> motherAdapter = new ArrayAdapter<>(getActivity(), R.layout.support_simple_spinner_dropdown_item, females);
        motherAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        motherSpinner.setAdapter(motherAdapter);
        motherSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedMother = (Animal) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Spinner para obtener el padre
        Spinner fatherSpinner = (Spinner) root.findViewById(R.id.s_father);

        final ArrayAdapter<Animal> fatherAdapter = new ArrayAdapter<>(getActivity(), R.layout.support_simple_spinner_dropdown_item, males);
        fatherAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        fatherSpinner.setAdapter(fatherAdapter);
        fatherSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedFather = (Animal) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedType = (AnimalType) parent.getItemAtPosition(position);
                females.clear();
                females.add(unknown);
                for (Animal a : getPossibleParents(false, selectedType)) {
                    females.add(a);
                }
                motherAdapter.notifyDataSetChanged();
                males.clear();
                males.add(unknown);
                for (Animal a : getPossibleParents(true, selectedType)) {
                    males.add(a);
                }
                fatherAdapter.notifyDataSetChanged();
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
        b_save = (Button) root.findViewById(R.id.b_save);

        et_birthDate.setOnClickListener(showCalendar(et_birthDate, Calendar.getInstance()));
        et_weaningDate.setOnClickListener(showCalendar(et_weaningDate, Calendar.getInstance()));
        et_soldDate.setOnClickListener(showCalendar(et_soldDate, Calendar.getInstance()));

        b_save.setOnClickListener(save());

        return root;
    }

    public View.OnClickListener save() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String errors = "";

                String name, color;
                Float buyPrice, birthWeight, weaningWeight, soldPrice, soldWeight;
                DateTime birthDate, weaningDate, soldDate;
                Boolean isMale;

                if (et_name.getText().length() < 1) {
                    errors += getString(R.string.errorNullName) + ".\n";
                }
                if (et_color.getText().length() < 3) {
                    errors += getString(R.string.errorNullColor) + ".\n";
                }

                name = et_name.getText().toString();
                color = et_color.getText().toString();
                buyPrice = (et_buyPrice.getText().length() > 0) ? Float.parseFloat(et_buyPrice.getText().toString()) : null;
                birthWeight = (et_birthWeight.getText().length() > 0) ? Float.parseFloat(et_birthWeight.getText().toString()) : null;
                weaningWeight = (et_weaningWeight.getText().length() > 0) ? Float.parseFloat(et_weaningWeight.getText().toString()) : null;
                soldPrice = (et_soldPrice.getText().length() > 0) ? Float.parseFloat(et_soldPrice.getText().toString()) : null;
                soldWeight = (et_soldWeight.getText().length() > 0) ? Float.parseFloat(et_soldWeight.getText().toString()) : null;

                birthDate = (et_birthDate.getText().length() > 0) ? new DateTime(et_birthDate.getTag()) : null;
                weaningDate = (et_weaningDate.getText().length() > 0) ? new DateTime(et_weaningDate.getTag()) : null;
                soldDate = (et_soldDate.getText().length() > 0) ? new DateTime(et_soldDate.getTag()) : null;

                isMale = rb_male.isChecked();

                if (errors.length() > 0) {
                    Toast.makeText(getContext(), errors, Toast.LENGTH_SHORT).show();
                } else {
                    if (selectedFather.getName().equals(getString(R.string.unknown))) {
                        selectedFather = null;
                    }
                    if (selectedMother.getName().equals(getString(R.string.unknown))) {
                        selectedMother = null;
                    }
                    Animal animal = new Animal(selectedType, name, buyPrice, birthDate, birthWeight, color, isMale, weaningDate, weaningWeight, soldDate, soldWeight, soldPrice, selectedMother, selectedFather);
                    DBConnection.getInstance().insert(animal);
                    Toast.makeText(getContext(), getString(R.string.msgSaved), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            }
        };
    }

    private void updateLabelDate(EditText et, Calendar myCalendar) {
        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
        et.setText(sdf.format(myCalendar.getTime()));
        et.setTag(myCalendar.getTimeInMillis());
    }

    private DatePickerDialog.OnDateSetListener dateSetListener(final Calendar calendar, final EditText et) {
        return new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabelDate(et, calendar);
            }
        };
    }

    private View.OnClickListener showCalendar(final EditText et, final Calendar myCalendar) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getContext(), dateSetListener(myCalendar, et), myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        };
    }

    private List<Animal> getPossibleParents(boolean isMale, AnimalType animalType) {
        QueryBuilder<Animal> queryBuilder = DBConnection.getInstance().queryBuilder(Animal.class);
        queryBuilder.where(AnimalDao.Properties.IsMale.eq(isMale), AnimalDao.Properties.AnimalTypeId.eq(animalType.getAnimalTypeId()));
        queryBuilder.orderDesc(AnimalDao.Properties.Bithdate);
        return queryBuilder.list();
    }

}
