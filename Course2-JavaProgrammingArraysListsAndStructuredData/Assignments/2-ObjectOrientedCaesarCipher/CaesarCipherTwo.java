public class CaesarCipherTwo {
    // instance variables - replace the example below with your own
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private String decryptedShiftedAlphabet1;
    private String decryptedShiftedAlphabet2;

    public CaesarCipherTwo(int key1, int key2) {
        shiftedAlphabet1 = getShiftedAlphabet(key1);
        shiftedAlphabet2 = getShiftedAlphabet(key2);
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        decryptedShiftedAlphabet1 = getShiftedAlphabet(26 - key1);
        decryptedShiftedAlphabet2 = getShiftedAlphabet(26 - key2);
    }

    private String getShiftedAlphabet(int key) {
        String upperCaseAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String beginningUpperCase = upperCaseAlphabet.substring(key);
        String endUpperCase = upperCaseAlphabet.substring(0, key);
        String shiftedAlphabetUpperCase = beginningUpperCase + endUpperCase;
        String lowerCaseAlphabet = "abcdefghijklmnopqrstuvwxyz";
        String beginningLowerCase = lowerCaseAlphabet.substring(key);
        String endLowerCase = lowerCaseAlphabet.substring(0, key);
        String alphabet = upperCaseAlphabet + lowerCaseAlphabet;
        String shiftedAlphabet = beginningUpperCase + endUpperCase + beginningLowerCase + endLowerCase;
        return shiftedAlphabet;
    }

    public String encrypt(String input) {
        StringBuilder finalMessage = new StringBuilder(input);
        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            int index = alphabet.indexOf(currentChar);
            if (index == -1) {
                continue;
            }

            if (i % 2 == 0) {
                char shiftedAlphabetChar = shiftedAlphabet1.charAt(index);
                finalMessage.setCharAt(i, shiftedAlphabetChar);
            } else {
                char shiftedAlphabetChar = shiftedAlphabet2.charAt(index);
                finalMessage.setCharAt(i, shiftedAlphabetChar);
            }
        }
        return finalMessage.toString();
    }

    public String decrypt(String encrypted) {
        StringBuilder finalMessage = new StringBuilder(encrypted);
        for (int i = 0; i < encrypted.length(); i++) {
            char currentChar = encrypted.charAt(i);
            int index = alphabet.indexOf(currentChar);
            if (index == -1) {
                continue;
            }

            if (i % 2 == 0) {
                char decryptedChar1 = decryptedShiftedAlphabet1.charAt(index);
                finalMessage.setCharAt(i, decryptedChar1);
            } else {
                char decryptedChar2 = decryptedShiftedAlphabet2.charAt(index);
                finalMessage.setCharAt(i, decryptedChar2);
            }
        }
        return finalMessage.toString();
    }
}
