package com.cruiz90.controldeganado.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cruiz90.controldeganado.R;
import com.cruiz90.controldeganado.entities.Vaccine;

import java.util.List;

/**
 * Created by ISC. Carlos Ruiz on 01/06/2017.
 */

public class VacinnesListAdapter extends BaseAdapter {

    private List<Vaccine> vaccines;
    private int layout;
    private Context context;

    public VacinnesListAdapter(List<Vaccine> vaccines, int layout, Context context) {
        this.vaccines = vaccines;
        this.layout = layout;
        this.context = context;
    }

    @Override
    public int getCount() {
        return vaccines.size();
    }

    @Override
    public Vaccine getItem(int position) {
        return vaccines.get(position);
    }

    @Override
    public long getItemId(int position) {
        return vaccines.get(position).getVaccineId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(layout, null);

        Vaccine vaccine = getItem(position);

        TextView tv_name = (TextView) v.findViewById(R.id.tv_name);
        TextView tv_isPregnantAllowed = (TextView) v.findViewById(R.id.tv_isPregnantAllowed);

        tv_name.setText(vaccine.getName());
        tv_isPregnantAllowed.setText(context.getString(R.string.isPregnantAllowed) +": "+(vaccine.getIsPregnantAllowed()?context.getString(R.string.yes):context.getString(R.string.no)));

        return v;
    }
}
