
import java.util.*;
public class FourthRatings
{
    // instance variables - replace the example below with your own
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList<Rating> answer = new ArrayList<Rating>(); 
        ArrayList<String> movieIDs = MovieDatabase.filterBy(new TrueFilter());
        for(String movieID : movieIDs){ 
            double averageRating = getAverageByID(movieID, minimalRaters);
            if(averageRating == 0){
                continue; 
            }
            Rating r = new Rating (movieID, averageRating); 
            answer.add(r); 
        }
        return answer; 
    }
    public double getAverageByID(String movieID, int minimalRaters){
        int totalRaters = 0;
        double ratingsSum = 0; 
        for(Rater rater: RaterDatabase.getRaters()){
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
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria){
        ArrayList<Rating> answer = new ArrayList<Rating>(); 
        ArrayList<String> movieIDs = MovieDatabase.filterBy(filterCriteria);
        for(String movieID: movieIDs){
            double averageRating = getAverageByID(movieID,minimalRaters); 
            if(averageRating == 0){
                continue; 
            }
            Rating r = new Rating(movieID, averageRating); 
            answer.add(r); 
        }
        return answer; 
    }
    private double dotProduct(Rater me, Rater r){
        ArrayList<String> meMovies = me.getItemsRated(); 
        double dotProd = 0; 
        for(String meMovie: meMovies){
            if(!r.hasRating(meMovie)){
                continue; 
            }
            double meRating = me.getRating(meMovie); 
            double rRating = r.getRating(meMovie); 
            double meRatingNormalized = meRating - 5; 
            double rRatingNormalized = rRating - 5; 
            dotProd += meRatingNormalized * rRatingNormalized; 
        }
        return dotProd; 
    }
    private ArrayList<Rating> getSimilarities(String id){
        ArrayList<Rating> answer = new ArrayList<Rating>(); 
        Rater me = RaterDatabase.getRater(id);
        for(Rater r: RaterDatabase.getRaters()){
            String rID = r.getID(); 
            if(rID.equals(id)){
                continue; 
            }
            double currentDP = dotProduct(me,r); 
            if(currentDP < 0){
                continue; 
            }
            Rating similarity = new Rating(rID, currentDP); 
            answer.add(similarity); 
        }
        Collections.sort(answer,Collections.reverseOrder()); 
        return answer; 
    }
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters){
        return getSimilarRatingsByFilter(id,numSimilarRaters,minimalRaters,new TrueFilter()); 
    }
    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters,Filter f){
        ArrayList<Rating> answer = new ArrayList<Rating>(); 
        ArrayList<Rating> similarities = getSimilarities(id); 
        ArrayList<Rating> topSimilarities = new ArrayList<Rating>(); 
        
        // if we don't have enough top similar raters
        if (similarities.size() < numSimilarRaters){
            numSimilarRaters = similarities.size();
        }
        
        for(int i=0; i < numSimilarRaters; i++){
            Rating similarity = similarities.get(i); 
            topSimilarities.add(similarity); 
        }
        ArrayList<String> allMovies = MovieDatabase.filterBy(f); 
        for(String movie: allMovies){
            double weightedRatingProduct = 0; 
            int numRaters = 0; 
            for(Rating similarity: topSimilarities){
                String currentRaterID = similarity.getItem();
                double currentWeight = similarity.getValue(); 
                Rater currentRater = RaterDatabase.getRater(currentRaterID); 
                if(!currentRater.hasRating(movie)){
                    continue; 
                }
                double currentRating = currentRater.getRating(movie); 
                weightedRatingProduct += currentRating * currentWeight; 
                numRaters += 1;
            }
            if(numRaters < minimalRaters){
                continue; 
            }
            double weightedAverageRating = weightedRatingProduct / numRaters; 
            Rating movieRating = new Rating(movie,weightedAverageRating); 
            answer.add(movieRating); 
        }
        Collections.sort(answer,Collections.reverseOrder()); 
        return answer; 
    }
}
