import java.util.HashMap;
import java.util.Random;
import java.util.ArrayList;

public class EfficientMarkovModel extends AbstractMarkovModel {
    private int order;
    private HashMap<String, ArrayList<String>> whatFollows;

    public EfficientMarkovModel(int n) {
        myRandom = new Random();
        order = n;
        whatFollows = new HashMap<String, ArrayList<String>>();
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    private void buildMap() {
        whatFollows.clear();
        int startIndex = 0;
        int endIndex = startIndex + order;
        while (endIndex <= myText.length()) {
            String key = myText.substring(startIndex, endIndex);
            if (!whatFollows.containsKey(key)) {
                ArrayList<String> newFollowsList = new ArrayList<String>();
                whatFollows.put(key, newFollowsList);
            }
            if (endIndex == myText.length()) {
                break;
            }
            String followCharacter = myText.substring(endIndex, endIndex + 1);
            ArrayList<String> followsList = whatFollows.get(key);
            followsList.add(followCharacter);
            whatFollows.put(key, followsList);
            /*
             * if(whatFollows.containsKey(key)){
             * ArrayList<String> followsList = whatFollows.get(key);
             * followsList.add(followCharacter);
             * whatFollows.put(key,followsList);
             * }
             * else{
             * ArrayList<String> newFollowsList = new ArrayList<String>();
             * newFollowsList.add(followCharacter);
             * whatFollows.put(key,newFollowsList);
             * }
             */
            startIndex += 1;
            endIndex += 1;
        }
        // System.out.println(whatFollows);
        printHashMapInfo();
    }

    public ArrayList<String> getFollows(String key) {
        ArrayList<String> value = whatFollows.get(key);
        return value;
    }

    public void printHashMapInfo() {
        // System.out.println(whatFollows);
        System.out.println("The number of keys: " + whatFollows.size());
        int largestLength = 0;
        int count = 0;
        for (String key : whatFollows.keySet()) {
            ArrayList<String> value = whatFollows.get(key);
            int currentLength = value.size();
            if (currentLength > largestLength) {
                largestLength = currentLength;
            }
        }
        System.out.println("The size of the largest value in the hash map is: " + largestLength);
        System.out.println("The keys with the largest values are: ");
        for (String key : whatFollows.keySet()) {
            ArrayList<String> value = whatFollows.get(key);
            int currentLength = value.size();
            if (currentLength == largestLength) {
                System.out.println(key);
            }
        }
    }

    public String getRandomText(int numChars) {
        if (myText == null) {
            return "";
        }
        buildMap();
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length() - order);
        String key = myText.substring(index, index + order);
        sb.append(key);
        for (int k = 0; k < numChars - order; k++) {
            // the key for the current iteration is the last char of generated text because
            // it is markovOne, if it was markovTwo then it would be the last two of the
            // generated text
            // System.out.println("new iteration");
            // System.out.println("generated so far: " + sb);

            key = sb.substring(sb.length() - order);
            // System.out.println("key: " + key);

            ArrayList<String> followsList = getFollows(key);
            if (followsList.size() == 0) {
                break;
            }
            // System.out.println("follows: " + whatFollows);
            // System.out.println(key);
            // System.out.println(followsList);
            int randomIndex = myRandom.nextInt(followsList.size());
            String character = followsList.get(randomIndex);
            sb.append(character);
            // System.out.println();
        }
        return sb.toString();
    }

    public String toString() {
        return "EfficientMarkovModel of order " + order;
    }
}
