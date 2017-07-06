package com.djjbanx;

/**
 * Created by shard on 6/28/2017.
 */
public class Parcel {
    private final long ParcelID;
    private int meta;
    private int buildingNumber;
    private String owner;

    private double percentOwnership;
    private String address;

    private String ownerAddress;
    private String desc;
    private int value;

    public Parcel(long parcelID, int meta) {
        ParcelID = parcelID;
        this.meta = meta;
    }

    public String getID() {
        return ParcelID+":"+meta;
    }

    public String getPercentOwnership() {
        return "%" + Math.round(percentOwnership*100);
    }

    public int getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(int buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public String getOwnerAddress() {
        return ownerAddress;
    }

    public void setOwnerAddress(String ownerAddress) {
        this.ownerAddress = ownerAddress;
    }

    public void setPercentOwnership(double percentOwnership) {
        this.percentOwnership = percentOwnership;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
