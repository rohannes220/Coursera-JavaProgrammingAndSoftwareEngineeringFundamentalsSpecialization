import java.util.Random;
import java.util.ArrayList;

public class MarkovOne extends AbstractMarkovModel {
    public MarkovOne() {
        myRandom = new Random();
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public String getRandomText(int numChars) {
        if (myText == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length() - 1);
        String key = myText.substring(index, index + 1);
        sb.append(key);
        for (int k = 0; k < numChars - 1; k++) {
            // the key for the current iteration is the last char of generated text because
            // it is markovOne, if it was markovTwo then it would be the last two of the
            // generated text
            // System.out.println("new iteration");
            // System.out.println("generated so far: " + sb);

            key = sb.substring(sb.length() - 1);
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

    public String toString() {
        return "MarkovModel of order 1";
    }
}
