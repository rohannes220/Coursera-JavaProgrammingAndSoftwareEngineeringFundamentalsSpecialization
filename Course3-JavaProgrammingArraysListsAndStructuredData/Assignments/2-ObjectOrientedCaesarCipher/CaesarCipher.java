public class CaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;

    public CaesarCipher(int key) {
        shiftedAlphabet = getShiftedAlphabet(key);
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        mainKey = key;
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
            char ch = input.charAt(i);
            int alphabetIndex = alphabet.indexOf(ch);
            if (alphabetIndex == -1) {
                continue;
            }
            char encryptedChar = shiftedAlphabet.charAt(alphabetIndex);
            finalMessage.setCharAt(i, encryptedChar);
        }
        String str = finalMessage.toString();
        return str;
    }

    public String decrypt(String encrypted) {
        /*
         * StringBuilder decryptedMessage = new StringBuilder(encrypt);
         * for(int i=0;i<encrypt.length();i++){
         * char ch = encrypt.charAt(i);
         * int shiftedAlphabetIndex = shiftedAlphabet.indexOf(ch);
         * if(shiftedAlphabetIndex == -1){
         * continue;
         * }
         * char decryptedChar = alphabet.charAt(shiftedAlphabetIndex);
         * decryptedMessage.setCharAt(i,decryptedChar);
         * }
         * return decryptedMessage.toString();
         */
        CaesarCipher decryptCaesarCipher = new CaesarCipher(26 - mainKey);
        return decryptCaesarCipher.encrypt(encrypted);
    }
}
