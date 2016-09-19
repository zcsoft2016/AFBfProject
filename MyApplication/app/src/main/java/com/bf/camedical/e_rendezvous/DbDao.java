package com.bf.camedical.e_rendezvous;

import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

import java.io.InputStream;
import java.util.List;

public abstract class DbDao {
        // Si je décide de la mettre à jour, il faudra changer cet attribut
        protected final static int VERSION = 1;
        protected final static String NOM = "donnees-E-Rdv.db";
        protected SQLiteDatabase myDb = null;
        protected DatabaseHandler mHandler = null;
        public Context context;
        public DbDao(Context pContext) {
            this.mHandler = new DatabaseHandler(pContext, NOM, null, VERSION);
            this.context=pContext;
        }
        public SQLiteDatabase open() {
            myDb = mHandler.getWritableDatabase();
            return myDb;
        }

        public void close() {
            myDb.close();
        }
    public void remplirDb(){
        // lecture des fichiers csv et copie de ces fichiers des des arrayList;
        InputStream inputVille = context.getResources().openRawResource(R.raw.ville);
        CsvFiletoList toVille = new CsvFiletoList(inputVille);
        List<String[]> listVille = toVille.read();
        InputStream inputSpe = context.getResources().openRawResource(R.raw.specialite);
        CsvFiletoList toSpe = new CsvFiletoList(inputSpe);
        List<String[]> listSpe = toSpe.read();
        InputStream inputCli = context.getResources().openRawResource(R.raw.clinique);
        CsvFiletoList toCli = new CsvFiletoList(inputCli);
        List<String[]> listCli = toCli.read();
        InputStream inputCre = context.getResources().openRawResource(R.raw.creneau);
        CsvFiletoList toCre = new CsvFiletoList(inputCre);
        List<String[]> listCre = toCre.read();
        InputStream inputJr = context.getResources().openRawResource(R.raw.jour);
        CsvFiletoList toJr = new CsvFiletoList(inputJr);
        List<String[]> listJr = toJr.read();
        InputStream inputRdv = context.getResources().openRawResource(R.raw.rdv);
        CsvFiletoList toRdv = new CsvFiletoList(inputRdv);
        List<String[]> listRdv = toRdv.read();
        // remplissage de la base de données:
        VilleDao repV = new VilleDao(context);
        repV.open();
        for (String[] el : listVille) {
            Ville v = new Ville();
            v.setId(Integer.parseInt(el[0]));
            v.setLabel(el[1]);
            v.setActive(Integer.parseInt(el[2]));
            repV.addrecord(v);
        }
        repV.close();
        SpecialiteDao repSpe = new SpecialiteDao(context);
        repSpe.open();
        for (String[] el : listSpe) {
            Specialite spe = new Specialite();
            spe.setId(Integer.parseInt(el[0]));
            spe.setLabel(el[1]);
            spe.setParent(Integer.parseInt(el[2]));
            spe.setFils(Integer.parseInt(el[3]));
            spe.setActive(Integer.parseInt(el[4]));
            repSpe.addrecord(spe);
        }
        repSpe.close();
        CliniqueDao repCli = new CliniqueDao(context);
        repCli.open();
        for (String[] el : listCli) {
            Clinique cli = new Clinique();
            cli.setId(Integer.parseInt(el[0]));
            cli.setLabel(el[1]);
            cli.setIdVille(Integer.parseInt(el[2]));
            cli.setActive(Integer.parseInt(el[3]));
            repCli.addrecord(cli);
        }
        repCli.close();
        CreneauDao repCre = new CreneauDao(context);
        repCre.open();
        for (String[] el : listCre) {
            Creneau cr = new Creneau();
            cr.setId(Integer.parseInt(el[0]));
            cr.setPeriod(el[1]);
            cr.setLabel(el[2]);
            cr.setActive(Integer.parseInt(el[3]));
            repCre.addrecord(cr);
        }
        repCre.close();
        JourDao repJr = new JourDao(context);
        repJr.open();
        for (String[] el : listJr) {
            Jour jr = new Jour();
            jr.setId(Integer.parseInt(el[0]));
            jr.setLabel(el[1]);
            repJr.addrecord(jr);
        }
        repJr.close();
        RdvDao repRdv = new RdvDao(context);
        repRdv.open();
        for (String[] el : listRdv) {
            Rdv rdv = new Rdv();
            rdv.setIdSpe(Integer.parseInt(el[0]));
            rdv.setIdCli(Integer.parseInt(el[1]));
            rdv.setIdCre(Integer.parseInt(el[2]));
            rdv.setIdJr(Integer.parseInt(el[3]));
            rdv.setEtat(Integer.parseInt(el[4]));
            repRdv.addrecord(rdv);
        }
    }

    }
