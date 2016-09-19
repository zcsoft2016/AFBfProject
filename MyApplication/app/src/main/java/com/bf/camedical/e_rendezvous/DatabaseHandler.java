package com.bf.camedical.e_rendezvous;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.content.Context;
public class DatabaseHandler  extends SQLiteOpenHelper{
    //construction de la table ville
    public static final String vTabName="Ville";
    public static final String vKey="id";
    public static final String nomVille="label";
    public static final String isActive="active";
    public static final String vTabCreate="CREATE TABLE "+vTabName
                                           +" ("+vKey+" INTEGER PRIMARY KEY ,"
                                           +nomVille+" TEXT,"
                                           +isActive+" INTEGER);";
    public static final String vTabDrop="DROP TABLE IF EXISTS "+vTabName+";";
    //construction de la table spécialité
    public static final String speTabName="specialite";
    public static final String speKey="id";
    public static final String speLabel="label";
    public static final String speParent="parent";
    public static final String speFils="fils";
    public static final String speTabCreate="CREATE TABLE "+speTabName
                                       + " ("+speKey+" INTEGER PRIMARY KEY ,"
                                       +speLabel+" TEXT,"
                                       +speParent+" INTEGER,"
                                       +speFils+" INTEGER ,"
                                       +isActive+" INTEGER);";
    public static final String speTabDrop="DROP TABLE IF EXISTS "+speTabName+";";
    // construction de la table clinique
    public static final String cliTabName="clinique";
    public static final String cliKey="id";
    public static final String cliLabel="label";
    public static final String vilKey="idVille";
    public static final String cliTabCreate="CREATE TABLE "+cliTabName
                                              +" ("+cliKey+" INTEGER PRIMARY KEY ,"
                                              +cliLabel+" TEXT,"
                                              + vilKey+" INTEGER,"
                                              +isActive+" INTEGER ,"
                                              + "FOREIGN KEY("+vilKey+") REFERENCES  "+ vTabName+"(id)"+");";
    public static final String cliTabDrop="DROP TABLE IF EXISTS "+cliTabName+";";
    // construction de la table jour
    public static final String jrTabName="Jour";
    public static final String jrKey="id";
    public static final String jrLabel="label";
    public static final String jrTabCreate="CREATE TABLE "+jrTabName
                                            +" ("+jrKey+" INTEGER PRIMARY KEY ,"
                                            +jrLabel+" TEXT);";
    public static final String jrTabDrop="DROP TABLE IF EXISTS "+jrTabName+";";
    // construction de la table Creneau
    public static final String creTabName="Creneau";
    public static final String creKey="id";
    public static final String crePeriod="period";
    public static final String creLabel="label";
    public static final String creTabCreate="CREATE TABLE "+creTabName
                                            +" ("+creKey+" INTEGER PRIMARY KEY ,"
                                            +crePeriod+" TEXT,"
                                            +creLabel+" TEXT ,"
                                            +isActive+" INTEGER);";
    public static final String creTabDrop="DROP TABLE IF EXISTS "+creTabName+";";
    //construction de la table Rdv
    public static final String rdvTabName="Rdv";
    public static final String speKeyR="idSpe";
    public static final String cliKeyR="idCli";
    public static final String creKeyR="idCre";
    public static final String jrKeyR="idJr";
    public static final String rdvEtat="etat";
    public static final String rdvTabCreate="CREATE TABLE "+rdvTabName
            +" ("+speKeyR+" INTEGER,"
            +cliKeyR+" INTEGER,"
            +creKeyR+" INTEGER,"
            +jrKeyR+" INTEGER,"
            +rdvEtat+" INTEGER,"
            +"FOREIGN KEY( "+speKeyR+" ) REFERENCES  "+ speTabName+"(id),"
            +"FOREIGN KEY( "+cliKeyR+" ) REFERENCES  "+ cliTabName+"(id),"
            +"FOREIGN KEY( "+creKeyR+" ) REFERENCES  "+ creTabName+"(id),"
            +"FOREIGN KEY( "+jrKeyR+" )  REFERENCES  "+ jrTabName+"(id),"
            +"PRIMARY KEY( "+speKeyR+","+cliKeyR+","+creKeyR+","+jrKeyR
            +"));";
    public static final String rdvTabDrop="DROP TABLE IF EXISTS "+rdvTabName+";";
    public DatabaseHandler(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(vTabCreate);
        db.execSQL(speTabCreate);
        db.execSQL(cliTabCreate);
        db.execSQL(jrTabCreate);
        db.execSQL(creTabCreate);
        db.execSQL(rdvTabCreate);


    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(vTabDrop);
        db.execSQL(speTabDrop);
        db.execSQL(cliTabDrop);
        db.execSQL(jrTabDrop);
        db.execSQL(creTabDrop);
        db.execSQL(rdvTabDrop);
        onCreate(db);

    }

}
