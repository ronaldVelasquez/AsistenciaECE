package com.inei.asistenciaece.Utils;

import java.util.HashMap;

public class ConstantsUtils {
    public static final String URL_LOGIN = "http://172.16.100.61/ece_ws/tablet-login";
    public static final String URL_VERSION = "http://172.16.100.61/ece_ws/tablet-version";
    public static final String URL_CONSOLIDATED = "http://demo7138985.mockable.io/login-web";
    public static final String URL_PADRON= "http://172.16.100.61/ece_ws/tablet-padron";
    public static final String URL_SYNC_PADRON= "http://172.16.100.61/ece_ws/tablet-sincronizar-padron";
    public static final HashMap<String, String> parameterError = new HashMap<String, String>(){{put("error", "No hay nada");}};
}
