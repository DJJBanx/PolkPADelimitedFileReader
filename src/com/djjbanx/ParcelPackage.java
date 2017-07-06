package com.djjbanx;

import java.util.ArrayList;

/**
 * Created by shard on 6/28/2017.
 */
public class ParcelPackage {
    private final long ParcelID;
    private int size;
    private int value;

    private ArrayList<Parcel> parcels = new ArrayList<>();

    public ParcelPackage(long parcelID, Parcel parcel) {
        ParcelID = parcelID;
        parcels.add(parcel);
        size = 1;
        value = 0;
    }

    public ParcelPackage addValue(int value) {
        this.value += value;
        return this;
    }

    public int getValue() {
        return value;
    }

    public ParcelPackage addParcel(Parcel parcel) {
        parcels.add(parcel);
        addValue(parcel.getValue());
        size++;
        return this;
    }

    public ArrayList<Parcel> getParcels() {
        return parcels;
    }

    public boolean containsMeta(int meta) {
        return meta <= size && meta > 0;
    }

    public Parcel getParcel(int meta) {
        return (meta>0&&meta<=size) ? parcels.get(meta-1) : null;
    }
}
