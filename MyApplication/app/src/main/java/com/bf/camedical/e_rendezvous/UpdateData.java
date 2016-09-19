package com.bf.camedical.e_rendezvous;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

public class UpdateData extends AsyncTask<String,Integer,Void> {
    DownFragment container;
    public UpdateData(DownFragment fragment){
        this.container=fragment;
    }
    @Override
    protected void onPreExecute(){
        DownFragment.taskId=2;
        container.beforeUpdateTask();
    }

    @Override
    protected void onProgressUpdate(Integer ... values){
        super.onProgressUpdate(values);
        if(container!=null && container.getActivity()!=null)
            container.updateUpdateProgressBar(values[0]);
    }
    @Override
    protected Void doInBackground(String... params) {

        int progress;
        long k=0;
        long nbGlobale=0;
        try {
            JSONObject jsonObjectVille=new JSONObject(params[0]);
            JSONArray jsonArrayVille=new JSONArray(jsonObjectVille.getString("Villes"));
            nbGlobale+=jsonArrayVille.length();
            JSONObject jsonObjectClinique=new JSONObject(params[1]);
            JSONArray jsonArrayClinique=new JSONArray(jsonObjectClinique.getString("Cliniques"));
            nbGlobale+=jsonArrayClinique.length();
            JSONObject jsonObjectSpecialite=new JSONObject(params[2]);
            JSONArray jsonArraySpecialite=new JSONArray(jsonObjectSpecialite.getString("Specialites"));
            nbGlobale+=jsonArraySpecialite.length();
            JSONObject jsonObjectCreneau=new JSONObject(params[3]);
            JSONArray jsonArrayCreneau=new JSONArray(jsonObjectCreneau.getString("Creneaux"));
            nbGlobale+=jsonArrayCreneau.length();
            JSONObject jsonObjectRdv=new JSONObject(params[4]);
            JSONArray jsonArrayRdv=new JSONArray(jsonObjectRdv.getString("Rdv"));
            nbGlobale +=jsonArrayRdv.length();
            JSONObject jsonObjectJour=new JSONObject(params[5]);
            JSONArray jsonArrayJour=new JSONArray(jsonObjectJour.getString("Jour"));
            nbGlobale+=jsonArrayJour.length();
        }
        catch (Exception e){
            e.printStackTrace();
        }
         /*===========================================================================
                                Mise a jour de la  table Ville
              ===========================================================================*/
        try {
            JSONObject jsonObject=new JSONObject(params[0]);
            JSONArray jsonArray=new JSONArray(jsonObject.getString("Villes"));
            for(int i=0;i<jsonArray.length();i++){
                JSONObject obj=new JSONObject(jsonArray.getString(i));
                Ville ville=new Ville();
                ville.setId(obj.getInt("id"));
                ville.setLabel(obj.getString("label"));
                ville.setActive(obj.getInt("active"));
                VilleDao villeDao=new VilleDao(ContextProvider.getAppContext());
                villeDao.open();
                if(villeDao.getVilleById(ville.getId())==null){
                    villeDao.addrecord(ville);
                }
                else{
                    Ville inDb=villeDao.getVilleByLabel(ville.getLabel());
                    if (inDb==null||inDb.getActive()!=ville.getActive()){
                        villeDao.updaterecord(ville);
                    }
                }
                villeDao.close();
                k++;
                progress=(int)((k/(float)nbGlobale)*100);
                publishProgress(progress);
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
        /*===========================================================================
                                Mise a jour de la  table Clinique
              ===========================================================================*/
        try {
            JSONObject jsonObject=new JSONObject(params[1]);
            JSONArray jsonArray=new JSONArray(jsonObject.getString("Cliniques"));
            for(int i=0;i<jsonArray.length();i++){
                JSONObject obj=new JSONObject(jsonArray.getString(i));
                Clinique clinique=new Clinique();
                clinique.setId(obj.getInt("id"));
                clinique.setLabel(obj.getString("label"));
                clinique.setIdVille(obj.getInt("idVille"));
                clinique.setActive(obj.getInt("active"));
                CliniqueDao cliniqueDao=new CliniqueDao(ContextProvider.getAppContext());
                cliniqueDao.open();
                if(cliniqueDao.getCliniqueById(clinique.getId())==null){
                    cliniqueDao.addrecord(clinique);
                }
                else {
                    Clinique inDb=cliniqueDao.getCliniqueByLabel(clinique.getLabel());
                    if (inDb==null || inDb.getIdVille()!=clinique.getIdVille()||inDb.getActive()!=clinique.getActive()){
                        cliniqueDao.updaterecord(clinique);
                    }
                }
                cliniqueDao.close();
                k++;
                progress=(int)((k/(float)nbGlobale)*100);
                publishProgress(progress);
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
            /*===========================================================================
                                Mise a jour de la  table Spécialité
              ===========================================================================*/
        try {
            JSONObject jsonObject=new JSONObject(params[2]);
            JSONArray jsonArray=new JSONArray(jsonObject.getString("Specialites"));
            for(int i=0;i<jsonArray.length();i++){
                JSONObject obj=new JSONObject(jsonArray.getString(i));
                Specialite specialite=new Specialite();
                specialite.setId(obj.getInt("id"));
                specialite.setLabel(obj.getString("label"));
                specialite.setParent(obj.getInt("idParent"));
                specialite.setFils(obj.getInt("fils"));
                specialite.setActive(obj.getInt("active"));
                SpecialiteDao specialiteDao=new SpecialiteDao(ContextProvider.getAppContext());
                specialiteDao.open();
                if(specialiteDao.getSpecialiteById(specialite.getId())==null){
                    specialiteDao.addrecord(specialite);
                }
                else{
                    Specialite inDb=specialiteDao.getSpecialiteByLabel(specialite.getLabel());
                    if(inDb==null || inDb.getParent()!=specialite.getParent()||inDb.getFils()!=specialite.getFils()||inDb.getActive()!=specialite.getActive()){
                        specialiteDao.updaterecord(specialite);
                    }
                }
                specialiteDao.close();
                k++;
                progress=(int)((k/(float)nbGlobale)*100);
                publishProgress(progress);
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
        /*===========================================================================
                                Mise a jour de la  table Creneau
              ===========================================================================*/
        try {
            JSONObject jsonObject=new JSONObject(params[3]);
            JSONArray jsonArray=new JSONArray(jsonObject.getString("Creneaux"));
            for(int i=0;i<jsonArray.length();i++){
                JSONObject obj=new JSONObject(jsonArray.getString(i));
                Creneau creneau=new Creneau();
                creneau.setId(obj.getInt("id"));
                creneau.setLabel(obj.getString("label"));
                creneau.setPeriod(obj.getString("period"));
                creneau.setActive(obj.getInt("active"));
                CreneauDao creneauDao=new CreneauDao(ContextProvider.getAppContext());
                creneauDao.open();
                if(creneauDao.getCreneauById(creneau.getId())==null){
                    creneauDao.addrecord(creneau);
                }
                else{
                    Creneau inDb=creneauDao.getCreneauByLabel(creneau.getLabel());
                    if (inDb==null||inDb.getActive()!=creneau.getActive()){
                        creneauDao.updaterecord(creneau);
                    }
                }
                creneauDao.close();
                k++;
                progress=(int)((k/(float)nbGlobale)*100);
                publishProgress(progress);
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }

            /*===========================================================================
                                Mise a jour de la  table Rdv
              ===========================================================================*/

        try {
            JSONObject jsonObject=new JSONObject(params[4]);
            JSONArray jsonArray=new JSONArray(jsonObject.getString("Rdv"));
            for(int i=0;i<jsonArray.length();i++){
                JSONObject obj=new JSONObject(jsonArray.getString(i));
                Rdv rdv=new Rdv();
                rdv.setIdSpe(obj.getInt("idSpe"));
                rdv.setIdCli(obj.getInt("idCli"));
                rdv.setIdCre(obj.getInt("idCre"));
                rdv.setIdJr(obj.getInt("idJr"));
                rdv.setEtat(obj.getInt("etat"));
                RdvDao rdvDao=new RdvDao(ContextProvider.getAppContext());
                rdvDao.open();
                Rdv inDb=rdvDao.getRdv(rdv.getIdSpe(),rdv.getIdCli(),rdv.getIdCre(),rdv.getIdJr());
                if(inDb==null){
                    rdvDao.addrecord(rdv);
                }
                else{
                    if(inDb.getEtat()!=rdv.getEtat()){
                        rdvDao.updaterecord(rdv);
                    }
                }
                rdvDao.close();
                k++;
                progress=(int)((k/(float)nbGlobale)*100);
                publishProgress(progress);
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }

            /*===========================================================================
                                Mise a jour de la  table Jour
              ===========================================================================*/
        try {
            JSONObject jsonObject=new JSONObject(params[5]);
            JSONArray jsonArray=new JSONArray(jsonObject.getString("Jour"));
            for(int i=0;i<jsonArray.length();i++){
                JSONObject obj=new JSONObject(jsonArray.getString(i));
                Jour jour=new Jour();
                jour.setId(obj.getInt("id"));
                jour.setLabel(obj.getString("label"));
                JourDao jourDao=new JourDao(ContextProvider.getAppContext());
                jourDao.open();
                if(jourDao.getJourById(jour.getId())==null){
                    jourDao.addrecord(jour);
                }
                else{
                    Jour inDb=jourDao.getJourByLabel(jour.getLabel());
                    if (inDb==null){
                        jourDao.updaterecord(jour);
                    }
                }
                jourDao.close();
                k++;
                progress=(int)((k/(float)nbGlobale)*100);
                publishProgress(progress);
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPostExecute(Void Result){
        if(container!=null && container.getActivity()!=null) {
            container.afterUdpateTask();
            DownFragment.taskId=1;
            this.container=null;
        }
        DownFragment.isterminated=true;
    }
}

