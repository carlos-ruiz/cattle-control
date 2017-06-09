package com.cruiz90.controldeganado.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cruiz90.controldeganado.R;
import com.cruiz90.controldeganado.entities.AnimalHasVaccines;
import com.cruiz90.controldeganado.util.Utils;

import java.util.List;

/**
 * Created by ISC. Carlos Ruiz on 09/06/2017.
 */

public class VaccinationHistoryAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<AnimalHasVaccines> animalHasVaccines;

    public VaccinationHistoryAdapter(Context context, int layout, List<AnimalHasVaccines> animalHasVaccines) {
        this.context = context;
        this.layout = layout;
        this.animalHasVaccines = animalHasVaccines;
    }

    @Override
    public int getCount() {
        return animalHasVaccines.size();
    }

    @Override
    public AnimalHasVaccines getItem(int position) {
        return animalHasVaccines.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(layout, null);

        TextView vaccineName = (TextView) v.findViewById(R.id.tv_vaccine_name);
        TextView vaccineDate = (TextView) v.findViewById(R.id.tv_vaccine_date);

        vaccineName.setText(getItem(position).getVaccine().getName());
        vaccineDate.setText(Utils.getDateAsString(getItem(position).getVaccineDate()));

        return v;
    }
}
