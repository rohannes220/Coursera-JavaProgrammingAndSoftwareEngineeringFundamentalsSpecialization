
/**
 * Write a description of class Part2 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Part2 {
    // instance variables - replace the example below with your own
    public int howMany(String stringa, String stringb) {
        int count = 0;
        
       int startIndex = 0;
        while (true) {
            int currIndex = stringb.indexOf(stringa, startIndex);
            if (currIndex == -1) {
                break;
            }
            count += 1;
            startIndex = stringa.length() + currIndex;
        }
        return count;
        
        // int currIndex = stringb.indexOf(stringa);
        // while (currIndex != -1) {
        // count += 1;
        // currIndex = stringb.indexOf(stringa, currIndex + stringa.length());
        // }
        // return count;
    };

    public void testHowMany() {
        // (LOL,ABLOLABGLOL) --> 2
        // (HO, ABCDEG )-- >0
        // (eat, I like to eat) --> 1
        // ("A", ABCDEFALOLYUOA) --> 3
        // ("knocked", I knocked at the door twice) --> 1
        // ("ate", I ATE PIZZA AND AT ATE TOO MUCH) --> 2
        String[] paramOne = {
                "LOL",
                "HO",
                "EAT",
                "A",
                "knocked",
                "ATE",
                "AA",
                "AB"
        };

        String[] paramTwos = {
                "LOLABGLOL",
                "ABCDEG",
                "I like to EAT",
                "ABCDEFALOLYUOA",
                "I knocked at the door twice",
                "I ATE PIZZA AND ATE TOO MUCH",
                "ATAAAA",
                "CDABEFABGTABGUABJIKTOPLAB"
        };

        int[] expectedOutputs = { 2, 0, 1, 3, 1, 2, 2, 5 };
        int failedCases = 0;
        int totalCases = paramOne.length;
        for (int i = 0; i < totalCases; i++) {
            String stringa = paramOne[i];
            String stringb = paramTwos[i];
            int expectedOutput = expectedOutputs[i];
            int actualOutput = howMany(stringa, stringb);
            if (actualOutput != expectedOutput) {
                failedCases += 1;
                System.out.println("Test failed");
                System.out.println("Stringa: " + stringa);
                System.out.println("Stringb: " + stringb);
                System.out.println("Actual Output: " + actualOutput);
                System.out.println("Expected Output: " + expectedOutput);
                System.out.println();
            }
        }
        System.out.println("Testing of howMany Completed");
        System.out.println((totalCases - failedCases) + "/" + totalCases + " passed");
    }
}
