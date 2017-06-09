package com.cruiz90.controldeganado.fragments;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.cruiz90.controldeganado.R;
import com.cruiz90.controldeganado.entities.Animal;
import com.cruiz90.controldeganado.entities.AnimalHasVaccines;
import com.cruiz90.controldeganado.entities.Vaccine;
import com.cruiz90.controldeganado.util.DBConnection;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ApplyVaccineFragment extends Fragment {


    public ApplyVaccineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_apply_vaccine, container, false);

        final List<Vaccine> vaccines = DBConnection.getInstance().loadAll(Vaccine.class);
        final Spinner spinner = (Spinner) root.findViewById(R.id.s_vaccine);
        final ArrayAdapter<Vaccine> adapter = new ArrayAdapter<>(getActivity(), R.layout.support_simple_spinner_dropdown_item, vaccines);
        spinner.setAdapter(adapter);

        final List<Animal> animals = DBConnection.getInstance().loadAll(Animal.class);
        final ListView listView = (ListView) root.findViewById(R.id.lv_vaccines);
        ArrayAdapter<Animal> listAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_multiple_choice, animals);
        listView.setAdapter(listAdapter);

        final EditText et_date = (EditText) root.findViewById(R.id.et_date);
        et_date.setOnClickListener(showCalendar(et_date, Calendar.getInstance()));
        updateLabelDate(et_date, Calendar.getInstance());

        Button b_save = (Button) root.findViewById(R.id.b_save);
        b_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int size = listView.getCount();
                SparseBooleanArray itemsChecked = listView.getCheckedItemPositions();
                Date date = new Date((Long) et_date.getTag());

                List<AnimalHasVaccines> selectedAnimals = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    if ((itemsChecked.get(i))) {
                        selectedAnimals.add(new AnimalHasVaccines(animals.get(i).getAnimalId(), vaccines.get(spinner.getSelectedItemPosition()).getVaccineId(), date));
                    }
                }

                if (selectedAnimals.size() == 0) {
                    Toast.makeText(getContext(), getString(R.string.selectAtLeastOneAnimal), Toast.LENGTH_SHORT).show();
                    return;
                }

                for (AnimalHasVaccines av : selectedAnimals) {
                    DBConnection.getInstance().insert(av);

                }
                getFragmentManager().beginTransaction().replace(R.id.content_frame, new AppliedVaccinesFragment()).commit();
                Toast.makeText(getContext(), getString(R.string.msgSaved), Toast.LENGTH_SHORT).show();

            }
        });

        return root;
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

}
