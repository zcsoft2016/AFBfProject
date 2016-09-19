package com.bf.camedical.e_rendezvous;
import android.content.Context;
import android.database.Cursor;
import android.content.ContentValues;
public class SpecialiteDao extends DbDao{
    public static final String speTabName="specialite";
    public static final String speKey="id";
    public static final String speLabel="label";
    public static final String speParent="parent";
    public static final String speFils="fils";
    public static final String isActive="active";
    /*public static final String speTabCreate="CREATE TABLE "+speTabName
            + " ("+speKey+" INTEGER PRIMARY KEY ,"
            +speLabel+" TEXT,"
            +speParent+" INTEGER,"
            +speFils+" INTEGER ,"
            +isActive+" INTEGER);";*/
    //public static final String speTabDrop="DROP TABLE IF EXISTS "+speTabName+";";
    public Cursor c;

    public SpecialiteDao(Context pContext){
     super(pContext);
    }
    public void addrecord(Specialite spe){
        ContentValues value = new ContentValues();
        value.put(speKey,spe.getId());
        value.put(speLabel, spe.getLabel());
        value.put(speParent,spe.getParent());
        value.put(speFils,spe.getFils());
        value.put(isActive,spe.getActive());
        myDb.insert(speTabName, null, value);
    }
    public void updaterecord(Specialite spe){
        ContentValues value = new ContentValues();
        value.put(speLabel, spe.getLabel());
        value.put(speParent,spe.getParent());
        value.put(speFils,spe.getFils());
        value.put(isActive,spe.getActive());
        myDb.update(speTabName, value, speKey + "=" + spe.getId(), null);
    }
    public Specialite getSpecialiteByLabel(String Nom){
        Cursor c = myDb.query(speTabName, new String[] {speKey, speLabel,speParent,speFils,isActive}, speLabel + " LIKE \"" + Nom +"\""+" and "+isActive+" =?", new String[]{String.valueOf(1)}, null,null,null);
        return cursortoSpe(c);
    }
    public Specialite getSpecialiteById(int id){
            Cursor c=myDb.query(speTabName, new String[] {speKey, speLabel,speParent,speFils,isActive},speKey+" =? and "+isActive+" =?",new String[]{String.valueOf(id),String.valueOf(1)},null,null,null);
        return cursortoSpe(c);
    }
    private Specialite cursortoSpe(Cursor c){
        Specialite spe = new Specialite();
        if (c.getCount() == 0)
           spe=null;
        else {
            c.moveToFirst();
            spe.setId(c.getInt(0));
            spe.setLabel(c.getString(1));
            spe.setParent(c.getInt(2));
            spe.setFils(c.getInt(3));
            spe.setActive(c.getInt(4));
            c.close();
        }
        return spe;
    }
    }

