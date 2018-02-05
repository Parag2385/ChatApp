package com.example.parag.chatapp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by parag on 05-02-2018.
 */

public class Utils {

    public static String formatDateTime(long timeInMillies) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a", Locale.ENGLISH);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeInMillies);

        String time = dateFormat.format(calendar.getTime());

        return time;
    }
}
