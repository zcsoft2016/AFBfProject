package com.bf.camedical.e_rendezvous;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.ArrayList;

public class CreneauActivity extends AppCompatActivity {

    public final static String CRENEAUX = "CRENEAUX";
    public Specialite specialite;
    public Creneau creneau;
    public Ville ville;
    public Clinique clinique;
    public int day;
    public int month;
    public int year;
    public String dateSelectionnee;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creneau);

        //Recuperation des elements
        specialite = getIntent().getParcelableExtra(SpecialiteActivity.SPECIALITE);
        ville = getIntent().getParcelableExtra(VilleActivity.VILLE);
        clinique = getIntent().getParcelableExtra(CliniqueActivity.CLINIQUE);
        day = getIntent().getIntExtra(DateActivity.DAY, -1);
        month = getIntent().getIntExtra(DateActivity.MONTH, -1);
        year = getIntent().getIntExtra(DateActivity.YEAR, -1);
        final ArrayList<Creneau> creneaux = getIntent().getParcelableArrayListExtra(CreneauActivity.CRENEAUX);

        //Affichage d'elements recuperés
        TextView villeTextView = (TextView) findViewById(R.id.creneau_ville);
        villeTextView.setText(ville.getLabel());

        TextView specialiteTxtView = (TextView) findViewById(R.id.creneau_specialite);
        specialiteTxtView.setText(specialite.getLabel());

        TextView cliniqueTextView = (TextView) findViewById(R.id.creneau_clinique);
        cliniqueTextView.setText(clinique.getLabel());

        final TextView date = (TextView) findViewById(R.id.creneau_date);
        dateSelectionnee = day + "/" + month + "/" + year;
        date.setText(dateSelectionnee);

        //Gestion des creneaux
        String[] creneauxLabels = new String[creneaux.size()];
        for (int i=0; i<creneaux.size(); i++) {
            creneauxLabels[i] = creneaux.get(i).getLabel();
        }
        final Spinner spinnerCreneau = (Spinner) findViewById(R.id.creneau);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, creneauxLabels);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCreneau.setAdapter(dataAdapter);

        //Envoi du sms
        creneau = creneaux.get((int) spinnerCreneau.getSelectedItemId());
        Button sendSms = (Button) findViewById(R.id.sendSmS);
        sendSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager=getSupportFragmentManager();
                ConfirmFragment confirmFragment=ConfirmFragment.newInstance(ville,clinique,specialite,creneau,day,month,year);
                confirmFragment.show(fragmentManager,"Dialog fragment");
            }
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return MainActivity.ClicOnMenuItem(CreneauActivity.this, item) || super.onOptionsItemSelected(item);
    }
}
