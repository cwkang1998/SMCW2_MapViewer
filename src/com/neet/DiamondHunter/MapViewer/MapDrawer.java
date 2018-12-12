package com.neet.DiamondHunter.MapViewer;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.io.*;

import javafx.scene.image.Image;

public class MapDrawer {

    public static String MAP_URL = "/Maps/testmap.map";
    public static final int[] DEFAULT_COORDINATE = {4, 12, 37, 26};

    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private Image tile;
    private Image itemSprite;
    private Image avatarSprite;

    private int tileSize;
    private int[][] map;
    private int mapWidth;
    private int mapHeight;


    public MapDrawer(Canvas canvas) {
        this.canvas = canvas;
        this.graphicsContext = canvas.getGraphicsContext2D();
        this.tileSize = 16;
        this.tile = new Image("/Tilesets/testtileset.gif");
        this.itemSprite = new Image("/Sprites/items.gif");
        this.avatarSprite = new Image("/Sprites/playersprites.gif");

        try {
            InputStream in = getClass().getResourceAsStream(MAP_URL); //read map file
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

        //Set canvas to dynamic
        canvas.setWidth(mapWidth * 16);
        canvas.setHeight(mapHeight * 16);
    }

    public void drawMap() { //draw map
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
                int sourcex = map[row][col];
                int sourcey = 0;
                if (sourcex >= (tile.getWidth() / tileSize)) {
                    sourcey++;
                    sourcex = (int) (sourcex - tile.getWidth() / tileSize);
                }
                graphicsContext.drawImage(tile, sourcex * tileSize, sourcey * tileSize, tileSize, tileSize,
                        col * tileSize, row * tileSize, tileSize, tileSize);
            }
        }
    }

    /**
     * Draw out the usable items boat and axe on the given coordinates.
     *
     * @param boatX X coordinate for the boat
     * @param boatY Y coordinate for the boat
     * @param axeX  X coordinate for the axe
     * @param axeY  Y coordinate for the axe
     */
    public void drawItems(int boatX, int boatY, int axeX, int axeY) {
        //Draw the axe
        graphicsContext.drawImage(itemSprite, 0, 16, tileSize, tileSize,
                (boatX * tileSize), (boatY * tileSize), tileSize, tileSize);

        //Draw the boat
        graphicsContext.drawImage(itemSprite, 16, 16, tileSize, tileSize,
                (axeX * tileSize), (axeY * tileSize), tileSize, tileSize);
    }

    public void drawAvatar() {
        //Draw the avatar
        graphicsContext.drawImage(avatarSprite, 0, 0, tileSize, tileSize,
                (17 * tileSize), (17 * tileSize), tileSize, tileSize);
    }

    /**
     * Check if axe and boat can be placed
     *
     * @param x
     * @param y
     * @return notClickable
     */
    public boolean notClickable(int x, int y) {
        boolean notClickable = false;
        try {
            if (map[y][x] >= 20) {
                notClickable = true;
            }
        } catch (Exception e) {
        }

        return notClickable;
    }
}