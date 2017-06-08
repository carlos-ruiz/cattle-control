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
 * Created by ISC. Carlos Ruiz on 07/06/2017.
 */

public class AppliedVaccinesAdapter extends BaseAdapter {

    private List<AnimalHasVaccines> vaccines;
    private Context context;
    private int layout;

    public AppliedVaccinesAdapter(List<AnimalHasVaccines> vaccines, Context context, int layout) {
        this.vaccines = vaccines;
        this.context = context;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return vaccines.size();
    }

    @Override
    public AnimalHasVaccines getItem(int position) {
        return vaccines.get(position);
    }

    @Override
    public long getItemId(int position) {
        return vaccines.get(position).getVaccineId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(layout, null);
        TextView vaccineName = (TextView) v.findViewById(R.id.tv_name);
        TextView applicationDate = (TextView) v.findViewById(R.id.tv_date);

        AnimalHasVaccines vaccineApplied = getItem(position);

        vaccineName.setText(vaccineApplied.getVaccine().getName());
        if (vaccineApplied.getVaccineDate() != null)
            applicationDate.setText(Utils.getDateAsString(vaccineApplied.getVaccineDate()));

        return v;
    }
}
