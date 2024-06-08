package at.ac.fhcampuswien.fhmdb.State;

import at.ac.fhcampuswien.fhmdb.models.Movie;

import java.util.List;

public class State {
    private SortState currentState;

    public State() {
        this.currentState = new UnsortedState();
    }

    public SortState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(SortState currentState) {
        this.currentState = currentState;
    }

    public List<Movie> sort(List<Movie>movies){
        return currentState.sorted(movies);
    }
}
