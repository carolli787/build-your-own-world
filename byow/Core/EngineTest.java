package byow.Core;

import byow.TileEngine.TETile;

import java.io.IOException;
import java.util.Arrays;

public class EngineTest {
    public static void main(String[] args) throws IOException {
        Engine engine = new Engine();
        TETile[][] world = engine.interactWithInputString("n1392967723524655428sddsaawwsaddw");
        Engine engine2 = new Engine();
        engine2.interactWithInputString("n1392967723524655428sddsaawws:q");
        TETile[][] world2 = engine2.interactWithInputString("laddw");
        System.out.println(engine2.seedString);
        System.out.println(Arrays.deepEquals(world,world2));
    }
}
