package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.database.*;
import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.models.MovieAPI;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.text.BreakIterator;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class HomeController implements Initializable {
    //TODO add/remove Button richtig implementiren exception handling weiter ausbreiten add/remove dao  UI FEEDBACK
    boolean booleanFlag = false;
    public JFXComboBox releaseYearComboBox;
    public JFXComboBox ratingComboBox;
    public JFXButton searchBtnByUrl;

    @FXML
    public JFXButton searchBtn;

    @FXML
    public TextField searchField;

    @FXML
    public JFXListView movieListView;

    @FXML
    public JFXComboBox genreComboBox;

    @FXML
    public JFXButton sortBtn;
    public Button watchListBtn;

    public List<Movie> filteredMovies = new ArrayList<>();
    public List<Movie> watchListMovies = new ArrayList<>();
    DatabaseManager databaseManager = getDatabaseManager();

    MovieRepository movieRepository = getMovieRepository();

    private MovieRepository getMovieRepository() {
        try {
            return new MovieRepository();
        } catch (DatabaseException e) {
            showErrorPopUp(e.getMessage(),-1);
        }
        return null;
    }

    WatchlistRepository watchlistRepository = getWatchListRepository();

    private WatchlistRepository getWatchListRepository() {
        try {
            return new WatchlistRepository();
        } catch (DatabaseException e) {
            showWarnPopUp(e.getMessage());
        }
        return null;
    }

    // Dao<MovieEntity,Long> movieDao = databaseManager.getMovieDao() ;
    //try für moviapi erweitern damit falls api versagt(internet kackt ab) die Db für die filme zuständig ist
    public List<Movie> allMovies = getData();

    private final ObservableList<Movie> observableMovies = FXCollections.observableArrayList();   // automatically updates corresponding UI elements when underlying data changes
    private ClickEventHandler clickEventHandler;
    ClickEventHandler<Movie> addToWatchlistClicked;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // databaseManager.testDB();

        ClickEventHandler<Movie> addToWatchlistClicked = this::add_removeFromWatchList;
       //ClickEventHandler<Movie> removeFromWatchlistClicked = removeFromWatchlist;

        //movieRepository.addAllMovies(allMovies); // Achtung Zeile befüllt DB!!
        //MovieEntity.fromMovies(allMovies);
        /*movieRepository.addAllMovies(allMovies);*/


        observableMovies.addAll(allMovies);         // add dummy data to observable list
        // initialize UI stuff
        movieListView.setItems(observableMovies);   // set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell(addToWatchlistClicked)); // use custom cell factory to display data

        // TODO add genre filter items with genreComboBox.getItems().addAll(...)
        genreComboBox.setPromptText("Filter by Genre");
        genreComboBox.getItems().addAll(Genre.values());
        releaseYearComboBox.getItems().addAll(IntStream.range(1972, 2019).boxed().toList());
        ratingComboBox.getItems().addAll(IntStream.range(0, 11).boxed().toList());


        // TODO add event handlers to buttons and call the regarding methods
        // either set event handlers in the fxml file (onAction) or add them here

        // Sort button example:
        sortBtn.setOnAction(actionEvent -> {
            if (sortBtn.getText().equals("Sort (asc)")) {
                // TODO sort observableMovies ascending

                observableMovies.clear();
                observableMovies.addAll(Movie.sortListAlphabetically(getFilteredMovies(), true));
                sortBtn.setText("Sort (desc)");
            } else {
                // TODO sort observableMovies descending

                observableMovies.clear();
                observableMovies.addAll(Movie.sortListAlphabetically(getFilteredMovies(), false));
                sortBtn.setText("Sort (asc)");
            }
        });


    }


    public void FilterMovieListByQuery(KeyEvent keyEvent) {
        if (Objects.equals(keyEvent.getCode().getName(), "Enter")) {
            try {
                setFilteredMovies(Movie.filterMovieListByQuery(allMovies, searchField.getText()));
                observableMovies.clear();
                observableMovies.addAll(getFilteredMovies());         // add dummy data to observable list

            } catch (NullPointerException e) {
                setFilteredMovies(allMovies);
                observableMovies.addAll(allMovies);         // add dummy data to observable list
                movieListView.setItems(null);
                movieListView.setItems(observableMovies);
            }

            // initialize UI stuff
            movieListView.setItems(null);
            movieListView.setItems(observableMovies);
        }
    }

    public void filterMovieList() {

        try {
            setFilteredMovies(Movie.filterMovieLists(allMovies, (Genre) genreComboBox.getValue(), searchField.getText()));
            observableMovies.clear();
            observableMovies.addAll(filteredMovies);
        } catch (NullPointerException e) {
        }
        // initialize UI stuff
        movieListView.setItems(null);
        movieListView.setItems(observableMovies);
    }

    public void filterMovieListByUrl() {

        double selectedRating;

        try {
            selectedRating = (int) ratingComboBox.getValue();

        } catch (NullPointerException e) {
            selectedRating = -1.0;

        }

        try {
            setFilteredMovies(MovieAPI.filterMovieListByUrl(searchField.getText(), (Genre) genreComboBox.getValue(), (Integer) releaseYearComboBox.getValue(), selectedRating));
        } catch (MovieApiException e) {
            showErrorPopUp(e.getMessage() + "\nFilterURL does not work without internet connection", -1);
            return;
        }

        observableMovies.clear();
        observableMovies.addAll(filteredMovies);

        // initialize UI stuff
        movieListView.setItems(null);
        movieListView.setItems(observableMovies);

        genreComboBox.getSelectionModel().clearSelection();
        releaseYearComboBox.getSelectionModel().clearSelection();
        ratingComboBox.getSelectionModel().clearSelection();
    }

    public static String getMostPopularActor(List<Movie> movies) {
        Stream<String[]> mainCast = movies.stream().map(Movie::getMainCast);
        //maincast= Maincast1[], MainCast2[],...
        List<String> stringList = mainCast.flatMap(Arrays::stream).toList();

        Map<String, Long> hashList = stringList.stream().collect(Collectors.groupingBy(s -> s, Collectors.counting()));
        //hashList=hashList.entrySet().stream().max(Map.Entry.comparingByKey());
        // source https://stackoverflow.com/questions/5911174/finding-key-associated-with-max-value-in-a-java-map
        try {

            return Collections.max(hashList.entrySet(), Map.Entry.comparingByValue()).getKey();

        } catch (NoSuchElementException e) {
            return null;
        }
    }


    public static int getLongestMovieTitle(List<Movie> movies) {
        try {

            return movies.stream().mapToInt(w -> w.getTitle().length()).max().getAsInt();
        } catch (NoSuchElementException e) {
            return 0;
        }


    }

    public static long countMoviesFrom(List<Movie> movies, String director) {
        try {

            return movies.stream()   //"{Director1,Director2,director...}"  =>director?
                    .filter(movie -> Arrays.toString(movie.getDirectors()).contains(director)).count();

        } catch (NoSuchElementException e) {
            return 0;
        }


    }


    public static List<Movie> getMoviesBetweenYears(List<Movie> movies, int startYear, int endYear) {
        try {

            return movies.stream().filter(movie -> movie.getReleaseYear() >= startYear && endYear >= movie.getReleaseYear()).toList();
        } catch (NoSuchElementException e) {
            return null;
        }

    }

    public DatabaseManager getDatabaseManager() {
        try {
            return DatabaseManager.getDatabase();
        } catch (DatabaseException e) {
            showErrorPopUp(e.getMessage(), 404);
           // showWarnPopUp(e.getMessage());

        }
        return null;
    }

    public List<Movie> getData() {
        List<Movie> movieList = null;
        try {
            movieList = MovieAPI.run("https://prog2.fh-campuswien.ac.at/movies");
        } catch (MovieApiException e) {
            showWarnPopUp(e.getMessage());
            try {
                return MovieEntity.toMovies(movieRepository.getAllMovies());

            }catch (NullPointerException d){
                showWarnPopUp("Neither the API nor the database is working, the program will be closed");
                System.exit(0);
            }
        }
        //movieRepository.addAllMovies(movieList);
        Movie.writeMoviesToFile(movieList);
        setFilteredMovies(movieList);
        return movieList;

            /* Idee: try {
            } catch (MovieApiException e){}

            */
        // return MovieEntity.toMovies(movieRepository.getAllMovies());

    }

    public List<Movie> getFilteredMovies() {
        return filteredMovies;
    }

    public void setFilteredMovies(List<Movie> filteredMovies) {
        this.filteredMovies = filteredMovies;
    }

    public void changeList(MouseEvent mouseEvent) {
        boolean booleanFlag = watchListBtn.getText().equals("See Watchlist");
        if (booleanFlag) {

            List<WatchlistMovieEntity> watchlist = null;
            try {
                watchlist = watchlistRepository.getAllMovies();
            } catch (DatabaseException e) {
                showErrorPopUp(e.getMessage(),-1);
            } catch (NullPointerException e) {
                showErrorPopUp("Programme was started without database. Please restart the programme.",-1);
                return;
            }
            List<MovieEntity> movieEntityList = new ArrayList<>();

            for (WatchlistMovieEntity watchlistMovieEntity : watchlist) {
                MovieEntity movieEntity = null;
                try {
                    movieEntity = movieRepository.getMovieEntityByApiId(watchlistMovieEntity.getApiId());
                } catch (DatabaseException e) {
                    showErrorPopUp(e.getMessage(),-1);
                }
                movieEntityList.add(movieEntity);
            }
            watchListBtn.setText("Back to Home Scene");

            setFilteredMovies(MovieEntity.toMovies(movieEntityList));
            setVisibilityOfElements(false);
        } else {
            setVisibilityOfElements(true);
            setFilteredMovies(allMovies);
            watchListBtn.setText("See Watchlist");

        }
        observableMovies.clear();
        observableMovies.addAll(filteredMovies);
    }

    private void add_removeFromWatchList(Movie movie) {
        if (watchListBtn.getText().equals("See Watchlist")) {
            addToWatchlist.onClick(movie);
        } else {
            removeFromWatchlist.onClick(movie);
        }
    }



    private final ClickEventHandler removeFromWatchlist = (clickedItem) ->
    {
        System.out.println();
        Movie movie = (Movie) clickedItem;
        try {
            watchlistRepository.removeFromWatchlist(movie.getId());
        } catch (DatabaseException e) {
            showErrorPopUp(e.getMessage(),-1);
        }
        List<WatchlistMovieEntity> watchlist = null;
        try {
            watchlist = watchlistRepository.getAllMovies();
        } catch (DatabaseException e) {
            showErrorPopUp(e.getMessage(),-1);
        }
        List<MovieEntity> movieEntityList = new ArrayList<>();

        for (WatchlistMovieEntity watchlistMovieEntity : watchlist) {
            MovieEntity movieEntity = null;
            try {
                movieEntity = movieRepository.getMovieEntityByApiId(watchlistMovieEntity.getApiId());
            } catch (DatabaseException e) {
                showErrorPopUp(e.getMessage(),-1);
            }
            movieEntityList.add(movieEntity);
        }
        setFilteredMovies(MovieEntity.toMovies(movieEntityList));

        observableMovies.clear();
        observableMovies.addAll(filteredMovies);


    };


    private final ClickEventHandler addToWatchlist = (clickedItem) ->
    {
        // add code to add movie to watchlist here
        Movie movie = (Movie) clickedItem;

       // System.out.println(movieCell.getText());
        try {
            watchlistRepository.addToWatchlist(movie.getId());
        } catch (DatabaseException e) {
            showErrorPopUp(e.getMessage(),-1);
        }  catch (NullPointerException e) {
            showErrorPopUp("Programme was started without database. Please restart the programme.",-1);
        }
        System.out.println(movie.getTitle());
        addToWatchlistClicked = removeFromWatchlist;

    };

    public void setVisibilityOfElements(boolean flag) {
        searchField.setVisible(flag);
        genreComboBox.setVisible(flag);
        releaseYearComboBox.setVisible(flag);
        ratingComboBox.setVisible(flag);
        searchBtn.setVisible(flag);
        searchBtnByUrl.setVisible(flag);
    }

    public void showErrorPopUp(String msg, int errorCode) {
        // string msg und vielleicht ein int i mitgeben um zu unterscheiden ansonsten code zu groß
        // Erstellt eine Fehlermeldung(Pop-up)
        ButtonType closeButton = new ButtonType("Close programme");
        ButtonType startWithoutDbButton = new ButtonType("Start programme without database");
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Error Message: " + msg);

        switch (errorCode) {
            case 0:
                alert.getButtonTypes().add(closeButton);
                break;
            case 404:
                alert.getButtonTypes().setAll(closeButton, startWithoutDbButton);
                break;
            // Add more cases as needed for different error codes
            default:
                // Handle other error codes here
                break;
        }

        alert.showAndWait().ifPresent(response -> {
            if (response == closeButton) {
               // System.out.println("Programm schließen");
                //This just terminates the program.
                Platform.exit();
                System.exit(0);
            } else {
               databaseManager = null;
                //System.out.println("Programm ohne Db starten");
            }
        });
       // alert.showAndWait();
    }

    public void showWarnPopUp(String msg) {


        // Erstellt eine Warnmeldung(Pop-up)
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText("Warning Message: "+msg);

        // Zeigt die Warnmeldung an und wartet auf eine Benutzerinteraktion
        alert.showAndWait();


    }

/*private final ClickEventHandler<Movie> onAddToWatchlistClicked = (clickedItem) -> {
        // add code to add movie to watchlist here
    };

    private final ClickEventHandler<Movie> onRemoveFromWatchlistClicked = (clickedItem) -> {
        // add code to remove movie from watchlist here
       // removeFromWatchlist(clickedItem);
    };*/


}