package at.ac.fhcampuswien.fhmdb.database;

import at.ac.fhcampuswien.fhmdb.DatabaseException;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.models.MovieAPI;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DatabaseManager {
    public  static  final String DB_URL ="jdbc:h2:file:./db2/moviesDB";
    public  static  final String username ="user";
    public  static  final String password ="user";

    private static ConnectionSource conn;
    private final Dao<MovieEntity,Long> movieDao;
    private final Dao<WatchlistMovieEntity,Long> watchlistDao;


    private static DatabaseManager instance;

    private DatabaseManager() throws DatabaseException {
        try {
            createConnectionSource();
           movieDao =DaoManager.createDao(conn,MovieEntity.class);
           watchlistDao =DaoManager.createDao(conn,WatchlistMovieEntity.class);
            createTable();
        }catch (DatabaseException e) {
            throw new DatabaseException("Error when creating connection of the databank and table");
        } catch (SQLException e) {
            throw new DatabaseException("Error by creating the dao Object");
        }
    }

    public static DatabaseManager  getDatabase() throws DatabaseException {
        if (instance==null){
            try {
                instance=new DatabaseManager();

            }catch (DatabaseException e){
                throw new DatabaseException("No instance of DatabaseManager can be created");
            }
        }
        return instance;
    }
    private static void createConnectionSource() throws DatabaseException {
        try {
            conn = new JdbcConnectionSource(DB_URL, username,password);
        } catch (SQLException e ) {
            throw new DatabaseException("Logging into the databank failed",e);
        }

    }
    private static void createTable() throws DatabaseException {
        //TableUtils.dropTable(conn, MovieEntity.class,false);
       // TableUtils.dropTable(conn, WatchlistMovieEntity.class,false);
        try {
            TableUtils.createTableIfNotExists(conn, MovieEntity.class);
            TableUtils.createTableIfNotExists(conn, WatchlistMovieEntity.class);
        } catch (SQLException e) {
            throw new DatabaseException("Creating of table failed. Close the databank before starting the programme",e);
        }
       // TableUtils.dropTable(conn, WatchlistMovieEntity.class, false);
    }

    public Dao<MovieEntity, Long> getMovieDao() {
        return movieDao;
    }

    public Dao<WatchlistMovieEntity, Long> getWatchlistDao() {
        return watchlistDao;
    }
    /*public void testDB(){
        WatchlistRepository watchlistRepository = new WatchlistRepository();
        MovieEntity movieEntity = new MovieEntity("123456","The Shawshank Redemption","Two imprisoned men bend over","Drama",1994,"https://example.com",142,9.8);
        try {
            // MovieRepository.removeAll();
            movieDao.create(movieEntity);
            watchlistRepository.addToWatchlist(movieEntity.apiId);
            /*movieDao.create(movieEntity);
            movieDao.create(movieEntity);
            movieDao.create(movieEntity);
            movieDao.create(movieEntity);
            movieDao.create(movieEntity);




            System.out.println(movieDao.countOf());
           // movieDao.delete(movieDao.queryForAll());
            //System.out.println(movieDao.queryForAll());
            // MovieRepository.addAllMovies(allMovies);
            // System.out.println(movieDao.countOf());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    */
}
