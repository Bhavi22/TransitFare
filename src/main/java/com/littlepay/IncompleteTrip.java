package com.littlepay;

import java.time.Duration;

/*
 * IncompleteTrip class extends the abstract class Trips and implements TripCost
 * The protected method calculateCost is used to evaluate each trip's fare
 * Each incomplete trip's fare is assumed as
 * the highest amount from the tapped StopId to all routes possible
 */
public class IncompleteTrip extends Trip {

    public IncompleteTrip(Tap tap) {
            started = tap.getDateUtc();
            finished = tap.getDateUtc();
            durationSecs = String.valueOf(Duration.between(tap.getDateUtc(), tap.getDateUtc()).getSeconds());
            fromStopId = tap.getStopId();
            toStopId = tap.getStopId();
            companyId = tap.getCompanyId();
            busId = tap.getBusId();
            pan = tap.getPan();
            status = "Incomplete";
        }

        @Override
        public double calculateCost(String fromStopId,String toStopId) {
        if (fromStopId.equalsIgnoreCase("Stop1"))
            chargeAmount=FARE[2];
        else if (fromStopId.equalsIgnoreCase("Stop2"))
            chargeAmount=FARE[1];
        else if (fromStopId.equalsIgnoreCase("Stop3"))
            chargeAmount=FARE[2];

        return chargeAmount;
    }
}
