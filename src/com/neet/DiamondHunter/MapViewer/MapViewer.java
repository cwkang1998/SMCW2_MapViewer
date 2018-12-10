package com.neet.DiamondHunter.MapViewer;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.canvas.GraphicsContext;


public class MapViewer extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane page = FXMLLoader.load(getClass().getResource("MapViewer.fxml"));
        Scene scene = new Scene(page);
        scene.getStylesheets().add(getClass().getResource("MapViewer.css").toExternalForm());
        primaryStage.setTitle("MapViewer");
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.setResizable(false);
        primaryStage.show();

    }


    public static void main(String[] args){
        launch(args);
    }
}
