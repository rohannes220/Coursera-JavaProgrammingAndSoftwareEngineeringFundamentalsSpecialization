import java.util.Random;
import java.util.ArrayList;

public class MarkovFour {
    private String myText;
    private Random myRandom;

    public MarkovFour() {
        myRandom = new Random();
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String s) {
        myText = s.trim();
    }

    public String getRandomText(int numChars) {
        if (myText == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length() - 4);
        String key = myText.substring(index, index + 4);
        sb.append(key);
        for (int k = 0; k < numChars - 4; k++) {
            // the key for the current iteration is the last char of generated text because
            // it is markovOne, if it was markovTwo then it would be the last two of the
            // generated text
            // System.out.println("new iteration");
            // System.out.println("generated so far: " + sb);

            key = sb.substring(sb.length() - 4);
            // System.out.println("key: " + key);

            ArrayList<String> whatFollows = getFollows(key);
            // System.out.println("follows: " + whatFollows);

            int randomIndex = myRandom.nextInt(whatFollows.size());
            String character = whatFollows.get(randomIndex);
            sb.append(character);
            // System.out.println();
        }
        return sb.toString();
    }

    public ArrayList<String> getFollows(String key) {
        int keyLength = key.length();
        ArrayList<String> follows = new ArrayList<String>();
        int start = 0;
        while (start < myText.length()) {
            int index = myText.indexOf(key, start);
            if (index == myText.length() - keyLength || index == -1) {
                break;
            }
            String nextChar = myText.substring(index + keyLength, index + 1 + keyLength);
            follows.add(nextChar);
            start = index + 1;
        }
        return follows;
    }
}
