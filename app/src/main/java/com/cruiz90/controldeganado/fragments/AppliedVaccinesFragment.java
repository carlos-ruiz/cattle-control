package com.cruiz90.controldeganado.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cruiz90.controldeganado.R;
import com.cruiz90.controldeganado.entities.AnimalHasVaccines;
import com.cruiz90.controldeganado.util.DBConnection;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AppliedVaccinesFragment extends Fragment {


    public AppliedVaccinesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        List<AnimalHasVaccines> animalsHasVaccines = DBConnection.getInstance().loadAll(AnimalHasVaccines.class);
        int count = 0;
        for (AnimalHasVaccines ahv : animalsHasVaccines){
            Log.i(""+count++, "Animal: "+ahv.getAnimalId()+" - Vaccine: "+ahv.getVaccineId()+" - Date: "+(ahv.getVaccineDate()==null?"sin fecha":ahv.getVaccineDate().toString()));
        }
        return inflater.inflate(R.layout.fragment_applied_vaccines, container, false);
    }

}
