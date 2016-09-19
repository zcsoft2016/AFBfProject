package com.bf.camedical.e_rendezvous;
import android.content.Context;
import android.database.Cursor;
import android.content.ContentValues;
public class JourDao extends DbDao{
    public static final String jrTabName="Jour";
    public static final String jrKey="id";
    public static final String jrLabel="label";
   /* public static final String tab_create="CREATE TABLE "+jrTabName+" ("+jrKey+" INTEGER PRIMARY KEY ,"+jrLabel+" TEXT);";
    public static final String drop_tab="DROP TABLE IF EXISTS "+jrTabName+";";*/

    public JourDao(Context pContext) {
        super(pContext);
    }
    public void addrecord(Jour jr){
        ContentValues value = new ContentValues();
        value.put(jrKey,jr.getId());
        value.put(JourDao.jrLabel, jr.getLabel());
        myDb.insert(jrTabName, null, value);
    }
    public void updaterecord(Jour jr){
        ContentValues value = new ContentValues();
        value.put(jrKey,jr.getId());
        value.put(JourDao.jrLabel,jr.getLabel());
        myDb.update(jrTabName, value, jrKey + "=" + jr.getId(), null);
    }
    public Jour getJourByLabel(String Nom){
        Cursor c = myDb.query(jrTabName, new String[] {jrKey, jrLabel}, jrLabel + " LIKE \"" + Nom +"\"", null, null,null,null);
        return cursortoVille(c);
    }
    public Jour getJourById(int id){
        Cursor c = myDb.query(jrTabName, new String[] {jrKey, jrLabel},jrKey+" =?",new String[]{String.valueOf(id)}, null,null,null);
        return cursortoVille(c);
    }
    private Jour cursortoVille(Cursor c){
        Jour jr =new Jour();
        if (c.getCount() == 0)
            jr=null;
        else {
            c.moveToFirst();
            jr.setId(c.getInt(0));
            jr.setLabel(c.getString(1));
            c.close();
        }
        return jr;
    }
    public Jour getJourByDate(String dateS){
        Cursor c=this.myDb.rawQuery("SELECT strftime('%w','"+dateS+"');",null);
        c.moveToFirst();
        int indexjr=c.getInt(0);
        c.close();
        Jour jr=null;
        switch (indexjr){
            case 0:
                jr=this.getJourByLabel("Dimanche");
                break;
            case 1:
                jr=this.getJourByLabel("Lundi");
                break;
            case 2:
                jr=this.getJourByLabel("Mardi");
                break;
            case 3:
                jr=this.getJourByLabel("Mercredi");
                break;
            case 4:
                jr=this.getJourByLabel("Jeudi");
                break;
            case 5:
                jr=this.getJourByLabel("Vendredi");
                break;
            case 6:
                jr=this.getJourByLabel("Samedi");
                break;
        }
      return jr;
    }
}
