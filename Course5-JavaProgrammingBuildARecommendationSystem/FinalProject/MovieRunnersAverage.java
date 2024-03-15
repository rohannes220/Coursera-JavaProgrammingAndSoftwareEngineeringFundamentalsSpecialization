import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnersAverage {
    public void printAverageRatings() {
        SecondRatings sr1 = new SecondRatings("ratedmoviesfull.csv", "ratings.csv");
        int movieSize = sr1.getMovieSize();
        int raterSize = sr1.getRaterSize();
        System.out.println("The number of movies is: " + movieSize);
        System.out.println("The number of raters is: " + raterSize);
        int minimalRatings = 12;
        ArrayList<Rating> answer = sr1.getAverageRatings(minimalRatings);
        System.out.println(answer.size());
        Collections.sort(answer);
        for (Rating rating : answer) {
            System.out.print(rating.getValue());
            String currentMovieID = rating.getItem();
            String movieName = sr1.getTitle(currentMovieID);
            System.out.println(" " + movieName);
        }
    }

    public void getAverageRatingOneMovie() {
        SecondRatings sr1 = new SecondRatings("ratedmoviesfull.csv", "ratings.csv");
        String movieName = "Vacation";
        String movieID = sr1.getID(movieName);
        double movieRating = sr1.getAverageByID(movieID, 2);
        System.out.println(movieName + ": " + movieRating);
    }
}
