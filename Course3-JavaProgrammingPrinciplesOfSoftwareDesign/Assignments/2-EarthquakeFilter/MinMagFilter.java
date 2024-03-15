public class MinMagFilter implements Filter {
    private double magMin;
    private String filterName;

    public MinMagFilter(double min, String name) {
        magMin = min;
        filterName = name;
    }

    public boolean satisfies(QuakeEntry qe) {
        return qe.getMagnitude() >= magMin;
    }

    public String getName() {
        return filterName;
    }

}
