
/**
 * Write a description of class Part1 here.
 *
 * @author Rohan Kumar
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*; 
public class Part1
{
    // instance variables - replace the example below with your own
    public void tester(){
        FileResource fr = new FileResource("exportdata.csv"); 
        
        // part2
        CSVParser parser = fr.getCSVParser();
        bigExporter(parser,"$999,999,999,999"); 
        // part3
        // parser = fr.getCSVParser(); // reset parser
       
    }
    public String countryInfo(CSVParser parser, String country){
        
        for(CSVRecord record: parser){
            String currentCountry = record.get("Country"); 
            if(currentCountry.equals(country)){
                String finalAnswer = currentCountry + ": " + record.get("Exports") + ": " + record.get("Value (dollars)");
                return finalAnswer; 
            }
        }
        return "NOT FOUND"; 
    } 
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
        for(CSVRecord record: parser){
            String currentExports = record.get("Exports");
            int exportItem1Index = currentExports.indexOf(exportItem1); 
            int exportItem2Index = currentExports.indexOf(exportItem2); 
            if(exportItem1Index != -1 && exportItem2Index != -1){
                String currentCountry = record.get("Country"); 
                System.out.println(currentCountry); 
            }
        }
    }
    public int numberOfExporters(CSVParser parser, String exportItem){
        int count = 0; 
        for(CSVRecord record: parser){
            String currentExports = record.get("Exports");
            int exportIndex = currentExports.indexOf(exportItem); 
            if(exportIndex != -1){
                count += 1; 
            }
        }
        return count; 
    }
    public void bigExporter(CSVParser parser, String amount){
        int length = amount.length(); 
        for(CSVRecord record:parser){
            String currentValue = record.get("Value (dollars)");
            int currValLength = currentValue.length(); 
            if(currValLength > length){
                String currentCountry = record.get("Country"); 
                System.out.println(currentCountry + " " + currentValue); 
            }
        }
    }
}
