package com.bf.camedical.e_rendezvous;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class DownFragment extends Fragment {
    private static final String versTabVille="versionVille";
    private static final String versTabClinique="versionClinique";
    private static final String versTabSpecialite="versionSpecialite";
    private static final String versTabCreneau="versionCreneau";
    private static final String versTabJour="versionJour";
    private static final String versTabRdv="versionRdv";
    private DownloadData myDownloadTask;
    private UpdateData myUpdateTask;
    public static int taskId=1;
    public   ConnectivityManager connectivityManager=null;
    public NetworkInfo networkInfo=null;
    public int[] myDbVersion;
    public static boolean isterminated=true;
    public DownFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view=inflater.inflate(R.layout.fragment_down, container, false);
        Button buttonDeb=(Button)view.findViewById(R.id.btnLaunch);
        connectivityManager=(ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        final SharedPreferences mypreferences= PreferenceManager.getDefaultSharedPreferences(getActivity().getBaseContext());
        buttonDeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                networkInfo = connectivityManager.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    checkVersion();
                    myDbVersion = new int[]{mypreferences.getInt(versTabVille, 1), mypreferences.getInt(versTabClinique, 1), mypreferences.getInt(versTabSpecialite, 1), mypreferences.getInt(versTabCreneau, 1), mypreferences.getInt(versTabRdv, 1), mypreferences.getInt(versTabJour, 1)};
                }
                else
                    Toast.makeText(getActivity().getBaseContext(), "Vous ne disposez pas actuellement d'une connexion internet", Toast.LENGTH_SHORT).show();
            }
        });
        Button buttonFin=(Button)view.findViewById(R.id.btnEnd);
        buttonFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isDownloadTaskRunning(myDownloadTask)) {
                    myDownloadTask.cancel(true);
                }
            }
        });
        setRetainInstance(true);
        return view;
    }
    public void checkVersion(){
        CheckDbUpdate myCheckTask=new CheckDbUpdate(this);
        DownFragment.isterminated=false;
        myCheckTask.execute();
    }

    public void chargeDonnee() {
        myDownloadTask=new DownloadData(this);
        myDownloadTask.execute();
    }
   public void updateDonne(String sVille,String sClinique,String sSpecialite,String sCreneau,String sRdv,String sJour){
        myUpdateTask=new UpdateData(this);
        myUpdateTask.execute(sVille, sClinique, sSpecialite, sCreneau, sRdv, sJour);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        switch (DownFragment.taskId){
            case 1:
                if(isDownloadTaskRunning(myDownloadTask))
                    showDownloadProgressBar();
                else hideDownloadProgressBar();
                break;
            case 2:
                hideDownloadProgressBar();
                if(isUpdateTaskRunning(myUpdateTask)) {
                    showUpdateTask();
                }
                else hideUdpateTask();
                break;
        }

    }
    private boolean isDownloadTaskRunning(DownloadData myTask) {
        return myTask != null && !myTask.getStatus().equals(DownloadData.Status.FINISHED);
    }

    private boolean isUpdateTaskRunning(UpdateData myTask) {
        return myTask != null && !myTask.getStatus().equals(UpdateData.Status.FINISHED);
    }
    public void checkCancel(){
        Toast.makeText(getActivity().getBaseContext(), "interuption de la mise à jour. Veuillez vérifier votre connexion et réessayer", Toast.LENGTH_LONG).show();
    }
    public void afterCheckTask(boolean estaJour){
        if(estaJour){
            Toast.makeText(getActivity().getBaseContext(), "La base de données est déjà à jour!!", Toast.LENGTH_LONG).show();
        }
        else{
            SharedPreferences mypreferences= PreferenceManager.getDefaultSharedPreferences(getActivity().getBaseContext());
            SharedPreferences.Editor editor=mypreferences.edit();
            editor.putInt(versTabVille,myDbVersion[0]);
            editor.apply();
            editor.putInt(versTabClinique, myDbVersion[1]);
            editor.apply();
            editor.putInt(versTabSpecialite, myDbVersion[2]);
            editor.apply();
            editor.putInt(versTabCreneau, myDbVersion[3]);
            editor.apply();
            editor.putInt(versTabRdv, myDbVersion[4]);
            editor.apply();
            editor.putInt(versTabJour, myDbVersion[5]);
            editor.apply();
            chargeDonnee();
        }
    }
    public void showDownloadProgressBar() {
        TextView texDown=(TextView)getActivity().findViewById(R.id.downloadText);
        texDown.setVisibility(View.VISIBLE);
        ProgressBar progressBar=(ProgressBar)getActivity().findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        TextView progress=(TextView)getActivity().findViewById(R.id.percent);
        progress.setVisibility(View.VISIBLE);
        Button buttonFin=(Button)getActivity().findViewById(R.id.btnEnd);
        buttonFin.setClickable(true);
        buttonFin.setBackgroundResource(R.drawable.btn_annuler_style);
        Button buttonDeb=(Button)getActivity().findViewById(R.id.btnLaunch);
        buttonDeb.setClickable(false);
        buttonDeb.setBackgroundResource(R.drawable.btn_lancer_style_gris);


    }

    public void hideDownloadProgressBar() {
        TextView texDown=(TextView)getActivity().findViewById(R.id.downloadText);
        texDown.setVisibility(View.GONE);
        ProgressBar progressBar=(ProgressBar)getActivity().findViewById(R.id.progressBar);
        progressBar.setProgress(0);
        progressBar.setVisibility(View.GONE);
        TextView progress=(TextView)getActivity().findViewById(R.id.percent);
        progress.setText("0 %");
        progress.setVisibility(View.GONE);
        Button buttonFin=(Button)getActivity().findViewById(R.id.btnEnd);
        buttonFin.setClickable(false);
        buttonFin.setBackgroundResource(R.drawable.btn_annuler_style_gris);
        Button buttonDeb=(Button)getActivity().findViewById(R.id.btnLaunch);
        buttonDeb.setClickable(true);
        buttonDeb.setBackgroundResource(R.drawable.btn_lancer_style);
    }
   public  void updateDownloadProgressBar(int percent) {
       ProgressBar progressBar = (ProgressBar) getActivity().findViewById(R.id.progressBar);
       progressBar.setProgress(percent);
       TextView progress=(TextView)getActivity().findViewById(R.id.percent);
       progress.setText(String.valueOf(percent) + " %");
   }
    public void beforeDownloadTask() {
        showDownloadProgressBar();
        Toast.makeText(getActivity().getBaseContext(), "Début du telechargement", Toast.LENGTH_LONG).show();

    }
    public void afterDownloadTask() {
        hideDownloadProgressBar();
        Toast.makeText(getActivity().getBaseContext(), "Fin du telechargement", Toast.LENGTH_LONG).show();
    }
    public void cancelDownloadTask(boolean internetProblem){
        if(internetProblem) {
            Toast.makeText(getActivity().getBaseContext(), "Interuption de la mise à jour.\n Veuillez vérifier votre connexion et réessayer", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(getActivity().getBaseContext(), "Annulation du telechargement", Toast.LENGTH_LONG).show();
        }
        hideDownloadProgressBar();
    }
    public void showUpdateTask(){
        Button buttonDeb=(Button)getActivity().findViewById(R.id.btnLaunch);
        buttonDeb.setBackgroundResource(R.drawable.btn_lancer_style_gris);
        TextView texUpdate=(TextView)getActivity().findViewById(R.id.updateText);
        texUpdate.setVisibility(View.VISIBLE);
        ProgressBar updateProgressBar=(ProgressBar)getActivity().findViewById(R.id.updateProgressBar);
        updateProgressBar.setVisibility(View.VISIBLE);
        TextView progress=(TextView)getActivity().findViewById(R.id.updatePercent);
        progress.setVisibility(View.VISIBLE);
    }
    public  void hideUdpateTask(){
        Button buttonDeb=(Button)getActivity().findViewById(R.id.btnLaunch);
        buttonDeb.setBackgroundResource(R.drawable.btn_lancer_style);
        TextView texUpdate=(TextView)getActivity().findViewById(R.id.updateText);
        texUpdate.setVisibility(View.GONE);
        ProgressBar updateProgressBar=(ProgressBar)getActivity().findViewById(R.id.updateProgressBar);
        updateProgressBar.setVisibility(View.GONE);
        TextView progress=(TextView)getActivity().findViewById(R.id.updatePercent);
        progress.setVisibility(View.GONE);
    }
    public void beforeUpdateTask(){
        showUpdateTask();
        Button buttonDeb=(Button)getActivity().findViewById(R.id.btnLaunch);
        buttonDeb.setClickable(false);
        Toast.makeText(getActivity().getBaseContext(), "Début de la Mise à jour", Toast.LENGTH_LONG).show();
    }
    public  void afterUdpateTask(){
        hideUdpateTask();
        Button buttonDeb=(Button)getActivity().findViewById(R.id.btnLaunch);
        buttonDeb.setClickable(true);
        Toast.makeText(getActivity().getBaseContext(), "Fin de la Mise à jour", Toast.LENGTH_LONG).show();

    }
    public  void updateUpdateProgressBar(int percent) {
        ProgressBar progressBar = (ProgressBar) getActivity().findViewById(R.id.updateProgressBar);
        progressBar.setProgress(percent);
        TextView progress=(TextView)getActivity().findViewById(R.id.updatePercent);
        progress.setText(String.valueOf(percent) + " %");
    }
}
