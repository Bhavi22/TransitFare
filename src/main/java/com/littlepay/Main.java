/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.littlepay;

import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.util.List;

/* @Author Bhavi Mehta
 *  Main class
 */

public class Main {
    String tapFile = "./src/main/resources/taps.csv";
    String tripFile = "./trips.csv";

    public static void main(String[] args) {
        Main runner=new Main();
        if(runner.initiateForCSVFormat())
        System.out.println("The calculated trip totals can be found in trips.csv file that has been generated");
        else
        System.out.println("Please correct file related error");
    }
/* method to process csv format files
 * return type boolean is used to identify if the input file was erroneous or if the file was empty.
 */
    public boolean initiateForCSVFormat(){
        List<Tap> tapsFromFile;
        List<Trip> trips;
        try {
            CSVFileProcessing csvFileProcessing = new CSVFileProcessing();
            tapsFromFile = csvFileProcessing.readFile(tapFile);
            //Process taps from file into calculated trips
            if(tapsFromFile!=null) {
                ProcessTap processTap = new ProcessTap(tapsFromFile);
                trips = processTap.process();
                //Write finalized trips to csv file
                csvFileProcessing.writeFile(trips,tripFile);
                return true;
            }
            else {
                return false;
            }
        }
        catch(IOException e){
            System.out.println(e);
            return false;
        }
        catch (CsvValidationException ce){
            System.out.println(ce);
            return false;
        }

    }

}