import java.util.*;
import edu.duke.*;

public class VignereBreakerTester {
    public void testSliceString() {
        VigenereBreaker vb = new VigenereBreaker();
        System.out.println("Expected: 'adgjm'," + "Actual: '" + vb.sliceString("abcdefghijklm", 0, 3) + "'");
        System.out.println("Expected: 'behk'," + "Actual: '" + vb.sliceString("abcdefghijklm", 1, 3) + "'");
        System.out.println("Expected: 'cfil'," + "Actual: '" + vb.sliceString("abcdefghijklm", 2, 3) + "'");
        System.out.println("Expected: 'aeim'," + "Actual: '" + vb.sliceString("abcdefghijklm", 0, 4) + "'");
        System.out.println("Expected: 'bfj'," + "Actual: '" + vb.sliceString("abcdefghijklm", 1, 4) + "'");
        System.out.println("Expected: 'cgk'," + "Actual: '" + vb.sliceString("abcdefghijklm", 2, 4) + "'");
        System.out.println("Expected: 'dhl'," + "Actual: '" + vb.sliceString("abcdefghijklm", 3, 4) + "'");
        System.out.println("Expected: 'afk'," + "Actual: '" + vb.sliceString("abcdefghijklm", 0, 5) + "'");
        System.out.println("Expected: 'bgl'," + "Actual: '" + vb.sliceString("abcdefghijklm", 1, 5) + "'");
        System.out.println("Expected: 'chm'," + "Actual: '" + vb.sliceString("abcdefghijklm", 2, 5) + "'");
        System.out.println("Expected: 'di'," + "Actual: '" + vb.sliceString("abcdefghijklm", 3, 5) + "'");
        System.out.println("Expected: 'ej'," + "Actual: '" + vb.sliceString("abcdefghijklm", 4, 5) + "'");
    }

    public void testTryKeyLength() {
        VigenereBreaker vb = new VigenereBreaker();
        FileResource fr = new FileResource("data/secretmessage1.txt");
        String encrypted = fr.asString();
        int[] answer = vb.tryKeyLength(encrypted, 4, 'e');
        System.out.println(Arrays.toString(answer));
    }
}
