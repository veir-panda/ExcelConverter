package me.veir.excelconverter.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "t_stations")
public class T_Station implements Serializable{

    @Id
    @Column(name = "stationid")
    private String stationid;

    @Column(name = "longitudex")
    private String longidudex;

    @Column(name = "latitudey")
    private String latitudey;


}
