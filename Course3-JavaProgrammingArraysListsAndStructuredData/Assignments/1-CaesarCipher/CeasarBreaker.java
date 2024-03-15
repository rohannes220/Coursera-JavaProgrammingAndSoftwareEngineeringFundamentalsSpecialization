import edu.duke.*;
import java.util.*;

public class CeasarBreaker {
    public String decryptTwoKeys(String encrypted) {
        // StringBuilder sb = new StringBuilder(encrypted);
        StringBuilder even = new StringBuilder();
        StringBuilder odd = new StringBuilder();
        for (int i = 0; i < encrypted.length(); i++) {
            char c = encrypted.charAt(i);
            if (i % 2 == 1) {
                odd.append(c);
            } else {
                even.append(c);
            }
        }
        String evenString = even.toString();
        String oddString = odd.toString();
        int key1 = getKey(evenString);
        int key2 = getKey(oddString);
        System.out.println("Key 1: " + key1);
        System.out.println("Key 2: " + key2);
        CaesarCipher cipher = new CaesarCipher();
        String decryptedString = cipher.encryptTwoKeys(encrypted, 26 - key1, 26 - key2);
        return decryptedString;
    }

    public String decrypt(String encrypted) {
        CaesarCipher cipher = new CaesarCipher();
        int key = getKey(encrypted);
        System.out.println("key: " + key);
        return cipher.encrypt(encrypted, 26 - key);
    }

    public void testDecrypt() {
        FileResource fr = new FileResource();
        String originalMessage = fr.asString();
        CaesarCipher cc = new CaesarCipher();
        String encrypted = cc.encrypt(originalMessage, 5);
        System.out.println("The encrypted message is this: " + encrypted);
        String decrypted = decrypt(encrypted);
        System.out.println("The decrypted message is this: " + decrypted);
    }

    public int getKey(String input) {
        int[] freqs = countLetters(input);
        int maxLetterIndex = indexOfMax(freqs);
        int key = maxLetterIndex - 4;
        if (key < 0) {
            key = key + 26;
        }
        return key;
    }

    public int[] countLetters(String encrypted) {
        encrypted = encrypted.toLowerCase();
        String alphabets = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for (int i = 0; i < encrypted.length(); i++) {
            char ch = encrypted.charAt(i);
            int alphabetIndex = alphabets.indexOf(ch);
            if (alphabetIndex == -1) {
                continue;
            }
            counts[alphabetIndex] += 1;
        }
        return counts;
    }

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

    public void testDecryptTwoKeys() {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        String decrypted = decryptTwoKeys(encrypted);
        System.out.println("The decrypted message is: " + decrypted);
    }
}
