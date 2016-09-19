package com.bf.camedical.e_rendezvous;


import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadData extends AsyncTask<Void,Integer,Void> {

    DownFragment container;
    private String stringVilles;
    private String stringCliniques;
    private String stringSpecialites;
    private String stringCreneaux;
    private String stringRdv;
    private String stringJour;
    private boolean haveInternet=true;
    public DownloadData(DownFragment downFragment){
        this.container=downFragment;
    }
    @Override
    protected void onPreExecute(){
        container.beforeDownloadTask();
        DownFragment.taskId=1;
    }
    @Override
    protected void onProgressUpdate(Integer ... values){
        super.onProgressUpdate(values);
        if(container!=null && container.getActivity()!=null)
            container.updateDownloadProgressBar(values[0]);
    }
    @Override
    protected void onCancelled(){
        container.cancelDownloadTask(!haveInternet);
        DownFragment.isterminated=true;
    }
    @Override
    protected Void doInBackground(Void... params) {
        String urlVilles="http://files.fm/down.php?i=ehvlaok&n=ville2.json";
        String urlCliniques="http://files.fm/down.php?i=oyhsxkz&n=cliniques1.json";
        String urlSpecialites=" http://files.fm/down.php?i=nfbgaha&n=Specialites2.json";
        String urlRdv="http://files.fm/down.php?i=dakfcsu&n=rdv4.json";
        String urlJour="http://files.fm/down.php?i=rsbndom&n=jour.json";
        String urlCreneau="http://files.fm/down.php?i=opvpmep&n=creneau.json";
        stringVilles="";
        stringCliniques="";
        stringSpecialites="";
        stringCreneaux="";
        stringRdv="";
        stringJour="";
        int progress;
        long k=0;
        UrlFileToList urlFileToList=new UrlFileToList();
        long nbTotalVilles=urlFileToList.nbEnregistrement(urlVilles);
        long nbTotalSpecialites=urlFileToList.nbEnregistrement(urlSpecialites);
        long nbTotalCliniques=urlFileToList.nbEnregistrement(urlCliniques);
        long nbTotalCreneaux=urlFileToList.nbEnregistrement(urlCreneau);
        long nbTotalRdv=urlFileToList.nbEnregistrement(urlRdv);
        long nbTotalJour=urlFileToList.nbEnregistrement(urlJour);
        long nbTotal=nbTotalVilles+nbTotalSpecialites+nbTotalCliniques+nbTotalCreneaux+nbTotalRdv+nbTotalJour;
        for (int i=1;i<7;i++)
        {   switch (i){
            case 1:

                try {
                    URL urlPage = new URL(urlVilles);
                    HttpURLConnection connection = (HttpURLConnection) urlPage.openConnection();
                    InputStream inputStream = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    String line;
                    while((line= reader.readLine()) != null) {
                        stringVilles=stringVilles+line+"\n";
                        k++;
                        progress=(int)((k/(float)nbTotal)*100);
                        publishProgress(progress);
                        container.networkInfo = container.connectivityManager.getActiveNetworkInfo();
                        if (container.networkInfo == null || !container.networkInfo.isConnected()) {
                            try {
                                Thread.sleep(5000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        container.networkInfo = container.connectivityManager.getActiveNetworkInfo();
                        if (container.networkInfo == null || !container.networkInfo.isConnected()) {
                            haveInternet=false;
                            this.cancel(true);
                            break;
                        }
                    }

                    connection.disconnect();
                    reader.close();
                    inputStream.close();

                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 2:

                try {
                    URL urlPage = new URL(urlCliniques);
                    HttpURLConnection connection = (HttpURLConnection) urlPage.openConnection();
                    InputStream inputStream = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    String line;
                    while((line= reader.readLine()) != null) {
                        stringCliniques=stringCliniques+line+"\n";
                        k++;
                        progress=(int)((k/(float)nbTotal)*100);
                        publishProgress(progress);
                        container.networkInfo = container.connectivityManager.getActiveNetworkInfo();
                        if (container.networkInfo == null || !container.networkInfo.isConnected()) {
                            try {
                                Thread.sleep(5000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        container.networkInfo = container.connectivityManager.getActiveNetworkInfo();
                        if (container.networkInfo == null || !container.networkInfo.isConnected()) {
                            haveInternet=false;
                            this.cancel(true);
                            break;
                        }
                    }

                    connection.disconnect();
                    reader.close();
                    inputStream.close();

                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 3:

                try {
                    URL urlPage = new URL(urlSpecialites);
                    HttpURLConnection connection = (HttpURLConnection) urlPage.openConnection();
                    InputStream inputStream = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    String line;
                    while((line= reader.readLine()) != null) {
                        stringSpecialites=stringSpecialites+line+"\n";
                        k++;
                        progress=(int)((k/(float)nbTotal)*100);
                        publishProgress(progress);
                        container.networkInfo = container.connectivityManager.getActiveNetworkInfo();
                        if (container.networkInfo == null || !container.networkInfo.isConnected()) {
                            try {
                                Thread.sleep(5000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        container.networkInfo = container.connectivityManager.getActiveNetworkInfo();
                        if (container.networkInfo == null || !container.networkInfo.isConnected()){
                            haveInternet=false;
                            this.cancel(true);
                            break;
                        }
                    }

                    connection.disconnect();
                    reader.close();
                    inputStream.close();

                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 4:
                try {
                    URL urlPage = new URL(urlCreneau);
                    HttpURLConnection connection = (HttpURLConnection) urlPage.openConnection();
                    InputStream inputStream = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    String line;
                    while((line= reader.readLine()) != null) {
                        stringCreneaux=stringCreneaux+line+"\n";
                        k++;
                        progress=(int)((k/(float)nbTotal)*100);
                        publishProgress(progress);
                        container.networkInfo = container.connectivityManager.getActiveNetworkInfo();
                        if (container.networkInfo == null || !container.networkInfo.isConnected()) {
                            try {
                                Thread.sleep(5000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        container.networkInfo = container.connectivityManager.getActiveNetworkInfo();
                        if (container.networkInfo == null || !container.networkInfo.isConnected()){
                            haveInternet=false;
                            this.cancel(true);
                            break;
                        }
                    }

                    connection.disconnect();
                    reader.close();
                    inputStream.close();

                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 5:

                try {
                    URL urlPage = new URL(urlRdv);
                    HttpURLConnection connection = (HttpURLConnection) urlPage.openConnection();
                    InputStream inputStream = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    String line;
                    while((line= reader.readLine()) != null) {
                        stringRdv=stringRdv+line+"\n";
                        k++;
                        progress=(int)((k/(float)nbTotal)*100);
                        publishProgress(progress);
                        container.networkInfo = container.connectivityManager.getActiveNetworkInfo();
                        if (container.networkInfo == null || !container.networkInfo.isConnected()) {
                            try {
                                Thread.sleep(5000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        container.networkInfo = container.connectivityManager.getActiveNetworkInfo();
                        if (container.networkInfo == null || !container.networkInfo.isConnected()) {
                            haveInternet=false;
                            this.cancel(true);
                            break;
                        }
                    }

                    connection.disconnect();
                    reader.close();
                    inputStream.close();

                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 6:
                try {
                    URL urlPage = new URL(urlJour);
                    HttpURLConnection connection = (HttpURLConnection) urlPage.openConnection();
                    InputStream inputStream = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    String line;
                    while((line= reader.readLine()) != null) {
                        stringJour=stringJour+line+"\n";
                        k++;
                        progress=(int)((k/(float)nbTotal)*100);
                        publishProgress(progress);
                        container.networkInfo = container.connectivityManager.getActiveNetworkInfo();
                        if (container.networkInfo == null || !container.networkInfo.isConnected()) {
                            try {
                                Thread.sleep(5000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        container.networkInfo = container.connectivityManager.getActiveNetworkInfo();
                        if (container.networkInfo == null || !container.networkInfo.isConnected()){
                            haveInternet=false;
                            this.cancel(true);
                            break;
                        }
                    }
                    connection.disconnect();
                    reader.close();
                    inputStream.close();

                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                break;

        }
        }
        return null;
    }
    @Override
    protected void onPostExecute(Void result){
        if(container!=null && container.getActivity()!=null){
            container.afterDownloadTask();
            container.updateDonne(stringVilles,stringCliniques,stringSpecialites,stringCreneaux,stringRdv,stringJour);

        }
    }
}