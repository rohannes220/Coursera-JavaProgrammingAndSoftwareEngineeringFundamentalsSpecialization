import java.util.ArrayList;
import java.util.Collections;

public class RecommendationRunner implements Recommender
{
    public ArrayList<String> getItemsToRate() {
        GenreFilter g = new GenreFilter("Action");
        int numMovies = 15;
        ArrayList<String> actionMovies = MovieDatabase.filterBy(g);
        Collections.shuffle(actionMovies);
        ArrayList<String> items = new ArrayList<String>();
        for (int i = 0; i < numMovies; i++) {
            items.add(actionMovies.get(i));
        }
        return items;
    }
    
    public void printRecommendationsFor(String webRaterId) {
        // Load movies and raters
        String movieFileName = "ratedmoviesfull.csv";
        String ratingsFileName = "ratings.csv"; 
        MovieDatabase.initialize(movieFileName);
        RaterDatabase.initialize(ratingsFileName);
        
        int numMovies = 20;
        int numSimilarRaters = 20;
        int minimalRaters = 5;
        
        FourthRatings fr = new FourthRatings();
        ArrayList<Rating> similarRatings = fr.getSimilarRatings(webRaterId, numSimilarRaters, minimalRaters);
        
        if (similarRatings.isEmpty()) {
            System.out.println("<strong>Not enough movies rated</strong>");
            return;
        }
        
        System.out.println("<style>\ntable, th, td {\nborder: 1px solid black;\nborder-collapse: collapse;\n}\n</style>");
        System.out.println("<table>");
        System.out.println("<tr><th>Movie</th><th>Poster</th><th>Director</th><th>Genres</th></tr>");
        int count = 0;
        for(Rating r: similarRatings) {
            count += 1;
            if (count > numMovies) {
                break;
            }
            
            String movieId = r.getItem();
            String title = MovieDatabase.getTitle(movieId);
            String poster = MovieDatabase.getPoster(movieId);
            String director = MovieDatabase.getDirector(movieId);
            String genres = MovieDatabase.getGenres(movieId);
            
            System.out.println("<tr>");
            System.out.println("<td>" + title + "</td>");
            System.out.println("<td><img src=\"" + poster + "\" width=\"50\" ></td>");
            System.out.println("<td>" + director + "</td>");
            System.out.println("<td>" + genres + "</td>");
            System.out.println("</tr>");
        }
        System.out.println("</table>");
    }
}
