package at.ac.fhcampuswien.fhmdb.models;

public class UrlBuilder {
    private int rating;
    private int releaseYear;
    private String query;
    private Genre genre;
    private final String URL ="https://prog2.fh-campuswien.ac.at/movies?";

    public UrlBuilder() {
       reset();
    }

    public String build(){
        StringBuilder stb = new StringBuilder(URL);
        if (rating != 0) stb.append("&rating=").append(rating);
        if (releaseYear != 0) stb.append("&releaseYear=").append(releaseYear);
        if (query != null) stb.append("&query=").append(query);
        if (genre != null) stb.append("&genre=").append(genre);
        String result = stb.toString();
        reset();
        return result;
    }
    public UrlBuilder setRating(int rating) {
        this.rating = rating;
        return this;
    }

    public UrlBuilder setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
        return this;

    }

    public UrlBuilder setQuery(String query) {
        this.query = query;
        return this;

    }

    public UrlBuilder setGenre(Genre genre) {
        this.genre = genre;
        return this;

    }
    private void reset() {
        this.rating = 0;
        this.releaseYear = 0;
        this.query = null;
        this.genre = null;
    }

}
