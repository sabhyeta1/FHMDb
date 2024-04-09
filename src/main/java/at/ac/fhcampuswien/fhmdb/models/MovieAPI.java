package at.ac.fhcampuswien.fhmdb.models;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class MovieAPI {
    // https://prog2.fh-campuswien.ac.at/movies -> https://prog2.fh-campuswien.ac.at/movies?query=wrgg&genre=ROMANCE&releaseYear=2010&ratingFrom=4' -> ...movies? query,genre,releaseYear und rating Abfrage; url anhängen wenn stimmt
    static OkHttpClient client = new OkHttpClient();

    static public List<Movie> run(String url)  {
        Request request = new Request.Builder()
                .url(url)
                .header("USER-AGENT","http.agent")
                .build();

        try (Response response = client.newCall(request).execute()) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Movie>>(){}.getType();
            assert response.body() != null;
            //https://stackoverflow.com/questions/20773850/gson-typetoken-with-dynamic-arraylist-item-type
            return gson.fromJson(response.body().string(),type);
        } catch (IOException e){
            return Movie.initializeMovies();
        }
    }
    public static List<Movie> filterMovieListByUrl(String searchText, Genre selectedGenre, Integer selectedReleaseYear, Double selectedRating) {
        StringBuilder stb = new StringBuilder("https://prog2.fh-campuswien.ac.at/movies?");

        if (searchText.isBlank()) {
            // searchText = "";

        } else stb.append("&query=").append(searchText);


        if (selectedGenre == null) {
            // selectedGenre = Genre.NONE;

        } else  stb.append("&genre=").append(selectedGenre);

        if (selectedReleaseYear == null) {
            //selectedReleaseYear = -1;


        } else stb.append("&releaseYear=").append(selectedReleaseYear);

        if (selectedRating == -1.0){

        } else  stb.append("&ratingFrom=").append(selectedRating);

        return MovieAPI.run(stb.toString());
    }

    public static void main(String[] args) {
        MovieAPI movieAPI = new MovieAPI();
        List<Movie> res = movieAPI.run("https://prog2.fh-campuswien.ac.at/movies");

    }
}
