import java.util.*;

public class MarkovWordOne implements IMarkovModel {
    private String[] myText;
    private Random myRandom;

    public MarkovWordOne() {
        myRandom = new Random();
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String text) {
        myText = text.split("\\s+");
    }

    private int indexOf(String[] words, String target, int start) {
        int arrayLength = words.length;
        for (int i = start; i < arrayLength; i++) {
            String currentWord = words[i];
            if (currentWord.equals(target)) {
                return i;
            }
        }
        return -1;
    }

    public void testIndexOf() {
        String[] test = "this is just a test yes this is a simple test".split("\\s+");
        int indexOfThis0 = indexOf(test, "this", 0);
        System.out.println("For this 0 " + indexOfThis0);
        int indexOfThis3 = indexOf(test, "this", 3);
        System.out.println("For this 3 " + indexOfThis3);
        int indexOfFrog0 = indexOf(test, "frog", 0);
        System.out.println("For frog 0 " + indexOfFrog0);
        int indexOfFrog3 = indexOf(test, "frog", 5);
        System.out.println("For frog 5 " + indexOfFrog3);
        int indexOfSimple2 = indexOf(test, "simple", 2);
        System.out.println("For simple 2 " + indexOfSimple2);
        int indexOfTest5 = indexOf(test, "test", 5);
        System.out.println("For test 5 " + indexOfTest5);
    }

    public String getRandomText(int numWords) {
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length - 1); // random word to start with
        String key = myText[index];
        sb.append(key);
        sb.append(" ");
        for (int k = 0; k < numWords - 1; k++) {
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
            key = next;
        }
        return sb.toString().trim();
    }

    private ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<String>();
        int start = 0;
        while (start < myText.length) {
            int index = indexOf(myText, key, start);
            if (index == myText.length - 1 || index == -1) {
                break;
            }
            String nextWord = myText[index + 1];
            follows.add(nextWord);
            start = index + 1;
        }
        return follows;
    }

}
