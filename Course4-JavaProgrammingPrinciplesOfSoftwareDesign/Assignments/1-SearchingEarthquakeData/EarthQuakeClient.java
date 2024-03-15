import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
            double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : quakeData) {
            double currentMag = qe.getMagnitude();
            if (currentMag > magMin) {
                answer.add(qe);
            }
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
            double distMax, Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : quakeData) {
            Location currentLocation = qe.getLocation();
            double currentDistance = currentLocation.distanceTo(from);
            if (currentDistance < distMax) {
                answer.add(qe);
            }
        }
        return answer;
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

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        // String source =
        // "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");
        ArrayList<QuakeEntry> filteredMags = filterByMagnitude(list, 5.0);
        dumpCSV(filteredMags);
        System.out.println("Found " + filteredMags.size() + " quakes that match that criteria");
    }

    public void closeToMe() {
        EarthQuakeParser parser = new EarthQuakeParser();
        // String source =
        // "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");

        // This location is Durham, NC
        // Location city = new Location(35.988, -78.907);
        // This location is Bridgeport, CA
        Location city = new Location(38.17, -118.82);

        // TODO
        ArrayList<QuakeEntry> answer = filterByDistanceFrom(list, 1000000, city);
        dumpCSV(answer);
        System.out.println("Found " + answer.size() + " quakes that match that criteria");
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        // String source =
        // "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }

    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth) {
        ArrayList<QuakeEntry> filteredQuakes = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : quakeData) {
            double currentDepth = qe.getDepth();
            if (currentDepth > minDepth && currentDepth < maxDepth) {
                filteredQuakes.add(qe);
            }
        }
        return filteredQuakes;
    }

    public void quakesOfDepth() {
        EarthQuakeParser parser = new EarthQuakeParser();
        // String source =
        // "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");

        ArrayList<QuakeEntry> filteredQuakes = filterByDepth(list, -4000.0, -2000.0);
        dumpCSV(filteredQuakes);
        System.out.println("Found " + filteredQuakes.size() + " quakes that match that criteria");
    }

    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String where, String phrase) {
        ArrayList<QuakeEntry> phrasedQuakes = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : quakeData) {
            String currentTitle = qe.getInfo();
            int currentSize = currentTitle.length();
            if (where.equals("start")) {
                boolean startsWith = currentTitle.startsWith(phrase);
                if (startsWith) {
                    phrasedQuakes.add(qe);
                }
            }
            if (where.equals("end")) {
                boolean endsWith = currentTitle.endsWith(phrase);
                if (endsWith) {
                    phrasedQuakes.add(qe);
                }
            }
            if (where.equals("any")) {
                boolean contains = currentTitle.contains(phrase);
                if (contains) {
                    phrasedQuakes.add(qe);
                }
            }
        }
        return phrasedQuakes;
    }

    public void quakesByPhrase() {
        EarthQuakeParser parser = new EarthQuakeParser();
        // String source =
        // "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");

        ArrayList<QuakeEntry> filteredQuakes = filterByPhrase(list, "any", "Can");
        dumpCSV(filteredQuakes);
        System.out.println("Found " + filteredQuakes.size() + " quakes that match that criteria");
    }
}
