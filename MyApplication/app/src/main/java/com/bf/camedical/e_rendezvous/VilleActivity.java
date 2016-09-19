package com.bf.camedical.e_rendezvous;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;

public class VilleActivity extends AppCompatActivity {

    //public final static String NONE = "Choisir une ville";
    public final static String VILLE = "Ville";

    //private Spinner spinnerVille;
    private ArrayAdapter<String> dataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ville);

        //Gestion des villes
        final GridView gridView = (GridView) findViewById(R.id.gridView);
        registerForContextMenu(gridView);
        final ArrayList<Ville> villes = new RequeteClient(getApplicationContext()).returnVilles();

        gridView.setAdapter(new VilleAdapter(getApplicationContext(), villes));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                GridViewVilleListener(v, position, villes);
            }
        });

        ImageView img_ville = (ImageView) findViewById(R.id.img_ville);
        int[] images = new int[]{R.drawable.centenaire, R.drawable.placecineaste};
        int indice = (int) Math.round(Math.random());
        int imageChoisie = images[indice];
        img_ville.setImageResource(imageChoisie);

    }

    private void GridViewVilleListener(View view, int position, ArrayList<Ville> villes) {
        Ville ville = villes.get(position);
        Intent specialiteActivity = new Intent(VilleActivity.this, SpecialiteActivity.class);
        specialiteActivity.putExtra(VilleActivity.VILLE, ville);
        startActivity(specialiteActivity);
    }

   /* public void VilleItemListener(int position) {
        if (position != 0) {
               String villeLabel = (String) spinnerVille.getSelectedItem();
               Intent specialiteActivity = new Intent(VilleActivity.this, SpecialiteActivity.class);
               VilleDao villeDao = new VilleDao(getApplicationContext());
               villeDao.open();
               Ville villeChoisie = villeDao.getVilleByLabel(villeLabel);
               specialiteActivity.putExtra(VilleActivity.VILLE, villeChoisie);
               startActivity(specialiteActivity);

        }
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return MainActivity.ClicOnMenuItem(VilleActivity.this, item) || super.onOptionsItemSelected(item);
    }
   /* @Override
    public void onResume() {
        super.onResume();
        spinnerVille.setSelection(dataAdapter.getPosition(NONE));
    }*/
}
