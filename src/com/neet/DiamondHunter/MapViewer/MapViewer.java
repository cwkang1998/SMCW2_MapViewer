package com.neet.DiamondHunter.MapViewer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MapViewer extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane page = FXMLLoader.load(getClass().getResource("MapViewer.fxml"));
        Scene scene = new Scene(page);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    public static void main(String[] args){
        launch(args);
    }
}
