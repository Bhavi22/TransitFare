package com.littlepay;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
/*
 * CancelledTrip class extends the abstract class Trips and implements TripCost
 * The constructor of CompleteTrip is used to define the trip's values
 * The protected method calculateCost returns cancelled fare of 0.00
 *  as cancelled trips have no charge currently
 */
public class CancelledTrip extends Trip  {
    public CancelledTrip(Tap tapOn, Tap tapOff) {
            started = tapOn.getDateUtc();
            finished = tapOff.getDateUtc();
            durationSecs = String.valueOf(Duration.between(tapOn.getDateUtc(), tapOff.getDateUtc()).getSeconds());
            fromStopId = tapOn.getStopId();
            toStopId = tapOff.getStopId();
            companyId = tapOn.getCompanyId();
            busId = tapOn.getBusId();
            pan = tapOn.getPan();
            status = "Cancelled";
        }

    public double calculateCost(String fromStopId, String toStopId) {
        chargeAmount=CANCELLED_FARE;
        return chargeAmount;
    }
}
