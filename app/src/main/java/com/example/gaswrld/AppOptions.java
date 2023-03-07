package com.example.gaswrld;

import com.google.appinventor.components.runtime.ComponentContainer;
import com.google.appinventor.components.runtime.TinyDB;

//import static net.fachtnaroe.gwlogin2023_fr.PrivateApplicationSettings.CLOUD_VERIFICATION_URL;
//import static net.fachtnaroe.gwlogin2023_fr.PrivateApplicationSettings.GAME_URL;
//import static net.fachtnaroe.gwlogin2023_fr.PrivateApplicationSettings.PRIVATE_TESTING_PASS;
//import static net.fachtnaroe.gwlogin2023_fr.PrivateApplicationSettings.PRIVATE_TESTING_USER;

public class AppOptions{

    public static final String authenticationURL = "https://grassworld.fachtnaroe.net/auth/";
    //public static final String testEmail="krzysztof.noj22@student.tcfe.ie";
    //public static final String testpass="trans13nt";
    public static final boolean TESTING=true;
    public static final String URL_MAIN="https://grassworld.fachtnaroe.net/";
    public static final Integer SplashTimeOut=1000;
    // the buildNumber can be generated automatically. Look in build.gradle to see how
    public final String buildNumber=Integer.toString(BuildConfig.VERSION_CODE);
    //    public final String versionName=BuildConfig.VERSION_NAME;


    public Boolean showStartingMessage=true;
    // the countdown controls whether the "skip starting message" option is shown.
    public Integer startingMessageCountdown=5;
    // a general yes/no flag <0 unconfigured, >0 configured
    public Integer configurationStatus=-1;
    TinyDB localDB;
    private static final String str_showStartingMessage="showStartingMessage";
    private static final String str_startingMessageCountdown="WhatALongWindedWayOfDoingThis";
    private static final String str_configurationStatus="configurationStatus";
    // providing a NAME_DEFAULT_DEVICE saves on testing/debugging time
//    public static final String default_DEVICE_NAME ="TCFE-CO2-98-88";

    public AppOptions(ComponentContainer screenName){
        localDB= new TinyDB(screenName);
    }

    public boolean get () {
        try {
            showStartingMessage=(boolean) localDB.GetValue(str_showStartingMessage,showStartingMessage);
            startingMessageCountdown=(Integer) localDB.GetValue(str_startingMessageCountdown,startingMessageCountdown);
            configurationStatus=(Integer) localDB.GetValue(str_configurationStatus, configurationStatus);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public boolean set () {
        try {
            localDB.StoreValue(str_showStartingMessage, showStartingMessage);
            localDB.StoreValue(str_startingMessageCountdown,startingMessageCountdown);
            localDB.StoreValue(str_configurationStatus, configurationStatus);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    String makeGetString(String sensor){
        String test1 = this.URL_MAIN+"?device=";
        test1+= "";
        test1+="&";
        test1+="sensor="+sensor;
        return test1;
    }
}