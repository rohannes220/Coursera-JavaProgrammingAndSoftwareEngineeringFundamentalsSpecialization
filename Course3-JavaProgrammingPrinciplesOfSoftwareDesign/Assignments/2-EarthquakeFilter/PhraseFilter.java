public class PhraseFilter implements Filter {
    private String where;
    private String phrase;
    private String filterName;

    public PhraseFilter(String w, String p, String name) {
        phrase = p;
        where = w;
        filterName = name;
    }

    public boolean satisfies(QuakeEntry qe) {
        if (where.equals("start") && qe.getInfo().startsWith(phrase)) {
            return true;
        }
        if (where.equals("end") && qe.getInfo().endsWith(phrase)) {
            return true;
        }
        if (where.equals("any") && qe.getInfo().contains(phrase)) {
            return true;
        }
        return false;
    }

    public String getName() {
        return filterName;
    }
}
