package com.cruiz90.controldeganado.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.cruiz90.controldeganado.R;
import com.cruiz90.controldeganado.activities.MainActivity;
import com.cruiz90.controldeganado.entities.Animal;
import com.cruiz90.controldeganado.entities.AnimalHasVaccines;
import com.cruiz90.controldeganado.entities.AnimalHasVaccinesDao;
import com.cruiz90.controldeganado.entities.Vaccine;
import com.cruiz90.controldeganado.util.DBConnection;
import com.cruiz90.controldeganado.util.Utils;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsAppliedVaccineFragment extends Fragment {

    private static final String VACCINE_ARG = "vaccine";
    private static final String DATE_ARG = "date";
    private Long vaccineId;
    private Date applicationDate;

    public static DetailsAppliedVaccineFragment newInstance(Long vaccineId, Long applicationDateInMilis) {

        Bundle args = new Bundle();
        args.putLong(VACCINE_ARG, vaccineId);
        args.putLong(DATE_ARG, applicationDateInMilis);
        DetailsAppliedVaccineFragment fragment = new DetailsAppliedVaccineFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public DetailsAppliedVaccineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (getArguments() != null) {
            this.vaccineId = getArguments().getLong(VACCINE_ARG);
            this.applicationDate = getArguments().getLong(DATE_ARG) == 0 ? null : new Date(getArguments().getLong(DATE_ARG));
        }

        View v = inflater.inflate(R.layout.fragment_details_applied_vaccine, container, false);
        QueryBuilder<AnimalHasVaccines> queryBuilder = DBConnection.getInstance().queryBuilder(AnimalHasVaccines.class);
        queryBuilder.where(AnimalHasVaccinesDao.Properties.VaccineId.eq(vaccineId), AnimalHasVaccinesDao.Properties.VaccineDateInMilis.eq(applicationDate.getTime()));

        final List<AnimalHasVaccines> vaccinatedAnimals = queryBuilder.list();
        Vaccine vaccine = DBConnection.getInstance().load(Vaccine.class, vaccineId);

        TextView tv_vaccineName = (TextView) v.findViewById(R.id.tv_vaccine);
        TextView tv_applicationDate = (TextView) v.findViewById(R.id.tv_applicationDate);
        ListView lv_vaccinatedAnimals = (ListView) v.findViewById(R.id.lv_vaccinatedAnimals);

        tv_vaccineName.setText(vaccine.getName());
        tv_applicationDate.setText(applicationDate != null ? Utils.getDateAsString(applicationDate) : "Sin fecha");

        ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, vaccinatedAnimals);
        lv_vaccinatedAnimals.setAdapter(adapter);

        lv_vaccinatedAnimals.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Animal animal = vaccinatedAnimals.get(position).getAnimal();
                ((MainActivity) getActivity()).updateActionBarTitle(animal.getName());
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_frame, DetailsAnimalFragment.newInstance(animal.getAnimalId()))
                        .commit();
            }
        });

        return v;
    }

}
