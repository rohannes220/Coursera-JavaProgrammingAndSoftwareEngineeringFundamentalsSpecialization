import java.util.*;

public class MarkovWordTwo implements IMarkovModel {
    private String[] myText;
    private Random myRandom;

    public MarkovWordTwo() {
        myRandom = new Random();
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String text) {
        myText = text.split("\\s+");
    }

    private int indexOf(String[] words, String target1, String target2, int start) {
        int arrayLength = words.length;
        for (int i = start; i < arrayLength - 1; i++) {
            String currentWord1 = words[i];
            String currentWord2 = words[i + 1];
            if (currentWord1.equals(target1) && currentWord2.equals(target2)) {
                return i;
            }
        }
        return -1;
    }

    public void testIndexOf() {
        String[] test = "this is just a test yes this is a simple test".split("\\s+");
        int indexOfThis0 = indexOf(test, "this", "is", 0);
        System.out.println("For this is 0 (answer 0) " + indexOfThis0);
        int indexOfThis3 = indexOf(test, "this", "is", 3);
        System.out.println("For this 3 (answer 6)" + indexOfThis3);
        int indexOfFrog0 = indexOf(test, "frog", "leg", 0);
        System.out.println("For frog 0 (answer -1)" + indexOfFrog0);
        int indexOfFrog3 = indexOf(test, "frog", "you", 5);
        System.out.println("For frog 5 -1" + indexOfFrog3);
        int indexOfSimple2 = indexOf(test, "simple", "this", 2);
        System.out.println("For simple 2 (-1" + indexOfSimple2);
    }

    public String getRandomText(int numWords) {
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length - 2); // random word to start with
        String key1 = myText[index];
        String key2 = myText[index + 1];
        sb.append(key1);
        sb.append(" ");
        sb.append(key2);
        sb.append(" ");
        for (int k = 0; k < numWords - 2; k++) {
            ArrayList<String> follows = getFollows(key1, key2);
            // System.out.println("Key: " + key);
            // System.out.println(follows);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key1 = key2;
            key2 = next;
        }
        return sb.toString().trim();
    }

    private ArrayList<String> getFollows(String key1, String key2) {
        ArrayList<String> follows = new ArrayList<String>();
        int start = 0;
        while (start < myText.length - 1) {
            int index = indexOf(myText, key1, key2, start);
            if (index == myText.length - 2 || index == -1) {
                break;
            }
            String nextWord = myText[index + 2];
            follows.add(nextWord);
            start = index + 1;
        }
        return follows;
    }

}
