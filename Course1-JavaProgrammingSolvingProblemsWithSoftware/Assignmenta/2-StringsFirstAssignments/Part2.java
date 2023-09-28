
/**
 * Write a description of class Part2 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Part2
{
    // instance variables - replace the example below with your own
    String findSimpleGene(String dna, String startCodon, String stopCodon){
        if(dna.length() == 0){
            return ""; 
        }
        char c = dna.charAt(0);
        if(c>='a' && c <= 'z') {
           startCodon = startCodon.toLowerCase(); 
           stopCodon = stopCodon.toLowerCase(); 
        }
        int start = dna.indexOf(startCodon);
        if(start < 0){
            return ""; 
        }
        int stop = dna.indexOf(stopCodon,start);
        if(stop < 0){
            return ""; 
        }
        if((stop - start) % 3 == 0){
            return dna.substring(start,stop+3);
        }
        return ""; 
    }
    void testSimpleGene(){
        // Valid gene case
        String dna = "AAGATGGHATAALAAABBNGG";
        String startCodon = "ATG";
        String stopCodon = "TAA"; 
        String gene = findSimpleGene(dna,startCodon,stopCodon); 
        System.out.println("The DNA is:" + dna); 
        System.out.println("The gene is:" + gene); 
        
        dna = "AAAGBBTAA"; 
        gene = findSimpleGene(dna,startCodon,stopCodon); 
        System.out.println("The DNA is:" + dna); 
        System.out.println("The gene is:" + gene); 
        
        dna = "ATGUYTCFG";
        gene = findSimpleGene(dna,startCodon,stopCodon); 
        System.out.println("The DNA is:" + dna); 
        System.out.println("The gene is:" + gene); 
        
        dna = "FTGUYTCFG";
        gene = findSimpleGene(dna,startCodon, stopCodon); 
        System.out.println("The DNA is:" + dna); 
        System.out.println("The gene is:" + gene); 
        
        dna = "ATGFUTAA";
        gene = findSimpleGene(dna,startCodon, stopCodon); 
        System.out.println("The DNA is:" + dna); 
        System.out.println("The gene is:" + gene); 
        
        dna = "ATGTAA";
        gene = findSimpleGene(dna,startCodon, stopCodon); 
        System.out.println("The DNA is:" + dna); 
        System.out.println("The gene is:" + gene); 
        
        dna = "";
        gene = findSimpleGene(dna,startCodon, stopCodon); 
        System.out.println("The DNA is:" + dna); 
        System.out.println("The gene is:" + gene); 
        
        dna = "TAAATG";
        gene = findSimpleGene(dna,startCodon, stopCodon); 
        System.out.println("The DNA is:" + dna); 
        System.out.println("The gene is:" + gene); 
        
        dna = "TAAAGATGGCATAAGA";
        gene = findSimpleGene(dna,startCodon, stopCodon); 
        System.out.println("The DNA is:" + dna); 
        System.out.println("The gene is:" + gene); 
        
        //LOWERCASE DNA WITH NO ATG
        dna = "lolhaataa"; 
        gene = findSimpleGene(dna,startCodon, stopCodon); 
        System.out.println("The DNA is:" + dna); 
        System.out.println("The gene is:" + gene); 
        
        //LOWERCASE DNA WITH NO TAA
        dna = "atgghtyui";
        gene = findSimpleGene(dna,startCodon, stopCodon); 
        System.out.println("The DNA is:" + dna); 
        System.out.println("The gene is:" + gene); 
        
        //LOWERCASE VALID 
        dna = "ahhlolatgroflmataa";
        gene = findSimpleGene(dna,startCodon, stopCodon); 
        System.out.println("The DNA is:" + dna); 
        System.out.println("The gene is:" + gene); 
        
    }
}
