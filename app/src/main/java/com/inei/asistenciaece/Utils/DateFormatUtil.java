package com.inei.asistenciaece.Utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateFormatUtil {
    public static String getDateTime(){
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String parseDate = sdf.format(date);
        return parseDate;
    }

    public static String getTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        String parseDate = sdf.format(date);
        return parseDate;
    }

    public static String getDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String parseDate = sdf.format(date);
        return parseDate;
    }
}
