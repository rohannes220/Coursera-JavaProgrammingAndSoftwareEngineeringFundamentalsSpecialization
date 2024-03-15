import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    // k elements have been sorted at the end
    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int k) {
        int n = quakeData.size();
        // since last k elements are sorted the last two elements to be compared
        // are n-k-2 and n-k-1
        // i goes from 0 to n-k-2
        // in each iteration if elements at i and i +1 are in the wrong order they are
        // swapped
        for (int i = 0; i <= n - k - 2; i++) {
            double currentMag = quakeData.get(i).getMagnitude();
            double nextMag = quakeData.get(i + 1).getMagnitude();
            if (currentMag > nextMag) {
                swap(quakeData, i, i + 1);
            }
        }
    }

    private void printArrayList(ArrayList<QuakeEntry> in) {
        for (QuakeEntry qe : in) {
            System.out.println(qe);
        }
    }

    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in) {
        // printArrayList(in);
        int n = in.size();
        for (int i = 0; i < n - 1; i++) {
            onePassBubbleSort(in, i);
            // System.out.println("After Pass: " + (i+1));
            // printArrayList(in);
        }
    }

    private void swap(ArrayList<QuakeEntry> quakeData, int i, int j) {
        QuakeEntry quakeEntryi = quakeData.get(i);
        QuakeEntry quakeEntryj = quakeData.get(j);
        quakeData.set(i, quakeEntryj);
        quakeData.set(j, quakeEntryi);
    }

    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i = from + 1; i < quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }

    public void sortByMagnitude(ArrayList<QuakeEntry> in) {

        for (int i = 0; i < in.size(); i++) {
            int minIdx = getSmallestMagnitude(in, i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i, qmin);
            in.set(minIdx, qi);
        }

    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser();
        // String source =
        // "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/earthQuakeDataWeekDec6sample2.atom";
        // String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);

        System.out.println("read data for " + list.size() + " quakes");
        // sortbyLargestDepth(list);
        sortByMagnitudeWithBubbleSortWithCheck(list);
        // sortByMagnitudeWithCheck(list);
        // printArrayList(list);

    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        // String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        // String source =
        // "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for (QuakeEntry qe : list) {
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                    qe.getLocation().getLatitude(),
                    qe.getLocation().getLongitude(),
                    qe.getMagnitude(),
                    qe.getInfo());
        }

    }

    public int getLargestDepth(ArrayList<QuakeEntry> quakeData, int from) {
        int maxIndex = from;
        for (int i = from; i < quakeData.size(); i++) {
            double currentDepth = quakeData.get(i).getDepth();
            double maxDepth = quakeData.get(maxIndex).getDepth();
            if (currentDepth > maxDepth) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public void sortbyLargestDepth(ArrayList<QuakeEntry> in) {
        for (int i = 0; i < in.size(); i++) {
            int maxIndex = getLargestDepth(in, i);
            // swap quake entries at i and max index
            swap(in, maxIndex, i);
        }
    }

    private boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes) {
        int n = quakes.size();
        for (int i = 0; i < n - 1; i++) {
            double currentMag = quakes.get(i).getMagnitude();
            double nextMag = quakes.get(i + 1).getMagnitude();
            if (currentMag > nextMag) {
                return false;
            }
        }
        return true;
    }

    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in) {
        int n = in.size();
        int count = 0;
        for (int i = 0; i < n - 1; i++) {
            if (checkInSortedOrder(in) == true) {
                break;
            }
            onePassBubbleSort(in, i);
            count += 1;
        }
        System.out.println("Number of passes " + count);
    }

    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in) {
        int count = 0;
        for (int i = 0; i < in.size(); i++) {
            if (checkInSortedOrder(in)) {
                break;
            }
            int minIdx = getSmallestMagnitude(in, i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i, qmin);
            in.set(minIdx, qi);
            count += 1;
        }
        System.out.println("The number of pases for selection sort " + count);
    }
}
