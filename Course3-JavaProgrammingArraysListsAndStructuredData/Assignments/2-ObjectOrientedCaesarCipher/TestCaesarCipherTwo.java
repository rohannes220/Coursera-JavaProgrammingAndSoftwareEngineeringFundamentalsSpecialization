import edu.duke.*;

public class TestCaesarCipherTwo {
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

    public int getKey(String input) {
        int[] freqs = countLetters(input);
        int maxLetterIndex = indexOfMax(freqs);
        int key = maxLetterIndex - 4;
        if (key < 0) {
            key = key + 26;
        }
        return key;
    }

    public void simpleTests() {
        FileResource fr = new FileResource();
        String message = fr.asString();
        CaesarCipherTwo cc = new CaesarCipherTwo(17, 3);
        System.out.println("The original message is: " + message);
        String encryptedMessage = cc.encrypt(message);
        System.out.println("The encrypted message is: " + encryptedMessage);
        String decryptedMessage = cc.decrypt(encryptedMessage);
        System.out.println("The decrypted message is: " + decryptedMessage);

    }

    public String breakCaesarCipher(String encryptedMessage) {
        int key1 = getKey(encryptedMessage);
        int key2 = getKey(encryptedMessage);
        System.out.println("key1: " + key1);
        System.out.println("key2: " + key2);
        CaesarCipherTwo cc = new CaesarCipherTwo(key1, key2);
        return cc.decrypt(encryptedMessage);
    }
}