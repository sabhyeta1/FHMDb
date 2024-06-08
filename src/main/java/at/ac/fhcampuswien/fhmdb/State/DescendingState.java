package at.ac.fhcampuswien.fhmdb.State;

import at.ac.fhcampuswien.fhmdb.models.Movie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DescendingState implements SortState {
    @Override
    public List<Movie> sorted(List<Movie> movies) {

        return movies.stream()
                .sorted(Comparator.comparing(Movie::getTitle).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public SortState getNextState() {
        return new AscendingState();

    }

    @Override
    public String getText() {
        return "Sort (desc)";
    }
}
