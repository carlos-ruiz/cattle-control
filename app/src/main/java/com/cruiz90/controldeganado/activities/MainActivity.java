package com.cruiz90.controldeganado.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.cruiz90.controldeganado.R;
import com.cruiz90.controldeganado.entities.AnimalType;
import com.cruiz90.controldeganado.fragments.AddAnimalFragment;
import com.cruiz90.controldeganado.fragments.AddDiseaseFragment;
import com.cruiz90.controldeganado.fragments.AddExpenseFragment;
import com.cruiz90.controldeganado.fragments.AddVaccineFragment;
import com.cruiz90.controldeganado.fragments.AppliedVaccinesFragment;
import com.cruiz90.controldeganado.fragments.ApplyVaccineFragment;
import com.cruiz90.controldeganado.fragments.ListAnimalsFragment;
import com.cruiz90.controldeganado.fragments.ListDiseasesFragment;
import com.cruiz90.controldeganado.fragments.ListExpensesFragment;
import com.cruiz90.controldeganado.fragments.ListVaccinesFragment;
import com.cruiz90.controldeganado.util.DBConnection;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setToolbar();
        initAnimalTypes();

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);

        setDefaultFragment();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                boolean fragmentTransaction = false;
                Fragment fragment = null;

                switch (item.getItemId()) {
                    case R.id.menu_animals:
                        fragmentTransaction = true;
                        fragment = new ListAnimalsFragment();
                        break;
                    case R.id.menu_add_animal:
                        fragmentTransaction = true;
                        fragment = new AddAnimalFragment();
                        break;
                    case R.id.menu_vaccines:
                        fragmentTransaction = true;
                        fragment = new ListVaccinesFragment();
                        break;
                    case R.id.menu_add_vaccine:
                        fragmentTransaction = true;
                        fragment = new AddVaccineFragment();
                        break;
                    case R.id.menu_apply_vaccine:
                        fragmentTransaction = true;
                        fragment = new ApplyVaccineFragment();
                        break;
                    case R.id.menu_applied_vaccines:
                        fragmentTransaction = true;
                        fragment = new AppliedVaccinesFragment();
                        break;
                    case R.id.menu_diseases:
                        fragmentTransaction = true;
                        fragment = new ListDiseasesFragment();
                        break;
                    case R.id.menu_add_disease:
                        fragmentTransaction = true;
                        fragment = new AddDiseaseFragment();
                        break;
                    case R.id.menu_expenses:
                        fragmentTransaction = true;
                        fragment = new ListExpensesFragment();
                        break;
                    case R.id.menu_add_expense:
                        fragmentTransaction = true;
                        fragment = new AddExpenseFragment();
                        break;
                }

                if (fragmentTransaction) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
                    item.setChecked(true);
                    getSupportActionBar().setTitle(item.getTitle());
                    drawerLayout.closeDrawers();
                }
                return true;
            }
        });
    }

    public void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setDefaultFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new ListAnimalsFragment()).commit();
        MenuItem item = navigationView.getMenu().findItem(R.id.menu_animals);
//        item.setChecked(true);
        getSupportActionBar().setTitle(item.getTitle());
    }

    public void updateActionBarTitle(String title){
        getSupportActionBar().setTitle(title);
    }

    public MenuItem getMenuItemById(int id){
        return navigationView.getMenu().findItem(id);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void initAnimalTypes() {
        if (DBConnection.getInstance().count(AnimalType.class) == 0) {
            String[] animalTypes = {"Ovino", "Bovino", "Caprino", "Equino", "Porcino"};
            for (String animalType : animalTypes) {
                AnimalType at = new AnimalType(animalType);
                DBConnection.getInstance().insert(at);
            }
        } else {
            Log.i("DB", "AnimalTypes already initialized");
        }
    }
}
