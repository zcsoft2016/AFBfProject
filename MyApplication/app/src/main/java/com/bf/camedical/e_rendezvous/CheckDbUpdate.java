package com.bf.camedical.e_rendezvous;

import android.os.AsyncTask;

public class CheckDbUpdate extends AsyncTask<Void,Integer,Void> {
    boolean dbaJour;
    DownFragment container;

    public CheckDbUpdate(DownFragment container) {
        this.container = container;
    }

    @Override
    protected Void doInBackground(Void... params) {
        String urlVersion="http://files.fm/down.php?i=okhcrwg&n=version1.json";
        UrlFileToList urlFileToList=new UrlFileToList();
        int[] testVersion=urlFileToList.downloadVersion(urlVersion);
       //dbaJour=true;
       dbaJour=false;
           /* for(int i=0;i<testVersion.length;i++){
                if(testVersion[i]>container.myDbVersion[i]){
                    dbaJour=false;
                   container.myDbVersion[i]=testVersion[i];
                }
            }*/
        if(container!=null && container.getActivity()!=null) {
            container.networkInfo = container.connectivityManager.getActiveNetworkInfo();
            if (container.networkInfo == null || !container.networkInfo.isConnected())
                this.cancel(true);
        }
        return null;
    }

    @Override
    protected void onCancelled(){
        if(container!=null && container.getActivity()!=null) container.checkCancel();
        DownFragment.isterminated=true;
    }
    @Override
    protected void onPostExecute(Void result) {
        if(container!=null && container.getActivity()!=null) {
            container.afterCheckTask(dbaJour);
        }
    }
}
