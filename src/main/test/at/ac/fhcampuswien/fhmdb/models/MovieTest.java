package at.ac.fhcampuswien.fhmdb.models;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;





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

        //Act - ausführung der methode die ausgeführt wird
        actual = Movie.initializeMovies();

        //Assert - überprüfen, ob es auch stimmt
        assertArrayEquals(expected.toArray(), actual.toArray());
        //assertThat(actual).usingRecursiveComparison().isEqualTo(expected);


    }
}

