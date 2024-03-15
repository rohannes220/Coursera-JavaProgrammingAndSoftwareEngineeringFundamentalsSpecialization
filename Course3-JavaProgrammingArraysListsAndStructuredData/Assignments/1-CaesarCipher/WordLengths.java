import edu.duke.*;

public class WordLengths {
    public int indexOfMax(int[] values) {
        int maxValue = values[0];
        int maxIndex = 0;
        for (int i = 1; i < values.length - 1; i++) {
            int currValue = values[i];
            if (currValue >= maxValue) {
                maxValue = currValue;
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public int getLength(String input) {
        int length = input.length();
        char firstChar = input.charAt(0);
        char lastChar = input.charAt(length - 1);
        boolean isFirstCharLetter = Character.isLetter(firstChar);
        boolean isLastCharLetter = Character.isLetter(lastChar);
        if (length == 1) {
            if (!isFirstCharLetter) {
                return 0;
            }
            return 1;
        }
        if (!isFirstCharLetter) {
            length -= 1;
        }
        if (!isLastCharLetter) {
            length -= 1;
        }
        return length;
    }

    public void countWordLengths(FileResource resource, int[] counts) {
        for (String word : resource.words()) {
            if (word.length() == 0) {
                continue;
            }
            int length = getLength(word);
            if (length > counts.length - 1) {
                counts[counts.length - 1] += 1;
            } else {
                counts[length] += 1;
            }
        }
    }

    public void testCountWordLengths() {
        FileResource fr = new FileResource();
        int[] counts = new int[31];
        countWordLengths(fr, counts);
        int indexOfLargestNumber = indexOfMax(counts);
        System.out.println("The length with the maximum count is: " + indexOfLargestNumber);
        for (int i = 0; i < counts.length - 1; i++) {
            int count = counts[i];
            if (count == 0) {
                continue;
            }
            System.out.println(count + " words of length " + i);
        }
    }

}
