package com.bf.camedical.e_rendezvous;

import android.os.Parcel;
import android.os.Parcelable;

public class Specialite implements Parcelable {
    private  int id;
    private String label;
    private int parent;
    private int fils;
    private int active;
    public Specialite(){

    }
    public Specialite(int id,String nom,int idParent,int fils,int active){
        this.id=id;
        this.label=nom;
        this.parent=idParent;
        this.fils=fils;
        this.active=active;
    }

    protected Specialite(Parcel in) {
        id = in.readInt();
        label = in.readString();
        parent = in.readInt();
        fils = in.readInt();
    }

    public static final Creator<Specialite> CREATOR = new Creator<Specialite>() {
        @Override
        public Specialite createFromParcel(Parcel in) {
            return new Specialite(in);
        }

        @Override
        public Specialite[] newArray(int size) {
            return new Specialite[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public int getParent() {
        return parent;
    }

    public int getFils(){
        return fils;
    }
    public void setActive(int active) {
        this.active = active;
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

    public void setParent(int parent) {
        this.parent = parent;
    }

    public void setFils(int fils) {
        this.fils = fils;
    }

    @Override
    public String toString() {
        return "ID: "+id+"Nom: "+label+"ID parent: "+parent;
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
        dest.writeInt(parent);
        dest.writeInt(fils);
    }
}
