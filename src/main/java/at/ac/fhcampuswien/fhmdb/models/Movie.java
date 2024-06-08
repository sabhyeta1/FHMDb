package at.ac.fhcampuswien.fhmdb.models;

import at.ac.fhcampuswien.fhmdb.database.MovieEntity;

import java.io.*;
import java.util.*;

public class Movie implements Serializable {
    private String title;
    private String description;
    private List <Genre> genres;

    private String id;

    private int releaseYear;
    private String imgUrl;
    private int lengthInMinutes;
    private String [] directors;
    private String [] writers;
    private String [] mainCast;

    private double rating;




    public Movie(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Movie(String title, String description, List<Genre> genres) {
        this(title, description);
        this.genres = genres;
    }

    public Movie(String title, int releaseYear, String[] directors, String[] mainCast) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.directors = directors;
        this.mainCast = mainCast;
    }

    public Movie(String title, String description, List<Genre> genres, String id, int releaseYear, String imgUrl, int lengthInMinutes, double rating) {
        this.title = title;
        this.description = description;
        this.genres = genres;
        this.id = id;
        this.releaseYear = releaseYear;
        this.imgUrl = imgUrl;
        this.lengthInMinutes = lengthInMinutes;
        this.rating = rating;
    }

    public Movie(String title, String description, List<Genre> genres, String id, int releaseYear, String imgUrl, int lengthInMinutes, String[] directors, String[] writers, String[] mainCast, double rating) {
        this.title = title;
        this.description = description;
        this.genres = genres;
        this.id = id;
        this.releaseYear = releaseYear;
        this.imgUrl = imgUrl;
        this.lengthInMinutes = lengthInMinutes;
        this.directors = directors;
        this.writers = writers;
        this.mainCast = mainCast;
        this.rating = rating;
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

    public String getId() {
        return id;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public int getLengthInMinutes() {
        return lengthInMinutes;
    }

    public String[] getDirectors() {
        return directors;
    }

    public String[] getWriters() {
        return writers;
    }

    public String[] getMainCast() {
        return mainCast;
    }

    public double getRating() {
        return rating;
    }

    public static List<Movie> initializeMovies(){
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Legally Blonde", "Elle Woods, a fashionable sorority queen, is dumped by her boyfriend. She decides to follow him to law school. While there, she figures out that there is more to her than just looks.", Arrays.asList(Genre.COMEDY)));
        movies.add(new Movie("The Dark Knight", "When the menace known as the Joker emerges from his mysterious past, he wreaks havoc and chaos on the people of Gotham. The Dark Knight must accept one of the greatest psychological and physical tests of his ability to fight injustice.", Arrays.asList(Genre.ACTION, Genre.CRIME, Genre.DRAMA)));
        movies.add(new Movie("Inception", "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a CEO.", Arrays.asList(Genre.ACTION, Genre.SCIENCE_FICTION, Genre.THRILLER)));
        movies.add(new Movie("The Notebook", "Based on Nicholas Sparks' novel, this film tells the unforgettable story of Noah and Allie, two young lovers torn apart by fate and class differences in the 1940s South.", Arrays.asList(Genre.DRAMA, Genre.ROMANCE)));
        movies.add(new Movie("La La Land", "A vibrant celebration of love, dreams, and the pursuit of happiness in Los Angeles. Mia, an aspiring actress, and Sebastian, a dedicated jazz musician, explore the joy and pain of pursuing your dreams.", Arrays.asList(Genre.COMEDY, Genre.DRAMA, Genre.ROMANCE)));
        movies.add(new Movie("The Grand Budapest Hotel", "A writer encounters the owner of an aging high-class hotel, who tells him of his early years serving as a lobby boy in the hotel's glorious years under an exceptional concierge.", Arrays.asList( Genre.ADVENTURE,Genre.COMEDY, Genre.DRAMA)));
        movies.add(new Movie("Get Out", "A young African-American visits his white girlfriend's parents for the weekend, where his simmering uneasiness about their reception of him eventually reaches a boiling point.", Arrays.asList(Genre.HORROR, Genre.THRILLER, Genre.MYSTERY)));
        movies.add(new Movie("Pride and Prejudice", "A romantic drama based on Jane Austen's novel. It follows Elizabeth Bennet's development, who learns the error of making hasty judgments and appreciates the difference between superficial goodness and actual goodness.", Arrays.asList(Genre.DRAMA, Genre.ROMANCE)));
        movies.add(new Movie("Parasite", "A South Korean black comedy thriller that tells the story of the Kim family, a poor household scheming to become employed by the wealthy Park family by infiltrating their household and posing as unrelated, highly qualified individuals.", Arrays.asList(Genre.COMEDY, Genre.DRAMA, Genre.THRILLER)));
        movies.add(new Movie("Forrest Gump", "The story depicts several decades in the life of Forrest Gump, a slow-witted but kind-hearted and athletically-talented man from Alabama.", Arrays.asList(Genre.DRAMA, Genre.ROMANCE)));
        movies.add(new Movie("The Lion King", "A young lion prince flees his kingdom only to learn the true meaning of responsibility and bravery.", Arrays.asList(Genre.ADVENTURE, Genre.ANIMATION, Genre.DRAMA)));
        movies.add(new Movie("Schindler's List", "In German-occupied Poland during World War II, industrialist Oskar Schindler gradually becomes concerned for his Jewish workforce after witnessing their persecution by the Nazis.", Arrays.asList(Genre.BIOGRAPHY, Genre.DRAMA, Genre.HISTORY)));
        movies.add(new Movie("Deadpool", "A wisecracking mercenary gets experimented on and becomes immortal but ugly, and sets out to track down the man who ruined his looks.", Arrays.asList(Genre.ACTION, Genre.ADVENTURE, Genre.COMEDY)));
        movies.add(new Movie("The Shawshank Redemption", "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.", Arrays.asList(Genre.DRAMA)));
        movies.add(new Movie("Finding Nemo", "After his son is captured in the Great Barrier Reef and taken to Sydney, a timid clownfish sets out on a journey to bring him home.", Arrays.asList(Genre.ADVENTURE, Genre.ANIMATION, Genre.COMEDY)));
        movies.add(new Movie("Pulp Fiction", "The lives of two mob hitmen, a boxer, a gangster's wife, and a pair of diner bandits intertwine in four tales of violence and redemption.", Arrays.asList(Genre.CRIME, Genre.DRAMA)));
        movies.add(new Movie("Toy Story", "A cowboy doll is profoundly threatened and jealous when a new spaceman figure supplants him as top toy in a boy's room.", Arrays.asList(Genre.ANIMATION, Genre.ADVENTURE, Genre.COMEDY)));
        movies.add(new Movie("The Godfather", "The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.", Arrays.asList(Genre.CRIME, Genre.DRAMA)));
        movies.add(new Movie("The Matrix", "A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.", Arrays.asList(Genre.ACTION, Genre.SCIENCE_FICTION)));
        movies.add(new Movie("The Avengers", "Earth's mightiest heroes must come together and learn to fight as a team if they are going to stop the mischievous Loki and his alien army from enslaving humanity.", Arrays.asList(Genre.ACTION, Genre.ADVENTURE, Genre.SCIENCE_FICTION)));
        movies.add(new Movie("The Lord of the Rings: The Fellowship of the Ring", "A meek Hobbit from the Shire and eight companions set out on a journey to destroy the powerful One Ring and save Middle-earth from the Dark Lord Sauron.", Arrays.asList(Genre.ADVENTURE, Genre.FANTASY)));
        movies.add(new Movie("The Social Network", "As Harvard student Mark Zuckerberg creates the social networking site that would become known as Facebook, he is sued by the twins who claimed he stole their idea.", Arrays.asList(Genre.BIOGRAPHY, Genre.DRAMA)));
        movies.add(new Movie("Frozen", "When the newly-crowned Queen Elsa accidentally uses her power to turn things into ice to curse her home in infinite winter, her sister Anna teams up with a mountain man, his playful reindeer, and a snowman to change the weather condition.", Arrays.asList(Genre.ANIMATION, Genre.ADVENTURE, Genre.COMEDY)));
        movies.add(new Movie("Jurassic Park", "During a preview tour, a theme park suffers a major power breakdown that allows its cloned dinosaur exhibits to run amok.", Arrays.asList(Genre.ADVENTURE, Genre.SCIENCE_FICTION)));
        movies.add(new Movie("The Pursuit of Happiness", "A struggling salesman takes custody of his son as he's poised to begin a life-changing professional endeavor.", Arrays.asList(Genre.BIOGRAPHY, Genre.DRAMA)));
        movies.add(new Movie("The Hangover", "Three buddies wake up from a bachelor party in Las Vegas, with no memory of the previous night and the bachelor missing.", Arrays.asList(Genre.COMEDY)));
        movies.add(new Movie("The Silence of the Lambs", "A young FBI cadet must receive the help of an incarcerated and manipulative cannibal killer to help catch another serial killer, a madman who skins his victims.", Arrays.asList(Genre.CRIME, Genre.DRAMA, Genre.THRILLER)));
        movies.add(new Movie("Star Wars: Episode IV - A New Hope", "Luke Skywalker joins forces with a Jedi Knight, a cocky pilot, a Wookiee, and two droids to save the galaxy from the Empire's world-destroying battle station.", Arrays.asList(Genre.ACTION, Genre.ADVENTURE, Genre.FANTASY)));
        movies.add(new Movie("Titanic", "A seventeen-year-old aristocrat falls in love with a kind but poor artist aboard the luxurious, ill-fated R.M.S. Titanic.", Arrays.asList(Genre.DRAMA, Genre.ROMANCE)));
        movies.add(new Movie("The Wizard of Oz", "Dorothy Gale is swept away from a farm in Kansas to a magical land of Oz in a tornado and embarks on a quest with her new friends to see the Wizard who can help her return home to Kansas and help her friends as well.", Arrays.asList(Genre.ADVENTURE, Genre.FAMILY, Genre.FANTASY)));
        movies.add(new Movie("The Departed", "An undercover cop and a mole in the police attempt to identify each other while infiltrating an Irish gang in South Boston.", Arrays.asList(Genre.CRIME, Genre.DRAMA, Genre.THRILLER)));
        movies.add(new Movie("Beauty and the Beast", "A prince cursed to spend his days as a hideous monster sets out to regain his humanity by earning a young woman's love.", Arrays.asList(Genre.ANIMATION, Genre.FAMILY, Genre.FANTASY)));
        movies.add(new Movie("Shrek", "A mean lord exiles fairytale creatures to the swamp of a grumpy ogre, who must go on a quest and rescue a princess for the lord in order to get his land back.", Arrays.asList(Genre.ANIMATION, Genre.ADVENTURE, Genre.COMEDY)));
        movies.add(new Movie("Goodfellas", "The story of Henry Hill and his life in the mob, covering his relationship with his wife Karen Hill and his mob partners Jimmy Conway and Tommy DeVito in the Italian-American crime syndicate.", Arrays.asList(Genre.BIOGRAPHY, Genre.CRIME, Genre.DRAMA)));


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
            return initializeMovies();
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
            return initializeMovies();
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
    public static void writeMoviesToFile(List<Movie> movies) {
        String filename = "Text.txt";
        File file = new File(filename);
        if (file.exists() && file.length() > 0){
            try (FileOutputStream fileOutputStream = new FileOutputStream(filename);
                 ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {

                objectOutputStream.writeObject(movies);
                System.out.println("Movies have been written to " + filename);
            } catch (IOException e) {
                System.err.println("Error writing movies to file: " + e.getMessage());
            }
        }

    }

    public static List<Movie> readMoviesFromFile(String filename)  throws  IOException{
        List<Movie> movies = null;
        try (FileInputStream fileInputStream = new FileInputStream(filename);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            movies = (List<Movie>) objectInputStream.readObject();
            System.out.println("Movies have been read from " + filename);
            return movies;

        } catch (IOException e) {
            System.err.println("Error reading movies from file: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return movies;
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
