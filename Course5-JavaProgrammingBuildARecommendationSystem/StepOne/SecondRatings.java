
/**
 * Write a description of class SecondRatings here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.*;
public class SecondRatings
{
    // instance variables - replace the example below with your own
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    public SecondRatings(String movieFile, String ratingFile){
        FirstRatings fr1 = new FirstRatings(); 
        myMovies = fr1.loadMovies("data/" + movieFile);
        myRaters = fr1.loadRaters("data/" + ratingFile); 
    }
    public int getMovieSize(){
        return myMovies.size(); 
    }
    public int getRaterSize(){
        return myRaters.size(); 
    }
    public String getTitle(String movieID){
        for(Movie movie: myMovies){
            String currentMovieID = movie.getID();
            if(movieID.equals(currentMovieID)){
                return movie.getTitle(); 
            }
        }
        return "Movie ID does not exist"; 
    }
    public String getID(String movieName){
        for(Movie movie: myMovies){
            String currentMovie = movie.getTitle();
            if(movieName.equals(currentMovie)){
                return movie.getID(); 
            }
        }
        return "NO SUCH TITLE"; 
    }
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList<Rating> answer = new ArrayList<Rating>(); 
        for(Movie movie : myMovies){
            String currentMovieID = movie.getID(); 
            double averageRating = getAverageByID(currentMovieID, minimalRaters);
            if(averageRating == 0){
                continue; 
            }
            Rating r = new Rating (currentMovieID, averageRating); 
            answer.add(r); 
        }
        return answer; 
    }
    public double getAverageByID(String movieID, int minimalRaters){
        int totalRaters = 0;
        double ratingsSum = 0; 
        for(Rater rater: myRaters){
            if(rater.hasRating(movieID)){
                double currentRating = rater.getRating(movieID); 
                ratingsSum += currentRating; 
                totalRaters += 1; 
            }
        }
        if(totalRaters < minimalRaters){
            return 0.0; 
        }
        double average = ratingsSum / totalRaters; 
        return average; 
    }
}
