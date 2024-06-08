package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.factory.ControllerFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class FhmdbApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        ControllerFactory cf = new ControllerFactory();
        FXMLLoader fxmlLoader = new FXMLLoader(FhmdbApplication.class.getResource("home-view.fxml"));
        fxmlLoader.setControllerFactory(cf); // in bearbeitung
        Scene scene = new Scene(fxmlLoader.load(), 950, 700);
        scene.getStylesheets().add(Objects.requireNonNull(FhmdbApplication.class.getResource("styles.css")).toExternalForm());
        stage.setTitle("FHMDb");
        stage.setScene(scene);
        stage.show();
        //DatabaseManager.getDatabase().testDB();
    }

    public static void main(String[] args) {
        launch();
    }
}