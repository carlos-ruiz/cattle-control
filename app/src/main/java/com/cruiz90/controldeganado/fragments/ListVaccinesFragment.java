package com.cruiz90.controldeganado.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.cruiz90.controldeganado.R;
import com.cruiz90.controldeganado.adapters.VacinnesListAdapter;
import com.cruiz90.controldeganado.entities.Vaccine;
import com.cruiz90.controldeganado.util.DBConnection;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListVaccinesFragment extends Fragment {

    private ListView listView;

    public ListVaccinesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_list_vaccines, container, false);
        listView = (ListView) root.findViewById(R.id.lv_vaccines);

        final List<Vaccine> vaccines = DBConnection.getInstance().loadAll(Vaccine.class);
        VacinnesListAdapter adapter = new VacinnesListAdapter(vaccines, R.layout.vaccine_list_item, getContext());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), vaccines.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });
        return root;
    }

}
