package com.neet.DiamondHunter.MapViewer;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class MapViewerController {

    @FXML
    private Canvas canvas;
    private MapDrawer tilemap;

    boolean isValid;

    @FXML
    public void validation() {
        canvas.setOnMouseMoved(event -> {
            if (tilemap.clickable((int) event.getX() / 16, (int) event.getY() / 16)) {
                isValid = true;
            } else {
                isValid = false;
            }
        });
    }


    public void initialize() {

        tilemap = new MapDrawer("/Maps/testmap.map", 16);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        tilemap.drawMap(gc);
    }
}