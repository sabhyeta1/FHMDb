package at.ac.fhcampuswien.fhmdb.models;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class MovieTest {

    @Test
    void check_if_movies_get_created_correctly_by_checking_movie_title() {

        //Arrange - variablen werden deklariert
        List<Movie> expected = new ArrayList<>();
        expected.add(new Movie("Legally Blonde", "Elle Woods, a fashionable sorority queen, is dumped by her boyfriend. She decides to follow him to law school. While there, she figures out that there is more to her than just looks.", Arrays.asList(Genre.COMEDY)));
        expected.add(new Movie("The Dark Knight", "When the menace known as the Joker emerges from his mysterious past, he wreaks havoc and chaos on the people of Gotham. The Dark Knight must accept one of the greatest psychological and physical tests of his ability to fight injustice.", Arrays.asList(Genre.ACTION, Genre.DRAMA, Genre.CRIME)));
        expected.add(new Movie("Inception", "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a CEO.", Arrays.asList(Genre.ACTION, Genre.SCIENCE_FICTION, Genre.THRILLER)));


        List<Movie> actual = new ArrayList<>();

        boolean check = true;

        //Act - ausführung der methode die ausgeführt wird
        actual = Movie.initializeMovies();
        for (int i = 0; i < expected.size(); i++) {
            // Legally Blonde (expected) == Legally Blonde (actual)
            if (!(expected.get(i).getTitle().equals(actual.get(i).getTitle()))) {
                check = false;
            }
        }


        //Assert - überprüfen, ob es auch stimmt
        System.out.println(expected);
        System.out.println(actual);
        assertTrue(check);


    }

    @Test
    void check_if_method_set_on_true_returns_the_movieList_in_alphabetic_order() {
        //Assert
        List<Movie> movies = Movie.initializeMovies();
        List<Movie> expected = new ArrayList<>();
        List<Movie> actual = new ArrayList<>();
        boolean checker = true;

        expected.add(new Movie("Inception", "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a CEO.", Arrays.asList(Genre.ACTION, Genre.SCIENCE_FICTION, Genre.THRILLER)));
        expected.add(new Movie("Legally Blonde", "Elle Woods, a fashionable sorority queen, is dumped by her boyfriend. She decides to follow him to law school. While there, she figures out that there is more to her than just looks.", List.of(Genre.COMEDY)));
        expected.add(new Movie("The Dark Knight", "When the menace known as the Joker emerges from his mysterious past, he wreaks havoc and chaos on the people of Gotham. The Dark Knight must accept one of the greatest psychological and physical tests of his ability to fight injustice.", Arrays.asList(Genre.ACTION, Genre.DRAMA, Genre.CRIME)));

        //Act
        actual = Movie.sortListAlphabetically(movies, true);

        for (int i = 0; i < expected.size(); i++) {
            // Legally Blonde (expected) == Legally Blonde (actual)
            if (!(expected.get(i).getTitle().equals(actual.get(i).getTitle()))) {
                checker = false;
                break;
            }
        }
        //Arrange
        System.out.println(expected + "\n" + actual);
        assertTrue(checker);
    }

    @Test
    void check_if_method_set_on_false_returns_the_movieList_in_reverse_alphabetic_order() {
        //Arrange
        List<Movie> movieList = Movie.initializeMovies();
        List<Movie> expected = new ArrayList<>();
        List<Movie> actual = new ArrayList<>();
        boolean checker = true;

        expected.add(new Movie("The Dark Knight", "When the menace known as the Joker emerges from his mysterious past, he wreaks havoc and chaos on the people of Gotham. The Dark Knight must accept one of the greatest psychological and physical tests of his ability to fight injustice.", Arrays.asList(Genre.ACTION, Genre.DRAMA, Genre.CRIME)));
        expected.add(new Movie("Legally Blonde", "Elle Woods, a fashionable sorority queen, is dumped by her boyfriend. She decides to follow him to law school. While there, she figures out that there is more to her than just looks.", List.of(Genre.COMEDY)));
        expected.add(new Movie("Inception", "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a CEO.", Arrays.asList(Genre.ACTION, Genre.SCIENCE_FICTION, Genre.THRILLER)));

        //Act
        actual = Movie.sortListAlphabetically(movieList, false);

        for (int i = 0; i < expected.size(); i++) {
            // Legally Blonde (expected) == Legally Blonde (actual)
            if (!(expected.get(i).getTitle().equals(actual.get(i).getTitle()))) {
                checker = false;
                break;
            }
        }
        //Arrange
        System.out.println(expected + "\n" + actual);
        assertTrue(checker);
    }

    @Test
    void check_if_movie_list_is_correctly_filtered_by_genre() {
        //Arrange
        List<Movie> movieList = Movie.initializeMovies();
        List<Movie> expected = new ArrayList<>();
        List<Movie> actual = new ArrayList<>();

        expected.add(new Movie("The Dark Knight", "When the menace known as the Joker emerges from his mysterious past, he wreaks havoc and chaos on the people of Gotham. The Dark Knight must accept one of the greatest psychological and physical tests of his ability to fight injustice.", Arrays.asList(Genre.ACTION, Genre.DRAMA, Genre.CRIME)));
        expected.add(new Movie("Inception", "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a CEO.", Arrays.asList(Genre.ACTION, Genre.SCIENCE_FICTION, Genre.THRILLER)));
        //Act
        actual = Movie.filterMovieListByGenres(movieList, Genre.ACTION);

        //Assert

        assertEquals(expected, actual);

    }

    @Test
    void check_if_movie_list_is_returned_correctly_if_there_is_no_filter() {
        //Arrange
        List<Movie> movieList = Movie.initializeMovies();
        List<Movie> expected = Movie.initializeMovies();
        List<Movie> actual = new ArrayList<>();

        //Act
        actual = Movie.filterMovieListByGenres(movieList, null);

        //Assert
        System.out.println(expected + " \n" + actual);
        assertEquals(expected, actual);

    }

    @Test
    void check_if_movie_list_has_no_elements_when_filtered_by_genre_with_no_films() {
        //Arrange
        List<Movie> movieList = Movie.initializeMovies();
        List<Movie> expected = new ArrayList<>();
        List<Movie> actual = new ArrayList<>();

        //Act
        actual = Movie.filterMovieListByGenres(movieList, Genre.DOCUMENTARY);

        //Assert
        System.out.println(expected + " \n" + actual);
        assertEquals(expected, actual);

    }

    @Test
    void check_if_movie_list_contains_filtered_elements_from_filtered_movie_list_after_being_sorted() {
        //Arrange
        List<Movie> movieList = Movie.initializeMovies();
        List<Movie> filteredMovieList = Movie.filterMovieListByGenres(movieList, Genre.ACTION);
        List<Movie> expected = new ArrayList<>();
        List<Movie> actual = new ArrayList<>();

        expected.add(new Movie("Inception", "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a CEO.", Arrays.asList(Genre.ACTION, Genre.SCIENCE_FICTION, Genre.THRILLER)));
        expected.add(new Movie("The Dark Knight", "When the menace known as the Joker emerges from his mysterious past, he wreaks havoc and chaos on the people of Gotham. The Dark Knight must accept one of the greatest psychological and physical tests of his ability to fight injustice.", Arrays.asList(Genre.ACTION, Genre.DRAMA, Genre.CRIME)));

        //Act
        actual = Movie.sortListAlphabetically(filteredMovieList, true);

        //Assert
        System.out.println(expected + " \n" + actual);
        assertEquals(expected, actual);

    }

    @Test
    void test_if_filter_movie_list_by_query_returns_matching_movies() {
        // Arrange
        List<Movie> movieList = Movie.initializeMovies();
        List<Movie> expected = new ArrayList<>();
        List<Movie> actual = new ArrayList<>();
        String query = "Leg";

        expected.add(new Movie("Legally Blonde", "Elle Woods, a fashionable sorority queen, is dumped by her boyfriend. She decides to follow him to law school. While there, she figures out that there is more to her than just looks.", Arrays.asList(Genre.COMEDY)));


        // Act
        actual = Movie.filterMovieListByQuery(movieList, query);

        // Assert
        System.out.println(expected + "\n" + actual);
        assertEquals(expected, actual);
    }

    @Test
    void check_if_movie_gets_back_origin_list_if_query_is_empty() {
        //Arrange
        List<Movie> movieList = Movie.initializeMovies();
        List<Movie> expected = new ArrayList<>();
        List<Movie> actual = new ArrayList<>();
        String query = "";

        expected.add(new Movie("Legally Blonde", "Elle Woods, a fashionable sorority queen, is dumped by her boyfriend. She decides to follow him to law school. While there, she figures out that there is more to her than just looks.", Arrays.asList(Genre.COMEDY)));
        expected.add(new Movie("The Dark Knight", "When the menace known as the Joker emerges from his mysterious past, he wreaks havoc and chaos on the people of Gotham. The Dark Knight must accept one of the greatest psychological and physical tests of his ability to fight injustice.", Arrays.asList(Genre.ACTION, Genre.DRAMA, Genre.CRIME)));
        expected.add(new Movie("Inception", "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a CEO.", Arrays.asList(Genre.ACTION, Genre.SCIENCE_FICTION, Genre.THRILLER)));

        //Act
        actual = Movie.filterMovieListByQuery(movieList, query);

        //Assert
        System.out.println(expected + "\n" + actual);
        assertEquals(expected, actual);

    }

    @Test
    void check_if_movie_gets_back_origin_list_if_query_is_invalid() {
        //Arrange
        List<Movie> movieList = Movie.initializeMovies();
        List<Movie> expected = new ArrayList<>();
        List<Movie> actual = new ArrayList<>();
        String query = "*";

        //Act
        actual = Movie.filterMovieListByQuery(movieList, query);

        //Assert
        System.out.println(expected + "\n" + actual);
        assertEquals(expected, actual);
    }

}



