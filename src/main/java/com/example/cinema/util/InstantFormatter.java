package com.example.cinema.util;

import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

@Slf4j
public class InstantFormatter {
    public static final ZoneOffset MINSK_OFFSET = ZoneOffset.ofHours(3);
    private static final DateTimeFormatter formatter = DateTimeFormatter
            .ofLocalizedDateTime(FormatStyle.LONG)
            .withLocale(Locale.UK)
            .withZone(ZoneId.ofOffset("UTC", MINSK_OFFSET));

    public static String format(Instant instant) {
        String formatted = formatter.format(instant);
        log.info("Formatted date UTC+03:00");
        log.info(formatted);
        return formatted.substring(0, formatted.length() - 9);
    }
}
