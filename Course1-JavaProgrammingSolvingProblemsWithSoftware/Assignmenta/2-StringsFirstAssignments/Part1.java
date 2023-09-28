
/**
 * Write a description of class Part1 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Part1
{
    String findSimpleGene(String dna){
        int start = dna.indexOf("ATG"); 
        if(start < 0){
            return ""; 
        }
        int stop = dna.indexOf("TAA",start);
        if(stop < 0){
            return ""; 
        }
        if((stop - start) % 3 == 0){
            return dna.substring(start,stop+3);
        }
        return ""; 
    }
    
    void testSimpleGene(){
        String dna = "AAGATGGHATAALAAABBNGG";
        String gene = findSimpleGene(dna); 
        System.out.println("The DNA is:" + dna); 
        System.out.println("The gene is:" + gene); 
        
        dna = "AAAGBBTAA";
        gene = findSimpleGene(dna); 
        System.out.println("The DNA is:" + dna); 
        System.out.println("The gene is:" + gene); 
        
        dna = "ATGUYTCFG";
        gene = findSimpleGene(dna); 
        System.out.println("The DNA is:" + dna); 
        System.out.println("The gene is:" + gene); 
        
        dna = "FTGUYTCFG";
        gene = findSimpleGene(dna); 
        System.out.println("The DNA is:" + dna); 
        System.out.println("The gene is:" + gene); 
        
        dna = "ATGFUTAA";
        gene = findSimpleGene(dna); 
        System.out.println("The DNA is:" + dna); 
        System.out.println("The gene is:" + gene); 
        
        dna = "ATGTAA";
        gene = findSimpleGene(dna); 
        System.out.println("The DNA is:" + dna); 
        System.out.println("The gene is:" + gene); 
        
        dna = "";
        gene = findSimpleGene(dna); 
        System.out.println("The DNA is:" + dna); 
        System.out.println("The gene is:" + gene); 
        
        dna = "TAAATG";
        gene = findSimpleGene(dna); 
        System.out.println("The DNA is:" + dna); 
        System.out.println("The gene is:" + gene); 
        
        dna = "TAAAGATGGCATAAGA";
        gene = findSimpleGene(dna); 
        System.out.println("The DNA is:" + dna); 
        System.out.println("The gene is:" + gene); 
    }
}
