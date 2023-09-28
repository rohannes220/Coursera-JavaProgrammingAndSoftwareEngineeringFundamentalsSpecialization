import edu.duke.StorageResource; 
/**
 * Write a description of class Part1 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Part1
{
    // instance variables - replace the example below with your own
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
    
    public void testGetAllGenes(){
        String dna = "ATGATCTAATTTATGCTGCAACGGTGAAGA";
        StorageResource answer = getAllGenes(dna); 
        System.out.println("dna: " + dna); 
        for(String gene: answer.data()){
            System.out.println(gene); 
        }
        
        dna = "ATGATCATAAGAAGATAATAGAGGGCCATGTAA"; 
        answer = getAllGenes(dna); 
        System.out.println("dna: " + dna); 
        for(String gene: answer.data()){
            System.out.println(gene); 
        }
    }
}
