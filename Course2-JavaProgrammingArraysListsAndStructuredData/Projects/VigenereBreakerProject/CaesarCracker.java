import edu.duke.*;

public class CaesarCracker {
    // character that is assumed to be the most common in the English language
    char mostCommon;

    // cefault constructor, initializes the mostCommon character to 'e
    public CaesarCracker() {
        mostCommon = 'e';
    }

    // allows specifying a different mostCommon character
    public CaesarCracker(char c) {
        mostCommon = c;
    }

    // count the occurrences of each letter in the provided messa
    public int[] countLetters(String message) {
        // define the alphabet
        String alph = "abcdefghijklmnopqrstuvwxyz";

        // initialize the array for the count for each letter
        int[] counts = new int[26];

        // iterate through each character in the message
        for (int k = 0; k < message.length(); k++) {

            // find the index of the lowercase version of the character in the alphabet
            int dex = alph.indexOf(Character.toLowerCase(message.charAt(k)));

            // If the character is a valid letter, increment its count
            if (dex != -1) {
                counts[dex] += 1;
            }
        }
        return counts;
    }

    // find the index of the maximum value in an array
    public int maxIndex(int[] vals) {
        int maxDex = 0;

        // iterate through each element in the array
        for (int k = 0; k < vals.length; k++) {
            // Update maxDex if the current element is greater than the current maximum
            if (vals[k] > vals[maxDex]) {
                maxDex = k;
            }
        }
        return maxDex;
    }

    // determine the key used in a Caesar cipher based on the most common letter
    // assumption
    public int getKey(String encrypted) {
        // count the occurrences of each letter in the encrypted message
        int[] freqs = countLetters(encrypted);

        // find the index of the most frequently occurring letter
        int maxDex = maxIndex(freqs);

        // calculate the key by assuming the most common letter in English is 'e'
        int mostCommonPos = mostCommon - 'a';
        int dkey = maxDex - mostCommonPos;
        if (maxDex < mostCommonPos) {
            dkey = 26 - (mostCommonPos - maxDex);
        }

        // adjust the key if needed for correct decryption
        return dkey;
    }

    // decrypt an encrypted message using the calculated key
    public String decrypt(String encrypted) {
        // get the key using the getKey method
        int key = getKey(encrypted);

        // create a CaesarCipher instance with the calculated key
        CaesarCipher cc = new CaesarCipher(key);

        // decrypt the message using the created CaesarCipher instance
        return cc.decrypt(encrypted);

    }

}
