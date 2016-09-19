package com.bf.camedical.e_rendezvous;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UrlFileToList {
    public int[] downloadVersion(String url){
        String vers="";
        int[] newVersion=new int[]{1,1,1,1,1,1};
        try {
            URL urlPage = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlPage.openConnection();
            InputStream inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while((line= reader.readLine()) != null) {
                vers=vers+line+"\n";
            }

            connection.disconnect();
            reader.close();
            inputStream.close();

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        try {
            JSONObject jsonObject = new JSONObject(vers);
            JSONArray jsonArray = new JSONArray(jsonObject.getString("Version"));
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = new JSONObject(jsonArray.getString(i));
                newVersion[0]=obj.getInt("Ville");
                newVersion[1]=obj.getInt("Clinique");
                newVersion[2]=obj.getInt("Specialite");
                newVersion[3]=obj.getInt("Creneau");
                newVersion[4]=obj.getInt("Rdv");
                newVersion[5]=obj.getInt("Jour");
            }
            }
        catch (Exception e){
            e.printStackTrace();
        }
        return newVersion;
    }
    public long nbEnregistrement(String url){
        long nb=0;
        try {
            URL urlPage = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlPage.openConnection();
            InputStream inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            while(( reader.readLine()) != null) {
                nb++;
            }

            connection.disconnect();
            reader.close();
            inputStream.close();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return nb;

    }

}
