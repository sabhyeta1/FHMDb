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
            throw new DatabaseException("watchListDao konnte nicht erstellt werden");
        }
        this.movieRepository = new MovieRepository(); // Assuming you have a MovieRepository

    }

    public int addToWatchlist(String apiId) {
        try {
            List<WatchlistMovieEntity> watchlist = getAllMovies();
            for (WatchlistMovieEntity watchlistMovie : watchlist) {
                if (watchlistMovie.getApiId().equals(apiId)) {
                    // Movie already exists in watchlist, no need to add it again
                    return 0; // Indicate that no change was made
                }
            }
            MovieEntity movieEntity = movieRepository.getMovieEntityByApiId(apiId);
            WatchlistMovieEntity watchlistMovie = new WatchlistMovieEntity(apiId);
            //watchlistMovie.setMovie(movieEntity);

            return watchListDao.create(watchlistMovie);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int removeFromWatchlist(String apiId) {
        try {
            for (WatchlistMovieEntity watchlistMovieEntity : watchListDao) {
                if (watchlistMovieEntity.getApiId().equals(apiId)) {
                   return watchListDao.delete(watchlistMovieEntity);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
    public int removeAll(){
        try {
            return watchListDao.delete(getAllMovies());
        } catch ( SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<WatchlistMovieEntity> getAllMovies(){
        try {
            //System.out.println(movieDao.countOf());
            return watchListDao.queryForAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
