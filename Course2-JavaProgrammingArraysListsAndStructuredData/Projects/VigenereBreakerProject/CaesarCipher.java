import edu.duke.*;

public class CaesarCipher {
    // these fields are neede to help create new alphabets/keys for every object
    private String alphabet; // ABCDE..Z
    private String shiftedAlphabet; // new alphabet according to the key
    private int theKey; // key of 3 means you start from D and whatever is before comes after Z

    // function is used to create a new alphabet used to decrypt message
    public CaesarCipher(int key) { // i.e. key 5
        theKey = key;
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        // Create the shifted alphabet by concatenating substrings
        // The first part starts from the position specified by 'key' and goes to the
        // end of the alphabet
        // The second part starts from the beginning of the alphabet and goes up to the
        // position specified by 'key'
        shiftedAlphabet = alphabet.substring(key) +
                alphabet.substring(0, key);

        // Append the lowercase version of the alphabet to the original alphabet
        alphabet = alphabet + alphabet.toLowerCase();

        // Append the lowercase version of the shifted alphabet to the shifted alphabet
        shiftedAlphabet = shiftedAlphabet + shiftedAlphabet.toLowerCase();
    }

    // Transform a single character 'c' based on substitution from 'from' to 'to'
    // alphabet
    private char transformLetter(char c, String from, String to) {
        // find the index of c in thr from alphabet
        int idx = from.indexOf(c);

        // if the character is found in the 'from' alphabet, replace it with the
        // corresponding character from the 'to' alphabet
        if (idx != -1) {
            return to.charAt(idx);
        }

        // if the character is not found in the 'from' alphabet, leave it unchanged
        return c;
    }

    // encrypt a single letter 'c' using the substitution cipher
    public char encryptLetter(char c) {
        return transformLetter(c, alphabet, shiftedAlphabet);
    }

    // decrypt the letter going backwards
    public char decryptLetter(char c) {
        return transformLetter(c, shiftedAlphabet, alphabet);
    }

    // Transform a given input string using the provided 'from' and 'to' alphabets
    private String transform(String input, String from, String to) {
        // Create a StringBuilder with the content of the input string
        StringBuilder sb = new StringBuilder(input);

        // Iterate through each character in the input string
        for (int i = 0; i < sb.length(); i++) {
            // Get the current character
            char c = sb.charAt(i);

            // Transform the character using the transformLetter method
            c = transformLetter(c, from, to);

            // Set the transformed character back into the StringBuilder at the same
            // position

            sb.setCharAt(i, c);
        }
        // Convert the modified StringBuilder back to a string and return the
        // transformed string
        return sb.toString();
    }

    // Encrypt a given input string using the substitution cipher
    public String encrypt(String input) {
        return transform(input, alphabet, shiftedAlphabet);
    }

    // Decrypt a given input string using the inverse substitution cipher
    public String decrypt(String input) {
        return transform(input, shiftedAlphabet, alphabet);
    }

    // Return a string representation of the substitution cipher, currently
    // representing only the key
    public String toString() {
        return "" + theKey;
    }

}
