package at.ac.fhcampuswien.fhmdb.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Movie {
    private String title;
    private String description;
    private List <Genre> genres;

    public Movie(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Movie(String title, String description, List<Genre> genres) {
        this(title, description);
        this.genres = genres;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public static List<Movie> initializeMovies(){
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Legally Blonde", "xyz", Arrays.asList(Genre.COMEDY,Genre.ACTION)));
        movies.add(new Movie("Rush Hour", "abc", Arrays.asList(Genre.ADVENTURE,Genre.ACTION)));
        movies.add(new Movie("Titanic", "lmnop", Arrays.asList(Genre.DOCUMENTARY,Genre.ACTION)));
        movies.add(new Movie("Train to Busan", "qwertz", Arrays.asList(Genre.HORROR,Genre.ACTION)));





        return movies;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", genres=" + genres +
                '}';
    }
}
