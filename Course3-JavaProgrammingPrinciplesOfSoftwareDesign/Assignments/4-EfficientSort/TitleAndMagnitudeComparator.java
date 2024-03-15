import java.util.Comparator;

public class TitleAndMagnitudeComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        String title1 = q1.getInfo();
        String title2 = q2.getInfo();
        String lastWord1 = lastWord(title1);
        String lastWord2 = lastWord(title2);
        if (lastWord1.compareTo(lastWord2) < 0) {
            return -1;
        }
        if (lastWord1.compareTo(lastWord2) > 0) {
            return 1;
        }
        if (q1.getMagnitude() < q2.getMagnitude()) {
            return -1;
        }
        if (q1.getMagnitude() > q2.getMagnitude()) {
            return 1;
        }
        return 0;
    }

    private String lastWord(String sentence) {
        // I eat cheese stick
        int lastSpaceIndex = sentence.lastIndexOf(' ');
        if (lastSpaceIndex == -1) {
            return sentence;
        }
        return sentence.substring(lastSpaceIndex + 1, sentence.length());
    }
}
