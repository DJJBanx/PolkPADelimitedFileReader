package com.djjbanx;

public class Main {

    public static void main(String[] args) {

        Reader owner = new FTP_OWNER("src/com/djjbanx/ftp_owner.txt");
        Reader site = new FTP_SITE("src/com/djjbanx/ftp_site.txt");
        Reader.printOwnerExclusiveParcels("Barnett", "Carol");
         /****/
    }
}
