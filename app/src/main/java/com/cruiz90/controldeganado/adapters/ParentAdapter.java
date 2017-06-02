package com.cruiz90.controldeganado.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.cruiz90.controldeganado.entities.Animal;

import java.util.List;

/**
 * Created by ISC. Carlos Ruiz on 01/06/2017.
 */

public class ParentAdapter extends BaseAdapter {

    private List<Animal> animals;
    private int layout;
    private Context context;

    public ParentAdapter(List<Animal> animals, int layout, Context context) {
        this.animals = animals;
        this.layout = layout;
        this.context = context;
    }

    @Override
    public int getCount() {
        return animals.size();
    }

    @Override
    public Animal getItem(int position) {
        return animals.get(position);
    }

    @Override
    public long getItemId(int position) {
        return animals.get(position).getAnimalId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(layout, null);



        return null;
    }
}
