import edu.duke.StorageResource;
import edu.duke.FileResource;

public class Part3 {
    public double cgRatio(String dna) {
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
    // Strings longer than 9:
    // abc
    // bcd
    //
    // Count: 2
    //
    // Strings with cg ratio higher than 0.35:
    // abc
    // bcd
    // defg

    // Count: 3

    // Length of the longest string: 4
    public void processGenes(StorageResource sr) {
        StorageResource genesLongerThanThreshold = new StorageResource();
        StorageResource genesCGRatioHigherThan35 = new StorageResource();
        int maxGeneLength = 1;
        int threshold = 60;
        for (String gene : sr.data()) {
            int geneLength = gene.length();
            if (geneLength > threshold) {
                genesLongerThanThreshold.add(gene);
            }
            if (cgRatio(gene) > 0.35) {
                genesCGRatioHigherThan35.add(gene);
            }
            if (geneLength > maxGeneLength) {
                maxGeneLength = geneLength;
            }
        }
        System.out.println("Number of strings: " + sr.size());
        System.out.println();

        System.out.println("Strings longer than " + threshold + ":");
        System.out.println("Count: " + genesLongerThanThreshold.size());
        for (String gene : genesLongerThanThreshold.data()) {
            System.out.println(gene);
        }
        System.out.println("");

        System.out.println("Strings with cg ratio higher than 0.35:");
        System.out.println("Count: " + genesCGRatioHigherThan35.size());
        for (String gene : genesCGRatioHigherThan35.data()) {
            System.out.println(gene);
        }
        System.out.println("");

        System.out.println("Length of the longest string: " + maxGeneLength);

        /*
         * System.out.println("Number of strings: " + sr.size());
         * 
         * System.out.println("Strings longer than 60:");
         * int count9 = 0;
         * for(String item: sr.data()){
         * int length = item.length();
         * if(length > 60){
         * count9 += 1;
         * System.out.println(item);
         * }
         * }
         * System.out.println("");
         * System.out.println("Count: " + count9);
         * System.out.println("");
         * 
         * System.out.println("Strings with cg ratio higher than 0.35:");
         * int higherThan35 = 0;
         * for(String item: sr.data()){
         * double ratio = cgRatio(item);
         * if(ratio > 0.35){
         * higherThan35 += 1;
         * System.out.println(item);
         * }
         * }
         * System.out.println("");
         * System.out.println("Count: " + higherThan35);
         * System.out.println("");
         * 
         * int maxGeneLength = Integer.MIN_VALUE;
         * for(String item: sr.data()){
         * int length = item.length();
         * if(length > maxGeneLength){
         * maxGeneLength = length;
         * }
         * }
         * System.out.println("Length of the longest string: " + maxGeneLength);
         */
    }

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

    public StorageResource getAllGenes(String dna) {
        int start = 0;
        StorageResource genesList = new StorageResource();
        while (true) {
            // try to get the gene
            String gene = findGene(dna, start);

            // if gene is empty, end the loop
            if (gene.length() == 0) {
                break;
            }

            // if gene is valid add it to the storage resource
            genesList.add(gene);

            // update start
            start = dna.indexOf(gene, start) + gene.length();
        }
        return genesList;
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

    public void processDna(String dna) {
        dna = dna.toUpperCase();
        System.out.println("DNA: " + dna);
        StorageResource sr = getAllGenes(dna);
        processGenes(sr);
        System.out.println();
        System.out.println();
    }

    public void testCountCTG() {
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString();
        System.out.println(countCTG(dna));
    }

    public void testProcessGenes() {
        // processDna("HUYATGGUYIOLPOUTOYTAAJKOTRESOUPATGTAAKJOUYHFHASAATGHUYTREQWESUYOPINLIBORTGALOLLOL");
        // processDna("LOLATGTAALOLATGYOUTGASMH");
        // processDna("LOLATGCCCGGGCCCTAGATGCCCGGGCLOGOCCIGGCGTAAATGCCCTAGATGLOLHAHTGA");
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString();
        processDna(dna);
    }
}
