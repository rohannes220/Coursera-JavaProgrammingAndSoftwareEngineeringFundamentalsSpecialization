import java.util.*;
import edu.duke.*;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;

    public WordFrequencies() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }

    public void findUnique() {
        myWords.clear();
        myFreqs.clear();
        FileResource resource = new FileResource();
        for (String s : resource.words()) {
            s = s.toLowerCase();
            int index = myWords.indexOf(s);
            if (index == -1) {
                myWords.add(s);
                myFreqs.add(1);
            } else {
                int currentValue = myFreqs.get(index);
                myFreqs.set(index, currentValue + 1);
            }
        }
    }

    public void tester() {
        findUnique();
        int index = findIndexOfMax();

        for (int i = 0; i < myWords.size(); i++) {
            System.out.println(myFreqs.get(i) + " " + myWords.get(i));
        }
        System.out.println("The word that occurs the most often and its count are: " + myWords.get(index) + " "
                + myFreqs.get(index));
        System.out.println("The number of unique words: " + myWords.size());
    }

    public int findIndexOfMax() {
        int highestValue = 0;
        int highestIndex = -1;
        for (int i = 0; i < myFreqs.size(); i++) {
            int currentValue = myFreqs.get(i);
            if (currentValue > highestValue) {
                highestIndex = i;
                highestValue = currentValue;
            }
        }
        return highestIndex;
    }
}
