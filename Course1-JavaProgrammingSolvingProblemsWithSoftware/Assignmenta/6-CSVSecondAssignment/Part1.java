
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*; 
public class Part1
{
    // instance variables - replace the example below with your own
    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord coldestTempRecord = null; 
        for(CSVRecord record:parser){
            // If current row's tem is -9999, we can skip it
            double currentTemp = 
                    Double.parseDouble(record.get("TemperatureF"));
            if(currentTemp == -9999){
                continue; 
            }
            
            // At this point current row's temp is not -9999
            // so we can continue
            if(coldestTempRecord == null){
                coldestTempRecord = record;
            }
            else{ 
                double coldestTemp = 
                    Double.parseDouble(coldestTempRecord.get("TemperatureF")); 
                if(currentTemp < coldestTemp){
                    coldestTempRecord = record; 
                }
            }
        }
        return coldestTempRecord; 
    }
    
    public void testColdestHourInFile(){
       FileResource fr = new FileResource();  
       CSVParser parser = fr.getCSVParser(); 
       CSVRecord answer = coldestHourInFile(parser); 
       System.out.println("Coldest Temperature: " + answer.get("TemperatureF"));  
       System.out.println("At: " + answer.get("TimeEDT")); 
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord lowestHumidityRecord = null;
        for(CSVRecord record : parser){
            String currentHumidityString = record.get("Humidity"); 
            if(currentHumidityString.equals("N/A")){
                continue; 
            }
            if(lowestHumidityRecord == null){
                lowestHumidityRecord = record;
            }
            else{
                double currentHumidity = Double.parseDouble(currentHumidityString); 
                double lowestHumidity = Double.parseDouble(lowestHumidityRecord.get("Humidity")); 
                if(currentHumidity < lowestHumidity){
                    lowestHumidityRecord = record; 
                }
                
            }
        }
        return lowestHumidityRecord; 
    }
    
    public void testLowestHumidityInFile(){
    FileResource fr = new FileResource();
    CSVParser parser = fr.getCSVParser();
    CSVRecord record = lowestHumidityInFile(parser);
    System.out.println("Lowest Humidity was " + record.get("Humidity") + " at " + record.get("DateUTC")); 
    }
    
    public CSVRecord lowestHumidityInFiles(){
        DirectoryResource dr = new DirectoryResource();
        CSVRecord lowestHumidityAcrossFiles = null; 
        for(File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f); 
            CSVParser parser = fr.getCSVParser(); 
            CSVRecord currentLowestHumidityRecord = lowestHumidityInFile(parser); 
            if(lowestHumidityAcrossFiles == null){
                lowestHumidityAcrossFiles = currentLowestHumidityRecord; 
            }
            else{
                double currentHumidity = Double.parseDouble(currentLowestHumidityRecord.get("Humidity")); 
                double lowestHumidity = Double.parseDouble(lowestHumidityAcrossFiles.get("Humidity")); 
                if(currentHumidity < lowestHumidity){
                    lowestHumidityAcrossFiles = currentLowestHumidityRecord; 
                }
            }
        }
        return lowestHumidityAcrossFiles; 
    }
    
    public void testLowestHumidityAcrossFiles(){
        CSVRecord answer = lowestHumidityInFiles();
        System.out.println("The lowest humidity is " + answer.get("Humidity") + " at " + answer.get("DateUTC")); 
    }
    
    public String fileWithColdestTemperature(){
        DirectoryResource dr  =  new DirectoryResource();
        File lowestTempFile = null;
        double lowestTemp = 0; 
        for(File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f); 
            CSVParser parser = fr.getCSVParser();
            CSVRecord currFileLowestTempRecord = coldestHourInFile(parser);
            double currTemp = Double.parseDouble(currFileLowestTempRecord.get("TemperatureF"));
            if (lowestTempFile == null) {
                lowestTempFile = f;
                lowestTemp = currTemp;
            }
            else {
                if (currTemp < lowestTemp) {
                    lowestTempFile = f;
                    lowestTemp = currTemp;
                }
            }
        }
        String name = lowestTempFile.getName(); 
        return name; 
    }
    
    public void testFileWithColdestTemperature(){
        String answer = fileWithColdestTemperature(); 
        System.out.println("Coldest day was in file " +answer); 
        FileResource fr = new FileResource("nc_weather/2013/" + answer); 
        CSVParser parser = fr.getCSVParser();
        CSVRecord temp = coldestHourInFile(parser); 
        System.out.println("Coldest temperature on that day was " + temp.get("TemperatureF")); 
        System.out.println("All the Temperatures on the coldest day were: "); 
        parser = fr.getCSVParser();
        for(CSVRecord record: parser){
            System.out.println(record.get("DateUTC") + ": " + record.get("TemperatureF")); 
        }
    }
    
    public double averageTemperatureInFile(CSVParser parser){
        double sum = 0.0;
        int length = 0;
        for(CSVRecord record:parser){
            double currTemp = Double.parseDouble(record.get("TemperatureF"));
            sum = sum + currTemp;
            length = length + 1; 
        }
        double average = sum / length; 
        return average; 
    }
    
    public double averageHumidityInFile(CSVParser parser){
        double sum = 0.0;
        int length = 0;
        for(CSVRecord record:parser){
            double currHum = Double.parseDouble(record.get("Humidity"));
            sum = sum + currHum;
            length = length + 1; 
        }
        double average = sum / length; 
        return average; 
    }
    
    public void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser(); 
        double answer = averageTemperatureInFile(parser); 
        System.out.println("Average temperature in file is " + answer); 
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser,int value){
        double newValue = value * 1.0;
        double length = 0.0;
        double sum = 0.0; 
        for(CSVRecord record: parser){
            double currentHumidity =  Double.parseDouble(record.get("Humidity"));
            double currentTemperature = Double.parseDouble(record.get("TemperatureF")); 
            if(currentHumidity >= newValue){
                length += 1; 
                sum += currentTemperature; 
            }
        }
        if(sum == 0.0){
            return sum; 
        }
        double average = sum / length; 
        return average; 
    }
    
    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        int value = 80; 
        double answer = averageTemperatureWithHighHumidityInFile(parser, value);
        if(answer == 0.0){
            System.out.print("No temperatures with that humidity"); 
        }
        else{
            System.out.print("Average Temp when high Humidity is " + answer);
        }
    }
    
public void printAveragesForFile(){
    DirectoryResource dr = new DirectoryResource();
    double totalTempAcrossFiles = 0;
    double totalHumAcrossFiles = 0;
    int totalTempEntries = 0;
    int totalHumEntries = 0; 
    for(File f: dr.selectedFiles()){
        double currFileTempSum = 0;
        double currFileHumSum = 0; 
        int currTempEntries = 0;
        int currHumEntries = 0;
        FileResource fr = new FileResource(f);
        CSVParser parser = fr.getCSVParser();  
        for(CSVRecord record:parser){
            double currentTemp = Double.parseDouble(record.get("TemperatureF")); 
            if(currentTemp != -9999){
                currFileTempSum += currentTemp;
                currTempEntries += 1; 
                totalTempAcrossFiles += currentTemp; 
                totalTempEntries += 1;
            }
            String currentHumidityString = record.get("Humidity");
            if(!currentHumidityString.equals("N/A")){
                double currentHum =  Double.parseDouble(currentHumidityString);
                currFileHumSum += currentHum; 
                currHumEntries += 1;
                totalHumAcrossFiles += currentHum; 
                totalHumEntries += 1;   
            }
            
        }
        double currFileTempAve = currFileTempSum / currTempEntries;
        double currFileHumAve = currFileHumSum / currHumEntries;
        String fileName = f.getName(); 
        System.out.println("File: " + fileName + ", Average temperature: " + currFileTempAve  + ", Average humidity: " + currFileHumAve); 
    }
    double entireTempAve = totalTempAcrossFiles / totalTempEntries; 
    double entireHumAve = totalHumAcrossFiles / totalHumEntries;
    System.out.println(); 
    System.out.println("Summary: Average temperature: " + entireTempAve + ", Average humidity: " + entireHumAve); 
}
}
