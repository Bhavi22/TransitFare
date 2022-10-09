package com.littlepay;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;
import com.opencsv.bean.CsvNumber;

import java.time.LocalDateTime;
/*
 * Trips is an abstract class that is extended by
 * CompleteTrip,CancelledTrip and IncompleteTrip classes.
 * It can be extended to other trip statuses as required
 */
public abstract class Trip {
    public static final double[] FARE={3.25,5.50,7.30};
    public static final double CANCELLED_FARE=0.00;
    @CsvBindByPosition(position = 0)
    @CsvDate(value = "dd-MM-yyyy HH:mm")
    protected LocalDateTime finished;
    @CsvBindByPosition(position = 1)
    @CsvDate(value = "dd-MM-yyyy HH:mm")
    protected LocalDateTime started;
    @CsvBindByPosition(position = 2)
    protected String durationSecs;
    @CsvBindByPosition(position = 3)
    protected String fromStopId;
    @CsvBindByPosition(position = 4)
    protected String toStopId;
    @CsvNumber("$0.00")
    @CsvBindByPosition(position = 5)
    protected double chargeAmount;
    @CsvBindByPosition(position = 6)
    protected String companyId;
    @CsvBindByPosition(position = 7)
    protected String busId;
    @CsvBindByPosition(position = 8)
    protected String pan;
    @CsvBindByPosition(position = 9)
    protected String status;

    public LocalDateTime getFinished() {
        return finished;
    }

    public LocalDateTime getStarted() {
        return started;
    }
    public abstract double calculateCost(String fromStopId,String toStopId);
}