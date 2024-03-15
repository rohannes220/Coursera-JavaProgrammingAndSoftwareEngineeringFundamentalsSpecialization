import java.util.ArrayList;

public class LargestQuake {
    public void findLargestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        // String source =
        // "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size());

        ArrayList<QuakeEntry> answer = getLargest(list, 50);
        for (QuakeEntry qe : answer) {
            System.out.println(qe);
        }

    }

    private int indexOfLargest(ArrayList<QuakeEntry> data) {
        double bigMag = 0.0;
        int maxMagIndex = -1;
        for (int i = 0; i < data.size(); i++) {
            QuakeEntry qe = data.get(i);
            double currentMag = qe.getMagnitude();
            if (currentMag > bigMag) {
                bigMag = currentMag;
                maxMagIndex = i;
            }
        }
        return maxMagIndex;
    }

    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany) {
        ArrayList<QuakeEntry> largestQuakes = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        if (quakeData.size() < howMany) {
            howMany = quakeData.size();
        }
        for (int i = 0; i < howMany; i++) {
            int largeIndex = indexOfLargest(copy);
            QuakeEntry largestQuake = copy.get(largeIndex);
            largestQuakes.add(largestQuake);
            copy.remove(largeIndex);
        }
        return largestQuakes;
    }
}
