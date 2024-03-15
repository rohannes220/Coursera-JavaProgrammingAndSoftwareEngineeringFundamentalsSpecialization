public class MagnitudeFilter implements Filter {
    private double minMag;
    private double maxMag;
    private String filterName;

    /**
     * Constructor for objects of class MagnitudeFilter
     */
    public MagnitudeFilter(double min, double max, String name) {
        // initialise instance variables
        minMag = min;
        maxMag = max;
        filterName = name;
    }

    public boolean satisfies(QuakeEntry qe) {
        if (qe.getMagnitude() >= minMag && qe.getMagnitude() <= maxMag) {
            return true;
        }
        return false;
    }

    public String getName() {
        return filterName;
    }
}
