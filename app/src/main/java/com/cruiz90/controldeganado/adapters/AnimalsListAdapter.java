package com.cruiz90.controldeganado.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cruiz90.controldeganado.R;
import com.cruiz90.controldeganado.entities.Animal;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by ISC. Carlos Ruiz on 30/05/2017.
 */

public class AnimalsListAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Animal> animals;

    public AnimalsListAdapter(Context context, int layout, List<Animal> animals) {
        this.context = context;
        this.layout = layout;
        this.animals = animals;
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
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(layout, null);
        Animal animal = getItem(position);

        TextView name = (TextView) v.findViewById(R.id.name);
        TextView color = (TextView) v.findViewById(R.id.color);
        TextView birthDate = (TextView) v.findViewById(R.id.birthDate);
        TextView animalType = (TextView) v.findViewById(R.id.animalType);

        name.setText(animal.getName());
        color.setText(animal.getColor());
        if (animal.getAnimalType() != null)
            animalType.setText(animal.getAnimalType().getName());

        if (animal.getBithdate() != null) {
            String format = "dd/MM/yyyy";
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            birthDate.setText(sdf.format(animal.getBithdate().toDate()));
        }
        return v;
    }
}
