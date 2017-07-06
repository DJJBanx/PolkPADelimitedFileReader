package com.djjbanx;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by shard on 6/28/2017.
 */
public class FTP_OWNER extends Reader{

    public FTP_OWNER(String location) {
        super(location);
    }

    /**
     * "222601000000021010","1","CRESCASA LLC","1.0000","","2000 E EDGEWOOD DR STE 102","","LAKELAND FL 33803-3600","LAKELAND","FL","33803-3600"
     * ParcelID             Meta    Owner      Percent Mail         Address 1           A2         Address 3           City    State    ZIP
     *
     * @param sCurrentLine The current line of the FTP_OWNER.TXT document.
     */
    @Override
    protected void execute(ArrayList<String> sCurrentLine) {
        try {
            long ParcelID = Long.parseLong(parseLine(sCurrentLine.get(0)));
            int meta = Integer.parseInt(parseLine(sCurrentLine.get(1)));
            String owner = parseLine(sCurrentLine.get(2));
            double percentOwnership = Double.parseDouble(parseLine(sCurrentLine.get(3)));
            String address = analyzeAddress(sCurrentLine);

            Parcel tempest = new Parcel(ParcelID, meta);
            tempest.setOwner(owner);
            tempest.setPercentOwnership(percentOwnership);
            tempest.setOwnerAddress(address);

            if (packages.containsKey(ParcelID)) {
                if (packages.get(ParcelID).containsMeta(meta)) {
                    packages.get(ParcelID).getParcel(meta).setOwner(owner);
                    packages.get(ParcelID).getParcel(meta).setPercentOwnership(percentOwnership);
                    packages.get(ParcelID).getParcel(meta).setOwnerAddress(address);
                } else packages.get(ParcelID).addParcel(tempest);
            } else {
                ParcelPackage temp = new ParcelPackage(ParcelID, tempest);
                packages.put(ParcelID, temp);
            }
        } catch (NumberFormatException e) {}
    }

    private String analyzeAddress(ArrayList<String> sCurrentline) {
        String city = parseLine(sCurrentline.get(8));
        String state = parseLine(sCurrentline.get(9));
        String zip = parseLine(sCurrentline.get(10));
        String address1="",address2="",address3="";
        if (!sCurrentline.get(5).equals("")) address1 = parseLine(sCurrentline.get(5));
        else System.out.println("WOAH WOAH WOAH WOAH WOAH WOAH WOAH WOAH " + sCurrentline.get(0));
        return address1 + ", " + city + ", " + state + ", " + zip;
    }
}
