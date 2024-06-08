package at.ac.fhcampuswien.fhmdb.database;

import at.ac.fhcampuswien.fhmdb.DatabaseException;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public class MovieRepository {
     Dao<MovieEntity,Long>movieDao;
     static MovieRepository instance;
     public static MovieRepository getInstance() throws DatabaseException {
         if (instance == null){
             instance = new MovieRepository();
         }
         return instance;
     }
    private MovieRepository() throws DatabaseException {

        try {
            movieDao=DatabaseManager.getDatabase().getMovieDao();
        } catch (DatabaseException e) {
            throw new DatabaseException("No instances of MovieRepository could be created");
        }
    }
    public int addAllMovies(List<Movie>movieList) throws DatabaseException {

        try {
            List<MovieEntity>movieEntities = getAllMovies();

            for (Movie movie : movieList) {
              //  boolean found = false;
                // Check if the title of movie1 is present in the second list
                for (MovieEntity movieEntity : movieEntities) {
                    if (movie.getTitle().equals(movieEntity.title)) {
                        // If the title is found, set found flag to true and break the inner loop
                        movieList.remove(movie);
                        break;
                    }
                }

            }
            movieDao.create(MovieEntity.fromMovies(movieList));
        } catch (SQLException e) {
            throw new DatabaseException("The entry can't be created");
        }

        return 1;
    }

    public int addToWatchlist(String apiId) throws DatabaseException {
        try {
            List<MovieEntity> movieEntityList = getAllMovies();
            for (MovieEntity movieEntity : movieEntityList) {
                if (movieEntity.apiId.equals(apiId)) {
                    // Movie already exists in watchlist, no need to add it again
                    return 0; // Indicate that no change was made
                }
            }
            MovieEntity movieEntity = getMovieEntityByApiId(apiId);
            //watchlistMovie.setMovie(movieEntity);

            return movieDao.create(movieEntity);
        } catch (SQLException e) {
            throw new DatabaseException("The entry could not be added to the table");
        } catch (DatabaseException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public int removeFromMovieList(String apiId) throws DatabaseException {
        try {
            for (MovieEntity movieEntity : movieDao) {
                if (movieEntity.apiId.equals(apiId)) {
                        return movieDao.delete(movieEntity);
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Entry couldn't be found to delete");
        }
        return 0;
    }
     public int removeAll() throws DatabaseException {

        try {
           return movieDao.delete(getAllMovies());
        } catch (DatabaseException e) {
            throw new DatabaseException(e.getMessage());
        } catch (SQLException e) {
            throw new DatabaseException("Entry couldn't be deleted");
        }
     }
     public List<MovieEntity> getAllMovies() throws DatabaseException {
        try {
            //System.out.println(movieDao.countOf());
            return movieDao.queryForAll();
        } catch (SQLException e) {
            throw new DatabaseException("Unbable to colect all Entries");
        }
    }
    public void getFirstEntry(){
       // System.out.println(movieDao.queryForAll());
    }

    public MovieEntity getMovieEntityByApiId(String apiId) throws DatabaseException {
        try {
            // Query for the MovieEntity with the given apiId
            List<MovieEntity> result = movieDao.queryBuilder()
                    .where()
                    .eq("apiId", apiId)
                    .query();

            // Check if any MovieEntity was found
            if (result != null && !result.isEmpty()) {
                // Return the first MovieEntity found (assuming apiId is unique)
                return result.get(0);
            } else {
                // If no MovieEntity with the given apiId was found, return null
                return null;
            }
        } catch (SQLException e) {
           // throw new RuntimeException();
            throw new DatabaseException("The Entry could not be found in the table");
        }
    }
}
