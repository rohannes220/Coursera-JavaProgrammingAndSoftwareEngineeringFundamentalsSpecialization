import java.util.Comparator;

public class TitleAndDepthComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        String title1 = q1.getInfo();
        String title2 = q2.getInfo();

        if (title1.compareTo(title2) < 0) {
            return -1;
        }
        if (title1.compareTo(title2) > 0) {
            return 1;
        }
        // title1 and title2 are the same
        if (q1.getDepth() < q2.getDepth()) {
            return -1;
        }
        if (q1.getDepth() > q2.getDepth()) {
            return 1;
        }
        return 0;
    }
}
