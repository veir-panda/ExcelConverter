package me.veir.excelconverter.converter;

import io.github.biezhi.excel.plus.converter.Converter;

public class StationIDConverter implements Converter<Integer> {

    @Override
    public String write(Integer integer) {
        return null;
    }

    @Override
    public Integer read(String value) {
        value = value.trim();
        try{
            return Integer.valueOf(value);
        } catch (NumberFormatException e){
            try{
                String tmp = String.valueOf(Integer.valueOf(String.valueOf(value.charAt(0)).toUpperCase().charAt(0))) + value.substring(1);
                return Integer.valueOf(tmp);
            } catch (Exception e1){
            }
        }
        System.out.println();
        System.out.println("StationID转换错误："+"|" + value +"|");
        return -1;
    }
}
