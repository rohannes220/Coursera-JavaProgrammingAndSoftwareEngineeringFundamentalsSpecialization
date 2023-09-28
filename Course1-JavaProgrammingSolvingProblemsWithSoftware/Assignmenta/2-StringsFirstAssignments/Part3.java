
/**
 * Write a description of class Part3 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Part3
{
    boolean twoOccurrences(String a, String b){
        int firstIndex = b.indexOf(a); 
        if(firstIndex == -1){
            return false; 
        }
        int secondIndex = b.indexOf(a, firstIndex+1); 
        if(secondIndex == -1){
            return false; 
        }
        return true; 
    }
    
    String lastPart(String c, String d){
        int firstIndex = d.indexOf(c); 
        if(firstIndex == -1){
            return d; 
        }
        return d.substring(firstIndex+c.length(),d.length()); 
    }
    void testtwoOccurences(){
        
        String a = "by";
        String b = "A story by Abby Long"; 
        boolean result = twoOccurrences(a,b); 
        System.out.println("\na:" + a);
        System.out.println("b:" + b); 
        System.out.println("Does Occur Twice:" + result); 
        System.out.println("Expected result: true");
        
        //A occurs in B once
        a = "by";
        b = "A story by Abe Long"; 
        result = twoOccurrences(a,b); 
        System.out.println("\na:" + a);
        System.out.println("b:" + b); 
        System.out.println("Does Occur Twice:" + result);
        System.out.println("Expected result: false");
        
        //A and B are the same
        a = "by";
        b = "by"; 
        result = twoOccurrences(a,b); 
        System.out.println("\na:" + a);
        System.out.println("b:" + b); 
        System.out.println("Does Occur Twice:" + result);
        System.out.println("Expected result: false");
        
        //A in B three times
        a = "shells";
        b = "She shells shells seashells by the seashore"; 
        result = twoOccurrences(a,b); 
        System.out.println("\na:" + a);
        System.out.println("b:" + b); 
        System.out.println("Does Occur Twice:" + result);
        System.out.println("Expected result: true");
        
        //A in B doesn't exist
        a = "by";
        b = "A story Abe Long"; 
        result = twoOccurrences(a,b); 
        System.out.println("\na:" + a);
        System.out.println("b:" + b); 
        System.out.println("Does Occur Twice:" + result);
        System.out.println("Expected result: false");
        
        //Length a is greater than b
        a = "by";
        b = "A"; 
        result = twoOccurrences(a,b); 
        System.out.println("\na:" + a);
        System.out.println("b:" + b); 
        System.out.println("Does Occur Twice:" + result);
        System.out.println("Expected result: false");
    }
    
    void test(){
        //an and banana
        String c = "an";
        String d = "banana"; 
        String lastHalf = lastPart(c,d);
        System.out.println("The part of the string after '" + c + "' in '" + d + "' is '" + lastHalf + "'"); 
        
        c = "Roh";
        d = "Rohan"; 
        lastHalf = lastPart(c,d);
        System.out.println("The part of the string after '" + c + "' in '" + d + "' is '" + lastHalf + "'"); 
        
        c = "volu";
        d = "revolution"; 
        lastHalf = lastPart(c,d);
        System.out.println("The part of the string after '" + c + "' in '" + d + "' is '" + lastHalf + "'"); 
        
        //zoo and forst
        c = "zoo";
        d = "forest"; 
        lastHalf = lastPart(c,d);
        System.out.println("The part of the string after '" + c + "' in '" + d + "' is '" + lastHalf + "'"); 
       
       //sea and sea
       c = "sea";
        d = "sea"; 
        lastHalf = lastPart(c,d);
        System.out.println("The part of the string after '" + c + "' in '" + d + "' is '" + lastHalf + "'"); 
       
       c = "forest";
        d = "zoo"; 
        lastHalf = lastPart(c,d);
        System.out.println("The part of the string after '" + c + "' in '" + d + "' is '" + lastHalf + "'"); 
       
       
       
       
    }
}
