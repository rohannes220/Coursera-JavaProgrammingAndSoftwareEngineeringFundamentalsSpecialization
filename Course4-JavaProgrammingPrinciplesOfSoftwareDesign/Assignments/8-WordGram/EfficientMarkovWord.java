import java.util.HashMap;
import java.util.Random;
import java.util.ArrayList;

public class EfficientMarkovWord implements IMarkovModel {
    private String[] myText;
    private HashMap<WordGram, ArrayList<String>> followsMap;
    private Random myRandom;
    private int myOrder;

    public EfficientMarkovWord(int order) {
        myRandom = new Random();
        myOrder = order;
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String text) {
        myText = text.split("\\s+");
        buildMap();
        printHashMapInfo();
    }

    private int indexOf(String[] words, WordGram target, int start) {
        int arrayLength = words.length;
        int wordGramLength = target.length();
        for (int i = start; i <= arrayLength - wordGramLength; i++) {
            WordGram current = new WordGram(myText, i, wordGramLength);
            if (current.equals(target)) {
                return i;
            }
        }
        return -1;
    }

    /*
     * public void testIndexOf(){
     * String[] test =
     * "this is just a test yes this is a simple test".split("\\s+");
     * int indexOfThis0 = indexOf(test,"this",0);
     * System.out.println("For this 0 " + indexOfThis0);
     * int indexOfThis3 = indexOf(test,"this",3);
     * System.out.println("For this 3 " + indexOfThis3);
     * int indexOfFrog0 = indexOf(test,"frog",0);
     * System.out.println("For frog 0 " + indexOfFrog0);
     * int indexOfFrog3 = indexOf(test,"frog",5);
     * System.out.println("For frog 5 " + indexOfFrog3);
     * int indexOfSimple2 = indexOf(test,"simple", 2);
     * System.out.println("For simple 2 " + indexOfSimple2);
     * int indexOfTest5 = indexOf(test,"test", 5);
     * System.out.println("For test 5 " + indexOfTest5);
     * }
     */
    public String getRandomText(int numWords) {
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length - myOrder); // random word to start with
        WordGram key = new WordGram(myText, index, myOrder);
        sb.append(key.toString());
        sb.append(" ");
        for (int k = 0; k < numWords - myOrder; k++) {
            ArrayList<String> follows = getFollows(key);
            // System.out.println("Key: " + key);
            // System.out.println(follows);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = key.shiftAdd(next);
        }
        return sb.toString().trim();
    }

    private ArrayList<String> getFollows(WordGram input) {
        // return the corresponding value of the key in the hash map
        if (followsMap.containsKey(input)) {
            return followsMap.get(input);
        }
        return new ArrayList<String>();
    }

    private void buildMap() {
        followsMap = new HashMap<WordGram, ArrayList<String>>();
        for (int i = 0; i <= myText.length - myOrder; i++) {
            WordGram current = new WordGram(myText, i, myOrder);

            if (!followsMap.containsKey(current)) {
                ArrayList<String> followsList = new ArrayList<String>();
                followsMap.put(current, followsList);
            }

            if (i + myOrder >= myText.length) {
                break;
            }

            String followsWord = myText[i + myOrder];
            ArrayList<String> followsList = followsMap.get(current);
            followsList.add(followsWord);
        }
    }

    private int largestValue() {
        int largestSize = 0;
        for (WordGram key : followsMap.keySet()) {
            ArrayList<String> currentValue = followsMap.get(key);
            int currentSize = currentValue.size();
            if (currentSize > largestSize) {
                largestSize = currentSize;
            }
        }
        return largestSize;
    }

    private void printKeysWithHighestValue(int largestSize) {
        for (WordGram key : followsMap.keySet()) {
            ArrayList<String> currentValue = followsMap.get(key);
            int currentSize = currentValue.size();
            if (currentSize == largestSize) {
                System.out.println(key);
            }
        }
    }

    private void printHashMapInfo() {
        System.out.println("The hash map: " + followsMap);
        System.out.println("The number of keys: " + followsMap.size());
        int largestKeySize = largestValue();
        System.out.println("The largest key size: " + largestKeySize);
        System.out.println("All the keys that have the maximum size value: ");
        printKeysWithHighestValue(largestKeySize);
    }
}
