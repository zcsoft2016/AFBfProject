package com.bf.camedical.e_rendezvous;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class RequeteClient {
    private Context context;
    public RequeteClient(Context context) {
        this.context=context;
    }
    /* ===========================================================================================================================
                                         Requete qui retourne toutes les villes
       ===========================================================================================================================
       */
    public ArrayList<Ville> returnVilles(){
        ArrayList<Ville> villes=new ArrayList<>();
        VilleDao villeDao=new VilleDao(context);
        villeDao.open();
        Cursor c=villeDao.myDb.rawQuery("SELECT id FROM Ville ORDER BY label",null);
        while(c.moveToNext()){
            villes.add(villeDao.getVilleById(c.getInt(0)));
        }
        c.close();
        return villes;
    }

    /* ===========================================================================================================================
                                         Requete qui retourne toutes les spécialités
       ===========================================================================================================================
       */

    public ArrayList<Specialite> returnSpecialites(){
        ArrayList<Specialite> cat = new ArrayList<>();
        SpecialiteDao speDao= new SpecialiteDao(context);
        speDao.open();
        Cursor c=speDao.myDb.query(SpecialiteDao.speTabName, new String[]{SpecialiteDao.speKey },SpecialiteDao.speParent+" =?",new  String[]{String.valueOf(0)},null, null,SpecialiteDao.speLabel);
        while(c.moveToNext()){
            cat.add(speDao.getSpecialiteById(c.getInt(0)));
        }
        c.close();
        speDao.close();
        return cat;
    }
    /* ============================================================================================================
                 Requete qui retourne les sous catégories de spécialité connaisant la catégorie de spécialité
         ============================================================================================================
       */

    public ArrayList<Specialite> sousCategories(int speId){
        ArrayList<Specialite> sousCat = new ArrayList<>();
        SpecialiteDao speDao= new SpecialiteDao(context);
        speDao.open();
        Specialite spe=speDao.getSpecialiteById(speId);
        if(spe.getFils()==0) sousCat=null;
        else{
            Cursor c=speDao.myDb.query(SpecialiteDao.speTabName,new String[]{SpecialiteDao.speKey},SpecialiteDao.speParent+" =?",new String[]{String.valueOf(speId)},null,null,SpecialiteDao.speLabel);
            while(c.moveToNext()){
                sousCat.add(speDao.getSpecialiteById(c.getInt(0)));
            }
            c.close();
        }
        speDao.close();
        return sousCat;
    }
    /* ===========================================================================================================================
               Requete qui retourne les cliniques situées dans une ville donnée et qui consultent pour une spécialité donnée
       ===========================================================================================================================
       */

    public ArrayList<Clinique>  returnClinique(int idV,int idS){
        CliniqueDao cliDao = new CliniqueDao(context);
        cliDao.open();
        Cursor c=cliDao.myDb.query(true,RdvDao.rdvTabName, new String[]{RdvDao.cliKeyR}, RdvDao.speKeyR + " =? and " + RdvDao.rdvEtat + " =?", new String[]{String.valueOf(idS), String.valueOf(1)}, null,null,null,null);
        int tabId[]=new int[c.getCount()];
        int  i=0;
        while (c.moveToNext()){
            tabId[i]=c.getInt(0);
            i++;
        }
        ArrayList<Clinique> selection=cliDao.cliniqueInVille(tabId,idV);
        c.close();
       Collections.sort(selection,new cliniqueComparator());
        return selection;
    }
    /* ===========================================================================================================================
               Requete qui pour une Spécialité, une clinique,une date,retourne la liste des creneaux de consultation disponibles
       ===========================================================================================================================
       */
    public ArrayList<Creneau> returnCreneau(int idS,int idCl,String dateStr){
        JourDao jrdao=new JourDao(context);
        ArrayList<Creneau> result=new ArrayList<>();
        jrdao.open();
        Jour jr=jrdao.getJourByDate(dateStr);
        if(jr!=null) {
            Cursor c = jrdao.myDb.rawQuery("SELECT a.id FROM Creneau a INNER JOIN Rdv b WHERE a.id=b.idCre and b.etat=? and " + "b.idCli =? and " + "b.idSpe=? and " + "b.idJr=? ", new String[]{String.valueOf(1), String.valueOf(idCl), String.valueOf(idS), String.valueOf(jr.getId())});
            if (c.getCount() == 0) result = null;
            else {
                while (c.moveToNext()) {
                    CreneauDao creDao = new CreneauDao(context);
                    creDao.open();
                    result.add(creDao.getCreneauById(c.getInt(0)));
                }
                c.close();
            }
        }
        else result=null;
        return result;
    }
    public class cliniqueComparator implements Comparator<Clinique>{

        @Override
        public int compare(Clinique lhs, Clinique rhs) {
            return lhs.getLabel().compareTo(rhs.getLabel());
        }
    }
}
