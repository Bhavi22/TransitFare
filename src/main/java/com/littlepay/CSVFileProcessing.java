package com.littlepay;

import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.opencsv.exceptions.CsvValidationException;

import javax.annotation.processing.FilerException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


public class CSVFileProcessing {

    /* method to read taps.csv */
    public List<Tap> readFile(String tapFile) throws IOException, CsvValidationException {

        try {
            Reader reader = Files.newBufferedReader(Paths.get(tapFile));

            List<Tap> tapsList = new CsvToBeanBuilder(reader)
                    .withType(Tap.class)
                    .withSeparator(',')
                    .withIgnoreLeadingWhiteSpace(true)
                    .withIgnoreEmptyLine(true).withSkipLines(1).build().parse();

            return tapsList;
        }
        catch (IOException e){
            System.out.println(e);
            return null;
        }

    }

/*
 * Method to write Trips objects into trips.csv file
 */
    public void writeFile(List<Trip> trips, String tripFile) throws IOException {

        Path filePath = Paths.get(tripFile);

        try (Writer writer = Files.newBufferedWriter(filePath, StandardCharsets.UTF_8)) {
            writer.append("Started, Finished, DurationSecs, FromStopId, ToStopId, ChargeAmount, CompanyId, BusID, PAN, Status");
            writer.append("\n");
            StatefulBeanToCsv<Trip> beanToCsv = new StatefulBeanToCsvBuilder<Trip>(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .build();

            beanToCsv.write(trips);

        } catch (CsvRequiredFieldEmptyException e) {
            throw new RuntimeException(e);
        } catch (CsvDataTypeMismatchException e) {
            throw new RuntimeException(e);
        }
    }
}
