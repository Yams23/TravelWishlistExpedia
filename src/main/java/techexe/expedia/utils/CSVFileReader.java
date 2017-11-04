package techexe.expedia.utils;

import techexe.expedia.model.GlobalLatLngDetails;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVFileReader {

    public static List<GlobalLatLngDetails> readLatLongDetailsFrom(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        // read file line by line
        String line = null;
        Scanner scanner = null;
        int index = 0;
        List<GlobalLatLngDetails> locList = new ArrayList<>();

        while ((line = reader.readLine()) != null) {
            GlobalLatLngDetails loc = null;
            try {
                loc = new GlobalLatLngDetails();
                scanner = new Scanner(line);
                scanner.useDelimiter(",");
                while (scanner.hasNext()) {
                    String data = scanner.next();
                    System.out.print(data);
                    if (index == 0)
                        loc.setZipCode(data);
                    else if (index == 1)
                        loc.setLatitude(Double.parseDouble(data));
                    else if (index == 2)
                        loc.setLongitude(Double.parseDouble(data));
                    else if (index == 3)
                        loc.setCity(data);
                    else if (index == 4)
                        loc.setCountry(data);
                    else
                        System.out.println("invalid data::" + data);
                    index++;
                }
                index = 0;
            } catch (NumberFormatException e) {
                break;
            }
            locList.add(loc);



        }

        //close reader
        reader.close();
        return locList;
    }
}
