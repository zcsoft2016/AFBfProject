package com.bf.camedical.e_rendezvous;

public class Telephone {
    private String pro;
    private String perso;

    public Telephone(){}

    public Telephone(String pro, String perso) {
        this.pro = pro;
        this.perso = perso;
    }

    public String getPro() {
        return pro;
    }

    public String getPerso() {
        return perso;
    }

    public void setPro(String pro) {
        this.pro = pro;
    }

    public void setPerso(String perso) {
        this.perso = perso;
    }
}

