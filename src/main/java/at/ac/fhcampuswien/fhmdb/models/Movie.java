package at.ac.fhcampuswien.fhmdb.models;

import java.util.*;

public class Movie {
    private String title;
    private String description;
    private List <Genre> genres;

    public Movie(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Movie(String title, String description, List<Genre> genres) {
        this(title, description);
        this.genres = genres;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getGenres() { // ADVENTUREDRAMA ->  DRAMA, ROMANCE;
        StringBuilder s = new StringBuilder();
        for (Genre genre : genres) {
            s.append(genre).append(", ");

        }
        s.delete(s.length() - 2, s.length());
        return s.toString();
    }

    public static List<Movie> initializeMovies(){
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Legally Blonde", "Elle Woods, a fashionable sorority queen, is dumped by her boyfriend. She decides to follow him to law school. While there, she figures out that there is more to her than just looks.", Arrays.asList(Genre.COMEDY)));
        movies.add(new Movie("The Dark Knight", "When the menace known as the Joker emerges from his mysterious past, he wreaks havoc and chaos on the people of Gotham. The Dark Knight must accept one of the greatest psychological and physical tests of his ability to fight injustice.", Arrays.asList(Genre.ACTION, Genre.DRAMA, Genre.CRIME)));
        movies.add(new Movie("Inception", "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a CEO.", Arrays.asList(Genre.ACTION, Genre.SCIENCE_FICTION, Genre.THRILLER)));
       /* movies.add(new Movie("The Notebook", "Based on Nicholas Sparks' novel, this film tells the unforgettable story of Noah and Allie, two young lovers torn apart by fate and class differences in the 1940s South.", Arrays.asList(Genre.DRAMA, Genre.ROMANCE)));
        movies.add(new Movie("La La Land", "A vibrant celebration of love, dreams, and the pursuit of happiness in Los Angeles. Mia, an aspiring actress, and Sebastian, a dedicated jazz musician, explore the joy and pain of pursuing your dreams.", Arrays.asList(Genre.COMEDY, Genre.DRAMA, Genre.ROMANCE)));
        movies.add(new Movie("The Grand Budapest Hotel", "A writer encounters the owner of an aging high-class hotel, who tells him of his early years serving as a lobby boy in the hotel's glorious years under an exceptional concierge.", Arrays.asList(Genre.COMEDY, Genre.DRAMA, Genre.ADVENTURE)));
        movies.add(new Movie("Get Out", "A young African-American visits his white girlfriend's parents for the weekend, where his simmering uneasiness about their reception of him eventually reaches a boiling point.", Arrays.asList(Genre.HORROR, Genre.THRILLER, Genre.MYSTERY)));
        movies.add(new Movie("Pride and Prejudice", "A romantic drama based on Jane Austen's novel. It follows Elizabeth Bennet's development, who learns the error of making hasty judgments and appreciates the difference between superficial goodness and actual goodness.", Arrays.asList(Genre.DRAMA, Genre.ROMANCE)));
        movies.add(new Movie("Parasite", "A South Korean black comedy thriller that tells the story of the Kim family, a poor household scheming to become employed by the wealthy Park family by infiltrating their household and posing as unrelated, highly qualified individuals.", Arrays.asList(Genre.COMEDY, Genre.DRAMA, Genre.THRILLER)));

        */


        return movies;
    }

    public static List<Movie> sortListAlphabetically(List<Movie> movieList, boolean swap) {
        List<String> titleList = new ArrayList<>();
        List<Movie> sortedMovie = new ArrayList<>();
        for (int i = 0; i < movieList.size(); i++) {
            titleList.add(movieList.get(i).getTitle());
        }
        if (swap) {
            Collections.sort(titleList);
        } else titleList.sort(Comparator.reverseOrder());

        for (String title : titleList){

            for (int i =0;i< movieList.size(); i++){
                if (movieList.get(i).getTitle().equals(title)) {
                    sortedMovie.add(movieList.get(i));
                }
            }
        }

        return sortedMovie;
    }

    public static List<Movie> filterMovieListByGenres(List<Movie>movieList, Genre genre){
        try {
            genre.toString();
        } catch (NullPointerException e){
            return movieList;
        }

        if (genre==Genre.NONE){
            return movieList;
        }

        List<Movie>filteredMovieGenreList = new ArrayList<>();
        for (int i=0;i< movieList.size();i++){
            if (movieList.get(i).getGenres().contains(genre.toString())){
                filteredMovieGenreList.add(movieList.get(i));
            }
        }
        return filteredMovieGenreList;
    }

    public static List<Movie> filterMovieListByQuery(List<Movie>movieList, String Query) {
        if (Query.isEmpty()){
            return movieList;
        }
        List<Movie> filteredMovieListQuery = new ArrayList<>();
        for (int i= 0; i<movieList.size(); i++){
            if (movieList.get(i).getTitle().toUpperCase().contains(Query.toUpperCase()) || (movieList.get(i).getDescription().toUpperCase().contains(Query.toUpperCase()))) {
                filteredMovieListQuery.add(movieList.get(i));
            }
        }
    return filteredMovieListQuery;
    }

    public static List<Movie> filterMovieLists(List<Movie>movieList, Genre genre, String query){

        if (genre==null && query.isBlank()){
            return movieList;
        } else if (genre!=null && query.isBlank()) {
            return filterMovieListByGenres(movieList, genre);
        } else if (genre==null) {
            return filterMovieListByQuery(movieList, query);
        } else {
            return filterMovieListByQuery(filterMovieListByGenres(movieList, genre), query);
        }

       // return movieList;
    }

    @Override
    public String toString() {
        return "{" + title +
                "," + description +
                ", " + genres.toString() + "}\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(title, movie.title) && Objects.equals(description, movie.description) && Objects.equals(genres, movie.genres);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, genres);
    }
}
