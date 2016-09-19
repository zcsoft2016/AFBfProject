package com.bf.camedical.e_rendezvous;
import android.os.Parcel;
import android.os.Parcelable;


public class Clinique implements Parcelable{
    private int id;
    private String label;
    private int idVille;
    private int active;
    public Clinique(){

    }
    public Clinique(int id,String nom,int vKey,int active){
        this.id=id;
        this.label=nom;
        this.idVille=vKey;
        this.active=active;
    }

    protected Clinique(Parcel in) {
        id = in.readInt();
        label = in.readString();
        idVille = in.readInt();
    }

    public static final Creator<Clinique> CREATOR = new Creator<Clinique>() {
        @Override
        public Clinique createFromParcel(Parcel in) {
            return new Clinique(in);
        }

        @Override
        public Clinique[] newArray(int size) {
            return new Clinique[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public int getIdVille() {
        return idVille;
    }

    public int getActive() {
        return active;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setIdVille(int id_ville) {
        this.idVille = id_ville;
    }

    @Override
    public String toString() {
        return "ID: "+id+"\nNom: "+"Ville="+idVille;
    }

    public void setActive(int active) {
        this.active = active;
    }

    /**
     * Describe the kinds of special objects contained in this Parcelable's
     * marshalled representation.
     *
     * @return a bitmask indicating the set of special object types marshalled
     * by the Parcelable.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Flatten this object in to a Parcel.
     *
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(label);
        dest.writeInt(idVille);
    }

}
