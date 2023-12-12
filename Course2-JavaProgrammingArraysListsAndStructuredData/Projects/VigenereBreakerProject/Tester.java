import edu.duke.*;

public class Tester {
    // instance variables - replace the example below with your own
    public void testCaesarCipher() {
        CaesarCipher cc = new CaesarCipher(4);
        String message = "CHEESE";
        String encrypted = cc.encrypt(message);
        String decrypted = cc.decrypt(encrypted);
        System.out.println("The encrypted is: " + encrypted);
        System.out.println("The decrypted is: " + decrypted);
        char c = 'L';
        char encChar = cc.encryptLetter(c);
        char decrChar = cc.decryptLetter(encChar);
        System.out.println("The encChar is:" + encChar);
        System.out.println("The decrChar is:" + decrChar);
    }

    public void testCaesarCracker() {
        CaesarCracker ccEnglish = new CaesarCracker();
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        String decrypted = ccEnglish.decrypt(encrypted);
        System.out.println("The decrypted English word is: " + decrypted);
        CaesarCracker ccPortugese = new CaesarCracker('a');
        FileResource frPort = new FileResource();
        String encryptedPort = frPort.asString();
        String decryptedPort = ccPortugese.decrypt(encryptedPort);
        System.out.println("The decrypted Portugese word is: " + decryptedPort);
    }

    public void testVigenereCipher() {
        int[] key = new int[] { 17, 14, 12, 4 };
        FileResource fr = new FileResource();
        VigenereCipher vc = new VigenereCipher(key);
        String message = fr.asString();
        ;
        String encrypted = vc.encrypt(message);
        String decrypted = vc.decrypt(encrypted);
        System.out.println("The encrypted is: " + encrypted);
        System.out.println("The decrypted is: " + decrypted);
        System.out.println(vc);
    }

    public void testVigenereBreaker() {
        VigenereBreaker vb = new VigenereBreaker();

    }
}
