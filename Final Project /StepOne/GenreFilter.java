
public class GenreFilter implements Filter
{
    // instance variables - replace the example below with your own
    private String myGenre; 
    
    public GenreFilter(String genre) {
        myGenre = genre;
    }
    
    @Override
    public boolean satisfies(String id) {
        return MovieDatabase.getGenres(id).contains(myGenre); 
    }
    }
