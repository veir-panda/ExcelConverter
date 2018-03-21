package me.veir.excelconverter.Util;

import java.math.BigDecimal;

public class ConverterUtil {
    public static Double getValue(String value){
        Integer degree = Integer.valueOf(value.substring(0, value.length()- 4));
        Integer min = Integer.valueOf(value.substring(value.length()-4, value.length()-2));
        Integer sec = Integer.valueOf(value.substring(value.length()-2));
        Double v = degree + min/60.0 + sec/3600.0;
        BigDecimal bd = new BigDecimal(v);
        return bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
