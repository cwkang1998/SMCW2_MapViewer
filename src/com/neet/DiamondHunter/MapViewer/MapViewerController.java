package com.neet.DiamondHunter.MapViewer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import java.io.*;

public class MapViewerController {

    public int coordinates[] = new int[4];

    //if axe is chosen
    //coordinates[0]=x;
    //coordinates[1]=y;

    //if boat is chosen
    //coordinates[2]=i;
    //coordinates[3]=j;

    @FXML
    private Canvas canvas;
    private MapDrawer tilemap;

    @FXML
    private Button btnAxe;

    @FXML
    private Button btnBoat;

    boolean isValid;
    boolean axe = false;
    boolean boat = false;

    @FXML
    private Button btn_save;

    @FXML
    public void onClick_btn_save(ActionEvent event) {
        String filename = "Resources/Maps/Coordinates.txt";

        BufferedWriter bw = null;
        FileWriter fw = null;
        try {
            fw = new FileWriter(filename);
            bw = new BufferedWriter(fw);

            for (int i = 0; i < 4; i++) {
                bw.write(Integer.toString(coordinates[i]));
                bw.newLine();
            }
        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                if (bw != null)
                    bw.close();

                if (fw != null)
                    fw.close();

            } catch (IOException ex) {

                ex.printStackTrace();

            }
        }
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

        btnAxe.setOnMouseClicked(event -> {
            axe = true;
            boat = false;
        });

        btnBoat.setOnMouseClicked(event -> {
            axe = false;
            boat = true;
        });
    }
}