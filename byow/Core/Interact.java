package byow.Core;

import byow.TileEngine.TETile;
import edu.princeton.cs.algs4.StdDraw;

public class Interact {

    static TETile[][] returnTile;
    static int WIDTH;
    static int HEIGHT;
    public Interact(TETile[][] grid, int x, int y) {
        returnTile = grid;
        WIDTH = x;
        HEIGHT = y;
        StdDraw.setCanvasSize(WIDTH, HEIGHT);
    }


}
