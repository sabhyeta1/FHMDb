package at.ac.fhcampuswien.fhmdb.ui;

import at.ac.fhcampuswien.fhmdb.ClickEventHandler;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.Arrays;

public class MovieCell extends ListCell<Movie> implements ClickEventHandler {
    private final Label title = new Label();
    private final Label detail = new Label();
    private final VBox layout = new VBox(title, detail);
    Button actionButton = new Button("add/remove");
    Button removeButton = new Button("remove");


    boolean flag = true;
    public MovieCell (ClickEventHandler<Movie> addToWatchlistClicked){
        super();
        //TODO set Buttontext add/remove watchlist
        //TODO Callback for buttonclick
        actionButton.setOnAction(event -> {
            if (!isEmpty() && getItem() != null) {
                Movie movie = getItem();
                    addToWatchlistClicked.onClick(movie);


                   // removeFromWatchlistClicked.onClick(movie);


            }

        });
       /* removeButton.setOnAction(event -> {
            if (!isEmpty() && getItem() != null) {
                Movie movie = getItem();
                addToWatchlistClicked.onClick(movie);

                removeButton.setVisible(false);
                addButton.setVisible(true);


                // removeFromWatchlistClicked.onClick(movie);


            }
        });

        */

    }
    public MovieCell() {
    }






    @Override
    protected void updateItem(Movie movie, boolean empty) {
        super.updateItem(movie, empty);
        setGraphic(null);

        if (empty || movie == null) {
            setText(null);
        } else {
            this.getStyleClass().add("movie-cell");
            title.setText(movie.getTitle());
            detail.setText(
                    movie.getDescription() != null
                            ? movie.getDescription() +"\n\n Genre: "+movie.getGenres()+"\n\n Rating: "+movie.getRating()+" , Release Year: "+movie.getReleaseYear()//+"\n\n Directors: " + Arrays.toString(movie.getDirectors()).substring(1,Arrays.toString(movie.getDirectors()).length()-1)+" , MainCast: "+ Arrays.toString(movie.getMainCast()).substring(1,Arrays.toString(movie.getMainCast()).length()-1)
                            : "No description available"
            );
           // button.setBackground(new Background(new BackgroundFill(Color.web(""), null, null)));
            actionButton.setBackground(new Background(new BackgroundFill(Color.web("#f5c518"), null, null)));

           // removeButton.setBackground(new Background(new BackgroundFill(Color.web("#f5c518"), null, null)));


            VBox textVBox = new VBox();
            textVBox.getChildren().addAll(title, detail);

            // VBox for button
            VBox buttonVBox = new VBox();
            buttonVBox.getChildren().add(actionButton);
          //  buttonVBox.getChildren().add(removeButton);

            buttonVBox.setAlignment(Pos.CENTER_RIGHT);

            // HBox to hold both VBox containers
            HBox hbox = new HBox();
            hbox.getChildren().addAll(textVBox, buttonVBox);
            hbox.setHgrow(textVBox, Priority.ALWAYS);
           // hbox.setMaxWidth(Region.USE_COMPUTED_SIZE);

            layout.getChildren().clear();
            //layout.getChildren().addAll(title, detail, button);
            layout.getChildren().add(hbox);

            // color scheme
            title.getStyleClass().add("text-yellow");
            detail.getStyleClass().add("text-white");
            layout.setBackground(new Background(new BackgroundFill(Color.web("#454545"), null, null)));

            // layout


            title.fontProperty().set(title.getFont().font(20));
            detail.setMaxWidth(this.getScene().getWidth() - 100);
            detail.setWrapText(true);
            layout.setPadding(new Insets(10));
            layout.spacingProperty().set(10);
            layout.alignmentProperty().set(javafx.geometry.Pos.CENTER_LEFT);
            //layout.getChildren().addAll(title, detail, button);

            setGraphic(layout);

        }
    }

    @Override
    public void onClick(Object o) {

    }

}

