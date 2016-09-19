package com.bf.camedical.e_rendezvous;

import android.os.Parcel;
import android.os.Parcelable;

public class Ville implements Parcelable {
    private int id;
    private String label;
    private int active;
    public Ville(){

    }
    public Ville(int id,String label,int active){
        this.id=id;
        this.label=label;
        this.active=active;
    }

    protected Ville(Parcel in) {
        id = in.readInt();
        label = in.readString();
    }

    public static final Creator<Ville> CREATOR = new Creator<Ville>() {
        @Override
        public Ville createFromParcel(Parcel in) {
            return new Ville(in);
        }

        @Override
        public Ville[] newArray(int size) {
            return new Ville[size];
        }
    };

    public int getId(){
        return this.id;
    }

    public String getLabel(){
        return this.label;
    }

    public int getActive() {
        return active;
    }

    public void setId(int id){
        this.id=id;
    }

    public void setLabel(String label){
        this.label=label;
    }
    public String toString(){
        return "ID: "+id+" \nNOM: "+label;
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
    public void setActive(int active) {
        this.active = active;
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
    }
}
