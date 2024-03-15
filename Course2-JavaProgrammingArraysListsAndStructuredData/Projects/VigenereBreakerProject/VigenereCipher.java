import edu.duke.*;
import java.util.*;

// represents a Vigenere cipher, which is a collection of Caesar ciphers
public class VigenereCipher {
    // array of CaesarCipher instances forming the Vigenere cipher
    CaesarCipher[] ciphers;

    // constructor that initializes the Vigenere cipher with the given key
    public VigenereCipher(int[] key) {

        // create an array of CaesarCipher instances based on the key length
        ciphers = new CaesarCipher[key.length];

        // initialize each CaesarCipher instance with the corresponding key
        for (int i = 0; i < key.length; i++) {
            ciphers[i] = new CaesarCipher(key[i]);
        }
    }

    // encrypts the given input using the Vigenere cipher
    public String encrypt(String input) {
        StringBuilder answer = new StringBuilder();
        int i = 0;

        // iterate through each character in the input string
        for (char c : input.toCharArray()) {
            
            // determine the index of the current Caesar cipher in the array
            int cipherIndex = i % ciphers.length;

            // get the current CaesarCipher instance
            CaesarCipher thisCipher = ciphers[cipherIndex];

            // encrypt the current character using the current Caesar cipher and append it
            // to the result
            answer.append(thisCipher.encryptLetter(c));

            // increment the index for the next iteration
            i++;
        }
        return answer.toString();
    }

    // decrypts the given input using the Vigenere cipher
    public String decrypt(String input) {
        StringBuilder answer = new StringBuilder();
        int i = 0;

        // iterate through each character in the input string
        for (char c : input.toCharArray()) {
            // determine the index of the current Caesar cipher in the array
            int cipherIndex = i % ciphers.length;


            // get the current CaesarCipher instance
            CaesarCipher thisCipher = ciphers[cipherIndex];

            //decrypt the character 
            answer.append(thisCipher.decryptLetter(c));

            // move to the next one 
            i++;
        }
        return answer.toString();
    }

    //provides a string representation of the vigenere cipher 
    public String toString() {
        return Arrays.toString(ciphers);
    }

}
