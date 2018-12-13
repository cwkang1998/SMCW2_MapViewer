package com.neet.DiamondHunter.MapViewer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
<<<<<<< HEAD
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

=======
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
>>>>>>> feature/item-locator

public class MapViewer extends Application {

    public final static String COORDINATE_SAVE_FILE = "Coordinates.txt";

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane page = FXMLLoader.load(getClass().getResource("MapViewer.fxml"));
        Scene scene = new Scene(page);
        scene.getStylesheets().add(getClass().getResource("MapViewer.css").toExternalForm());
        primaryStage.setTitle("MapViewer");
        primaryStage.getIcons().add(new Image("/FXML-Image/windowsIcon.png"));
        primaryStage.setTitle(" MapViewer");
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.getIcons().add(new Image("/FXML-Image/windowsIcon.png"));

    }


    public static void main(String[] args) {

        launch(args);
    }
}
