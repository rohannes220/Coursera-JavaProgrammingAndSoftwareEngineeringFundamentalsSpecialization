import edu.duke.URLResource;

public class Part4 {
    void printYtLinks() {
        URLResource resource = new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
        for (String w : resource.words()) {
            String newString = w.toLowerCase();
            String y = "youtube.com";
            int ytIndex = newString.indexOf(y);
            if (ytIndex == -1) {
                continue;
            }
            String quote = "\"";
            int startQuoteIndex = w.lastIndexOf(quote, ytIndex);
            int endQuoteIndex = w.indexOf(quote, ytIndex);
            System.out.println(w.substring(startQuoteIndex + 1, endQuoteIndex));
        }
    }
}