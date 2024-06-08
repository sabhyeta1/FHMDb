package at.ac.fhcampuswien.fhmdb.State;

import at.ac.fhcampuswien.fhmdb.models.Movie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AscendingState implements SortState {
    @Override
    public List<Movie> sorted(List<Movie> movies) {
        /*
        List<String> titleList = new ArrayList<>();
        List<Movie> sortedMovie = new ArrayList<>();
        for (Movie movie : movies) {
            titleList.add(movie.getTitle());
        }
        Collections.sort(titleList);

        for (String title : titleList) {

            for (Movie movie : movies) {
                if (movie.getTitle().equals(title)) {
                    sortedMovie.add(movie);
                }
            }
        }
                return sortedMovie;

        */

        return movies.stream()
                .sorted(Comparator.comparing(Movie::getTitle))
                .collect(Collectors.toList());
    }

    @Override
    public SortState getNextState() {
        return new DescendingState();
    }

    @Override
    public String getText() {
        return "Sort (asc)";
    }
}
