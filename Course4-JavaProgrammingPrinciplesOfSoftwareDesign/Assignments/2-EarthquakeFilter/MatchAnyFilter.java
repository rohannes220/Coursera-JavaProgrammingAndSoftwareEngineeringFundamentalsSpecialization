import java.util.*;

public class MatchAnyFilter implements Filter {
    ArrayList<Filter> filters;

    public MatchAnyFilter() {
        filters = new ArrayList<Filter>();
    }

    public void addFilter(Filter f) {
        filters.add(f);
    }

    public boolean satisfies(QuakeEntry qe) {
        for (Filter f : filters) {
            if (f.satisfies(qe)) {
                return true;
            }
        }
        return false;
    }

    public String getName() {
        return "Match Any Filter";
    }
}
