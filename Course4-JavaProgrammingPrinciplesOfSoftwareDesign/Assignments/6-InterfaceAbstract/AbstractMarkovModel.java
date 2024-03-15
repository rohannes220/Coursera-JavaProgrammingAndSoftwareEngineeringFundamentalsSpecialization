import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;

    public AbstractMarkovModel() {
        myRandom = new Random();
    }

    public void setTraining(String s) {
        myText = s.trim();
    }

    abstract public String getRandomText(int numChars);

    protected ArrayList<String> getFollows(String key) {
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
