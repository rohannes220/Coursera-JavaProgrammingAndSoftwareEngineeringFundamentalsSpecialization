import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*; 
public class FirstRatings
{
    public ArrayList<Movie> loadMovies(String fileName){
        ArrayList<Movie> answer = new ArrayList<Movie>();
        FileResource fr = new FileResource(fileName); 
        for(CSVRecord record: fr.getCSVParser(true)){
            String id = record.get("id");
            String name = record.get("title");
            String releaseYear = record.get("year");
            String country = record.get("country");
            String genre = record.get("genre");
            String director = record.get("director");
            int duration = Integer.parseInt(record.get("minutes"));
            String posterLink = record.get("poster");
            Movie film = new Movie(id,name,releaseYear,genre,director,country, posterLink, duration);
            answer.add(film); 
        }
        return answer; 
    }
    
    public void testLoadMovies(){
        ArrayList<Movie> tester = loadMovies("data/ratedmoviesfull.csv"); 
        int totalMovies = tester.size(); 
        int comedyFilms = howManyComedy(tester);
        int totalGreater = howManyMinutes(150,tester); 
        System.out.println("The number of movies: " + totalMovies); 
        System.out.println("The comedy films are: " + comedyFilms); 
        System.out.println("The number of films greater than 150 is: " + totalGreater); 
        /*for(Movie film: tester){
            System.out.println(film); 
        }
        */
       // get hash manp counts
       HashMap<String, Integer> totalDirectorMovies = howManyMovies(tester); 
       // get max count
       int maximumMovies = maxMovies(totalDirectorMovies);
       System.out.println("The maximum movies directed by a director or directors is: " + maximumMovies);
       // get list of directores with  max count
       ArrayList <String> mostDirectors = getDirector(maximumMovies,totalDirectorMovies);
       for(String director: mostDirectors){
           System.out.println(director); 
       }
    }
    
    public int howManyComedy(ArrayList<Movie> films){
        int count = 0;
        for(Movie film: films){
            String genre = film.getGenres().toUpperCase(); 
            boolean isComedy = genre.contains("COMEDY");
            if(isComedy){
                count += 1; 
            }
        }
        return count; 
    }
    
     public int howManyMinutes(int targetMinutes, ArrayList<Movie> films){
        int count = 0;
        for(Movie film: films){
            int minutes = film.getMinutes(); 
            if(minutes > targetMinutes){
                count += 1; 
            }
        }
        return count; 
    }
    private int maxMovies(HashMap<String,Integer> directorCount){
        int maxNumber = 0; 
        for(String director: directorCount.keySet()){
            int directedFilms = directorCount.get(director); 
            if(directedFilms > maxNumber){
                maxNumber = directedFilms; 
            }
        }
    return maxNumber; 
    }
    private ArrayList <String> getDirector(int maxCount, HashMap<String,Integer> directorMovies){
        ArrayList<String> directors = new ArrayList<String>(); 
       for(String director: directorMovies.keySet()){
           int currentFilms = directorMovies.get(director); 
           if(currentFilms == maxCount){
               directors.add(director); 
           }
       }
       return directors; 
    }
    private HashMap<String, Integer> howManyMovies(ArrayList<Movie> releases){
        HashMap<String, Integer> directorCounts = new HashMap<String,Integer>(); 
        for(Movie film: releases){
            String[] directors = film.getDirector().split(",");
            for(String director: directors){
                director = director.trim();
                if(directorCounts.containsKey(director)){
                    int currentValue = directorCounts.get(director);
                    directorCounts.put(director, currentValue + 1); 
                }
                else{
                    directorCounts.put(director,1); 
                }
            }
        }
        return directorCounts; 
    }
    public ArrayList<Rater> loadRaters(String fileName){
        FileResource fr = new FileResource(fileName);
        ArrayList<Rater> answer = new ArrayList<Rater>(); 
        String currentRaterID = "";
        Rater currentRater = null; 
        for(CSVRecord record: fr.getCSVParser(true)){
            String raterID = record.get("rater_id");
            String movieID = record.get("movie_id");
            double rating = Double.parseDouble(record.get("rating"));
            if(!currentRaterID.equals(raterID)){
                currentRater = new EfficientRater(raterID);
                answer.add(currentRater); 
                currentRaterID = raterID;  
            }
            currentRater.addRating(movieID,rating); 
        }
        return answer;
    }
    public void testLoadRaters(){
        ArrayList<Rater> raters = loadRaters("data/ratings.csv"); 
        int number = raters.size();
        System.out.println("The number of raters is: " + number);
        String raterID = "193"; 
        int ratings = numberofRatings(raters,raterID); 
        System.out.println("The number of ratings  by " + raterID + " is " + ratings);
        String movieTitle = "1798709";
        int movieRatingsTotal = movieRatings(raters,movieTitle);
        System.out.println("The  number of ratings for " + movieTitle + " is " + movieRatingsTotal); 
        int totalMovies = differentMovies(raters);
        System.out.println("There were " + totalMovies + " rated ");
        HashMap<Rater,Integer> raterMovieCounts = raterCountForHashMap(raters); 
        int maxNumberOfRatings = maxMoviesByARater(raterMovieCounts); 
        System.out.println("The max movies by a rater are: " + maxNumberOfRatings); 
        ArrayList<Rater> maxCountRaters = ratersWithMaxCount(raterMovieCounts,maxNumberOfRatings); 
        for(Rater rater: maxCountRaters){
            System.out.println(rater.getID()); 
        }
        /*for(Rater rater: raters){
            System.out.print(rater.getID() + " : " );
            System.out.println(rater.numRatings());
            ArrayList<String> movieIDS = rater.getItemsRated();
            for(String movieID: movieIDS){
                double rating = rater.getRating(movieID); 
                System.out.println("The movie ID is: " + movieID + " and the rating is " + rating);
            }
        }*/
    }
    private int numberofRatings(ArrayList<Rater> raters, String raterID){
        for(Rater rater: raters){
            String currentID = rater.getID();
            if(currentID.equals(raterID)){
                int ratingSize = rater.numRatings();
                return ratingSize; 
            }
        }
        return -1; 
    }
    private int movieRatings(ArrayList<Rater> raters, String movieName){
        int count = 0; 
        for(Rater rater: raters){
            boolean hasMovie = rater.hasRating(movieName);
            if(hasMovie){
                count += 1; 
            }
        }
        return count; 
    }
    private int differentMovies(ArrayList<Rater> raters){
        HashSet<String> allMovies = new HashSet<String> (); 
        for(Rater rater: raters){
            ArrayList<String> movieIDS = rater.getItemsRated();
            for(String movieID: movieIDS){
                allMovies.add(movieID);
            }
        }
        return allMovies.size(); 
    }
    private HashMap<Rater,Integer> raterCountForHashMap(ArrayList<Rater> raters){
        HashMap<Rater,Integer> answer = new HashMap<Rater,Integer>(); 
        for(Rater rater: raters){
            int totalMovies = rater.numRatings(); 
            answer.put(rater,totalMovies); 
        }
        return answer; 
    }
    private int maxMoviesByARater(HashMap<Rater,Integer> input){
        int max = 0;
        for(Rater rater: input.keySet()){
            int currentValue = input.get(rater); 
            if(currentValue > max){
                max = currentValue; 
            }
        }
        return max; 
    }
    private ArrayList<Rater> ratersWithMaxCount(HashMap<Rater,Integer> ratersCounts, int maxCount){
        ArrayList<Rater> answer = new ArrayList<Rater>(); 
        for(Rater rater: ratersCounts.keySet()){
            int currentValue = ratersCounts.get(rater);
            if(currentValue == maxCount){
                answer.add(rater); 
            }
        }
        return answer; 
    }
}
