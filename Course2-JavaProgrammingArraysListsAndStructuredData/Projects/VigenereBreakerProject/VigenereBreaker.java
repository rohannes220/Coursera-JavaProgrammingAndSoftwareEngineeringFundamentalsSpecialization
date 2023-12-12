import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder sb = new StringBuilder();
        for (int i = whichSlice; i < message.length(); i += totalSlices) {
            char c = message.charAt(i);
            sb.append(c);
        }
        return sb.toString();
    }

    public void breakVigenere() {
        FileResource fr = new FileResource();
        String message = fr.asString();
        HashMap<String, HashSet<String>> languages = new HashMap<String, HashSet<String>>();
        String[] languageNames = new String[] { "Danish", "Dutch", "English", "French", "German", "Italian",
                "Portuguese", "Spanish" };
        for (String language : languageNames) {
            FileResource languageFR = new FileResource("dictionaries/" + language);
            HashSet<String> currentDictionary = readDictionary(languageFR);
            languages.put(language, currentDictionary);
        }
        breakForAllLangs(message, languages);
    }

    public HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> set = new HashSet<String>();
        for (String word : fr.lines()) {
            String lowerCase = word.toLowerCase();
            set.add(lowerCase);
        }
        return set;
    }

    // In the given language, we are trying to find the most common character
    private char mostCommonCharIn(HashSet<String> language) {
        char mostCommon = 'a';
        int maxCount = 0;
        for (char ch = 'a'; ch <= 'z'; ch++) {
            int count = getCount(ch, language);
            if (count > maxCount) {
                maxCount = count;
                mostCommon = ch;
            }
        }
        return mostCommon;
    }

    // given a charaacter from above, we see how many times it appears in each word
    // we then take that amount and compare it to the currentMaxCount
    private int getCount(char letter, HashSet<String> inputLanguage) {
        int count = 0;
        for (String word : inputLanguage) {
            word = word.toLowerCase();
            int length = word.length();
            for (int i = 0; i < length; i++) {
                char currentChar = word.charAt(i);
                if (currentChar == letter) {
                    count += 1;
                }
            }
        }
        return count;
    }

    public int countWords(String message, HashSet<String> dictionary) {
        int realWords = 0;
        for (String word : message.split("\\W+")) {
            if (dictionary.contains(word.toLowerCase())) {
                realWords += 1;
            }
        }
        return realWords;
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] keys = new int[klength];
        CaesarCracker cc = new CaesarCracker(mostCommon);
        for (int slice = 0; slice < klength; slice += 1) {
            String slicedString = sliceString(encrypted, slice, klength);
            int key = cc.getKey(slicedString);
            keys[slice] = key;
        }
        return keys;
    }

    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
        char mostCommonCharacter = mostCommonCharIn(dictionary);
        int highestNumWords = 0;
        String answer = null;
        int maxKeyLength = 0;
        for (int key = 1; key <= 100; key += 1) {
            int[] keys = tryKeyLength(encrypted, key, mostCommonCharacter);
            VigenereCipher vc = new VigenereCipher(keys);
            String decrypted = vc.decrypt(encrypted);
            int numWords = countWords(decrypted, dictionary);
            if (numWords > highestNumWords) {
                highestNumWords = numWords;
                answer = decrypted;
                maxKeyLength = keys.length;
            }
        }
        return answer;
    }

    public void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages) {
        int maxWords = 0;
        String maxLanguage = null;
        String maxDecrypted = null;
        for (String language : languages.keySet()) {
            HashSet<String> dictionary = languages.get(language);
            String decrypted = breakForLanguage(encrypted, dictionary);
            int numValidWords = countWords(decrypted, dictionary);
            if (numValidWords > maxWords) {
                maxWords = numValidWords;
                maxDecrypted = decrypted;
                maxLanguage = language;
            }
        }
        System.out.println("The language: " + maxLanguage);
        System.out.println("The message: " + maxDecrypted);
    }
}
