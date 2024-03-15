import java.util.*;

public class MovieRunnerSimilarRatings {
    public void printAverageRatingsByYearAfterAndGenre() {
        String movieFileName = "ratedmovies.csv";
        String ratingsFileName = "ratings.csv";
        String genre = "Drama";
        int year = 1990;
        int minimalRaters = 8;
        GenreFilter g1 = new GenreFilter(genre);
        YearAfterFilter yf1 = new YearAfterFilter(year);
        AllFilters af1 = new AllFilters();
        af1.addFilter(g1);
        af1.addFilter(yf1);
        FourthRatings fr1 = new FourthRatings();
        MovieDatabase.initialize(movieFileName);
        RaterDatabase.initialize(ratingsFileName);
        System.out.println("The number of raters is: " + RaterDatabase.size());
        System.out.println("The number of movies in the database is: " + MovieDatabase.size());
        ArrayList<Rating> r1 = fr1.getAverageRatingsByFilter(minimalRaters, af1);
        Collections.sort(r1);
        System.out.println("The number of items is: " + r1.size());
        for (Rating rating : r1) {
            String movieName = MovieDatabase.getTitle(rating.getItem());
            String movieDirectors = MovieDatabase.getDirector(rating.getItem());
            int movieYear = MovieDatabase.getYear(rating.getItem());
            double ratingValue = rating.getValue();
            System.out.println(ratingValue + " " + movieName + " " + movieYear);
            System.out.println("\t" + MovieDatabase.getGenres(rating.getItem()));
        }
    }

    public void printAverageRatings() {
        String movieFileName = "ratedmovies_short.csv";
        String ratingsFileName = "ratings_short.csv";
        FourthRatings fr1 = new FourthRatings();
        RaterDatabase.initialize(ratingsFileName);
        int raterSize = RaterDatabase.size();
        System.out.println("The number of raters is: " + raterSize);
        MovieDatabase.initialize(movieFileName);
        System.out.println("The number of movies in the database is: " + MovieDatabase.size());
        int minimalRatings = 2;
        ArrayList<Rating> answer = fr1.getAverageRatings(minimalRatings);
        System.out.println(answer.size());
        Collections.sort(answer);
        for (Rating rating : answer) {
            System.out.print(rating.getValue());
            String currentMovieID = rating.getItem();
            String movieName = MovieDatabase.getTitle(currentMovieID);
            System.out.println(" " + movieName);
        }
    }

    public void printSimilarRatings() {
        String movieFileName = "ratedmoviesfull.csv";
        String ratingsFileName = "ratings.csv";
        FourthRatings fr1 = new FourthRatings();
        RaterDatabase.initialize(ratingsFileName);
        int raterSize = RaterDatabase.size();
        System.out.println("The number of raters is: " + raterSize);
        MovieDatabase.initialize(movieFileName);
        System.out.println("The number of movies in the database is: " + MovieDatabase.size());

        String raterID = "65";
        int minimalRaters = 5;
        int numSimilarRaters = 20;
        ArrayList<Rating> answer = fr1.getSimilarRatings(raterID, numSimilarRaters, minimalRaters);
        for (Rating rating : answer) {
            String movieID = rating.getItem();
            double weightedRating = rating.getValue();
            System.out.println(MovieDatabase.getTitle(movieID));
            // System.out.println("The weighted average is " + weightedRating);
        }
    }

    public void printSimilarRatingsByGenre() {
        String movieFileName = "ratedmoviesfull.csv";
        String ratingsFileName = "ratings.csv";
        FourthRatings fr1 = new FourthRatings();
        RaterDatabase.initialize(ratingsFileName);
        int raterSize = RaterDatabase.size();
        System.out.println("The number of raters is: " + raterSize);
        MovieDatabase.initialize(movieFileName);
        System.out.println("The number of movies in the database is: " + MovieDatabase.size());

        String raterID = "964";
        int minimalRaters = 5;
        int numSimilarRaters = 20;
        GenreFilter g = new GenreFilter("Mystery");
        ArrayList<Rating> answer = fr1.getSimilarRatingsByFilter(raterID, numSimilarRaters, minimalRaters, g);
        for (Rating rating : answer) {
            String movieID = rating.getItem();
            double weightedRating = rating.getValue();
            System.out.println(MovieDatabase.getTitle(movieID));
            // System.out.println("The weighted average is " + weightedRating);
        }
    }

    public void printSimilarRatingsByDirector() {
        String movieFileName = "ratedmoviesfull.csv";
        String ratingsFileName = "ratings.csv";
        FourthRatings fr1 = new FourthRatings();
        RaterDatabase.initialize(ratingsFileName);
        int raterSize = RaterDatabase.size();
        System.out.println("The number of raters is: " + raterSize);
        MovieDatabase.initialize(movieFileName);
        System.out.println("The number of movies in the database is: " + MovieDatabase.size());

        String raterID = "120";
        int minimalRaters = 2;
        int numSimilarRaters = 10;
        DirectorsFilter g = new DirectorsFilter(
                "Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh");
        ArrayList<Rating> answer = fr1.getSimilarRatingsByFilter(raterID, numSimilarRaters, minimalRaters, g);
        for (Rating rating : answer) {
            String movieID = rating.getItem();
            double weightedRating = rating.getValue();
            System.out.println(MovieDatabase.getTitle(movieID));
            // System.out.println("The weighted average is " + weightedRating);
        }
    }

    public void printSimilarRatingsByGenreAndMinutes() {
        String movieFileName = "ratedmoviesfull.csv";
        String ratingsFileName = "ratings.csv";
        FourthRatings fr1 = new FourthRatings();
        RaterDatabase.initialize(ratingsFileName);
        int raterSize = RaterDatabase.size();
        System.out.println("The number of raters is: " + raterSize);
        MovieDatabase.initialize(movieFileName);
        System.out.println("The number of movies in the database is: " + MovieDatabase.size());

        String raterID = "168";
        int minimalRaters = 3;
        int numSimilarRaters = 10;
        GenreFilter g = new GenreFilter("Drama");
        MinutesFilter m = new MinutesFilter(80, 160);
        AllFilters a = new AllFilters();
        a.addFilter(m);
        a.addFilter(g);
        ArrayList<Rating> answer = fr1.getSimilarRatingsByFilter(raterID, numSimilarRaters, minimalRaters, a);
        for (Rating rating : answer) {
            String movieID = rating.getItem();
            double weightedRating = rating.getValue();
            System.out.println(MovieDatabase.getTitle(movieID));
            // System.out.println("The weighted average is " + weightedRating);
        }
    }

    public void printSimilarRatingsByYearAfterAndMinutes() {
        String movieFileName = "ratedmoviesfull.csv";
        String ratingsFileName = "ratings.csv";
        FourthRatings fr1 = new FourthRatings();
        RaterDatabase.initialize(ratingsFileName);
        int raterSize = RaterDatabase.size();
        System.out.println("The number of raters is: " + raterSize);
        MovieDatabase.initialize(movieFileName);
        System.out.println("The number of movies in the database is: " + MovieDatabase.size());

        String raterID = "314";
        int minimalRaters = 5;
        int numSimilarRaters = 10;
        YearAfterFilter g = new YearAfterFilter(1975);
        MinutesFilter m = new MinutesFilter(70, 200);
        AllFilters a = new AllFilters();
        a.addFilter(m);
        a.addFilter(g);
        ArrayList<Rating> answer = fr1.getSimilarRatingsByFilter(raterID, numSimilarRaters, minimalRaters, a);
        for (Rating rating : answer) {
            String movieID = rating.getItem();
            double weightedRating = rating.getValue();
            System.out.println(MovieDatabase.getTitle(movieID));
            // System.out.println("The weighted average is " + weightedRating);
        }
    }
}
