package com.bf.camedical.e_rendezvous;
import android.content.Context;
import android.database.Cursor;
import android.content.ContentValues;

public class CreneauDao extends DbDao{
    public static final String creTabName="Creneau";
    public static final String creKey="id";
    public static final String crePeriod="period";
    public static final String creLabel="label";
    public static final String isActive="active";
   /* public static final String creTabCreate="CREATE TABLE "+creTabName
            +" ("+creKey+" INTEGER PRIMARY KEY ,"
            +crePeriod+" TEXT,"
            +creLabel+" TEXT ,"
            +isActive+" INTEGER);";
    public static final String creTabDrop="DROP TABLE IF EXISTS "+creTabName+";";*/

    public CreneauDao(Context pContext) {
        super(pContext);
    }
    public void addrecord(Creneau cre){
        ContentValues value = new ContentValues();
        value.put(creKey,cre.getId());
        value.put(crePeriod, cre.getPeriod());
        value.put(creLabel,cre.getLabel());
        value.put(isActive,cre.getActive());
        myDb.insert(creTabName, null, value);
    }
    public void updaterecord(Creneau cre){
        ContentValues value = new ContentValues();
        value.put(crePeriod, cre.getPeriod());
        value.put(creLabel,cre.getLabel());
        value.put(isActive,cre.getActive());
        myDb.update(creTabName, value, creKey + "=" + cre.getId(), null);
    }
    public Creneau getCreneauByLabel(String Nom){
        Cursor c = myDb.query(creTabName, new String[] {creKey, crePeriod,creLabel,isActive}, crePeriod + " LIKE \"" + Nom +"\""+" and "+isActive+" =?",new String[]{String.valueOf(1)}, null,null,null);
        return cursortoCreneau(c);
    }
    public Creneau getCreneauById(int id){
        Cursor c=myDb.query(creTabName, new String[] {creKey, crePeriod,creLabel,isActive},creKey+" =? and "+isActive+" =?",new String[]{String.valueOf(id),String.valueOf(1)},null,null,null);
        return cursortoCreneau(c);
    }
    private Creneau cursortoCreneau(Cursor c){
        Creneau cre = new Creneau();
        if (c.getCount() == 0)
            cre=null;
        else {
            c.moveToFirst();
            cre.setId(c.getInt(0));
            cre.setPeriod(c.getString(1));
            cre.setLabel(c.getString(2));
            cre.setActive(c.getInt(3));
            c.close();
        }
        return cre;
    }
}
