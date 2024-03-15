import java.util.*;

public class MatchAllFilter implements Filter {
    private ArrayList<Filter> filtersList;

    public MatchAllFilter() {
        filtersList = new ArrayList<Filter>();
    }

    public void addFilter(Filter f) {
        filtersList.add(f);
    }

    public boolean satisfies(QuakeEntry qe) {
        for (Filter f : filtersList) {
            if (!f.satisfies(qe)) {
                return false;
            }
        }
        return true;
    }

    public String getName() {
        StringBuilder names = new StringBuilder();
        for (Filter f : filtersList) {
            names.append(f.getName());
            names.append(' ');
        }
        return names.toString();
    }
}
