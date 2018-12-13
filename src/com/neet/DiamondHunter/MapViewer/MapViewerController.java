package com.neet.DiamondHunter.MapViewer;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static com.neet.DiamondHunter.MapViewer.MapViewer.COORDINATE_SAVE_FILE;

/**
 * Controller for the MapViewer Application.
 * Handles all user input and interaction with MapViewer.
 */
public class MapViewerController {

    @FXML
    private Canvas canvas;

    private MapDrawer tileMap;

    /**
     * if axe is chosen
     * coordinates[0]=x;
     * coordinates[1]=y;
     * if boat is chosen
     * coordinates[2]=i;
     * coordinates[3]=j;
     */
    private boolean axe = false;
    private boolean boat = false;
    private int curMouseX;
    private int curMouseY;

    private int[] coordinates = new int[4];
    private ArrayList<int[]> coordinateHistory;

    /**
     * Initialize function, meant for fxml loader to initialize the controller
     */
    public void initialize() {
        this.tileMap = new MapDrawer(canvas);
        this.coordinateHistory = new ArrayList<>();
        readCoordinates();
        render();
    }

    /**
     * Handle Axe Button Click event
     */
    @FXML
    public void onBtnAxeClicked() {
        axe = true;
        boat = false;
        render();
    }

    /**
     * Handle Boat Button Click event
     */
    @FXML
    public void onBtnBoatClicked() {
        axe = false;
        boat = true;
        render();
    }

    /**
     * Hightlights your current cursor location on mouse moved
     *
     * @param event MouseEvent
     */
    @FXML
    public void highlightCursor(MouseEvent event) {
        curMouseX = (int) event.getX() / 16;
        curMouseY = (int) event.getY() / 16;
        render();
    }

    /**
     * Set the item's location
     *
     * @param event MouseEvent
     */
    @FXML
    public void setLocation(MouseEvent event) {
        int xCo = (int) event.getX() / 16;
        int yCo = (int) event.getY() / 16;
        if (tileMap.isClickable(xCo, yCo)) {
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
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("You can't place it here.");
                alert.showAndWait();
            }
        }

    }


    /**
     * Reset the items to their default preset location.
     */
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

    /**
     * Display an alert box with the instruction for the MapViewer
     */
    @FXML
    public void showInformation() {
        String instructions = "Buttons:\n\n 1) Axe\t\t: Set Axe Location\n 2) Boat\t\t: Set Boat Location\n " +
                "3) Default\t: Reset Axe and Boat Locations\n 4) Undo\t\t: Undo Previous Changes\n " +
                "5) Info\t\t: Show MapViewer Instruction";
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(null);
        alert.setHeaderText("MapViewer Instruction");
        alert.setContentText(instructions);
        alert.showAndWait();
    }

    /**
     * Undo the previous change, returning the items back to their previous coordinates
     */
    @FXML
    public void undoChanges() {
        boat = axe = false;
        if (coordinateHistory.size() > 1) {
            coordinateHistory.remove(coordinateHistory.size() - 1);
            coordinates = coordinateHistory.get(coordinateHistory.size() - 1).clone();
            saveNewCoordinates();
            render();
        }
    }

    /**
     * Read the coordinates from the coordinate save files
     */
    private void readCoordinates() {
        File filename = new File(COORDINATE_SAVE_FILE);
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                coordinates[i] = Integer.parseInt(line);
                i++;
            }

        } catch (IOException e) {
            //if there is no file, use the default coordinates
            coordinates[0] = MapDrawer.DEFAULT_COORDINATE[0];
            coordinates[1] = MapDrawer.DEFAULT_COORDINATE[1];
            coordinates[2] = MapDrawer.DEFAULT_COORDINATE[2];
            coordinates[3] = MapDrawer.DEFAULT_COORDINATE[3];
        }
        coordinateHistory.add(coordinates.clone());
    }

    private void saveNewCoordinates() {
        BufferedWriter bw;
        FileWriter fw;
        try {
            fw = new FileWriter(COORDINATE_SAVE_FILE);
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
        if (!Arrays.equals(coordinates, coordinateHistory.get(coordinateHistory.size() - 1))) {
            coordinateHistory.add(coordinates.clone());
        }
    }

    /**
     * Responsible for the rendering of the canvas.
     * All rendering related operations are done here.
     */
    private void render() {
        tileMap.drawMap();
        tileMap.drawAvatar();
        tileMap.drawDiamonds();
        tileMap.setAxeHighlighted(axe);
        tileMap.setBoatHighlighted(boat);
        tileMap.drawItems(coordinates[0], coordinates[1], coordinates[2], coordinates[3]);
        tileMap.drawCursorHighlight(curMouseX, curMouseY);
    }
}