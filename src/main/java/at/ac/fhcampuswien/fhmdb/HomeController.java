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
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
            List<Movie>sortedMovieList=new ArrayList<>();

            if(sortBtn.getText().equals("Sort (asc)")) {
                // TODO sort observableMovies ascending
                sortedMovieList=Movie.sortListAlphabetically(getFilteredMovies(),true);
                setFilteredMovies(sortedMovieList);
                observableMovies.clear();
                observableMovies.addAll(getFilteredMovies());
                sortBtn.setText("Sort (desc)");
            } else {
                // TODO sort observableMovies descending
                sortedMovieList=Movie.sortListAlphabetically(getFilteredMovies(),false);
                setFilteredMovies(sortedMovieList);
                observableMovies.clear();
                observableMovies.addAll(getFilteredMovies());
                sortBtn.setText("Sort (asc)");
            }
        });


    }


    public void filterMovieList(MouseEvent mouseEvent) {
        if (Genre.NONE.equals(Genre.valueOf(genreComboBox.getSelectionModel().getSelectedItem().toString()))){

            setFilteredMovies(allMovies);
            observableMovies.clear();
            observableMovies.addAll(allMovies);         // add dummy data to observable list
            // initialize UI stuff
            movieListView.setItems(null);
            movieListView.setItems(observableMovies);
            return;
        }
        setFilteredMovies(Movie.filterMovieListByGenres(allMovies,Genre.valueOf(genreComboBox.getSelectionModel().getSelectedItem().toString())));
        observableMovies.clear();
        observableMovies.addAll(filteredMovies);         // add dummy data to observable list
        // initialize UI stuff
        movieListView.setItems(null);
        movieListView.setItems(observableMovies);
        System.out.println(genreComboBox.getSelectionModel().getSelectedItem().toString());
    }

    public List<Movie> getFilteredMovies() {
        return filteredMovies;
    }

    public void setFilteredMovies(List<Movie> filteredMovies) {
        this.filteredMovies = filteredMovies;
    }
}