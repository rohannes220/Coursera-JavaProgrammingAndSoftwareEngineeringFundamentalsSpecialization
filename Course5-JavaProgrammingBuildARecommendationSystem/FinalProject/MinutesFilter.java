public class MinutesFilter implements Filter {
    // instance variables - replace the example below with your own
    private int myMinutesMin;
    private int myMinutesMax;

    public MinutesFilter(int minMinutes, int maxMinutes) {
        myMinutesMin = minMinutes;
        myMinutesMax = maxMinutes;
    }

    @Override
    public boolean satisfies(String id) {
        int currentMinutes = MovieDatabase.getMinutes(id);
        return currentMinutes >= myMinutesMin && currentMinutes <= myMinutesMax;
    }
}
