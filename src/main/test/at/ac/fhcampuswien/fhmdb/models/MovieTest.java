package at.ac.fhcampuswien.fhmdb.models;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;


class MovieTest {

    @Test
    void initializeMovies() {

        //Arrange - variablen werden deklariert
        List<Movie> expected = new ArrayList<>();
        expected.add(new Movie("Legally Blonde", "xyz", Arrays.asList(Genre.COMEDY,Genre.ACTION)));
        expected.add(new Movie("Rush Hour", "abc", Arrays.asList(Genre.ADVENTURE,Genre.ACTION)));
        expected.add(new Movie("Titanic", "lmnop", Arrays.asList(Genre.DOCUMENTARY,Genre.ACTION)));
        expected.add(new Movie("Train to Busan", "qwertz", Arrays.asList(Genre.HORROR,Genre.ACTION)));

        List<Movie> actual = new ArrayList<>();

        boolean check = true;

        //Act - ausführung der methode die ausgeführt wird
        actual = Movie.initializeMovies();
        for (int i = 0; i < expected.size(); i++) {
            // Legally Blonde (expected) == Legally Blonde (actual)
            if (! (expected.get(i).getTitle().equals(actual.get(i).getTitle()))) {
                check = false;
            }
        }


        //Assert - überprüfen, ob es auch stimmt
        System.out.println(expected);
        System.out.println(actual);
        assertTrue(check);


    }
}

