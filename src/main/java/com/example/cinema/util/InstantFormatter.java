package com.example.cinema.util;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class InstantFormatter {
    private static final DateTimeFormatter formatter = DateTimeFormatter
            .ofLocalizedDateTime(FormatStyle.LONG)
            .withLocale(Locale.UK)
            .withZone(ZoneId.systemDefault());

    public static final ZoneOffset MINSK_OFFSET = ZoneOffset.ofHours(3);

    public static String format(Instant instant) {
        return formatter.format(instant);
    }
}
