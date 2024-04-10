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
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
    public void filterMovieList() {

        try {
            setFilteredMovies(Movie.filterMovieLists(allMovies, (Genre) genreComboBox.getValue(),searchField.getText()));
            observableMovies.clear();
            observableMovies.addAll(filteredMovies);
        } catch (NullPointerException e){}
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

        setFilteredMovies(MovieAPI.filterMovieListByUrl(searchField.getText(), (Genre) genreComboBox.getValue(), (Integer) releaseYearComboBox.getValue(),selectedRating));

        observableMovies.clear();
        observableMovies.addAll(filteredMovies);

        // initialize UI stuff
        movieListView.setItems(null);
        movieListView.setItems(observableMovies);

        genreComboBox.getSelectionModel().clearSelection();
        releaseYearComboBox.getSelectionModel().clearSelection();
        ratingComboBox.getSelectionModel().clearSelection();
    }
    public static String getMostPopularActor(List<Movie> movies){
        for (int i = 0; i<movies.size();i++){
            for (int e =0; e<movies.get(i).getMainCast().length;e++){
                //speicher werte von main cast here ->movies.get(i).getMainCast()[e]
            }
        }
        Stream<String[]> mainCast=movies.stream().map(Movie::getMainCast);
        //maincast= Maincast1[], MainCast2[],...
        List<String>stringList=mainCast.flatMap(Arrays::stream).toList();
        Map<String,Long> hashList =  stringList.stream().collect(Collectors.groupingBy(s -> s, Collectors.counting()));
        //hashList=hashList.entrySet().stream().max(Map.Entry.comparingByKey());
        // source https://stackoverflow.com/questions/5911174/finding-key-associated-with-max-value-in-a-java-map
        try {

            return Collections.max(hashList.entrySet(),Map.Entry.comparingByValue()).getKey();

        } catch (NoSuchElementException e){
            return null;
        }
    }





    public static int getLongestMovieTitle(List<Movie> movies){
        try {

            return movies.stream()
                    .mapToInt(w -> w.getTitle().length())
                    .max()
                    .getAsInt();
        } catch (NoSuchElementException e){
            return 0;
        }


    }

    public static long countMoviesFrom(List<Movie> movies, String director){
      try {

            return movies.stream()   //"{Director1,Director2,director...}"  =>director?
                    .filter(movie -> Arrays.toString(movie.getDirectors()).contains(director))
                    .count();

        } catch (NoSuchElementException e){
            return 0;
        }


    }


    public static List<Movie> getMoviesBetweenYears(List<Movie> movies, int startYear, int endYear){
        try {

            return movies.stream()
                    .filter(movie -> movie.getReleaseYear()>=startYear && endYear>=movie.getReleaseYear())
                    .toList();
        } catch (NoSuchElementException e){
            return null;
        }

    }

    public List<Movie> getFilteredMovies() {
        return filteredMovies;
    }

    public void setFilteredMovies(List<Movie> filteredMovies) {
        this.filteredMovies = filteredMovies;
    }

}