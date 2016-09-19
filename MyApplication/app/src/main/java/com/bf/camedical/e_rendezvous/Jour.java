package com.bf.camedical.e_rendezvous;

public class Jour {
    private int id;
    private String label;
    public Jour(){

    }
    /*public Jour(int id,String nom){
        this.id=id;
        this.label=nom;
    }*/

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
