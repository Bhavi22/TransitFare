package com.littlepay;

import java.time.Duration;
import java.util.List;
/*
 * CompleteTrip class extends the abstract class Trips and implements TripCost
 * The constructor of CompleteTrip is used to define the trip's values
 * The protected method calculateCost is used to evaluate each trip's fare
 * based on Stop Ids tapped on at and tapped off at
 */
public class CompleteTrip extends Trip {


    public CompleteTrip(Tap tapOn,Tap tapOff) {
         {
            started = tapOn.getDateUtc();
            finished = tapOff.getDateUtc();
            durationSecs = String.valueOf(Duration.between(tapOn.getDateUtc(), tapOff.getDateUtc()).getSeconds());
            fromStopId = tapOn.getStopId();
            toStopId = tapOff.getStopId();
            companyId = tapOn.getCompanyId();
            busId = tapOn.getBusId();
            pan = tapOn.getPan();
            status = "Completed";
        }
    }

    @Override
    public double calculateCost(String fromStopId,String toStopId) {
        if (fromStopId.equalsIgnoreCase("Stop1") || toStopId.equalsIgnoreCase("Stop1")) {
            if (fromStopId.equalsIgnoreCase("Stop2") || toStopId.equalsIgnoreCase("Stop2")) {
                chargeAmount=FARE[0];
            } else if (fromStopId.equalsIgnoreCase("Stop3") || toStopId.equalsIgnoreCase("Stop3")) {
                chargeAmount=FARE[2];
            }
        } else if (fromStopId.equalsIgnoreCase("Stop2") || toStopId.equalsIgnoreCase("Stop2")) {
            if (fromStopId.equalsIgnoreCase("Stop3") || toStopId.equalsIgnoreCase("Stop3")) {
                chargeAmount=FARE[1];
            }
        }
        return chargeAmount;
    }
}
