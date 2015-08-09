package com.inei.asistenciaece.Utils;

import java.util.HashMap;

public class ConstantsUtils {
    public static final String URL_LOGIN = "http://192.95.3.149:90/ece_ws/tablet-login";
    public static final String URL_VERSION = "http://192.95.3.149:90/ece_ws/tablet-version";
    public static final String URL_CONSOLIDATED = "http://192.95.3.149:90/ece/login";
    public static final String URL_PADRON= "http://192.95.3.149:90/ece_ws/tablet-padron";
    public static final String URL_SYNC_PADRON= "http://192.95.3.149:90/ece_ws/tablet-sincronizar-padron";
    public static final HashMap<String, String> parameterError = new HashMap<String, String>(){{put("error", "No hay nada");}};
}
