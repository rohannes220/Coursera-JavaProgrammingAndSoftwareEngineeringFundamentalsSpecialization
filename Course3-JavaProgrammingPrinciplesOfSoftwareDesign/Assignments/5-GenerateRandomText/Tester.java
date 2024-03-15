import java.util.ArrayList;
import edu.duke.*;

public class Tester {
    public void testGetFollows() {
        MarkovOne mK1 = new MarkovOne();
        mK1.setTraining("this is a test yes this is a test.");
        ArrayList<String> answer1 = mK1.getFollows("es");
        System.out.println("Get follows of es");
        System.out.println("The size: " + answer1.size());
        System.out.println(answer1);

        System.out.println("Get follows of e");
        answer1 = mK1.getFollows("e");
        System.out.println("The size: " + answer1.size());
        System.out.println(answer1);

        System.out.println("Get follows of t");
        answer1 = mK1.getFollows("t");
        System.out.println("The size: " + answer1.size());
        System.out.println(answer1);
    }

    public void testGetFollowsWithFile() {
        MarkovOne mK2 = new MarkovOne();
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        mK2.setTraining(st);
        ArrayList<String> answer2 = mK2.getFollows("he");
        System.out.println("The size: " + answer2.size());
    }
}
