package com.kien.dddsample.infrastructure.util;

import java.util.Locale;

public class StringUtil {
    public static String moneyFormat(Integer number){
        return String.format(Locale.GERMANY, "%,d", number);
    }
}
