package at.ac.fhcampuswien.fhmdb.models;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;


class MovieTest {

    @Test
    void check_if_movies_get_created_correctly_by_checking_movie_title() {

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

    @Test
    void check_if_asc_makes_the_movieList_in_alphabetic_order() {
        //Assert
        List<Movie> movies = Movie.initializeMovies();
        List<Movie> expected = new ArrayList<>();
        List<Movie> actual = new ArrayList<>();
        boolean checker = true;

        expected.add(new Movie("Inception", "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a CEO.", Arrays.asList(Genre.ACTION, Genre.SCIENCE_FICTION, Genre.THRILLER)));
        expected.add(new Movie("Legally Blonde", "Elle Woods, a fashionable sorority queen, is dumped by her boyfriend. She decides to follow him to law school. While there, she figures out that there is more to her than just looks.", Arrays.asList(Genre.COMEDY)));
        expected.add(new Movie("The Dark Knight", "When the menace known as the Joker emerges from his mysterious past, he wreaks havoc and chaos on the people of Gotham. The Dark Knight must accept one of the greatest psychological and physical tests of his ability to fight injustice.", Arrays.asList(Genre.ACTION, Genre.DRAMA, Genre.CRIME)));

        //Act
        actual=Movie.sortListAlphabetically(movies,true);

        for (int i = 0; i < expected.size(); i++) {
            // Legally Blonde (expected) == Legally Blonde (actual)
            if (!(expected.get(i).getTitle().equals(actual.get(i).getTitle()))) {
                checker = false;
            }
        }
        //Arrange
        System.out.println(expected+"\n"+actual);
        assertTrue(checker);
    }
    @Test
    void check_if_desc_makes_the_movieList_in_reverse_alphabetic_order() {
        //Arrange
        List<Movie> movieList = Movie.initializeMovies();
        List<Movie> expected = new ArrayList<>();
        List<Movie> actual = new ArrayList<>();
        boolean checker = true;

        expected.add(new Movie("The Dark Knight", "When the menace known as the Joker emerges from his mysterious past, he wreaks havoc and chaos on the people of Gotham. The Dark Knight must accept one of the greatest psychological and physical tests of his ability to fight injustice.", Arrays.asList(Genre.ACTION, Genre.DRAMA, Genre.CRIME)));
        expected.add(new Movie("Legally Blonde", "Elle Woods, a fashionable sorority queen, is dumped by her boyfriend. She decides to follow him to law school. While there, she figures out that there is more to her than just looks.", Arrays.asList(Genre.COMEDY)));
        expected.add(new Movie("Inception", "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a CEO.", Arrays.asList(Genre.ACTION, Genre.SCIENCE_FICTION, Genre.THRILLER)));

        //Act
        actual=Movie.sortListAlphabetically(movieList,false);

        for (int i = 0; i < expected.size(); i++) {
            // Legally Blonde (expected) == Legally Blonde (actual)
            if (!(expected.get(i).getTitle().equals(actual.get(i).getTitle()))) {
                checker = false;
            }
        }
        //Arrange
        System.out.println(expected+"\n"+actual);
        assertTrue(checker);
    }
}

