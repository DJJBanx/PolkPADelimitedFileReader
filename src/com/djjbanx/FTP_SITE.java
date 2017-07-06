package com.djjbanx;

import java.util.ArrayList;

/**
 * Created by shard on 6/28/2017.
 */
public class FTP_SITE extends Reader {

    public FTP_SITE(String location) {
        super(location);
    }

    @Override
    protected void execute(ArrayList<String> sCurrentLine) {
        try {
            long ParcelID = Long.parseLong(parseLine(sCurrentLine.get(0)));
            int meta = Integer.parseInt(parseLine(sCurrentLine.get(1)));
            String address = analyzeAddress(sCurrentLine);

            Parcel tempest = new Parcel(ParcelID, meta);
            tempest.setAddress(address);

            if (packages.containsKey(ParcelID)) {
                if (packages.get(ParcelID).containsMeta(meta)) {
                    packages.get(ParcelID).getParcel(meta).setAddress(address);
                } else packages.get(ParcelID).addParcel(tempest);
            } else {
                ParcelPackage temp = new ParcelPackage(ParcelID, tempest);
                packages.put(ParcelID, temp);
            }
        } catch (NumberFormatException e) {}
    }

    private String analyzeAddress(ArrayList<String> sCurrentline) {
        String stnum = parseLine(sCurrentline.get(5));
        String stnumsuf = parseLine(sCurrentline.get(6));
        String stpref = parseLine(sCurrentline.get(4));
        String st = parseLine(sCurrentline.get(3));
        String stsuf = parseLine(sCurrentline.get(7));
        String stsufdir = parseLine(sCurrentline.get(8));
        String stunit = parseLine(sCurrentline.get(9));
        String zip = parseLine(sCurrentline.get(10));
        String city = parseLine(sCurrentline.get(11));

        String address = stnum;
        if (!(stnumsuf.equals("") || stnumsuf.equals(" ") || stnumsuf.contains("  "))) address+=stnumsuf;
        if (!(stpref.equals("") || stpref.equals(" ") || stpref.contains("  "))) address+=" " + stpref;
        address += " " + st;
        if (!(stsuf.equals("") || stsuf.equals(" ") || stsuf.contains("  "))) address+=" " + stsuf;
        if (!(stsufdir.equals("") || stsufdir.equals(" ") || stsufdir.contains("  "))) address+= " " + stsufdir;
        if (!(stunit.equals("") || stunit.equals(" ") || stunit.contains("  "))) address+=" " + stunit;
        address += ", " + city + ", " + "FL" + ", " + zip;
        return address;
    }
}
