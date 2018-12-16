# Diamond Hunter & MapViewer

This repository contains both projects: Diamond Hunter, which is the main original game, and MapViewer, which is a standalone Map Editor for the main game with the functionality to change the player usable items(Axe and Boat) location.

Both of the application are built into `.jar` files and placed into the [bin/](bin/) folder.

## Diamond Hunter

### Running Diamond Hunter

To run Diamond Hunter, open and start up [Diamond-Hunter.jar](bin/Diamond-Hunter.jar) and the game should be launched.

Windows user might encounter a problem with directly running [Diamond-Hunter.jar](bin/Diamond-Hunter.jar), normally with the game getting stuck at the menu. In that case, open up your command prompt(`cmd`), and make sure your current directory contains the Jar file (default location of the jar files will be in [bin/Diamond-Hunter.jar](bin/Diamond-Hunter.jar)), then type in the command `java -jar Diamond-Hunter.jar` in the command prompt to run it instead.

If you are running Diamond Hunter from the source code, simply run [com.neet.DiamondHunter.Main.Game.main()](src/com/neet/DiamondHunter/Main/Game.java) to start the application.

## MapViewer

### Running MapViewer

To run MapViewer, open and start up [MapViewer.jar](bin/MapViewer.jar) and the game should be launched just like Diamond Hunter.

Windows user might encounter a problem with directly running [MapViewer.jar](bin/MapViewer.jar), with nothing happening after attempting to run it. In that case, open up your command prompt(`cmd`), and make sure your current directory contains the Jar file (default location of the jar files will be in [bin/MapViewer.jar](bin/MapViewer.jar)), then type in the command `java -jar MapViewer.jar` in the command prompt to run it instead.

If you are running MapViewer from the source code however, run [com.neet.DiamondHunter.MapViewer.MapViewer.main()](src/com/neet/DiamondHunter/MapViewer/MapViewer.java) to start the application.

MapViewer relies on the [Coordinates.txt]() file for tracking any coordinates settings in it and will create it if it does not exist. Please do not delete it after changing the coordinates of the items via MapViewer as the main game may not work as expected with the new coordinates if tampered with.