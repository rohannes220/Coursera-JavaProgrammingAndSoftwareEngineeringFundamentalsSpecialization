import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerWithFilters {
    public void printAverageRatings() {
        String movieFileName = "ratedmoviesfull.csv";
        String ratingsFileName = "ratings.csv";
        ThirdRatings tr1 = new ThirdRatings(ratingsFileName);
        int raterSize = tr1.getRaterSize();
        System.out.println("The number of raters is: " + raterSize);
        MovieDatabase.initialize(movieFileName);
        System.out.println("The number of movies in the database is: " + MovieDatabase.size());
        int minimalRatings = 35;
        ArrayList<Rating> answer = tr1.getAverageRatings(minimalRatings);
        System.out.println(answer.size());
        Collections.sort(answer);
        for (Rating rating : answer) {
            System.out.print(rating.getValue());
            String currentMovieID = rating.getItem();
            String movieName = MovieDatabase.getTitle(currentMovieID);
            System.out.println(" " + movieName);
        }
    }

    public void printAverageRatingsByYear() {
        String movieFileName = "ratedmoviesfull.csv";
        String ratingsFileName = "ratings.csv";
        int year = 2000;
        int minimalRaters = 20;
        YearAfterFilter yf1 = new YearAfterFilter(year);
        ThirdRatings tr1 = new ThirdRatings(ratingsFileName);
        MovieDatabase.initialize(movieFileName);
        System.out.println("The number of raters is: " + tr1.getRaterSize());
        System.out.println("The number of movies in the database is: " + MovieDatabase.size());
        ArrayList<Rating> r1 = tr1.getAverageRatingsByFilter(minimalRaters, yf1);
        Collections.sort(r1);
        System.out.println("The number of items is: " + r1.size());
        for (Rating rating : r1) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getYear(rating.getItem()) + " "
                    + MovieDatabase.getTitle(rating.getItem()));
        }
    }

    public void printAverageRatingsByGenre() {
        String movieFileName = "ratedmoviesfull.csv";
        String ratingsFileName = "ratings.csv";
        String genre = "Comedy";
        int minimalRaters = 20;
        GenreFilter g1 = new GenreFilter(genre);
        ThirdRatings tr1 = new ThirdRatings(ratingsFileName);
        MovieDatabase.initialize(movieFileName);
        System.out.println("The number of raters is: " + tr1.getRaterSize());
        System.out.println("The number of movies in the database is: " + MovieDatabase.size());
        ArrayList<Rating> r1 = tr1.getAverageRatingsByFilter(minimalRaters, g1);
        Collections.sort(r1);
        System.out.println("The number of items is: " + r1.size());
        for (Rating rating : r1) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
            System.out.println("\t" + MovieDatabase.getGenres(rating.getItem()));
        }
    }

    public void printAverageRatingsByMinutes() {
        String movieFileName = "ratedmoviesfull.csv";
        String ratingsFileName = "ratings.csv";
        int minMinutes = 105;
        int maxMinutes = 135;
        int minimalRaters = 5;
        MinutesFilter m1 = new MinutesFilter(minMinutes, maxMinutes);
        ThirdRatings tr1 = new ThirdRatings(ratingsFileName);
        MovieDatabase.initialize(movieFileName);
        System.out.println("The number of raters is: " + tr1.getRaterSize());
        System.out.println("The number of movies in the database is: " + MovieDatabase.size());
        ArrayList<Rating> r1 = tr1.getAverageRatingsByFilter(minimalRaters, m1);
        Collections.sort(r1);
        System.out.println("The number of items is: " + r1.size());
        for (Rating rating : r1) {
            String movieName = MovieDatabase.getTitle(rating.getItem());
            int minutes = MovieDatabase.getMinutes(rating.getItem());
            double ratingValue = rating.getValue();
            System.out.println(ratingValue + " Time: " + minutes + " " + movieName);
        }
    }

    public void printAverageRatingsByDirectors() {
        String movieFileName = "ratedmoviesfull.csv";
        String ratingsFileName = "ratings.csv";
        String directors = "Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack";
        int minimalRaters = 4;
        DirectorsFilter d1 = new DirectorsFilter(directors);
        ThirdRatings tr1 = new ThirdRatings(ratingsFileName);
        MovieDatabase.initialize(movieFileName);
        System.out.println("The number of raters is: " + tr1.getRaterSize());
        System.out.println("The number of movies in the database is: " + MovieDatabase.size());
        ArrayList<Rating> r1 = tr1.getAverageRatingsByFilter(minimalRaters, d1);
        Collections.sort(r1);
        System.out.println("The number of items is: " + r1.size());
        for (Rating rating : r1) {
            String movieName = MovieDatabase.getTitle(rating.getItem());
            String movieDirectors = MovieDatabase.getDirector(rating.getItem());
            double ratingValue = rating.getValue();
            System.out.println(ratingValue + " " + movieName);
            System.out.println("\t" + movieDirectors);
        }
    }

    public void printAverageRatingsByYearAfterAndGenre() {
        String movieFileName = "ratedmoviesfull.csv";
        String ratingsFileName = "ratings.csv";
        String genre = "Drama";
        int year = 1990;
        int minimalRaters = 8;
        GenreFilter g1 = new GenreFilter(genre);
        YearAfterFilter yf1 = new YearAfterFilter(year);
        AllFilters af1 = new AllFilters();
        af1.addFilter(g1);
        af1.addFilter(yf1);
        ThirdRatings tr1 = new ThirdRatings(ratingsFileName);
        MovieDatabase.initialize(movieFileName);
        System.out.println("The number of raters is: " + tr1.getRaterSize());
        System.out.println("The number of movies in the database is: " + MovieDatabase.size());
        ArrayList<Rating> r1 = tr1.getAverageRatingsByFilter(minimalRaters, af1);
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

    public void printAverageRatingsByDirectorsAndMinutes() {
        String movieFileName = "ratedmoviesfull.csv";
        String ratingsFileName = "ratings.csv";
        String directors = "Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack";
        int minMinutes = 90;
        int maxMinutes = 180;
        int minimalRaters = 3;
        MinutesFilter m1 = new MinutesFilter(minMinutes, maxMinutes);
        DirectorsFilter yf1 = new DirectorsFilter(directors);
        AllFilters af1 = new AllFilters();
        af1.addFilter(m1);
        af1.addFilter(yf1);
        ThirdRatings tr1 = new ThirdRatings(ratingsFileName);
        MovieDatabase.initialize(movieFileName);
        System.out.println("The number of raters is: " + tr1.getRaterSize());
        System.out.println("The number of movies in the database is: " + MovieDatabase.size());
        ArrayList<Rating> r1 = tr1.getAverageRatingsByFilter(minimalRaters, af1);
        Collections.sort(r1);
        System.out.println("The number of items is: " + r1.size());
        for (Rating rating : r1) {
            String movieName = MovieDatabase.getTitle(rating.getItem());
            String movieDirectors = MovieDatabase.getDirector(rating.getItem());
            int movieYear = MovieDatabase.getYear(rating.getItem());
            double ratingValue = rating.getValue();
            int minutes = MovieDatabase.getMinutes(rating.getItem());
            System.out.println(ratingValue + " Time: " + minutes + " " + movieName);
            System.out.println(ratingValue + " " + movieName + " " + movieYear);
        }
    }
}
