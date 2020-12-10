package com.app.graphql.app.gql.util;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

public class DateUtil {

    public final static String FORMAT = "YYYY-MM-DD";

    public static Date parseDate(String date) throws ParseException {
        return DateUtils.parseDate(date, FORMAT);
    }

}
