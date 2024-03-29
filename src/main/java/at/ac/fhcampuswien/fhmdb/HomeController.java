package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
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

public class HomeController implements Initializable {
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

    public List<Movie> allMovies = Movie.initializeMovies();
    public List<Movie> filteredMovies = Movie.initializeMovies();


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
    public List<Movie> getFilteredMovies() {
        return filteredMovies;
    }

    public void setFilteredMovies(List<Movie> filteredMovies) {
        this.filteredMovies = filteredMovies;
    }

}