package com.littlepay;


import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;

import java.time.LocalDateTime;

public class Tap {
    @CsvBindByPosition(position = 0)
    int id;
    @CsvBindByPosition(position = 1)
    @CsvDate(value = "dd-MM-yyyy HH:mm")
    private LocalDateTime dateUtc;
    @CsvBindByPosition(position = 2)
    private String tapType;
    @CsvBindByPosition(position = 3)
    private String stopId;
    @CsvBindByPosition(position = 4)
    private String companyId;
    @CsvBindByPosition(position = 5)
    private String busId;
    @CsvBindByPosition(position = 6)
    private String pan;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDateUtc() {
        return dateUtc;
    }

    public void setDateUtc(LocalDateTime dateUtc) {

        this.dateUtc = dateUtc;
    }

    public String getTapType() {
        return tapType;
    }

    public void setTapType(String tapType) {
        this.tapType = tapType;
    }

    public String getStopId() {
        return stopId;
    }

    public void setStopId(String stopId) {
        this.stopId = stopId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId = busId;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

}
