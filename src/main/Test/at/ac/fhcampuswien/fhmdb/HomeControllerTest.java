package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

    class HomeControllerTest {
        static List<Movie>listMovie =new ArrayList<>();
        @BeforeAll
        static void setup(){
            listMovie = new ArrayList<>();
            listMovie.add(new Movie("The Lord of the Rings: The Two Towers", 2002, new String[]{"Peter Jackson"}, new String[]{"Elijah Wood", "Ian McKellen", "Viggo Mortensen"}));
            listMovie.add(new Movie("The Godfather", 1972, new String[]{"Francis Ford Coppola"}, new String[]{"Marlon Brando", "Elijah Wood", "James Caan"}));
            listMovie.add(new Movie("The Shawshank Redemption", 1994, new String[]{"Francis Ford Coppola"}, new String[]{"Tim Robbins", "Morgan Freeman", "Bob Gunton"}));
            System.out.println("Setup");
        }
        @Test
        void check_if_method_corresponds_correctly_by_returning_null_if_movie_list_is_empty_and_it_doesnt_crash_by_null_pointer_exception() {
            // Arrange
            List<Movie> emptyMovies = List.of();

            // Act
            String mostPopularActor = HomeController.getMostPopularActor(emptyMovies);

            // Assert
            assertNull(mostPopularActor);
        }

        @Test
        void check_if_method_corresponds_correctly_if_method_returns_Actor_which_occurs_most_often_in_main_cast_from_the_movies() {
            //Arrange
            List<Movie> movieList = new ArrayList<>();
            String expected = "Elijah Wood";
            String actual = "";

            movieList.add(new Movie("The Lord of the Rings: The Two Towers", 2002, new String[]{"Peter Jackson"}, new String[]{"Elijah Wood", "Ian McKellen", "Viggo Mortensen"}));
            //movieList.add(new Movie("The Godfather",1972, new String[]{"Francis Ford Coppola"},new String[]{  "Marlon Brando", "Al Pacino", "James Caan"}));
            movieList.add(new Movie("The Godfather", 1972, new String[]{"Francis Ford Coppola"}, new String[]{"Marlon Brando", "Elijah Wood", "James Caan"}));
            movieList.add(new Movie("The Shawshank Redemption", 1994, new String[]{"Francis Ford Coppola"}, new String[]{"Tim Robbins", "Morgan Freeman", "Bob Gunton"}));

            //Act
            actual = HomeController.getMostPopularActor(movieList);
            System.out.println(expected + "\n" + actual);

            //Assert
            assertEquals(expected, actual);
        }

        @Test
        void check_if_getLongestMovieTitle_returns_0_by_giving_the_method_an_empty_movie_list_() {
            // Arrange
            List<Movie> emptyMovies = Collections.emptyList();

            // Act
            int longestTitleLength = HomeController.getLongestMovieTitle(emptyMovies);

            // Assert
            assertEquals(0, longestTitleLength, "Should return 0 for an empty list of movies");
        }

        @Test
        void check_if_method_returns_correct_Number_with_the_highest_title_length_() {
            //Arrange
            List<Movie> movieList = new ArrayList<>();
            int expected = 15;
            int actual = 0;

            movieList.add(new Movie("Legally Blonde", "Elle Woods, a fashionable sorority queen, is dumped by her boyfriend. She decides to follow him to law school. While there, she figures out that there is more to her than just looks.", List.of(Genre.COMEDY)));
            movieList.add(new Movie("The Dark Knight", "When the menace known as the Joker emerges from his mysterious past, he wreaks havoc and chaos on the people of Gotham. The Dark Knight must accept one of the greatest psychological and physical tests of his ability to fight injustice.", Arrays.asList(Genre.ACTION, Genre.DRAMA, Genre.CRIME)));
            movieList.add(new Movie("Inception", "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a CEO.", Arrays.asList(Genre.ACTION, Genre.SCIENCE_FICTION, Genre.THRILLER)));

            //Act
            actual = HomeController.getLongestMovieTitle(movieList);

            //Assert
            System.out.println(expected + "\n" + actual);
            assertEquals(expected, actual);
        }

        @Test
        void check_if_countMoviesFrom_returns_0_by_giving_the_method_an_empty_movie_list_() {
            // Arrange
            List<Movie> emptyMovies = Collections.emptyList();
            String director = "DirectorX";

            // Act
            long actual = HomeController.countMoviesFrom(emptyMovies, director);

            // Assert
            assertEquals(0, actual, "Should return 0 for an empty list of movies");
        }

        @Test
        void check_if_method_returns_0_by_giving_the_method_a_movie_list_but_with_a_director_which_doesnt_contain_in_any_movie() {
            // Arrange
            List<Movie> movieList = new ArrayList<>();
            movieList.add(new Movie("The Lord of the Rings: The Two Towers", 2002, new String[]{"Peter Jackson"}, new String[]{"Elijah Wood", "Ian McKellen", "Viggo Mortensen"}));
            movieList.add(new Movie("TThe Godfather", 1972, new String[]{"Francis Ford Coppola"}, new String[]{"Marlon Brando", "Al Pacino", "James Caan"}));
            movieList.add(new Movie("The Shawshank Redemption", 1994, new String[]{"Francis Ford Coppola"}, new String[]{"Tim Robbins", "Morgan Freeman", "Bob Gunton"}));
            String director = "DirectorX";

            // Act
            long actual = HomeController.countMoviesFrom(movieList, director);

            // Assert
            assertEquals(0, actual, "Should return 0 for an empty list of movies");
        }

        @Test
        void check_if_method_returns_correct_number_in_which_how_often_director_exists_in_three_movie_objects() {
            //Arrange
            List<Movie> movieList = new ArrayList<>();
            long expected = 2;
            long actual = 0;
            String director = "Francis Ford Coppola";

            movieList.add(new Movie("The Lord of the Rings: The Two Towers", 2002, new String[]{"Peter Jackson"}, new String[]{"Elijah Wood", "Ian McKellen", "Viggo Mortensen"}));
            movieList.add(new Movie("The Godfather", 1972, new String[]{"Francis Ford Coppola"}, new String[]{"Marlon Brando", "Al Pacino", "James Caan"}));
            movieList.add(new Movie("The Shawshank Redemption", 1994, new String[]{"Francis Ford Coppola"}, new String[]{"Tim Robbins", "Morgan Freeman", "Bob Gunton"}));
            //        movieList.add(new Movie( "The Shawshank Redemption",1994, new String[]{"Frank Darabont"},new String[]{ "Tim Robbins", "Morgan Freeman", "Bob Gunton"}));
            //Act
            actual = HomeController.countMoviesFrom(movieList, director);

            //Assert
            System.out.println(expected + "\n" + actual);
            assertEquals(expected, actual);
        }

        @Test
        void check_if_method_returns_0_by_giving_the_method_an_empty_movie_list() {
            // Arrange
            List<Movie> emptyMovies = Collections.emptyList();
            int startYear = 2000;
            int endYear = 2020;
            int expected = 0;
            int actual=0;
            // Act
            List<Movie> moviesBetweenYears = HomeController.getMoviesBetweenYears(emptyMovies, startYear, endYear);
            assert moviesBetweenYears != null;
            actual=moviesBetweenYears.size();

            // Assert
            assertEquals(expected, actual, "Should return an empty list for an empty list of movies");
        }

        @Test
        void check_if_method_returns_0_by_giving_the_method_an_Movie_list_but_with_valid_release_Years_but_no_movie_in_between() {
            // Arrange
            List<Movie> movieList = new ArrayList<>();
            List<Movie> actual = new ArrayList<>();
            int startYear = 2005;
            int endYear = 2020;
            int expected = 0;

            movieList.add(new Movie("The Lord of the Rings: The Two Towers", 2002, new String[]{"Peter Jackson"}, new String[]{"Elijah Wood", "Ian McKellen", "Viggo Mortensen"}));
            movieList.add(new Movie("The Godfather", 1972, new String[]{"Francis Ford Coppola"}, new String[]{"Marlon Brando", "Al Pacino", "James Caan"}));
            movieList.add(new Movie("The Shawshank Redemption", 1994, new String[]{"Francis Ford Coppola"}, new String[]{"Tim Robbins", "Morgan Freeman", "Bob Gunton"}));


            // Act
            actual = HomeController.getMoviesBetweenYears(movieList, startYear, endYear);

            // Assert
            assert actual != null;
            assertEquals(expected, actual.size(), "Should return an empty list for an empty list of movies");
        }

        @Test
        void check_if_method_returns_correct_movie_list_between_valid_years() {
            //Arrange
            List<Movie> movieList = new ArrayList<>();
            List<Movie> expected = new ArrayList<>();
            List<Movie> actual = new ArrayList<>();

            movieList.add(new Movie("The Lord of the Rings: The Two Towers", 2002, new String[]{"Peter Jackson"}, new String[]{"Elijah Wood", "Ian McKellen", "Viggo Mortensen"}));
            movieList.add(new Movie("The Godfather", 1972, new String[]{"Francis Ford Coppola"}, new String[]{"Marlon Brando", "Al Pacino", "James Caan"}));
            movieList.add(new Movie("The Shawshank Redemption", 1994, new String[]{"Francis Ford Coppola"}, new String[]{"Tim Robbins", "Morgan Freeman", "Bob Gunton"}));

            expected.add(new Movie("The Godfather", 1972, new String[]{"Francis Ford Coppola"}, new String[]{"Marlon Brando", "Al Pacino", "James Caan"}));
            expected.add(new Movie("The Shawshank Redemption", 1994, new String[]{"Francis Ford Coppola"}, new String[]{"Tim Robbins", "Morgan Freeman", "Bob Gunton"}));
            //movieList.add(new Movie( "The Shawshank Redemption",1994, new String[]{"Frank Darabont"},new String[]{ "Tim Robbins", "Morgan Freeman", "Bob Gunton"}));
            //Act
            actual = HomeController.getMoviesBetweenYears(movieList, 1970, 1995);

            //Assert
            // System.out.println(expected + "\n" + actual);
            assertEquals(expected, actual);

        }
    }