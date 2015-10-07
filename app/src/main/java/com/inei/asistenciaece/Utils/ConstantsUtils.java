package com.inei.asistenciaece.Utils;

import java.util.HashMap;

public class ConstantsUtils {
//    public static final String HOSTNAME     = "http://capacitacion.amautas.net";
    public static final String HOSTNAME     = "http://172.16.100.61/ece_capacitacion";
    public static final String URL_LOGIN    = HOSTNAME + "/tablet-login";
    public static final String URL_VERSION  = HOSTNAME + "/tablet-version";
    public static final String URL_CONSOLIDATED = HOSTNAME + "/dashboard";
    public static final String URL_PADRON   = HOSTNAME + "/tablet-padron";
    public static final String URL_SYNC_PADRON  = HOSTNAME + "/tablet-sincronizar-padron";
    public static final HashMap<String, String> parameterError = new HashMap<String, String>(){{put("error", "No hay nada");}};
    /*public static final String URL_PADRON= "http://192.95.3.149:90/ece_ws/tablet-padron";*/
    /*public static final String URL_LOGIN = "http://192.95.3.149:90/ece_ws/tablet-login";*/
    /*public static final String URL_VERSION = "http://192.95.3.149:90/ece_ws/tablet-version";*/

    /*public static final String URL_CONSOLIDATED = "http://192.95.3.149:90/ece/login";*/
}
