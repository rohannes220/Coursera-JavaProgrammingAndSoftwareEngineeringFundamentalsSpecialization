import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : quakeData) {
            if (f.satisfies(qe)) {
                answer.add(qe);
            }
        }

        return answer;
    }

    public void quakesWithFilter() {
        EarthQuakeParser parser = new EarthQuakeParser();
        // String source =
        // "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");

        // Filter f1 = new MagnitudeFilter(3.5,4.5,"Magnitude");
        // Filter f2 = new DepthFilter(-55000.0,-20000.0,"Depth");
        Location Denver = new Location(39.7392, -104.9903);
        Filter f1 = new DistanceFilter(Denver, 1000000, "Distance");
        Filter f2 = new PhraseFilter("end", "a", "Phrase");
        ArrayList<QuakeEntry> r1 = filter(list, f1);
        ArrayList<QuakeEntry> r2 = filter(r1, f2);
        for (QuakeEntry qe : r2) {
            System.out.println(qe);
        }
        System.out.print(r2.size());
    }

    public void testMatchAllFilter() {
        EarthQuakeParser parser = new EarthQuakeParser();
        // String source =
        // "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");

        MatchAllFilter maf = new MatchAllFilter();
        Filter f1 = new MagnitudeFilter(1.0, 4.0, "Magnitude");
        Filter f2 = new DepthFilter(-180000.0, -30000.0, "Depth");
        Filter f3 = new PhraseFilter("any", "o", "Phrase");
        maf.addFilter(f1);
        maf.addFilter(f2);
        maf.addFilter(f3);
        ArrayList<QuakeEntry> result = filter(list, maf);
        for (QuakeEntry qe : result) {
            System.out.println(qe);
        }
        System.out.println("Filters used are: " + maf.getName());
        System.out.println(result.size());
    }

    public void testMatchAllFilter2() {
        EarthQuakeParser parser = new EarthQuakeParser();
        // String source =
        // "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");

        MatchAllFilter maf = new MatchAllFilter();
        Filter f1 = new MagnitudeFilter(0.0, 5.0, "Magnitude");
        Location denmark = new Location(55.7308, 9.1153);
        Filter f2 = new DistanceFilter(denmark, 3000000, "Distance");
        Filter f3 = new PhraseFilter("any", "e", "Phrase");
        maf.addFilter(f1);
        maf.addFilter(f2);
        maf.addFilter(f3);
        ArrayList<QuakeEntry> result = filter(list, maf);
        for (QuakeEntry qe : result) {
            System.out.println(qe);
        }
        System.out.println("Filters used are: " + maf.getName());
        System.out.println(result.size());
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        // String source = "../data/nov20quakedata.atom";
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

    public void matchAnyFilter() {
        EarthQuakeParser parser = new EarthQuakeParser();
        // String source =
        // "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");
        MatchAnyFilter maf = new MatchAnyFilter();
        Filter f1 = new MagnitudeFilter(0.0, 3.0, "Magnitude");
        Location tulsa = new Location(36.1314, -95.9372);
        Filter f2 = new DistanceFilter(tulsa, 10000000, "Distance");
        Filter f3 = new PhraseFilter("any", "Ca", "Phrase");
        maf.addFilter(f1);
        maf.addFilter(f2);
        maf.addFilter(f3);
        ArrayList<QuakeEntry> result = filter(list, maf);
        for (QuakeEntry qe : result) {
            System.out.println(qe);
        }
        System.out.println("Filters used are: " + maf.getName());
    }

}
