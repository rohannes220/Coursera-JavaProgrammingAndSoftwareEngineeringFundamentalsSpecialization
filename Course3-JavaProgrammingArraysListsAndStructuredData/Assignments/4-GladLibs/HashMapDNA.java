import java.util.*;
import edu.duke.*;

/**
 * Write a description of class HashMapDNA here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class HashMapDNA {
    // instance variables - replace the example below with your own
    private HashMap<String, Integer> codonCounts;

    public HashMapDNA() {
        codonCounts = new HashMap<String, Integer>();
    }

    public void buildCodonMap(int start, String dna) {
        codonCounts.clear();

        while (start + 3 <= dna.length()) {
            String codon = dna.substring(start, start + 3);
            if (codonCounts.containsKey(codon)) {
                codonCounts.put(codon, codonCounts.get(codon) + 1);
            } else {
                codonCounts.put(codon, 1);
            }
            start = start + 3;
        }
    }

    public void testBuildCodonMap() {
        String dna = "CGTTCAAGTTCAA";
        buildCodonMap(1, dna);
        System.out.println(codonCounts);
        String common = getMostCommonCodon();
        System.out.println(common);
        printCodonCounts(2, 2);
    }

    public String getMostCommonCodon() {
        int highestVal = 0;
        String highestCodon = "";
        for (String codon : codonCounts.keySet()) {
            int value = codonCounts.get(codon);
            if (value > highestVal) {
                highestVal = value;
                highestCodon = codon;
            }
        }
        return highestCodon;
    }

    public void printCodonCounts(int start, int end) {
        for (String codon : codonCounts.keySet()) {
            int value = codonCounts.get(codon);
            if (value >= start && value <= end) {
                System.out.println(codon + ":" + value);
            }
        }
    }

    // CGTTCAAGTTCAA
    public void tester() {
        FileResource fr = new FileResource();
        String dna = fr.asString();
        dna = dna.toUpperCase().trim();
        for (int i = 0; i < 3; i++) {
            System.out.println("Frame: " + i);
            buildCodonMap(i, dna);
            System.out.println("Total unique codon: " + codonCounts.size());
            String mostCommonCodon = getMostCommonCodon();
            System.out.println("The most common codon is:" + mostCommonCodon + ":" + codonCounts.get(mostCommonCodon));
            printCodonCounts(7, 7);
            // System.out.println(codonCounts);
            System.out.println();
        }

    }
}
