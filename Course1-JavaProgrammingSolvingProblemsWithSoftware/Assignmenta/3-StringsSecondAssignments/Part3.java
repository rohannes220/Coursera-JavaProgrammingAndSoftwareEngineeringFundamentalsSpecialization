
/**
 * Write a description of class Part3 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Part3
{
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
    };
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
    public int countGenes(String dna){
        int start = 0; 
        int count = 0;
        while(true){
            String gene = findGene(dna,start);
            if(gene.length() == 0){
                break; 
            }
            count += 1; 
            start = dna.indexOf(gene,start) + gene.length(); 
        }
        return count;  
    }
    public void testCountGenes(){
        System.out.println(countGenes("ATGTAAGATGCCCTAGT")); 
    }
}
