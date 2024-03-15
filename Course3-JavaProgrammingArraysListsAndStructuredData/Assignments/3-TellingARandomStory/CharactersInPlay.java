import java.util.*;
import edu.duke.*;

public class CharactersInPlay {
    private ArrayList<String> myCharacters;
    private ArrayList<Integer> myCounts;

    public CharactersInPlay() {
        myCharacters = new ArrayList<String>();
        myCounts = new ArrayList<Integer>();
    }

    public void update(String person) {
        int index = myCharacters.indexOf(person);
        if (index != -1) {
            int currentValue = myCounts.get(index);
            myCounts.set(index, currentValue + 1);
        } else {
            myCharacters.add(person);
            myCounts.add(1);
        }
    }

    public void findAllCharacters() {
        myCharacters.clear();
        myCounts.clear();
        FileResource resource = new FileResource();
        for (String line : resource.lines()) {
            int periodIndex = line.indexOf(".");
            if (periodIndex == -1) {
                continue;
            }
            String person = line.substring(0, periodIndex);
            update(person);
        }
    }

    public void tester() {
        findAllCharacters();
        charactersWithNumParts(10, 2000);
    }

    public void charactersWithNumParts(int num1, int num2) {
        for (int i = 0; i < myCharacters.size(); i++) {
            int currentCount = myCounts.get(i);
            if (currentCount >= num1 && currentCount <= num2) {
                System.out.println(myCharacters.get(i) + " " + myCounts.get(i));
            }
        }
    }
}
