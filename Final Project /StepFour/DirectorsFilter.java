
public class DirectorsFilter implements Filter
{
    // instance variables - replace the example below with your own
    private String myDirectors; 
    
    public DirectorsFilter(String directors) {
        myDirectors = directors;
    }
    
    @Override
    public boolean satisfies(String id) {
        String[] directorsArray = myDirectors.split(","); 
        String movieDirectors = MovieDatabase.getDirector(id); 
        for(String director: directorsArray){
            if(movieDirectors.contains(director)){
                return true; 
            }
        }
        return false; 
    }
}
   
