package com.bf.camedical.e_rendezvous;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    SectionsInfosPagerAdapter infosPageAdapter;
    ViewPager infosPager;
    int numeroPage = 0;
    private static final String  premiereFois="lancement";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initialiser une fois la base de données
        InitDatabase();

        //Gerer les informations qui defilent dans l'accueil
        infosPageAdapter = new SectionsInfosPagerAdapter(getSupportFragmentManager());
        infosPager = (ViewPager) findViewById(R.id.infos_pager);
        infosPager.setAdapter(infosPageAdapter);

        //Bouton Prendre un rendez-vous
        Button prendreRdv = (Button) findViewById(R.id.prendreRdv);
        prendreRdv.setOnClickListener(prendreRendezVousListener);

        //Mise en place du timer
        LaunchTimer();
    }

    private void LaunchTimer() {
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (numeroPage == infosPageAdapter.getCount()) {
                            numeroPage = 0;
                        }
                        infosPager.setCurrentItem(numeroPage++, true);
                    }
                });
            }
        }, 500, 3000);
    }

    private void InitDatabase() {
        JourDao repJr = new JourDao(this);
        repJr.open();
        SharedPreferences mypreferences= PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor=mypreferences.edit();
        int fois=mypreferences.getInt(premiereFois, 0);
        if(fois==0) {
            editor.putInt(premiereFois, 1);
            editor.apply();
        }
        if(fois<2) {
            editor.remove(premiereFois);
            editor.apply();
            editor.putInt(premiereFois,2);
            editor.apply();
            repJr.remplirDb();

        }
    }

    public View.OnClickListener prendreRendezVousListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent villeActivity = new Intent(MainActivity.this, VilleActivity.class);
            startActivity(villeActivity);
        }
    };
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return ClicOnMenuItem(MainActivity.this, item) || super.onOptionsItemSelected(item);
    }

    public static boolean ClicOnMenuItem(Context context, MenuItem item) {

        int id = item.getItemId();

        switch (id)
        {
            case R.id.aide:
                Toast.makeText(context, "Click sur le bouton Aide", Toast.LENGTH_LONG).show();
                return true;
            case R.id.aPropos:
                Toast.makeText(context, "Click sur le bouton A propos", Toast.LENGTH_LONG).show();
                return true;
            case R.id.maj:
                Toast.makeText(context, "Click sur le bouton Mettre a jour", Toast.LENGTH_LONG).show();
               Intent loadingActivity = new Intent(context, LoadingActivity.class);
                context.startActivity(loadingActivity);
                return true;
        }
        return false;
    }
}
