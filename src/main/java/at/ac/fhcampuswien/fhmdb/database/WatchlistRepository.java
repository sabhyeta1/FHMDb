package at.ac.fhcampuswien.fhmdb.database;

import at.ac.fhcampuswien.fhmdb.DatabaseException;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public class WatchlistRepository {
    Dao<WatchlistMovieEntity, Long> watchListDao;
    MovieRepository movieRepository;

    public WatchlistRepository() throws DatabaseException {

        try {
            this.watchListDao = DatabaseManager.getDatabase().getWatchlistDao();
        } catch (DatabaseException e) {
            throw new DatabaseException("watchListDao could not be created");
        }
        this.movieRepository = new MovieRepository(); // Assuming you have a MovieRepository

    }

    public int addToWatchlist(String apiId) throws DatabaseException {
        try {
            List<WatchlistMovieEntity> watchlist = getAllMovies();
            for (WatchlistMovieEntity watchlistMovie : watchlist) {
                if (watchlistMovie.getApiId().equals(apiId)) {
                    // Movie already exists in watchlist, no need to add it again
                    throw new DatabaseException("The entry is present in the table");
                    // Indicate that no change was made
                }
            }
           // MovieEntity movieEntity = movieRepository.getMovieEntityByApiId(apiId);
            WatchlistMovieEntity watchlistMovie = new WatchlistMovieEntity(apiId);
            //watchlistMovie.setMovie(movieEntity);

            return watchListDao.create(watchlistMovie);
        } catch (SQLException e) {
                throw new DatabaseException("The entry could not be added to the table");

        } catch (DatabaseException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public int removeFromWatchlist(String apiId) throws DatabaseException {
        try {
            for (WatchlistMovieEntity watchlistMovieEntity : watchListDao) {
                if (watchlistMovieEntity.getApiId().equals(apiId)) {
                   return watchListDao.delete(watchlistMovieEntity);
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error when removing an entry from the watchlist table");
        }
        return 0;
    }
    public int removeAll() throws DatabaseException {
        try {
            return watchListDao.delete(getAllMovies());
        } catch (DatabaseException | SQLException e) {
            throw new DatabaseException("Error when deleting the table");
        }
    }
    public List<WatchlistMovieEntity> getAllMovies() throws DatabaseException {
        try {
            //System.out.println(movieDao.countOf());
            return watchListDao.queryForAll();
        } catch (SQLException e) {
            throw new DatabaseException("Error when selecting from the whole table");
        }
    }
}
