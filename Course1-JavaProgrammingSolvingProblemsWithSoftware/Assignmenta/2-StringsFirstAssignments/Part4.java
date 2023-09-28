import edu.duke.URLResource; 

/**
 * Write a description of class Part4 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Part4
{
    // instance variables - replace the example below with your own
    void printYtLinks(){
        URLResource resource = new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
        for(String w : resource.words()){
            String newString = w.toLowerCase();  
            String y = "youtube.com";
            int ytIndex = newString.indexOf(y); 
            if(ytIndex == -1){
                continue; 
            }
            String quote = "\""; 
            int startQuoteIndex = w.lastIndexOf(quote,ytIndex);
            int endQuoteIndex = w.indexOf(quote,ytIndex);
            System.out.println(w.substring(startQuoteIndex+1,endQuoteIndex)); 
        }
    }
}