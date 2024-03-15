public class Part2 {
    public double cgRatio(String dna) {
        dna = dna.toUpperCase();
        int gCount = 0;
        int cCount = 0;
        int length = dna.length();
        for (int i = 0; i < length; i++) {
            char ch = dna.charAt(i);
            if (ch == 'C') {
                cCount += 1;
            }
            if (ch == 'G') {
                gCount += 1;
            }
        }
        double finalRatio = (1.0 * (cCount + gCount)) / length;
        return finalRatio;
    }

    public void testCgRatio() {
        // "CCCCCCCC" --> 1.0
        // "GGGGGGGGGGGGGGG" --> 1.0
        // "CCCGGGGG" -> 1.0
        // "LOLHAHAH" --> 0.0
        // "ATGCCATAG" --> 4/9
        // "aA" --> 0.0
        // "C" --> 1.0
        // "ASDFLUC" --> 1/7
        String[] inputs = {
                "CCCCCCCC",
                "GGGGGGGGGGGGGGG",
                "CCCGGGGG",
                "LOLHAHAH",
                "ATGCCATAG",
                "aA",
                "C",
                "ASDFLUC",
        };
        double[] expectedOutputs = {
                1.0, 1.0, 1.0, 0.0, 4.0 / 9, 0.0, 1.0, 1.0 / 7
        };
        int failedCases = 0;
        int totalCases = inputs.length;
        for (int i = 0; i < totalCases; i++) {
            String input = inputs[i];
            double expectedOutput = expectedOutputs[i];
            double actualOutput = cgRatio(input);
            if (expectedOutput != actualOutput) {
                failedCases += 1;
                System.out.println("Failed Test Case:");
                System.out.println("Input: " + input);
                System.out.println("Expected Output: " + expectedOutput);
                System.out.println("Actual Output: " + actualOutput);
                System.out.println();
            }
        }
        System.out.println("Testing of cgRatio Completed");
        System.out.println((totalCases - failedCases) + "/" + totalCases + " passed");

    }

    public void testCountCTG() {
        // "ATGLOLCTGHUY" --> 1
        // "CTGCTGCTG" --> 3
        // "" --> 0
        // "ATGTAAOILGUYCTG" --> 1
        // "CTGLOLCTGHIY" --> 2
        // "YOUABCCTGKIYCTGMCDCTGYUTCTG" --> 4
        // "YOUTUBE" --> 0;
        String[] inputs = {
                "ATGLOLCTGHUY",
                "CTGCTGCTG",
                "",
                "ATGTAAOILGUYCTG",
                "CTGLOLCTGHIY",
                "YOUABCCTGKIYCTGMCDCTGYUTCTG",
                "YOUTUBE",
        };
        int[] expectedOutputs = {
                1, 3, 0, 1, 2, 4, 0
        };
        int failedCases = 0;
        int totalCases = inputs.length;
        for (int i = 0; i < totalCases; i++) {
            String input = inputs[i];
            int expectedOutput = expectedOutputs[i];
            int actualOutput = countCTG(input);
            if (expectedOutput != actualOutput) {
                failedCases += 1;
                System.out.println("Failed Test Case:");
                System.out.println("Input: " + input);
                System.out.println("Expected Output: " + expectedOutput);
                System.out.println("Actual Output: " + actualOutput);
                System.out.println();
            }
        }
        System.out.println("Testing of countCTG Completed");
        System.out.println((totalCases - failedCases) + "/" + totalCases + " passed");

    }

    public int countCTG(String dna) {
        int length = dna.length();
        int count = 0;
        // int index = dna.indexOf("CTG");
        int startIndex = 0;
        /*
         * while(index != -1){
         * count += 1;
         * index = dna.indexOf("CTG",index + 3);
         * }
         */
        /*
         * while(true){
         * if(index == -1){
         * break;
         * }
         * count += 1;
         * index = dna.indexOf("CTG", index + 3);
         * }
         */
        while (true) {
            int ctgIndex = dna.indexOf("CTG", startIndex);
            if (ctgIndex == -1) {
                break;
            }
            count += 1;
            startIndex = ctgIndex + 3;
        }
        return count;
    }
}
