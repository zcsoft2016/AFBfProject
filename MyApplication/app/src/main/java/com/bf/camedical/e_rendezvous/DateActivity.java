package com.bf.camedical.e_rendezvous;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DateActivity extends AppCompatActivity {

    public final static String DAY = "DAY";
    public final static String MONTH = "MONTH";
    public final static String YEAR = "YEAR";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

        //Recuperation des elements
        final Specialite specialite = getIntent().getParcelableExtra(SpecialiteActivity.SPECIALITE);
        final Ville ville = getIntent().getParcelableExtra(VilleActivity.VILLE);
        final Clinique clinique = getIntent().getParcelableExtra(CliniqueActivity.CLINIQUE);

        //Affichage des elements recuperes
        TextView villeTextView = (TextView) findViewById(R.id.date_ville);
        villeTextView.setText(ville.getLabel());

        TextView specialiteTxtView = (TextView) findViewById(R.id.date_specialite);
        specialiteTxtView.setText(specialite.getLabel());

        TextView cliniqueTextView = (TextView) findViewById(R.id.date_clinique);
        cliniqueTextView.setText(clinique.getLabel());

        //Gestion de la date de rendez-vous
        final DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
        Button dateRendezVous = (Button) findViewById(R.id.dateRendezVous);
        dateRendezVous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateClickListener(datePicker, specialite, clinique, ville);
            }
        });

    }

    private void DateClickListener(DatePicker datePicker, Specialite specialite, Clinique clinique, Ville ville) {
        Intent creneauActivity = new Intent(DateActivity.this, CreneauActivity.class);
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth() + 1;
        int year = datePicker.getYear();

        String dateSelectionnee;
        if(month<10){
            if(day<10){
                dateSelectionnee=year+"-0"+month+"-0"+day;
            }
            else dateSelectionnee=year+"-0"+month+"-"+day;
        }
        else {
            if(day<0){
                dateSelectionnee= year+"-"+month+"-0"+day;
            }
            else dateSelectionnee= year+"-"+month+"-"+day;
        }
        ArrayList<Creneau> creneaux = new RequeteClient(getApplicationContext()).returnCreneau(specialite.getId(), clinique.getId(), dateSelectionnee);
        if(creneaux==null) {
            Toast.makeText(DateActivity.this, "Pas de creneaux disponible pour ce jour", Toast.LENGTH_SHORT).show();
        }
        else
        {
            creneauActivity.putExtra(SpecialiteActivity.SPECIALITE, specialite);
            creneauActivity.putExtra(VilleActivity.VILLE, ville);
            creneauActivity.putExtra(CliniqueActivity.CLINIQUE, clinique);
            creneauActivity.putExtra(DateActivity.DAY, day);
            creneauActivity.putExtra(DateActivity.MONTH, month);
            creneauActivity.putExtra(DateActivity.YEAR, year);
            creneauActivity.putExtra(CreneauActivity.CRENEAUX, creneaux);
            startActivity(creneauActivity);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return MainActivity.ClicOnMenuItem(DateActivity.this, item) || super.onOptionsItemSelected(item);
    }
}
