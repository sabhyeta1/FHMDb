package at.ac.fhcampuswien.fhmdb.database;

import at.ac.fhcampuswien.fhmdb.DatabaseException;
import at.ac.fhcampuswien.fhmdb.observerPattern.Observable;
import at.ac.fhcampuswien.fhmdb.observerPattern.Observer;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WatchlistRepository implements Observable {
    Dao<WatchlistMovieEntity, Long> watchListDao;
    MovieRepository movieRepository;
    static WatchlistRepository instance;
    private List<Observer> observers;

    public static WatchlistRepository getInstance() throws DatabaseException {
        if (instance == null){
             instance = new WatchlistRepository();
        }
        return instance;
    }

    private WatchlistRepository() throws DatabaseException {
        observers = new ArrayList<>();
        try {
            this.watchListDao = DatabaseManager.getDatabase().getWatchlistDao();
        } catch (DatabaseException e) {
            throw new DatabaseException("watchListDao could not be created");
        }

    }

    public void addToWatchlist(WatchlistMovieEntity movie) throws DatabaseException {
        List<WatchlistMovieEntity> watchlist = getAllMovies();

        boolean found = false;

        for (WatchlistMovieEntity watchlistMovie : watchlist) {

            if (watchlistMovie.getApiId().equals(movie.getApiId())) {
                found = true;
                break;
            }

        }

        if (found) {
            notifyObservers("Movie is already in Watchlist");
        } else {
            try {

                watchListDao.create(movie);
                notifyObservers("Movie was added to Watchlist");

            } catch (SQLException e) {
                throw new DatabaseException("Something went wrong while adding the move with the apiId \"" + movie.getApiId() + "\" to the Watchlist");
            }
        }
    }

    public void removeFromWatchlist(String apiId) throws DatabaseException {
        DeleteBuilder<WatchlistMovieEntity, Long> deleteBuilder = watchListDao.deleteBuilder();
        try {
            deleteBuilder.where().eq("apiId", apiId);
            deleteBuilder.delete();
            notifyObservers("Movie removed from Watchlist");
        } catch (SQLException e) {
            throw new DatabaseException("Error when removing an entry from the watchlist table");
        }
    }

    public int removeAll() throws DatabaseException {
        try {
            return watchListDao.delete(getAllMovies());
        } catch (SQLException e) {
            throw new DatabaseException("Error when deleting the table");
        }
    }

    public List<WatchlistMovieEntity> getAllMovies() throws DatabaseException {
        try {
            return watchListDao.queryForAll();
        } catch (SQLException e) {
            throw new DatabaseException("Error when selecting from the whole table");
        }
    }

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }

    }
}
