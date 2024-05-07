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

    public int addToWatchlist(String apiId) throws DatabaseException {
        try {
            List<WatchlistMovieEntity> watchlist = getAllMovies();
            for (WatchlistMovieEntity watchlistMovie : watchlist) {
                if (watchlistMovie.getApiId().equals(apiId)) {
                    // Movie already exists in watchlist, no need to add it again
                    throw new DatabaseException("Der Eintrag ist in der Tabelle vorhanden");
                    // Indicate that no change was made
                }
            }
           // MovieEntity movieEntity = movieRepository.getMovieEntityByApiId(apiId);
            WatchlistMovieEntity watchlistMovie = new WatchlistMovieEntity(apiId);
            //watchlistMovie.setMovie(movieEntity);

            return watchListDao.create(watchlistMovie);
        } catch (SQLException e) {
                throw new DatabaseException("Der Eintrag konnte nicht in die Tabelle hinzugefügt werden");

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
            throw new DatabaseException("Fehler beim Entfernen von einem Eintrag was der Watchlist Tabelle");
        }
        return 0;
    }
    public int removeAll() throws DatabaseException {
        try {
            return watchListDao.delete(getAllMovies());
        } catch (DatabaseException | SQLException e) {
            throw new DatabaseException("Beim Löschen der Tabelle ist ein Fehler aufgetreten");
        }
    }
    public List<WatchlistMovieEntity> getAllMovies() throws DatabaseException {
        try {
            //System.out.println(movieDao.countOf());
            return watchListDao.queryForAll();
        } catch (SQLException e) {
            throw new DatabaseException("Fehler beim Selektieren von der ganzen Tabelle eingetreten");
        }
    }
}
