package me.veir.excelconverter.model;

import io.github.biezhi.excel.plus.annotation.ExcelField;
import io.github.biezhi.excel.plus.annotation.ExcelSheet;
import lombok.Data;
import me.veir.excelconverter.converter.LongitudexConverter;
import me.veir.excelconverter.converter.StationIDConverter;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Entity
@Table(name = "china_station_4w", uniqueConstraints = {@UniqueConstraint(columnNames = "stationid")})

//@ExcelSheet("北京")
//@ExcelSheet("天津")
//@ExcelSheet("河北")

//@ExcelSheet("内蒙古")
//@ExcelSheet("辽宁")
//@ExcelSheet("吉林")
//@ExcelSheet("黑龙江")

//@ExcelSheet("江苏")
//@ExcelSheet("浙江")
//@ExcelSheet("安徽")
//@ExcelSheet("福建")
//@ExcelSheet("江西")
//@ExcelSheet("山东")
//@ExcelSheet("河南")
//@ExcelSheet("湖北")
//@ExcelSheet("湖南")
//@ExcelSheet("广东")
//@ExcelSheet("广西")
//@ExcelSheet("湖南")
//@ExcelSheet("重庆")
//@ExcelSheet("四川")
//@ExcelSheet("贵州")
//@ExcelSheet("云南")
//@ExcelSheet("西藏")
//@ExcelSheet("陕西")
//@ExcelSheet("甘肃")
//@ExcelSheet("青海")
//@ExcelSheet("宁夏")
//@ExcelSheet("新疆")
public class Station implements Serializable{
    @Id
    @Column(name = "stationid")
    @ExcelField(order = 5, columnName = "区站号", convertType = StationIDConverter.class)
    private Integer station;

    @Column(name = "longitudex")
    @ExcelField(order = 8, columnName = "经度(度分秒)", convertType = LongitudexConverter.class)
    private Double longitudex;

    @Column(name = "latitudey")
    @ExcelField(order = 9, columnName = "纬度（度分秒）", convertType = LongitudexConverter.class)
    private Double latitudey;

    //山西
    //上海
}
