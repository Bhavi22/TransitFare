package com.littlepay;

import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.util.*;

public class ProcessTap {
    public final List<Tap> tapsList;
    protected List<Trip> finalTrips = new ArrayList<>();
    protected List<Tap> tapOnList = new ArrayList<>();
    protected List<Tap> tapOffList = new ArrayList<>();

    public ProcessTap(List<Tap> tapsList) {
        this.tapsList = tapsList;
    }

    public List<Tap> getTapOnList() {
        return tapOnList;
    }

    public void setTapOnList(List<Tap> tapOnList) {
        this.tapOnList = tapOnList;
    }
    public void setTapOffList(List<Tap> tapOffList) {
        this.tapOffList = tapOffList;
    }
    public List<Tap> getTapOffList() {
        return tapOffList;
    }

    public List<Trip> process() throws CsvValidationException, IOException {
        Collections.sort(tapsList, Comparator.comparing(Tap::getDateUtc));
        processTapType();
        processTrip(getTapOnList(), tapOffList);
        Collections.sort(finalTrips, Comparator.comparing(Trip::getStarted));
        return finalTrips;
    }

    /*
        Method which segregates the taps obtained from the taps.csv
        into Tap Ons and Tap Offs and sorts the lists based on the date of the tap
    */
    public void processTapType() {
        List<Tap> tapOn = new ArrayList<Tap>();
        List<Tap> tapOff = new ArrayList<Tap>();
        for (Tap tap : tapsList) {
            if (tap.getTapType().equalsIgnoreCase("ON")) {
                tapOn.add(tap);
            } else if (tap.getTapType().equalsIgnoreCase("OFF")) {
                tapOff.add(tap);
            }
        }
        setTapOnList(tapOn);
        setTapOffList(tapOff);
        Collections.sort(getTapOnList(), Comparator.comparing(Tap::getDateUtc));
        Collections.sort(tapOffList, Comparator.comparing(Tap::getDateUtc));
    }


    /*
        Method to process the tap ons against the tap offs sequentially
        and process complete,cancelled and incomplete trips
     */
    public void processTrip(List<Tap> tapOnList, List<Tap> tapOffList) {
        /*
         * Set roundTrips is used to store all tap Ids
         * where a tap On matches a tap Off record
         */
        Set<Integer> roundTrips = new HashSet<Integer>();
        // Nested loop to find a matching tap Off record for every tap On
        for (int i = 0; i < tapOnList.size(); i++) {
            for (int j = 0; j < tapOffList.size(); j++) {
                /*
                 *  The condition to check if a tapOff has already been added to a round trip for a given PAN
                 *  if not, further evaluate if the trip was cancelled or completed
                 */
                if (tapOnList.get(i).getPan().equals(tapOffList.get(j).getPan()) && !roundTrips.contains(tapOffList.get(j).getId())) {
                    /*If the tap On and tap Off have a different Stop id, then mark it as completed Trip
                     * and evaluate trip cost
                     */
                    if (!(tapOnList.get(i).getStopId().equalsIgnoreCase(tapOffList.get(j).getStopId()))) {
                        CompleteTrip completeTrip = new CompleteTrip(tapOnList.get(i), tapOffList.get(j));
                        completeTrip.calculateCost(tapOnList.get(i).getStopId(), tapOffList.get(j).getStopId());
                        finalTrips.add(completeTrip);
                    }
                    /*If the tap On and tap Off have the same Stop id, then mark it as cancelled Trip
                     * and evaluate trip cost.
                     */
                    else {
                        CancelledTrip cancelledTrip = new CancelledTrip(tapOnList.get(i), tapOffList.get(j));
                        cancelledTrip.calculateCost(tapOnList.get(i).getStopId(), tapOffList.get(j).getStopId());
                        finalTrips.add(cancelledTrip);
                    }
                    /*If a successful match for tapOn record was found against a tap Off,
                     * add the tap ids to roundTrips Set
                     */
                    roundTrips.add(tapOnList.get(i).getId());
                    roundTrips.add(tapOffList.get(j).getId());
                    i++;
                }
            }
        }
        /*
         * Loop through the list of Tap Ons and validate if they've been added
         * under a round trip. If not, mark it as an incomplete trip and evaluate trip cost.
         */
        for (int n = 0; n < tapOnList.size(); n++) {
            if (!(roundTrips.contains(tapOnList.get(n).getId()))) {
                IncompleteTrip incompleteTrip = new IncompleteTrip(tapOnList.get(n));
                incompleteTrip.calculateCost(tapOnList.get(n).getStopId(), tapOnList.get(n).getStopId());
                finalTrips.add(incompleteTrip);
            }
        }
    }
}
