package com.neet.DiamondHunter.MapViewer;

import javafx.scene.canvas.GraphicsContext;

import java.io.*;

import javafx.scene.image.Image;

public class MapDrawer {
    private int x;
    private int y;
    private int tileSize;
    private int[][] map;
    private int mapWidth;
    private int mapHeight;
    private Image tile;


    public MapDrawer(String s, int tileSize) {
        this.tileSize = tileSize;

        try {
            InputStream in = getClass().getResourceAsStream(s); //read map file
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            mapWidth = Integer.parseInt(br.readLine());
            mapHeight = Integer.parseInt(br.readLine());
            map = new int[mapHeight][mapWidth];

            String delimiters = " ";
            for (int row = 0; row < mapHeight; row++) {
                String line = br.readLine();
                String[] tokens = line.split(delimiters);
                for (int col = 0; col < mapWidth; col++) {
                    map[row][col] = Integer.parseInt(tokens[col]);
                }
            }
        } catch (Exception e) {
        }
    }

    protected void drawMap(GraphicsContext gc) { //draw map
        tile = new Image("/Tilesets/testtileset.gif");
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
                int sourcex = map[row][col];
                int sourcey = 0;
                if (sourcex >= (tile.getWidth() / tileSize)) {
                    sourcey++;
                    sourcex = (int) (sourcex - tile.getWidth() / tileSize);
                }
                gc.drawImage(tile, sourcex * tileSize, sourcey * tileSize, tileSize, tileSize, col * tileSize, row * tileSize, tileSize, tileSize);
            }
        }
    }

    protected void drawItems(GraphicsContext gc) {
        Image itemSprite = new Image("/Sprites/items.gif");
        gc.drawImage(itemSprite, 0, 16, tileSize, tileSize,
                (4 * tileSize), (12 * tileSize), tileSize, tileSize);
        gc.drawImage(itemSprite, 16, 16, tileSize, tileSize,
                (37 * tileSize), (26 * tileSize), tileSize, tileSize);

    }

    /**
     * Check if axe and boat can be placed
     *
     * @param x
     * @param y
     * @return notClickable
     */
    public boolean clickable(int x, int y) {
        boolean notClickable = false;
        try {
            if (map[x][y] >= 20) {
                notClickable = true;
            }
        } catch (Exception e) {
        }

        return notClickable;
    }
}