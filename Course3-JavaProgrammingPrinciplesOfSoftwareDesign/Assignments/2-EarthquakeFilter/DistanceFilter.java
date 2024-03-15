public class DistanceFilter implements Filter {
    private Location location;
    private double maxDistance;
    private String filterName;

    public DistanceFilter(Location loc, double dist, String name) {
        location = loc;
        maxDistance = dist;
        filterName = name;
    }

    public boolean satisfies(QuakeEntry qe) {
        return qe.getLocation().distanceTo(location) < maxDistance;
    }

    public String getName() {
        return filterName;
    }
}
