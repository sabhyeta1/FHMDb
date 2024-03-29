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
    static OkHttpClient client = new OkHttpClient();

    static public List<Movie> run(String url) {
        Request request = new Request.Builder()
                .url(url)
                .header("User-Agent", "http.agent")
                .build();

        try (Response response = client.newCall(request).execute()) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Movie>>(){}.getType();

            return gson.fromJson(response.body().string(), type);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

        System.out.println(MovieAPI.run("https://prog2.fh-campuswien.ac.at/movies"));

    }
}
