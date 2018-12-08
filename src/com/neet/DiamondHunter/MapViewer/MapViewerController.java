package com.neet.DiamondHunter.MapViewer;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;

import java.io.*;

public class MapViewerController {

    @FXML
    private Canvas canvas;
    private MapDrawer tilemap;

    @FXML
    private Button btnAxe;

    @FXML
    private Button btnBoat;

    @FXML
    private Button btnSave;

    /**
     * if axe is chosen
     * coordinates[0]=x;
     * coordinates[1]=y;
     * if boat is chosen
     * coordinates[2]=i;
     * coordinates[3]=j;
     */
    public int coordinates[] = new int[4];
    boolean isValid = false;
    boolean axe = false;
    boolean boat = false;

    public int xCo, yCo;

    public void initialize() {

        tilemap = new MapDrawer(canvas.getGraphicsContext2D());
        readCoordinates();
        render();
        System.out.println(coordinates[0] + " " + coordinates[1] + " " + coordinates[2] + " " + coordinates[3]);

        btnAxe.setOnAction(event -> {
            axe = true;
            boat = false;

        });

        btnBoat.setOnAction(event -> {
            axe = false;
            boat = true;

        });
    }

    @FXML
    public void saveNewCoordinates() {
        String filename = "Resources/Maps/Coordinates.txt";

        BufferedWriter bw;
        FileWriter fw;
        try {
            fw = new FileWriter(filename);
            bw = new BufferedWriter(fw);

            for (int i = 0; i < 4; i++) {
                bw.write(Integer.toString(coordinates[i]));
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (IOException e) {

            e.printStackTrace();

        }
    }

    @FXML
    public void validation() {
        canvas.setOnMouseMoved(event -> {

            xCo = (int) event.getX() / 16;
            yCo = (int) event.getY() / 16;
            if (tilemap.notClickable(xCo, yCo)) {
                isValid = false;
            } else {
                isValid = true;
            }
        });
    }


    @FXML
    public void resetToDefaultCoordinates() {
        System.out.println(coordinates[0] + " " + coordinates[1] + " " + coordinates[2] + " " + coordinates[3]);
        coordinates = MapDrawer.DEFAULT_COORDINATE;
        saveNewCoordinates();
        System.out.println(coordinates[0] + " " + coordinates[1] + " " + coordinates[2] + " " + coordinates[3]);
        render();
    }

    public void readCoordinates() {

        File filename = new File("Resources/Maps/Coordinates.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                coordinates[i] = Integer.parseInt(line);
                i++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void render() {
        tilemap.drawMap();
        tilemap.drawItems(coordinates[0], coordinates[1], coordinates[2], coordinates[3]);
    }
}