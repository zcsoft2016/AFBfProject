package com.bf.camedical.e_rendezvous;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;

public class SpecialiteActivity extends AppCompatActivity {

    public final static String SPECIALITE = "Specialite";
    Ville ville;
    private ArrayList<Specialite> specialitesFilles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialite);

        //Recuperation
        ville = getIntent().getParcelableExtra(VilleActivity.VILLE);

        TextView villeTextView = (TextView) findViewById(R.id.specialite_ville);
        villeTextView.setText(ville.getLabel());

        //Gestion des specialités
        final GridView gridView = (GridView) findViewById(R.id.gridView);
        registerForContextMenu(gridView);
        final ArrayList<Specialite> specialites = new RequeteClient(getApplicationContext()).returnSpecialites();
        
        gridView.setAdapter(new SpecialiteAdapter(getApplicationContext(), specialites));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                GridViewSpecialiteListener(v, position, specialites);
            }
        });

    }

    private void GridViewSpecialiteListener(View v, int position, ArrayList<Specialite> specialites) {
        Specialite specialite = specialites.get(position);
        if (specialite.getFils() == 1) {
            ShowContextMenu(v, specialite);
        } else{
            ClicSurUneSpecialite(specialite, ville);
        }
    }

    private void ClicSurUneSpecialite(Specialite specialite, Ville ville) {
        Intent cliniqueActivity = new Intent(SpecialiteActivity.this, CliniqueActivity.class);
        cliniqueActivity.putExtra(SpecialiteActivity.SPECIALITE, specialite);
        cliniqueActivity.putExtra(VilleActivity.VILLE, ville);
        startActivity(cliniqueActivity);
    }

    private void ShowContextMenu(View view, Specialite specialite) {
        specialitesFilles = new RequeteClient(getApplicationContext()).sousCategories(specialite.getId());
        openContextMenu(view);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, view, menuInfo);
        menu.setHeaderTitle("Sous spécialités");
        for (int i=0; i<specialitesFilles.size(); i++) {
            menu.add(i, specialitesFilles.get(i).getId(), i,  specialitesFilles.get(i).getLabel());
        }
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int specialiteId = item.getItemId();
        SpecialiteDao specialiteDao = new SpecialiteDao(getApplicationContext());
        specialiteDao.open();
        Specialite specialite = specialiteDao.getSpecialiteById(specialiteId);
        ClicSurUneSpecialite(specialite, ville);
        return true;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return MainActivity.ClicOnMenuItem(SpecialiteActivity.this, item) || super.onOptionsItemSelected(item);
    }
}
