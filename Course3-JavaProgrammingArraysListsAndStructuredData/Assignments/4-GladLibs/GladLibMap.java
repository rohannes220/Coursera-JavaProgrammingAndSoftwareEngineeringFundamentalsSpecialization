import edu.duke.*;
import java.util.*;

public class GladLibMap {
    private HashMap<String, ArrayList<String>> myMap;
    private ArrayList<String> usedWordsList;

    private Random myRandom;
    private ArrayList<String> usedCategories;

    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "datalong";

    public GladLibMap() {
        myMap = new HashMap<String, ArrayList<String>>();
        usedCategories = new ArrayList<String>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
        usedWordsList = new ArrayList<String>();
    }

    public GladLibMap(String source) {
        myMap = new HashMap<String, ArrayList<String>>();
        usedCategories = new ArrayList<String>();
        initializeFromSource(source);
        myRandom = new Random();
        usedWordsList = new ArrayList<String>();
    }

    private void initializeFromSource(String source) {
        String[] categories = new String[] { "adjective", "noun", "color", "country", "name", "animal", "timeframe",
                "fruit", "verb" };
        for (String label : categories) {

            ArrayList<String> currLabelList = readIt(source + "/" + label + ".txt");
            myMap.put(label, currLabelList);
        }
    }

    private String randomFrom(ArrayList<String> source) {
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }

    private String getSubstitute(String label) {
        if (label.equals("number")) {
            return "" + myRandom.nextInt(50) + 5;
        }
        if (myMap.containsKey(label)) {
            if (!usedCategories.contains(label)) {
                usedCategories.add(label);
            }
            return randomFrom(myMap.get(label));
        }
        return "**UNKNOWN**";
    }

    // <noun> - house
    // <noun> - house, tree
    private String processWord(String w) {
        // "<adjective>" => "good"
        // "first-<verb>-second" => "first-run-second"
        // "first-<verb>-third" => "first-run-third"
        // ">noun<" => ">noun<"
        // "abc>def<noun>>hello" => "abc>deftree>hello"

        int first = w.indexOf("<");
        int last = w.indexOf(">", first);
        if (first == -1 || last == -1) {
            return w;
        }
        String prefix = w.substring(0, first);
        String suffix = w.substring(last + 1);
        String label = w.substring(first + 1, last);
        String sub = getSubstitute(label);
        int index = usedWordsList.indexOf(sub);

        while (index != -1) {
            sub = getSubstitute(label);
            index = usedWordsList.indexOf(sub);
        }

        usedWordsList.add(sub);

        return prefix + sub + suffix;
    }

    private void printOut(String s, int lineWidth) {
        int charsWritten = 0;
        for (String w : s.split("\\s+")) {
            if (charsWritten + w.length() > lineWidth) {
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w + " ");
            charsWritten += w.length() + 1;
        }
    }

    private String fromTemplate(String source) {
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for (String word : resource.words()) {
                story = story + processWord(word) + " ";
            }
        } else {
            FileResource resource = new FileResource(source);
            for (String word : resource.words()) {
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }

    private ArrayList<String> readIt(String source) {
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for (String line : resource.lines()) {
                list.add(line);
            }
        } else {
            FileResource resource = new FileResource(source);
            for (String line : resource.lines()) {
                list.add(line);
            }
        }
        return list;
    }

    private int totalWordsInMap() {
        int totalWords = 0;
        for (String key : myMap.keySet()) {
            totalWords += myMap.get(key).size();
        }
        return totalWords;
    }

    private int totalWordsConsidered() {
        int totalConsidered = 0;
        for (String label : usedCategories) {
            totalConsidered += myMap.get(label).size();
        }
        return totalConsidered;
    }

    public void makeStory() {
        usedWordsList.clear();
        usedCategories.clear();
        System.out.println("\n");
        String story = fromTemplate(dataSourceDirectory + "/madtemplate2.txt");
        int length = usedWordsList.size();
        printOut(story, 60);
        System.out.println("\n\nThe total words in the map: " + totalWordsInMap());
        System.out.println("\n\nThe total words considered are: " + totalWordsConsidered());
        System.out.println("\n\nThe number of replaced words are: " + length);
    }

}
