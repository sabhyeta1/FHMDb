package at.ac.fhcampuswien.fhmdb;

import java.io.IOException;

public class MovieApiException extends Exception {
    public MovieApiException(String msg){
        super(msg);
    }
    public MovieApiException(String msg,Throwable cause){
        super(msg,cause);
    }
}
