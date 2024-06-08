package at.ac.fhcampuswien.fhmdb.observerPattern;

public interface Observable {
    void addObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers(String message);

}
