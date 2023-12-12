public class CaesarCipher {
    public String getShiftedAlphabet(int key) {
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

    public String encrypt(String input, int key) {
        StringBuilder finalMessage = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String shiftedAlphabet = getShiftedAlphabet(key);
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

    public void testEncrypt() {
        String message = "FIRST LEGION ATTACK EAST FLANK!";
        int key = 23;
        String encrypted = encrypt(message, key);
        System.out.println("The final message is '" + encrypted + "'");
        String decrypted = encrypt(encrypted, 26 - key);
        System.out.println("The original message is '" + decrypted + "'");
        System.out.println(" ");

        message = "First Legion";
        key = 23;
        encrypted = encrypt(message, key);
        System.out.println("The final message is '" + encrypted + "'");
        decrypted = encrypt(encrypted, 26 - key);
        System.out.println("The original message is '" + decrypted + "'");
        System.out.println(" ");

        message = "First Legion";
        key = 17;
        encrypted = encrypt(message, key);
        System.out.println("The final message is '" + encrypted + "'");
        decrypted = encrypt(encrypted, 26 - key);
        System.out.println("The original message is '" + decrypted + "'");

        message = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        key = 15;
        encrypted = encrypt(message, key);
        System.out.println("The final message is '" + encrypted + "'");
    }

    public String encryptTwoKeys(String input, int key1, int key2) {
        StringBuilder finalMessage = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String shiftedAlphabet1 = getShiftedAlphabet(key1);
        String shiftedAlphabet2 = getShiftedAlphabet(key2);
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

    public void testEncryptTwoKeys() {
        /*
         * String message =
         * "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!"
         * ;
         * String answer = encryptTwoKeys(message, 8, 21);
         * System.out.println("The original is '" + message + "' final message: '" +
         * answer + "'");
         * String newAnswer = encryptTwoKeys(answer,18,5);
         * System.out.println("The original is '" + newAnswer +"'");
         * 
         * String newMessage = "Top ncmy qkff vi vguv vbg ycpx";
         * answer = encryptTwoKeys(newMessage, 24, 6);
         * System.out.println("The final message: '" + answer + "'");
         */
        String message = "Hfs cpwewloj loks cd Hoto kyg Cyy.";
        String encryptedMessage = encryptTwoKeys(message, 12, 2);
        System.out.println(encryptedMessage);
    }
}
