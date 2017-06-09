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

        FloatingActionButton fab_add = (FloatingActionButton) root.findViewById(R.id.fab_add);
        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).updateActionBarTitle(getString(R.string.addAnimal));
                ((MainActivity) getActivity()).getMenuItemById(R.id.menu_add_animal).setChecked(true);
                getFragmentManager().beginTransaction().replace(R.id.content_frame, new AddAnimalFragment()).commit();
            }
        });
        return root;
    }

}
