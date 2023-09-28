
public class Part1 {
    public int findStopCodon(String dna, int startCodonIndex, String stopCodon) {
        int currIndex = dna.indexOf(stopCodon, startCodonIndex);
        while (currIndex != -1) {
            int difference = currIndex - startCodonIndex;
            if (difference % 3 == 0) {
                return currIndex;
            }
            currIndex = dna.indexOf(stopCodon, currIndex + 3);
        }
        return dna.length();
    }

    public void testFindStopCodon() {
        // v v v v
        // ATGHGTAADTAA, 0, TAA -> 9
        // v v
        // ATGTAA, 0, TAA -> 3
        // v v v v v v v
        // HGAATGHYGHUGDDTAATAAG, 3, TAA -> 21
        // v v v v
        // ATGHGTAADTGA, 0, TGA -> 9
        // v v v v v v v v v
        // HADATGYUDTGADHGTAADTAADFTGA, 3, TGA -> 9
        // v v v v v v v v v
        // HGTATGDTAGTGATAATAATGATAGHI, 3, TAA -> 27
        // v v v v v v v v v
        // HGTATGDTAGTGATAATAATGATAGHI, 3, TAG -> 27
        // v v v v v v v v v
        // HGTATGDTAGTGATAATAATGATAGHI, 3, TGA -> 27
        // v v v v v v v v v
        // HGTATGTAGTGATAATAATGATAGHID, 3, TAA -> 12
        // v v v v v v v v v
        // HGTATGTAGTGATAATAATGATAGHID, 3, TAG -> 6
        // v v v v v v v v v
        // HGTATGTAGTGATAATAATGATAGHID, 3, TGA -> 9
        String[] paramOnes = {
                "ATGHGTAADTAA",
                "ATGTAA",
                "HGAATGHYGHUGDDTAATAAG",
                "ATGHGTAADTGA!",
                "HADATGYUDTGADHGTAADTAADFTGA",
                "HGTATGDTAGTGATAATAATGATAGHI",
                "HGTATGDTAGTGATAATAATGATAGHI",
                "HGTATGDTAGTGATAATAATGATAGHI",
                "HGTATGTAGTGATAATAATGATAGHID",
                "HGTATGTAGTGATAATAATGATAGHID",
                "HGTATGTAGTGATAATAATGATAGHID",
        };

        int[] paramTwos = { 0, 0, 3, 3, 0, 3, 3, 3, 3, 3, 3, 3 };
        String[] paramThrees = {
                "TAA",
                "TAA",
                "TAA",
                "TGA",
                "TGA",
                "TAA",
                "TAG",
                "TGA",
                "TAA",
                "TAG",
                "TGA",
        };
        int[] expectedOutputs = { 9, 3, 21, 9, 9, 27, 27, 27, 12, 6, 9 };
        int failedCases = 0;
        int totalCases = paramOnes.length;
        for (int i = 0; i < totalCases; i++) {
            String dna = paramOnes[i];
            int startCodonIndex = paramTwos[i];
            String stopCodon = paramThrees[i];
            int expectedOutput = expectedOutputs[i];
            int actualOutput = findStopCodon(dna, startCodonIndex, stopCodon);
            if (actualOutput != expectedOutput) {
                failedCases += 1;
                System.out.println("Test failed");
                System.out.println("DNA: " + dna);
                System.out.println("StartCodonIndex: " + startCodonIndex);
                System.out.println("StopCodon: " + stopCodon);
                System.out.println("Actual Output: " + actualOutput);
                System.out.println("Expected Output: " + expectedOutput);
                System.out.println();
            }
        }
        System.out.println("Testing of findStopCodon Completed");
        System.out.println((totalCases - failedCases) + "/" + totalCases + " passed");
    }

    public String findGene(String dna, int lookFrom) {
        int startCodonIndex = dna.indexOf("ATG", lookFrom);
        if (startCodonIndex == -1) {
            return "";
        }
        int taaIndex = findStopCodon(dna, startCodonIndex, "TAA");
        int tagIndex = findStopCodon(dna, startCodonIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startCodonIndex, "TGA");
        int[] values = { taaIndex, tagIndex, tgaIndex };
        int minValue = values[0];
        for (int i = 0; i < values.length; i++) {
            int currValue = values[i];
            if (currValue < minValue) {
                minValue = currValue;
            }
        }
        if (minValue == dna.length()) {
            return "";
        }
        String gene = dna.substring(startCodonIndex, minValue + 3);
        return gene;
    }

    public void testFindGene() {
        // SMHATGLOLHAHA --> ""
        // IHAVENOIDEA --> ""
        // HIRLOLATGSMHJUKIYUTAA --> "ATGSMHJUKIYUTAA"
        // PTOATGJOKETAA --> ""
        // ATGJKOTIORETTGAYUOTAA --> "ATGJKOTIORETTGA"
        String[] paramOnes = {
                "SMHATGLOLHAHA",
                "IHAVENOIDEA",
                "HIRLOLATGSMHJUKIYUTAA",
                "PTOATGJOKETAA",
                "ATGJKOTIORETTGAYUOTAA",
                "ADATGTAAHYATGYHGTGADD"
        };
        int[] paramTwos = {
                0, 0, 0, 0, 0, 8
        };
        String[] expectedOutputs = {
                "",
                "",
                "ATGSMHJUKIYUTAA",
                "",
                "ATGJKOTIORETTGA",
                "ATGYHGTGA"
        };
        int failedCases = 0;
        int totalCases = paramOnes.length;
        for (int i = 0; i < totalCases; i++) {
            String dna = paramOnes[i];
            int lookFrom = paramTwos[i];
            String expectedOutput = expectedOutputs[i];
            String actualOutput = findGene(dna, lookFrom);
            if (expectedOutput.equals(actualOutput) == false) {
                failedCases += 1;
                System.out.println("Test failed");
                System.out.println("DNA: " + dna);
                System.out.println("Actual Output: " + actualOutput);
                System.out.println("Expected Output: " + expectedOutput);
                System.out.println();
            }
        }
        System.out.println("Testing of findGene Completed");
        System.out.println((totalCases - failedCases) + "/" + totalCases + " passed");
    }

    public void printAllGenes(String dna) {
        int start = 0;
        while (true) {
            // try to get the gene
            String gene = findGene(dna, start);

            // if gene is empty, end the loop
            if (gene.length() == 0) {
                break;
            }

            // if gene is valid print it
            System.out.println(gene);

            // update start
            start = dna.indexOf(gene, start) + gene.length();
        }
    }

}
