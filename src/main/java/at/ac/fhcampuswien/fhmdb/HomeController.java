package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.models.MovieAPI;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.IntStream;

public class HomeController implements Initializable {
    public JFXComboBox releaseYearComboBox;
    public JFXComboBox ratingComboBox;
    public JFXButton searchBtnByUrl;
    MovieAPI movieAPI = new MovieAPI();

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

    public List<Movie> allMovies = movieAPI.run("https://prog2.fh-campuswien.ac.at/movies");
    public List<Movie> filteredMovies =  movieAPI.run("https://prog2.fh-campuswien.ac.at/movies");


    private final ObservableList<Movie> observableMovies = FXCollections.observableArrayList();   // automatically updates corresponding UI elements when underlying data changes

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        observableMovies.addAll(allMovies);         // add dummy data to observable list
        // initialize UI stuff
        movieListView.setItems(observableMovies);   // set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell()); // use custom cell factory to display data

        // TODO add genre filter items with genreComboBox.getItems().addAll(...)
        genreComboBox.setPromptText("Filter by Genre");
        genreComboBox.getItems().addAll(Genre.values());
        releaseYearComboBox.getItems().addAll(IntStream.range(1980,2019).boxed().toList());
        ratingComboBox.getItems().addAll(IntStream.range(0,11).boxed().toList());


        // TODO add event handlers to buttons and call the regarding methods
        // either set event handlers in the fxml file (onAction) or add them here

        // Sort button example:
        sortBtn.setOnAction(actionEvent -> {
            if(sortBtn.getText().equals("Sort (asc)")) {
                // TODO sort observableMovies ascending

                observableMovies.clear();
                observableMovies.addAll(Movie.sortListAlphabetically(getFilteredMovies(),true));
                sortBtn.setText("Sort (desc)");
            } else {
                // TODO sort observableMovies descending

                observableMovies.clear();
                observableMovies.addAll(Movie.sortListAlphabetically(getFilteredMovies(),false));
                sortBtn.setText("Sort (asc)");
            }
        });


    }






    public void FilterMovieListByQuery(KeyEvent keyEvent) {
        if (Objects.equals(keyEvent.getCode().getName(), "Enter")){
            try {
                setFilteredMovies(Movie.filterMovieListByQuery(allMovies,searchField.getText()));
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
    public void filterMovieList(MouseEvent mouseEvent) {

        try {
            setFilteredMovies(Movie.filterMovieLists(allMovies, (Genre) genreComboBox.getValue(),searchField.getText()));
            observableMovies.clear();
            observableMovies.addAll(filteredMovies);
        } catch (NullPointerException e){}
        // initialize UI stuff
        movieListView.setItems(null);
        movieListView.setItems(observableMovies);
    }

    public void filterMovieListByUrl(MouseEvent mouseEvent) {
        StringBuilder stb = new StringBuilder("https://prog2.fh-campuswien.ac.at/movies?");
        String searchText = searchField.getText();
        Genre selectedGenre = (Genre) genreComboBox.getValue();
        Integer selectedReleaseYear = (Integer) releaseYearComboBox.getValue();
        double selectedRating;

        if (searchText.isBlank()) {
            // searchText = "";

        } else stb.append("&query=").append(searchText);


        if (selectedGenre == null) {
            // selectedGenre = Genre.NONE;

        } else  stb.append("&genre=").append(selectedGenre);

        if (selectedReleaseYear == null) {
            //selectedReleaseYear = -1;


        } else stb.append("&releaseYear=").append(selectedReleaseYear);

        try {
            selectedRating = (int) ratingComboBox.getValue();
            stb.append("&ratingFrom=").append(selectedRating);

        } catch (NullPointerException e) {
            selectedRating = -1.0;
            stb.append("&ratingFrom=").append(selectedRating);

        }


        setFilteredMovies(MovieAPI.run(stb.toString()));
        // return movieList;

        // All parameters are not null, proceed with filtering

        observableMovies.clear();
        observableMovies.addAll(filteredMovies);

        // initialize UI stuff
        movieListView.setItems(null);
        movieListView.setItems(observableMovies);

        //Java Stream
        /*
        Movie.getMostPopularActor(filteredMovies);
        Movie.getLongestMovieTitle(filteredMovies);
        Movie.countMoviesFrom(filteredMovies,"Francis Ford Coppola");
        Movie.getMoviesBetweenYears(filteredMovies,2000,2020);
         */

        genreComboBox.getSelectionModel().clearSelection();
        releaseYearComboBox.getSelectionModel().clearSelection();
        ratingComboBox.getSelectionModel().clearSelection();
    }
    public List<Movie> getFilteredMovies() {
        return filteredMovies;
    }

    public void setFilteredMovies(List<Movie> filteredMovies) {
        this.filteredMovies = filteredMovies;
    }

}