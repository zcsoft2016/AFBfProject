package com.bf.camedical.e_rendezvous;

public class Rdv {
    private int idSpe;
    private int idCli;
    private int idJr;
    private int idCre;
    private int etat;
    public Rdv(){

    }
    /*public Rdv(int idSpe,int idCli,int idJr,int idCre,int etat){
        this.idSpe=idSpe;
        this.idCli=idCli;
        this.idJr=idJr;
        this.idCre=idCre;
        this.etat=etat;
    }*/

    public int getIdSpe() {
        return idSpe;
    }

    public int getIdCli() {
        return idCli;
    }

    public int getIdJr() {
        return idJr;
    }

    public int getIdCre() {
        return idCre;
    }

    public int getEtat() {
        return etat;
    }

    public void setIdSpe(int idSpe) {
        this.idSpe = idSpe;
    }

    public void setIdCli(int idCli) {
        this.idCli = idCli;
    }

    public void setIdJr(int idJr) {
        this.idJr = idJr;
    }

    public void setIdCre(int idCre) {
        this.idCre = idCre;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }
}
