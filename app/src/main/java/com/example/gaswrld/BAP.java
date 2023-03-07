package com.example.gaswrld;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class BAP {

    public static void dbg (String debugMsg) {
        System.err.print( "~~~> " + debugMsg + " <~~~\n");
    }
    public static boolean isValidEmailAddress(String email) {
        boolean result = true;
        dbg("Checking email validity");
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        }
        catch (AddressException ex) {
            result = false;
        }
        return result;
    }
}