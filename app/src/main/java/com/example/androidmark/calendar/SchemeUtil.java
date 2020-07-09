package com.example.androidmark.calendar;

import com.haibin.calendarview.Calendar;

import java.util.HashMap;
import java.util.Map;

public class SchemeUtil {

    public static Map<String, Calendar> getSchemes(final int year, final int month) {
        final int yellow = 0xFFFFC659;
        final int green = 0xFF00A89D;
        final int blue = 0xFF0085E3;

        return new HashMap<String, Calendar>() {
            {

                for (int i = -10; i < 10; i++) {
                    for (int j = 1; j < 28; j++) {
                        if (j % 2 == 0) {
                            put(getSchemeCalendar(year, month + i, j, null, yellow, green, blue).toString(),
                                    getSchemeCalendar(year, month + i, j, null, yellow, green, blue));
                        } else {
                            put(getSchemeCalendar(year, month + i, j, null, green, yellow).toString(),
                                    getSchemeCalendar(year, month + i, j, null, green, yellow));
                        }
                    }
                }

            }
        };

    }

    private static Calendar getSchemeCalendar(int year, int month, int day, String text, int... color) {
        Calendar calendar = new Calendar();
        calendar.setYear(year);
        calendar.setMonth(month);
        calendar.setDay(day);
        calendar.setScheme(text);
        for (int c : color) {
            calendar.addScheme(c, null);
        }
        return calendar;
    }


}
