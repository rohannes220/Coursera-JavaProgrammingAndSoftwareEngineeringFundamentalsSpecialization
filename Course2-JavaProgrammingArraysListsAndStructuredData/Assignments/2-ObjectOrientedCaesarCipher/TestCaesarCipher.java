import edu.duke.*;

public class TestCaesarCipher {
    public void simpleTests() {
        /*
         * FileResource fr = new FileResource();
         * String message = fr.asString();
         * CaesarCipher cc = new CaesarCipher(18);
         * System.out.println("The original message is: " + message);
         * String encryptedMessage = cc.encrypt(message);
         * System.out.println("The encrypted message is: " + encryptedMessage);
         * String decryptedMessage = cc.decrypt(encryptedMessage);
         * System.out.println("The decrypted message is: " + decryptedMessage);
         */

        String message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        CaesarCipher cc = new CaesarCipher(15);
        String encryptedMessage = cc.encrypt(message);
        System.out.println(encryptedMessage);
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

    public int getKey(String input) {
        int[] freqs = countLetters(input);
        int maxLetterIndex = indexOfMax(freqs);
        int key = maxLetterIndex - 4;
        if (key < 0) {
            key = key + 26;
        }
        return key;
    }

    public String breakCaesarCipher(String encryptedMessage) {
        int key = getKey(encryptedMessage);
        System.out.println("key: " + key);
        CaesarCipher cc = new CaesarCipher(key);
        return cc.decrypt(encryptedMessage);
    }
}
