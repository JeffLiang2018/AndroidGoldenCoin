package com.example.jeffliang.coen268finalproject.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateStringConvert {

//    private static DateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

    public static Date stringConvertToDate(String strDate) throws ParseException {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        return df.parse(strDate);
    }

    public static String dateConvertToString(Date date) {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        return df.format(date);
    }
}
