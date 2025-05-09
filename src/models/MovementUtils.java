package models;

import java.text.SimpleDateFormat;

public class MovementUtils {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    public static String formatDate(java.util.Date date) {
        return sdf.format(date);
    }

    public static java.util.Date parseDate(String date) throws java.text.ParseException {
        return sdf.parse(date);
    }
}
