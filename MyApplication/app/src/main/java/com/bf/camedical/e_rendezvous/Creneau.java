package com.bf.camedical.e_rendezvous;

import android.os.Parcel;
import android.os.Parcelable;

public class Creneau implements Parcelable{
    private int id;
    private String period;
    private String label;
    private int active;
    public Creneau(){

    }
    public Creneau(int id,String periode,String label,int active) {
        this.id=id;
        this.period = periode;
        this.label=label;
        this.active=active;
    }

    protected Creneau(Parcel in) {
        id = in.readInt();
        period = in.readString();
        label = in.readString();
    }

    public static final Creator<Creneau> CREATOR = new Creator<Creneau>() {
        @Override
        public Creneau createFromParcel(Parcel in) {
            return new Creneau(in);
        }

        @Override
        public Creneau[] newArray(int size) {
            return new Creneau[size];
        }
    };
    public int getId() {
        return id;
    }

    public String getPeriod() {
        return period;
    }

    public String getLabel() {
        return label;
    }

    public int getActive() {
        return active;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public void setLabel(String label) {
        this.label = label;
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
        dest.writeString(period);
        dest.writeString(label);
    }
    public void setActive(int active) {
        this.active = active;
    }
}
