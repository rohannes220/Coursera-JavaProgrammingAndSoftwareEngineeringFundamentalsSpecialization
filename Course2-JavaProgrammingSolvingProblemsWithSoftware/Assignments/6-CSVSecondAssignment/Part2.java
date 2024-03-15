import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class Part2 {
    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord coldestTempRecord = null;
        for (CSVRecord record : parser) {
            // If current row's tem is -9999, we can skip it
            double currentTemp = Double.parseDouble(record.get("TemperatureF"));
            if (currentTemp == -9999) {
                continue;
            }

            // At this point current row's temp is not -9999
            // so we can continue
            if (coldestTempRecord == null) {
                coldestTempRecord = record;
            } else {
                double coldestTemp = Double.parseDouble(coldestTempRecord.get("TemperatureF"));
                if (currentTemp < coldestTemp) {
                    coldestTempRecord = record;
                }
            }
        }
        return coldestTempRecord;
    }

    public String fileWithColdestTemperature() {
        DirectoryResource dr = new DirectoryResource();
        String lowestTemperatureFileName = null;
        double lowestTemperatureSoFar = 0;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord currentTemperatureRecord = coldestHourInFile(parser);
            double currentTemperature = Double.parseDouble(currentTemperatureRecord.get("TemperatureF"));
            if (lowestTemperatureFileName == null) {
                lowestTemperatureFileName = f.getName();
                lowestTemperatureSoFar = currentTemperature;
            } else {
                if (currentTemperature < lowestTemperatureSoFar) {
                    lowestTemperatureFileName = f.getName();
                    lowestTemperatureSoFar = currentTemperature;
                }

            }
        }
        return lowestTemperatureFileName;
    }
}
