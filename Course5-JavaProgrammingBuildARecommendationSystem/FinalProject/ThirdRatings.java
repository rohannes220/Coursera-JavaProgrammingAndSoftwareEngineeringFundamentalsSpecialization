import java.util.*;

public class ThirdRatings {
    private ArrayList<Rater> myRaters;

    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }

    public ThirdRatings(String ratingFile) {
        FirstRatings fr1 = new FirstRatings();
        myRaters = fr1.loadRaters("data/" + ratingFile);
    }

    public int getRaterSize() {
        return myRaters.size();
    }

    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> answer = new ArrayList<Rating>();
        ArrayList<String> movieIDs = MovieDatabase.filterBy(new TrueFilter());
        for (String movieID : movieIDs) {
            double averageRating = getAverageByID(movieID, minimalRaters);
            if (averageRating == 0) {
                continue;
            }
            Rating r = new Rating(movieID, averageRating);
            answer.add(r);
        }
        return answer;
    }

    public double getAverageByID(String movieID, int minimalRaters) {
        int totalRaters = 0;
        double ratingsSum = 0;
        for (Rater rater : myRaters) {
            if (rater.hasRating(movieID)) {
                double currentRating = rater.getRating(movieID);
                ratingsSum += currentRating;
                totalRaters += 1;
            }
        }
        if (totalRaters < minimalRaters) {
            return 0.0;
        }
        double average = ratingsSum / totalRaters;
        return average;
    }

    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> answer = new ArrayList<Rating>();
        ArrayList<String> movieIDs = MovieDatabase.filterBy(filterCriteria);
        for (String movieID : movieIDs) {
            double averageRating = getAverageByID(movieID, minimalRaters);
            if (averageRating == 0) {
                continue;
            }
            Rating r = new Rating(movieID, averageRating);
            answer.add(r);
        }
        return answer;
    }
}
