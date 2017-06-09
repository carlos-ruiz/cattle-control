package com.cruiz90.controldeganado.fragments;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.cruiz90.controldeganado.R;
import com.cruiz90.controldeganado.activities.MainActivity;
import com.cruiz90.controldeganado.adapters.AppliedVaccinesAdapter;
import com.cruiz90.controldeganado.entities.AnimalHasVaccines;
import com.cruiz90.controldeganado.entities.AnimalHasVaccinesDao;
import com.cruiz90.controldeganado.util.DBConnection;

import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AppliedVaccinesFragment extends Fragment {

    private List<AnimalHasVaccines> appliedVaccines;

    public AppliedVaccinesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_applied_vaccines, container, false);
        ListView listView = (ListView) v.findViewById(R.id.lv_appliedVaccines);

        QueryBuilder<AnimalHasVaccines> queryBuilder = DBConnection.getInstance().queryBuilder(AnimalHasVaccines.class);
        queryBuilder.where(new WhereCondition.StringCondition("1 GROUP BY VACCINE_DATE "));
        queryBuilder.orderDesc(AnimalHasVaccinesDao.Properties.VaccineDateInMilis);
        appliedVaccines = queryBuilder.list();

        AppliedVaccinesAdapter adapter = new AppliedVaccinesAdapter(appliedVaccines, getContext(), R.layout.applied_vaccines_list_item);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AnimalHasVaccines ahv = appliedVaccines.get(position);
                ((MainActivity) getActivity()).updateActionBarTitle(ahv.getVaccine().getName());
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_frame, DetailsAppliedVaccineFragment.newInstance(ahv.getVaccineId(), ahv.getVaccineDate() != null ? ahv.getVaccineDate().getTime() : 0)).commit();
            }
        });

        FloatingActionButton fab_add = (FloatingActionButton) v.findViewById(R.id.fab_add);
        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).updateActionBarTitle(getString(R.string.applyVaccine));
                ((MainActivity) getActivity()).getMenuItemById(R.id.menu_apply_vaccine).setChecked(true);
                getFragmentManager().beginTransaction().replace(R.id.content_frame, new ApplyVaccineFragment()).commit();
            }
        });

        return v;
    }

}
