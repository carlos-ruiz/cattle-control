package com.cruiz90.controldeganado.fragments;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.cruiz90.controldeganado.R;
import com.cruiz90.controldeganado.activities.MainActivity;
import com.cruiz90.controldeganado.entities.Animal;
import com.cruiz90.controldeganado.entities.AnimalDao;
import com.cruiz90.controldeganado.entities.AnimalType;
import com.cruiz90.controldeganado.util.DBConnection;

import org.greenrobot.greendao.query.QueryBuilder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsAnimalFragment extends Fragment {

    private static final String ARG_ANIMAL_ID = "animal_id";
    private Long animalId;
    private AnimalType selectedType;
    private Animal animal, selectedMother, selectedFather;
    private EditText et_name, et_buyPrice, et_birthDate, et_birthWeight, et_color, et_weaningDate, et_weaningWeight, et_soldDate, et_soldWeight, et_soldPrice;
    private Spinner s_animalType, s_mother, s_father;
    private RadioButton rb_male, rb_female;
    private Button b_save, b_edit;
    private Switch s_edit;
    private List<Animal> males, females;

    public DetailsAnimalFragment() {
        // Required empty public constructor
    }

    /**
     * @param animalId Animal ID. Id to show details
     * @return A new instance of fragment DetailsAnimalFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailsAnimalFragment newInstance(Long animalId) {
        DetailsAnimalFragment fragment = new DetailsAnimalFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_ANIMAL_ID, animalId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (getArguments() != null) {
            this.animalId = getArguments().getLong(ARG_ANIMAL_ID);
        }
        animal = DBConnection.getInstance().load(Animal.class, animalId);
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_add_animal, container, false);

        //inicializando edit texts
        s_animalType = (Spinner) root.findViewById(R.id.s_animalType);
        et_name = (EditText) root.findViewById(R.id.et_name);
        et_buyPrice = (EditText) root.findViewById(R.id.et_buyPrice);
        et_birthDate = (EditText) root.findViewById(R.id.et_birthDate);
        et_birthWeight = (EditText) root.findViewById(R.id.et_birthWeight);
        et_color = (EditText) root.findViewById(R.id.et_color);
        et_weaningDate = (EditText) root.findViewById(R.id.et_weaningDate);
        et_weaningWeight = (EditText) root.findViewById(R.id.et_weaningWeight);
        et_soldDate = (EditText) root.findViewById(R.id.et_soldDate);
        et_soldWeight = (EditText) root.findViewById(R.id.et_soldWeight);
        et_soldPrice = (EditText) root.findViewById(R.id.et_soldPrice);
        s_mother = (Spinner) root.findViewById(R.id.s_mother);
        s_father = (Spinner) root.findViewById(R.id.s_father);
        rb_male = (RadioButton) root.findViewById(R.id.rb_male);
        rb_female = (RadioButton) root.findViewById(R.id.rb_female);
        b_save = (Button) root.findViewById(R.id.b_save);
        b_edit = (Button) root.findViewById(R.id.b_edit);
        s_edit = (Switch) root.findViewById(R.id.s_edit);
        s_edit.setVisibility(View.VISIBLE);

        setFieldsEditable(false);

        s_edit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setFieldsEditable(isChecked);
            }
        });

        b_edit.setOnClickListener(saveChanges());

        //cargando datos
        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat);

        et_name.setText(animal.getName());
        et_buyPrice.setText(animal.getBuyPrice() == null ? "" : animal.getBuyPrice().toString());
        et_birthDate.setText(animal.getBithdate() == null ? "" : sdf.format(animal.getBithdate()));
        et_birthWeight.setText(animal.getBirthWeight() == null ? "" : animal.getBirthWeight().toString());
        et_color.setText(animal.getColor());
        et_weaningDate.setText(animal.getWeaningdate() == null ? "" : sdf.format(animal.getWeaningdate()));
        et_weaningWeight.setText(animal.getWeaningWeight() == null ? "" : animal.getWeaningWeight().toString());
        et_soldDate.setText(animal.getSolddate() == null ? "" : sdf.format(animal.getSolddate()));
        et_soldWeight.setText(animal.getSoldWeight() == null ? "" : animal.getSoldWeight().toString());
        et_soldPrice.setText(animal.getSoldPrice() == null ? "" : animal.getSoldPrice().toString());

        rb_female.setChecked(!animal.getIsMale());
        rb_male.setChecked(animal.getIsMale());

        Spinner spinner = (Spinner) root.findViewById(R.id.s_animalType);
        //Definiendo los valores a mostrar en el spinner
        List<AnimalType> animalTypes = DBConnection.getInstance().loadAll(AnimalType.class);
        ArrayAdapter<AnimalType> adapter = new ArrayAdapter<>(getActivity(), R.layout.support_simple_spinner_dropdown_item, animalTypes);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //Animal temporal para madre o padre desconocidos
        final Animal unknown = new Animal();
        unknown.setName(getString(R.string.unknown));

        selectedType = animalTypes.get(0);
        females = getPossibleParents(false, selectedType);
        females.add(0, unknown);
        males = getPossibleParents(true, selectedType);
        males.add(0, unknown);

        //Spinner para obtener la madre
        Spinner motherSpinner = (Spinner) root.findViewById(R.id.s_mother);
        //Definiendo los valores a mostrar en el spinner

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

        s_animalType.setSelection(getIndex(s_animalType, animal.getAnimalType()));
        s_mother.setSelection(getIndex(s_mother, animal.getMother()));
        s_father.setSelection(getIndex(s_father, animal.getFather()));

        et_birthDate.setOnClickListener(showCalendar(et_birthDate, Calendar.getInstance()));
        et_weaningDate.setOnClickListener(showCalendar(et_weaningDate, Calendar.getInstance()));
        et_soldDate.setOnClickListener(showCalendar(et_soldDate, Calendar.getInstance()));

        return root;
    }

    private int getIndex(Spinner spinner, Object animalType) {
        int index = 0;

        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).equals(animalType)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public void setFieldsEditable(boolean isEditable) {
        //Inhabilitando controles
        s_animalType.setEnabled(isEditable);
        s_mother.setEnabled(isEditable);
        s_father.setEnabled(isEditable);
        rb_female.setClickable(isEditable);
        rb_male.setClickable(isEditable);
        if (isEditable) {
            b_edit.setVisibility(Button.VISIBLE);
            et_name.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);
            et_buyPrice.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
            et_birthDate.setInputType(InputType.TYPE_DATETIME_VARIATION_DATE);
            et_birthWeight.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
            et_color.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);
            et_weaningDate.setInputType(InputType.TYPE_DATETIME_VARIATION_DATE);
            et_weaningWeight.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
            et_soldDate.setInputType(InputType.TYPE_DATETIME_VARIATION_DATE);
            et_soldPrice.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
            et_soldWeight.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
        } else {
            et_name.setKeyListener(null);
            et_buyPrice.setKeyListener(null);
            et_birthDate.setKeyListener(null);
            et_birthWeight.setKeyListener(null);
            et_color.setKeyListener(null);
            et_weaningDate.setKeyListener(null);
            et_weaningWeight.setKeyListener(null);
            et_soldDate.setKeyListener(null);
            et_soldPrice.setKeyListener(null);
            et_soldWeight.setKeyListener(null);
            b_edit.setVisibility(Button.GONE);
        }
        b_save.setVisibility(Button.GONE);
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
        queryBuilder.where(AnimalDao.Properties.IsMale.eq(isMale), AnimalDao.Properties.AnimalTypeId.eq(animalType.getAnimalTypeId()), AnimalDao.Properties.AnimalId.notEq(animal.getAnimalId()));
        queryBuilder.orderDesc(AnimalDao.Properties.Bithdate);
        return queryBuilder.list();
    }

    public View.OnClickListener saveChanges() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String errors = "";

                String name, color;
                Float buyPrice, birthWeight, weaningWeight, soldPrice, soldWeight;
                Date birthDate, weaningDate, soldDate;
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

                birthDate = (et_birthDate.getText().length() > 0) ? new Date((Long) et_birthDate.getTag()) : null;
                weaningDate = (et_weaningDate.getText().length() > 0) ? new Date((Long) et_weaningDate.getTag()) : null;
                soldDate = (et_soldDate.getText().length() > 0) ? new Date((Long) et_soldDate.getTag()) : null;

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
                    animal.setName(name);
                    animal.setAnimalType(selectedType);
                    animal.setAnimalTypeId(selectedType.getAnimalTypeId());
                    animal.setMother(selectedMother);
                    animal.setMotherId(selectedMother == null ? null : selectedMother.getAnimalId());
                    animal.setFather(selectedFather);
                    animal.setFatherId(selectedFather == null ? null : selectedFather.getAnimalId());
                    animal.setBuyPrice(buyPrice);
                    animal.setBithdate(birthDate);
                    animal.setBirthWeight(birthWeight);
                    animal.setColor(color);
                    animal.setIsMale(isMale);
                    animal.setWeaningdate(weaningDate);
                    animal.setWeaningWeight(weaningWeight);
                    animal.setSolddate(soldDate);
                    animal.setSoldPrice(soldPrice);
                    animal.setSoldWeight(soldWeight);

                    DBConnection.getInstance().insertOrReplace(animal);
                    Toast.makeText(getContext(), getString(R.string.msgChangesSaved), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            }
        };
    }

}
