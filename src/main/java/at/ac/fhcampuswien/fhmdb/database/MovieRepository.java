package at.ac.fhcampuswien.fhmdb.database;

import at.ac.fhcampuswien.fhmdb.DatabaseException;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public class MovieRepository {
     Dao<MovieEntity,Long>movieDao;
    public MovieRepository(){
        try {
            movieDao=DatabaseManager.getDatabase().getMovieDao();
        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        }
    }
    public int addAllMovies(List<Movie>movieList){

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
            throw new RuntimeException(e);
        }

        return 1;
    }
    public int removeFromMovieList(String apiId) {
        try {
            for (MovieEntity movieEntity : movieDao) {
                if (movieEntity.apiId.equals(apiId)) {
                        return movieDao.delete(movieEntity);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
     public int removeAll(){

        try {
           return movieDao.delete(getAllMovies());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
     public List<MovieEntity> getAllMovies(){
        try {
            //System.out.println(movieDao.countOf());
            return movieDao.queryForAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void getFirstEntry(){
       // System.out.println(movieDao.queryForAll());
    }

    public MovieEntity getMovieEntityByApiId(String apiId) {
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
            throw new RuntimeException(e);
        }
    }
}
