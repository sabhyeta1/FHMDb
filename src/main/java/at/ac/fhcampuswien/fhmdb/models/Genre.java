package at.ac.fhcampuswien.fhmdb.models;

import java.util.ArrayList;
import java.util.List;

public enum Genre {
    ACTION,
    ADVENTURE,
    ANIMATION,
    BIOGRAPHY,
    COMEDY,
    CRIME,
    DRAMA,
    DOCUMENTARY,
    FAMILY,
    FANTASY,
    HISTORY,
    HORROR,
    MUSICAL,
    MYSTERY,
    ROMANCE,
    SCIENCE_FICTION,
    SPORT,
    THRILLER,
    WAR,
    WESTERN,
    NONE;
    public static List<Genre> fromString(String str) {
        List<Genre> genres = new ArrayList<>();
        String[] genreNames= str.split(",");

        for (String name : genreNames) {
            try {
                genres.add(Genre.valueOf(name.trim().toUpperCase()));
            } catch (IllegalArgumentException e) {
                // If a string does not match any genre name, ignore it
            }
        }

        return genres;
    }
}

