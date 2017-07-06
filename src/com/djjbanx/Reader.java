package com.djjbanx;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by shard on 2/1/2017.
 */
public abstract class Reader {
    private BufferedReader br = null;
    private FileReader fr = null;
    private Writer w;

    private PrintWriter pr = null;

    private String location;

    public static HashMap<Long, ParcelPackage> packages;

    public Reader(String location) {
        this.location = location;
        packages = new HashMap<>();
        analyzeWords();
    }

    public static void printAddressExclusiveParcels(String searchAddress) {
        for (ParcelPackage parcelPackage: packages.values()) for (Parcel parcel:parcelPackage.getParcels()) {
            if (parcel.getAddress().contains(searchAddress.toUpperCase())) System.out.println(parcel.getID() + " - " + parcel.getAddress()+ " - " + parcel.getOwner());
        }
    }

    public static void printOwnerExclusiveParcels(String searchOwner) {
        for (ParcelPackage parcelPackage: packages.values()) for (Parcel parcel:parcelPackage.getParcels()) {
            if (parcel.getOwner() == null) System.out.println(parcel.getID() + " - " + parcel.getAddress()+ " - " + parcel.getOwner());
            if (parcel.getOwner().contains(searchOwner.toUpperCase())) System.out.println(parcel.getID() + " - " + parcel.getAddress()+ " - " + parcel.getOwner());
        }
    }

    public static void printAddressExclusiveParcels(String... searchAddresses) {
        for (ParcelPackage parcelPackage: packages.values()) for (Parcel parcel:parcelPackage.getParcels()) {
            boolean contains = true;
            for (String x:searchAddresses) if (!parcel.getAddress().contains(x.toUpperCase())) contains = false;
            if (contains == true) System.out.println(parcel.getID() + " - " + parcel.getAddress() + " - " + parcel.getOwner());
        }
    }

    public static void printOwnerExclusiveParcels(String... searchOwners) {
        for (ParcelPackage parcelPackage: packages.values()) for (Parcel parcel:parcelPackage.getParcels()) {
            boolean contains = true;
            for (String x:searchOwners) if (!parcel.getOwner().contains(x.toUpperCase())) contains = false;
            if (contains == true) System.out.println(parcel.getID() + " - " + parcel.getAddress() + " - " + parcel.getOwner());
        }
    }

    protected String parseLine(String line) {
        if (line.equals("\"\"")) return "";
        return line.substring(1, line.length()-1);
    }

    public void analyzeWords() {
        try {
            fr = new FileReader(location);
            br = new BufferedReader(fr);
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                ArrayList<String> strings = new ArrayList<>();
                for (String x:sCurrentLine.split(",")) strings.add(x);
                execute(strings);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) br.close();
                if (fr != null) fr.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    protected abstract void execute(ArrayList<String> sCurrentLine);
}
