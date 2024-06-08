package at.ac.fhcampuswien.fhmdb.State;

import at.ac.fhcampuswien.fhmdb.models.Movie;

import java.util.List;

public interface SortState {
    List<Movie>sorted(List<Movie>movies);
    SortState getNextState();
    String getText();
}
