import edu.duke.*;
import java.util.*;
import java.io.*;

public class WordsInFiles {
    private HashMap<String, ArrayList<String>> wordCounts;

    public WordsInFiles() {
        wordCounts = new HashMap<String, ArrayList<String>>();
    }

    private void addWordsFromFile(File f) {
        FileResource fr = new FileResource(f);
        String name = f.getName();
        for (String word : fr.words()) {
            // if the word exists
            if (wordCounts.containsKey(word)) {
                // since the word exists, there is no new arraylist object to create
                // the arraylist object is then store in the variable cwf
                // cwf holds the current arraylist object in the hashmap and to that we add the
                // file name
                ArrayList<String> currentWordFiles = wordCounts.get(word);

                // check to see if the file name already exists in the array list
                // if it doesnt then add the file in the cwf name otherwise continue;
                if (!currentWordFiles.contains(name)) {
                    currentWordFiles.add(name);
                }

            } else {
                // the word does not exist
                // we have to create a new arrayList object because it does not exist in the map
                // to that arrayList object we add the file name
                // now we add the arraylist to the hashamp since the arraylist is the value
                ArrayList<String> unknownWordFiles = new ArrayList<String>();
                unknownWordFiles.add(name);
                wordCounts.put(word, unknownWordFiles);
            }
        }
    }

    private void buildWordFileMap() {
        wordCounts.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {

            addWordsFromFile(f);
        }
    }

    public int maxNumber() {
        int highestValue = 0;
        for (String key : wordCounts.keySet()) {
            ArrayList<String> keyFiles = wordCounts.get(key);
            int numFiles = keyFiles.size();
            if (numFiles > highestValue) {
                highestValue = numFiles;
            }
        }
        return highestValue;
    }

    public void tester() {
        buildWordFileMap();
        int maxFiles = maxNumber();
        System.out.println("The maximum number of files are: " + maxFiles);
        ArrayList<String> maxWords = wordsInNumFiles(maxFiles);
        for (String word : maxWords) {
            System.out.println("word: " + word);
            printFilesIn(word);
            System.out.println();
        }
    }

    public void tester2() {
        buildWordFileMap();
        // System.out.println(wordCounts);
        /*
         * System.out.println(wordsInNumFiles(1));
         * System.out.println(wordsInNumFiles(2));
         * System.out.println(wordsInNumFiles(3));
         */
        System.out.println();
        System.out.println("4 files");
        System.out.println(wordsInNumFiles(4).size());
        System.out.println();
        System.out.println("7 files");
        System.out.println(wordsInNumFiles(7).size());
        printFilesIn("tree");
        System.out.println(" spa ce");
        printFilesIn("laid");
    }

    public ArrayList<String> wordsInNumFiles(int number) {
        ArrayList<String> answer = new ArrayList<String>();
        for (String key : wordCounts.keySet()) {
            ArrayList<String> keyFiles = wordCounts.get(key);
            int numFiles = keyFiles.size();
            if (numFiles == number) {
                answer.add(key);
            }
        }
        return answer;
    }

    public void printFilesIn(String word) {
        ArrayList<String> filesList = wordCounts.get(word);
        for (String fileName : filesList) {
            System.out.println(fileName);
        }
    }
}
