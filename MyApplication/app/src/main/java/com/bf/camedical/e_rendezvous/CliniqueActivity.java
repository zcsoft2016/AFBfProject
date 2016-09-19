package com.bf.camedical.e_rendezvous;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class CliniqueActivity extends AppCompatActivity {

    public final static String CLINIQUE = "Clinique";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinique);

        //Recuperation d'elements transmis
        final Specialite specialite = getIntent().getParcelableExtra(SpecialiteActivity.SPECIALITE);
        final Ville ville = getIntent().getParcelableExtra(VilleActivity.VILLE);

        //Affichage des elements recuperes
        TextView specialiteTxtView = (TextView) findViewById(R.id.clinique_specialite);
        specialiteTxtView.setText(specialite.getLabel());
        TextView villeTextView = (TextView) findViewById(R.id.clinique_ville);
        villeTextView.setText(ville.getLabel());

        //Gestion des cliniques
        GridView gridView = (GridView) findViewById(R.id.gridView);
        final ArrayList<Clinique> cliniques = new RequeteClient(getApplicationContext()).returnClinique(ville.getId(), specialite.getId());
        if(cliniques.size()==0){
            FragmentManager fragmentManager=getSupportFragmentManager();
            CliniqueFragment cliniqueFragment=CliniqueFragment.newInstance(ville,specialite);
            cliniqueFragment.show(fragmentManager,"retour");
        }
        gridView.setAdapter(new CliniqueAdapter(getApplicationContext(), cliniques));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                GridViewCliniqueListener(position, specialite, ville, cliniques);
            }
        });

    }

    private void GridViewCliniqueListener(int position, Specialite specialite, Ville ville, ArrayList<Clinique> cliniques) {
        Intent dateActivity = new Intent(CliniqueActivity.this, DateActivity.class);
        dateActivity.putExtra(SpecialiteActivity.SPECIALITE, specialite);
        dateActivity.putExtra(VilleActivity.VILLE, ville);
        dateActivity.putExtra(CliniqueActivity.CLINIQUE, cliniques.get(position));
        startActivity(dateActivity);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return MainActivity.ClicOnMenuItem(CliniqueActivity.this, item) || super.onOptionsItemSelected(item);
    }
}
