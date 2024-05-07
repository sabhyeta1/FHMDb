package at.ac.fhcampuswien.fhmdb.database;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@DatabaseTable(tableName = "Movie")
public class MovieEntity {
    @DatabaseField(generatedId = true)
    private long id;
    @DatabaseField()

    String apiId;
    @DatabaseField()
    String title;
    @DatabaseField()
    String description;
    @DatabaseField()
    String genres;
    @DatabaseField()
    int releaseYear;
    @DatabaseField()
    String imgUrl;
    @DatabaseField()
    int lengthInMinutes;
    @DatabaseField()
    double rating;
    static List<Movie> staticMovieList = new ArrayList<>();

    public MovieEntity() {
    }

    public MovieEntity(String apiId, String title, String description, String genres, int releaseYear, String imgUrl, int lengthInMinutes, double rating) {
        this.apiId = apiId;
        this.title = title;
        this.description = description;
        this.genres = genres;
        this.releaseYear = releaseYear;
        this.imgUrl = imgUrl;
        this.lengthInMinutes = lengthInMinutes;
        this.rating = rating;
    }

    static String genresToString(List<Genre> genres) {

        return genres.toString();

    }

    public static List<MovieEntity> fromMovies(List<Movie> movies) {
        List<MovieEntity> movieEntityList = new ArrayList<>();
        for (Movie movie : movies) {
            // addStaticMovieList(movie);
            MovieEntity movieEntity = new MovieEntity(
                    movie.getId(),
                    movie.getTitle(),
                    movie.getDescription(),
                    movie.getGenres(),
                    movie.getReleaseYear(),
                    movie.getImgUrl(),
                    movie.getLengthInMinutes(),
                    movie.getRating()
            );
            movieEntityList.add(movieEntity);
        }
        // setStaticMovieList(movies);
        return movieEntityList;
    }

    public static List<Movie> toMovies(List<MovieEntity> movieEntities) {
        List<Movie> movieList = new ArrayList<>();
        List<Movie> movieListFromFile = null;
        try {
            movieListFromFile = Movie.readMoviesFromFile("Text.txt");
           // throw new IOException();
            for (MovieEntity movieEntity : movieEntities) {

                for (int i = 0; i < movieListFromFile.size(); i++) {
                    if (movieEntity.title.equals(movieListFromFile.get(i).getTitle()) && !movieList.contains(movieListFromFile.get(i))) {
                        movieList.add(movieListFromFile.get(i));
                        break;
                    }
                }
            }


        } catch (IOException e) {
            for (MovieEntity movieEntity : movieEntities){
              //  movieList.add(new Movie(movieEntity.title, movieEntity.description,Genre.fromString(movieEntity.genres),movieEntity.apiId, movieEntity.releaseYear, movieEntity.imgUrl, movieEntity.lengthInMinutes, movieEntity.rating));
                movieList.add(new Movie(movieEntity.title, movieEntity.description,Genre.fromString(movieEntity.genres),movieEntity.apiId, movieEntity.releaseYear, movieEntity.imgUrl, movieEntity.lengthInMinutes,new String[]{},new String[]{},new String[]{}, movieEntity.rating));

            }
        }

        // System.out.println(movieListFromFile);

        return movieList;
    }
}
   /* public static void addStaticMovieList(Movie movie){
        staticMovieList.add(movie);
    }
    public void deleteStaticMovieList(){
        staticMovieList.clear();
    }

    public static List<Movie> getStaticMovieList() {
        return staticMovieList;
    }

    public static void setStaticMovieList(List<Movie> staticMovieList) {
        MovieEntity.staticMovieList = staticMovieList;
    }

    */
// List<MovieEntity>fromMovies(List<Movie>movies){}
   // List<Movie>toMovies(List<MovieEntity> movieEntities){}



