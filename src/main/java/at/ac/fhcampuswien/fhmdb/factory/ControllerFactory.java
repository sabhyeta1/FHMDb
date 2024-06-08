package at.ac.fhcampuswien.fhmdb.factory;

import at.ac.fhcampuswien.fhmdb.HomeController;
import javafx.util.Callback;

public class ControllerFactory implements Callback<Class<?>, Object> {
    private static HomeController instance;

    @Override
    public Object call(Class<?> className) {
            try{
                return getInstance();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

    }


    public static HomeController getInstance() {
        if (instance == null) {
            instance = new HomeController();
        }
        return instance;
    }
}
