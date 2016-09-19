package com.bf.camedical.e_rendezvous;
import android.content.Context;
import android.database.Cursor;
import android.content.ContentValues;

import java.sql.ClientInfoStatus;
import java.util.ArrayList;

public class CliniqueDao extends DbDao{
    public static final String vTabName="Ville";
    public static final String cliTabName="clinique";
    public static final String cliKey="id";
    public static final String cliLabel="label";
    public static final String vilKey="idVille";
    public static final String isActive="active";
    public static final String cliTabCreate="CREATE TABLE "+cliTabName
            +" ("+cliKey+" INTEGER PRIMARY KEY ,"
            +cliLabel+" TEXT,"
            + vilKey+" INTEGER,"
            +isActive+" INTEGER ,"
            + "FOREIGN KEY("+vilKey+") REFERENCES  "+ vTabName+"(id)"+");";
    public static final String cliTabDrop="DROP TABLE IF EXISTS "+cliTabName+";";

    public CliniqueDao(Context pContext) {
        super(pContext);
    }

    public void addrecord(Clinique cli){
        ContentValues value = new ContentValues();
        value.put(CliniqueDao.cliKey, cli.getId());
        value.put(CliniqueDao.cliLabel, cli.getLabel());
        value.put(CliniqueDao.vilKey,cli.getIdVille());
        value.put(CliniqueDao.isActive,cli.getActive());
        myDb.insert(CliniqueDao.cliTabName, null, value);
    }
    public void removerecord(int id){
        myDb.delete(cliTabName, cliKey + "=" + id, null);
    }
    public void updaterecord(Clinique cli){
        ContentValues value = new ContentValues();
        value.put(cliLabel, cli.getLabel());
        value.put(vilKey, cli.getIdVille());
        value.put(CliniqueDao.isActive,cli.getActive());
        myDb.update(cliTabName, value, cliKey + "=" + cli.getId(), null);
    }
    public Clinique getCliniqueByLabel(String Nom){
        Cursor c = myDb.query(cliTabName, new String[] {cliKey, cliLabel,vilKey,isActive}, cliLabel + " LIKE \"" + Nom +"\""+" and "+isActive+" =?",new String[]{String.valueOf(1)}, null,null,null);
        return cursortoClinique(c);
    }
    public Clinique getCliniqueById(int id){
        Cursor c=myDb.query(cliTabName,new String[]{cliKey, cliLabel,vilKey,isActive},cliKey+" =? and "+isActive+" =?",new String[]{String.valueOf(id),String.valueOf(1)},null,null,null);
        return cursortoClinique(c);
    }
    private Clinique cursortoClinique(Cursor c){
        Clinique cli = new Clinique();
        if (c.getCount() == 0)
            cli=null;
        else {
            c.moveToFirst();
            cli.setId(c.getInt(0));
            cli.setLabel(c.getString(1));
            cli.setIdVille(c.getInt(2));
            cli.setActive(c.getInt(3));
            c.close();
        }
        return cli;
    }
    public ArrayList<Clinique> cliniqueInVille(int[] tab,int id) {
        ArrayList<Clinique> resul=new ArrayList<>();
        for(int i:tab){
            Clinique cl=this.getCliniqueById(i);
            if(cl.getIdVille()==id) resul.add(cl);
        }
        return resul;
    }
}
