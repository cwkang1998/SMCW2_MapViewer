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

    @FXML
    private Canvas canvas;
    private MapDrawer tilemap;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane page = FXMLLoader.load(getClass().getResource("MapViewer.fxml"));
        Scene scene = new Scene(page);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

    }


    public void initialize() {

        tilemap = new MapDrawer("/Maps/testmap.map", 16);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        tilemap.drawMap(gc);
    }

    public static void main(String[] args){
        launch(args);
    }
}
