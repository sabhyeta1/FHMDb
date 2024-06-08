package at.ac.fhcampuswien.fhmdb.State;

import at.ac.fhcampuswien.fhmdb.models.Movie;

import java.util.List;

public class UnsortedState implements SortState{
    @Override
    public List<Movie> sorted(List<Movie> movies) {
        return movies;
    }

    @Override
    public SortState getNextState() {
        return new AscendingState();

    }

    @Override
    public String getText() {
        return "(not_sorted)";
    }
}
