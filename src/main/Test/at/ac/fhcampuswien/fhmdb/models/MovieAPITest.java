package at.ac.fhcampuswien.fhmdb.models;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MovieAPITest {

    @Test
    void test_if_method_corresponds_correctly_with_all_parameters_provided() {
        //Arrange
        String searchText = "even";
        Genre selectedGenre = Genre.CRIME;
        int selectedReleaseYear = 1995;
        double selectedRating = 8;

        List<Movie> expected = new ArrayList<>();
        expected.add(new Movie("Seven", "Two detectives, a rookie and a veteran, hunt a serial killer who uses the seven deadly sins as his modus operandi."));

        // Act
        List<Movie> actual = MovieAPI.filterMovieListByUrl(searchText, selectedGenre, selectedReleaseYear, selectedRating);
        // Assert
        System.out.println(expected +"\n"+ actual);
        assertEquals(expected,actual);


    }

    @Test
    void test_if_method_corresponds_correctly_by_not_changing_the_amount_of_movies_if_you_filter_by_nothing() {
        //Arrange
        int expected = 31;
        List<Movie> actual = MovieAPI.filterMovieListByUrl("", null, null, -1.0);
        // Assert
        System.out.println(expected +"\n"+ actual);
        assertEquals(31,actual.size());


    }
}