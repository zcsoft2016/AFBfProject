package com.bf.camedical.e_rendezvous;
import android.content.Context;
import android.database.Cursor;
import android.content.ContentValues;

public class VilleDao extends DbDao{
    // DAO: data access object
    public static final String vTabName="Ville";
    public static final String vKey="id";
    public static final String nomVille="label";
    public static final String isActive="active";
    /*public static final String vTabCreate="CREATE TABLE "+vTabName
            +" ("+vKey+" INTEGER PRIMARY KEY ,"
            +nomVille+" TEXT,"
            +isActive+" INTEGER);";
    public static final String vTabDrop="DROP TABLE IF EXISTS "+vTabName+";"; */

    public VilleDao(Context pContext) {
        super(pContext);
    }

    public void addrecord(Ville v){
        ContentValues value = new ContentValues();
        value.put(VilleDao.vKey, v.getId());
        value.put(VilleDao.nomVille, v.getLabel());
        value.put(isActive,v.getActive());
        myDb.insert(VilleDao.vTabName, null, value);
    }
    public void updaterecord(Ville v){
        ContentValues value = new ContentValues();
        value.put(VilleDao.nomVille, v.getLabel());
        myDb.update(vTabName, value, vKey + "=" + v.getId(), null);
    }
    public Ville getVilleByLabel(String Nom){
        Cursor c = myDb.query(vTabName, new String[] {vKey, nomVille,isActive}, nomVille + " LIKE \"" + Nom +"\""+" and "+isActive+" =?", new String[]{String.valueOf(1)}, null,null,null);
        return cursortoVille(c);
    }
    public Ville getVilleById(int id){
        Cursor c = myDb.query(vTabName, new String[] {vKey, nomVille,isActive},vKey+" =? and "+isActive+" =?",new String[]{String.valueOf(id),String.valueOf(1)}, null,null,null);
        return cursortoVille(c);
    }
    private Ville cursortoVille(Cursor c){
        Ville v = new Ville();
        if (c.getCount() == 0)
             v=null;
        else {
            c.moveToFirst();
            v.setId(c.getInt(0));
            v.setLabel(c.getString(1));
            v.setActive(c.getInt(2));
            c.close();
        }
        return v;
    }
}