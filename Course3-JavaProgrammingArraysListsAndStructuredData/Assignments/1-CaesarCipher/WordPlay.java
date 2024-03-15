public class WordPlay {
    // instance variables - replace the example below with your own
    public boolean isVowel(char ch) {
        ch = Character.toLowerCase(ch);
        if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
            return true;
        }
        return false;
    }

    public void testIsVowel() {
        char[] inputs = { 'a', 'A', 'f', 'Z', 'o', 'Y', 'e', 'I', 'U' };

        boolean[] expectedOutputs = {
                true,
                true,
                false,
                false,
                true,
                false,
                true,
                true,
                true,
        };

        int totalTestCases = inputs.length;
        int failedCases = 0;
        for (int i = 0; i < totalTestCases; i++) {
            char input = inputs[i];
            boolean expectedOutput = expectedOutputs[i];
            boolean actualOutput = isVowel(input);
            if (expectedOutput != actualOutput) {
                System.out.println("Failed Test Case: ");
                System.out.println("Input: " + input);
                System.out.println("Expected Output: " + expectedOutput);
                System.out.println("Actual Output: " + actualOutput);
                failedCases += 1;
                System.out.println();
            }
        }
        System.out.println("Testing of isVowel Complete!");
        System.out.println("Success rate: " + (totalTestCases - failedCases) + "/" + totalTestCases);
    }

    public String replaceVowels(String phrase, char ch) {
        StringBuilder newPhrase = new StringBuilder(phrase);
        for (int i = 0; i < phrase.length(); i++) {
            char current = phrase.charAt(i);
            if (isVowel(current)) {
                newPhrase.setCharAt(i, ch);
            }
        }
        String str = newPhrase.toString();
        return str;
    }

    public void testIsReplaceVowels() {
        // "Hello World", '*' -> "H*ll* W*rld"
        // "Hello World", '-' -> "H-ll- W-rld"
        // "Rohan is a programmer", '^' -> "R^h^n ^s ^ pr^gr^mm^r"
        // "AEIOU", '$' -> "$$$$$"
        // "", '#' -> ""
        // "aaaaa", 'q' -> "qqqqq"
        // "rHythm", '@' -> "rHythm"
        String[] phrases = {
                "Hello World",
                "Hello World",
                "Rohan is a programmer",
                "AEIOU",
                "",
                "aaaaa",
                "rHythm"
        };
        char[] chars = {
                '*',
                '-',
                '^',
                '$',
                '#',
                'q',
                '@'
        };

        String[] expectedOutputs = {
                "H*ll* W*rld",
                "H-ll- W-rld",
                "R^h^n ^s ^ pr^gr^mm^r",
                "$$$$$",
                "",
                "qqqqq",
                "rHythm"
        };

        int totalTestCases = phrases.length;
        int failedCases = 0;
        for (int i = 0; i < totalTestCases; i++) {
            String phrase = phrases[i];
            char ch = chars[i];
            String expectedOutput = expectedOutputs[i];
            String actualOutput = replaceVowels(phrase, ch);
            if (!expectedOutput.equals(actualOutput)) {
                System.out.println("Failed Test Case: ");
                System.out.println("Phrase: " + phrase);
                System.out.println("Char: " + ch);
                System.out.println("Expected Output: " + expectedOutput);
                System.out.println("Actual Output: " + actualOutput);
                failedCases += 1;
                System.out.println();
            }
        }
        System.out.println("Testing of replaceVowels Complete!");
        System.out.println("Success rate: " + (totalTestCases - failedCases) + "/" + totalTestCases);
    }

    public String emphasize(String phrase, char ch) {
        StringBuilder newPhrase = new StringBuilder(phrase);
        ch = Character.toLowerCase(ch);
        for (int i = 0; i < phrase.length(); i++) {
            char current = phrase.charAt(i);
            current = Character.toLowerCase(current);
            int location = i + 1;
            if (current == ch) {
                if (location % 2 == 0) {
                    newPhrase.setCharAt(i, '+');
                } else {
                    newPhrase.setCharAt(i, '*');
                }
            }
        }
        String str = newPhrase.toString();
        return str;
    }

    public void testEmphasize() {
        // "Hello World", 'o' -> "Hell* W+rld"
        // "Hello World", 'l' -> "He*+o Wor+d"
        // "Rohan is a programmer", 'a' -> "Roh+n is + pr+gr*mmer"
        // "AEIOU", 'E' -> "A+IOU"
        // "aaaaa", 'a' -> ""*+*+*"
        // "rHythmm", 'm' -> "rHyth+*"
        String[] phrases = {
                "Hello World",
                "Hello World",
                "Rohan is a programmer",
                "AEIOU",
                "aaaaa",
                "rHythmm",
                "dna ctgaaactga",
                "Mary Bella Abracadabra",
        };
        char[] chars = {
                'o',
                'l',
                'a',
                'E',
                'a',
                'm',
                'a',
                'a',
        };

        String[] expectedOutputs = {
                "Hell* W+rld",
                "He*+o Wor+d",
                "Roh+n is + progr*mmer",
                "A+IOU",
                "*+*+*",
                "rHyth+*",
                "dn* ctg+*+ctg+",
                "M+ry Bell+ +br*c*d*br+",
        };

        int totalTestCases = phrases.length;
        int failedCases = 0;
        for (int i = 0; i < totalTestCases; i++) {
            String phrase = phrases[i];
            char ch = chars[i];
            String expectedOutput = expectedOutputs[i];
            String actualOutput = emphasize(phrase, ch);
            if (!expectedOutput.equals(actualOutput)) {
                System.out.println("Failed Test Case: ");
                System.out.println("Phrase: " + phrase);
                System.out.println("Char: " + ch);
                System.out.println("Expected Output: " + expectedOutput);
                System.out.println("Actual Output: " + actualOutput);
                failedCases += 1;
                System.out.println();
            }
        }
        System.out.println("Testing of emphasize Complete!");
        System.out.println("Success rate: " + (totalTestCases - failedCases) + "/" + totalTestCases);
    }
}
