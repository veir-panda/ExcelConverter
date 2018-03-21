package me.veir.excelconverter.consumer;

import io.github.biezhi.excel.plus.ExcelPlus;
import io.github.biezhi.excel.plus.exception.ExcelException;
import me.veir.excelconverter.Util.HibernateUtil;
import me.veir.excelconverter.converter.LongitudexConverter;
import me.veir.excelconverter.converter.StationIDConverter;
import me.veir.excelconverter.model.Station;
import me.veir.excelconverter.model.T_Station;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class StationConsumer {

    private static final Integer BATCH_MAX_ROW = 50;

    public static void main(String[] args) throws ExcelException {
        List<T_Station> t_stations = select("from T_Station");
        List<Station> stations = tStationsConverter(t_stations);
//        stations.forEach(System.out::println);
        saveWithBatch(stations);
    }

    public static List<Station> tStationsConverter (List t_stations){
        List<Station> stations = (List<Station>) t_stations.stream().map(
                x -> converter((T_Station) x)
        ).collect(Collectors.toList());
        return stations;
    }

    public static Station converter(T_Station t_station){
        Station station = new Station();
        station.setStation(new StationIDConverter().read(t_station.getStationid()));
        station.setLongitudex(new LongitudexConverter().read(t_station.getLongidudex()));
        station.setLatitudey(new LongitudexConverter().read(t_station.getLatitudey()));
        return station;
    }

    public static List select (String hql){
        Session session = HibernateUtil.getSession();
        List data = session.createQuery(hql).list();
        HibernateUtil.closeSession();
        return data;
    }

    public static void excelConverter() throws ExcelException {
        ExcelPlus excelPlus = new ExcelPlus();
        List list = excelPlus.read(new File("src/main/resources/data.xlsx"),
                Station.class)
                .startRow(2)
                .filter(station ->
                        station.getStation() != null
                                && station.getStation() != -1
                                && String.valueOf(station.getLongitudex()) != "-1.0"
                                && String.valueOf(station.getLatitudey()) != "-1.0"
                ).asList();
        /*List<Station> list = excelPlus.read(new File("src/main/resources/data.xlsx"), Station.class)
                .startRow(2)
                .asList();*/
        /*list.forEach(x -> System.out.println(x));
        System.out.println(list.size());*/
        System.out.println("新增了 "+saveWithBatch(list) + "条数据");
    }

    public static long saveWithBatch(List datalist){
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        for (int i = 0; i <datalist.size(); i++) {
            try {
                session.saveOrUpdate(datalist.get(i));
            }catch (Exception e){
                System.out.println("----------------");
                System.out.println(datalist.get(i));
                System.out.println("----------------");
            }
            if (i % BATCH_MAX_ROW == 0){
                session.flush();
                session.clear();
            }
        }
        session.flush();
        session.clear();
        tx.commit();
        return datalist.size();
    }
}
