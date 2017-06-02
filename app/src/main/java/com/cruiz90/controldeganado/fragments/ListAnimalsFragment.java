package com.cruiz90.controldeganado.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.cruiz90.controldeganado.R;
import com.cruiz90.controldeganado.adapters.AnimalsListAdapter;
import com.cruiz90.controldeganado.entities.Animal;
import com.cruiz90.controldeganado.util.DBConnection;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListAnimalsFragment extends Fragment {
    private ListView listView;

    public ListAnimalsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_list_animals, container, false);
        final List<Animal> animals = DBConnection.getInstance().loadAll(Animal.class);

        listView = (ListView) root.findViewById(R.id.lv_animals);
        AnimalsListAdapter adapter = new AnimalsListAdapter(getContext(), R.layout.animal_list_item, animals);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fragment fragment = DetailsAnimalFragment.newInstance(animals.get(position).getAnimalId());
                getFragmentManager().beginTransaction().replace(R.id.content_frame,fragment).commit();
            }
        });
        return root;
    }

}
