package com.neet.DiamondHunter.MapViewer;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import java.io.*;

public class MapViewerController {

    @FXML
    private Canvas canvas;
    private MapDrawer tileMap;

    @FXML
    private Button btnAxe;

    @FXML
    private Button btnBoat;

    /**
     * if axe is chosen
     * coordinates[0]=x;
     * coordinates[1]=y;
     * if boat is chosen
     * coordinates[2]=i;
     * coordinates[3]=j;
     */
    private int xCo, yCo;
    private int[] coordinates = new int[4];
    private boolean isValid = false;
    private boolean axe = false;
    private boolean boat = false;


    public void initialize() {

        tileMap = new MapDrawer(canvas);
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
            if (tileMap.isClickable(xCo, yCo)) {
                isValid = false;
            } else {
                isValid = true;
            }
        });
    }

    @FXML
    public void setLocation() {
        canvas.setOnMouseClicked(event -> {
            if (isValid) {
                if (boat) {
                    coordinates[0] = xCo;
                    coordinates[1] = yCo;
                    System.out.println(coordinates[0] + " " + coordinates[1] + " " + coordinates[2] + " " + coordinates[3]);
                    saveNewCoordinates();
                    render();
                }

                if (axe) {
                    coordinates[2] = xCo;
                    coordinates[3] = yCo;
                    System.out.println(coordinates[0] + " " + coordinates[1] + " " + coordinates[2] + " " + coordinates[3]);
                    saveNewCoordinates();
                    render();
                }
            } else {
                if (boat || axe) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Warning");
                    alert.setHeaderText(null);
                    alert.setContentText("Access Denied");
                    alert.showAndWait();
                }
            }
        });
    }


    @FXML
    public void resetToDefaultCoordinates() {
        boat = axe = false;
        coordinates[0] = MapDrawer.DEFAULT_COORDINATE[0];
        coordinates[1] = MapDrawer.DEFAULT_COORDINATE[1];
        coordinates[2] = MapDrawer.DEFAULT_COORDINATE[2];
        coordinates[3] = MapDrawer.DEFAULT_COORDINATE[3];
        saveNewCoordinates();
        render();
    }

    @FXML
    public void showInformation() {
        String instructions = "Buttons:\n\n 1) Axe\t\t: Set Axe Location\n 2) Boat\t\t: Set Boat Location\n " +
                "3) Default\t: Reset coordinates to defaults.\n 4) Undo\t\t: Undo to previous changes.\n " +
                "5) Info\t\t: Show instruction of MapViewer.";
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(null);
        alert.setHeaderText("MapViewer Instruction");
        alert.setContentText(instructions);
        alert.showAndWait();
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
        tileMap.drawMap();
        tileMap.drawItems(coordinates[0], coordinates[1], coordinates[2], coordinates[3]);
    }
}