package me.veir.excelconverter.converter;

import io.github.biezhi.excel.plus.converter.Converter;
import me.veir.excelconverter.Util.ConverterUtil;

public class LongitudexConverter implements Converter<Double> {
    @Override
    public String write(Double aDouble) {
        return null;
    }

    @Override
    public Double read(String value) {
        value = value.trim();
        try {
            return ConverterUtil.getValue(value);
        }catch (Exception e){
            return -1.0;
        }
    }
}
