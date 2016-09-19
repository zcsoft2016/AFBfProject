package com.bf.camedical.e_rendezvous;
import android.content.Context;
import android.database.Cursor;
import android.content.ContentValues;
public class RdvDao extends DbDao {
    public static final String rdvTabName="Rdv";
   // public static final String cliTabName="clinique";
   // public static final String speTabName="specialite";
    //public static final String creTabName="Creneau";
    //public static final String jrTabName="Jour";
    public static final String speKeyR="idSpe";
    public static final String cliKeyR="idCli";
    public static final String creKeyR="idCre";
    public static final String jrKeyR="idJr";
    public static final String rdvEtat="etat";
  /*  public static final String rdvTabCreate="CREATE TABLE"+rdvTabName
                                            +"("+speKeyR+"INTEGER,"
                                            +cliKeyR+"INTEGER,"
                                            +creKeyR+"INTEGER,"
                                            +jrKeyR+"INTEGER,"
                                            +rdvEtat+"INTEGER,"
                                            +"FOREIGN KEY("+speKeyR+")REFERENCES "+ speTabName+"(id),"
                                            +"FOREIGN KEY("+cliKeyR+")REFERENCES "+ cliTabName+"(id),"
                                            +"FOREIGN KEY("+creKeyR+")REFERENCES "+ creTabName+"(id),"
                                            +"FOREIGN KEY("+jrKeyR+")REFERENCES "+ jrTabName+"(id)"
                                            +"PRIMARY KEY("+speKeyR+","+cliKeyR+","+creKeyR+","+jrKeyR
                                            +"));"; */
    //public static final String rdvTabDrop="DROP TABLE IF EXISTS "+rdvTabName+";";
    public static final String condSelect=speKeyR + "=? and "+cliKeyR+"=? and "+creKeyR+"=? and "+jrKeyR+"=?";

    public RdvDao(Context pContext) {
        super(pContext);
    }
    public void addrecord(Rdv rdv){
        ContentValues value = new ContentValues();
        value.put(RdvDao.speKeyR, rdv.getIdSpe());
        value.put(RdvDao.cliKeyR,rdv.getIdCli());
        value.put(RdvDao.creKeyR,rdv.getIdCre());
        value.put(RdvDao.jrKeyR,rdv.getIdJr());
        value.put(RdvDao.rdvEtat,rdv.getEtat());
        myDb.insert(RdvDao.rdvTabName, null, value);
    }
    public void updaterecord(Rdv rdv){
        ContentValues value = new ContentValues();
        value.put(RdvDao.rdvEtat,rdv.getEtat());
        myDb.update(rdvTabName, value, condSelect,new String[]{String.valueOf(rdv.getIdSpe()),String.valueOf(rdv.getIdCli()),String.valueOf(rdv.getIdCre()),String.valueOf(rdv.getIdJr())});
    }
    public Rdv getRdv(int idspe,int idcli,int idcr,int idjr){
        Cursor c = myDb.query(rdvTabName, new String[] {speKeyR, cliKeyR,creKeyR,jrKeyR,rdvEtat},condSelect,new String[]{String.valueOf(idspe),String.valueOf(idcli),String.valueOf(idcr),String.valueOf(idjr)}, null,null,null);
        return cursortoRdv(c);
    }
    private Rdv cursortoRdv(Cursor c){
       Rdv rdv = new Rdv();
        if (c.getCount() == 0)
            rdv=null;
        else {
            c.moveToFirst();
            rdv.setIdSpe(c.getInt(0));
            rdv.setIdCli(c.getInt(1));
            rdv.setIdCre(c.getInt(2));
            rdv.setIdJr(c.getInt(3));
            rdv.setEtat(c.getInt(4));
            c.close();
        }
        return rdv;
    }
}
