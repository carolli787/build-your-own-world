
package byow.Core;




import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import edu.princeton.cs.algs4.StdDraw;




import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;


import static byow.Core.Interact.returnTile;
import static byow.Core.Peter.fw;
import static java.lang.Character.isAlphabetic;
import static java.lang.Character.isDigit;
//import byow.TileEngine.Tileset;




public class Engine {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;


    public String seedString;


    public static int startX = 0;
    public static int startY = 0;








    /**
     * Method used for exploring a fresh world. This method should handle all inputs,
     * including inputs from the main menu.
     */
    public void interactWithKeyboard() throws IOException {
        StdDraw.clear(Color.magenta);
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.setXscale(0, WIDTH);
        StdDraw.setYscale(0, HEIGHT);
        Font titleFont = new Font("title", Font.BOLD, 35);
        Font subTitleFont = new Font("peter", Font.BOLD, 20);
        StdDraw.setFont(titleFont);
        StdDraw.text(WIDTH / 2, 2 * HEIGHT / 3, "title of the game");
        StdDraw.setFont(subTitleFont);
        StdDraw.text(WIDTH / 2, HEIGHT / 2, "New Game (N)");
        StdDraw.text(WIDTH / 2, HEIGHT / 2 - 2, "Load Game (L)");
        StdDraw.text(WIDTH / 2, HEIGHT / 2 - 4, "Quit Game (Q)");
        char firstKey = 'r';
        while (firstKey != 'N' && firstKey != 'L' && firstKey != 'Q' && firstKey != 'q' && firstKey != 'l' && firstKey != 'n') {
            if (StdDraw.hasNextKeyTyped()) {
                //System.out.print(StdDraw.nextKeyTyped());
                firstKey = StdDraw.nextKeyTyped();
            }
        }
        if (firstKey == 'N' || firstKey == 'n') {
            StdDraw.clear(Color.cyan);
            //StdDraw.setFont(titleFont);
            //StdDraw.text(WIDTH / 2, 2 * HEIGHT / 3 , "Please type a seed number that ends in 'S'.");
            seedString = "N";
            StdDraw.pause(500);
            char seedChar = 'N';
            //seedString += StdDraw.nextKeyTyped();
            while (seedChar != 'S') {
                StdDraw.clear(Color.cyan);
                StdDraw.text(WIDTH / 2, 2 * HEIGHT / 3, "Please type a seed number that ends in 'S'.");
                StdDraw.text(WIDTH / 2, HEIGHT / 2, seedString);
                while (!StdDraw.hasNextKeyTyped()) {
                }
                seedChar = StdDraw.nextKeyTyped();
                seedString += seedChar;
            }


            StdDraw.clear(Color.cyan);
            StdDraw.text(WIDTH / 2, 2 * HEIGHT / 3, "Please type a seed number that ends in 'S'.");
            StdDraw.text(WIDTH / 2, HEIGHT / 2, seedString);




            TETile[][] returnTile = interactWithInputString(seedString);
            Peter.worldReturn(WIDTH, HEIGHT, returnTile, seedString);


        } else if (firstKey == 'L' || firstKey == 'l'){
            new Scanner("file.txt");
//            new Scanner(Peter.text)
            Scanner fileRead = new Scanner(Peter.text);
            String input = "";
            if (fileRead.hasNextLine()) {
                //fileRead.nextLine();
                while(fileRead.hasNextLine()){
                    input += fileRead.nextLine();
                }

            }
            String sepSeed = "";
            String sepMovement = "";
            boolean seenS = false;
            for(int x = 0; x <input.length(); x ++){
                if (seenS == false){
                    sepSeed += input.charAt(x);
                    if (input.charAt(x) == 'S' || input.charAt(x) == 's'){
                        seenS = true;
                    }
                } else {
                    sepMovement += input.charAt(x);
                }


            }
            TETile[][] savedWorld = interactWithInputString(sepSeed);
            //interactWithInputString(input); // TAKING THIS OUT FOR A SECOND (was used to get to loaded world)
            int passX = 0;
            int passY = 0;
            for(int x = 0; x < savedWorld.length; x++){
                for(int y = 0; y < savedWorld[0].length; y ++){
                    if(savedWorld[x][y] == Tileset.AVATAR){
                        passX = x;
                        passY = y;


                    }
                }
            }
            ComplexWorldGenerator.complexWorldGenerator(sepSeed, sepMovement, savedWorld, passX, passY);




        } else if (firstKey == 'Q' || firstKey == 'q'){
            System.exit(1);
        }
    }


    /**
     * Method used for autograding and testing your code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The engine should
     * behave exactly as if the user typed these characters into the engine using
     * interactWithKeyboard.
     *
     * Recall that strings ending in ":q" should cause the game to quite save. For example,
     * if we do interactWithInputString("n123sss:q"), we expect the game to run the first
     * 7 commands (n123sss) and then quit and save. If we then do
     * interactWithInputString("l"), we should be back in the exact same state.
     *
     * In other words, running both of these:
     *   - interactWithInputString("n123sss:q")
     *   - interactWithInputString("lww")
     *
     * should yield the exact same world state as:
     *   - interactWithInputString("n123sssww")
     *
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] interactWithInputString(String input) {

        TETile[][] returnTile;
            input.replaceAll("\\s", "");
            String sepSeed = "";
            String sepMovement = "";
            boolean seenS = false;
            for (int x = 0; x < input.length(); x++) {
                if (isAlphabetic(input.charAt(x))) {
                    sepMovement += input.charAt(x);
                } else if (isDigit(input.charAt(x))) {
                    sepSeed += input.charAt(x);
                }
//                } else if(input.charAt(x) == 'l' || input.charAt(x) == 'L'){
//                    new Scanner("file.txt");
////            new Scanner(Peter.text)
//                    Scanner fileRead = new Scanner("file.txt");
//                    String i = "";
//                    if (fileRead.hasNextLine()) {
//                        //fileRead.nextLine();
//                        i = fileRead.nextLine();
//                } else if(input.charAt(x) == ':' && input.charAt(x) == 'q') {
//                        File ext = new File("file.txt");
//                        try {
//                            fw.write(sepMovement);
//                            fw.close();
//                        } catch (IOException e) {
//                            throw new IOException(e);
//                        }
//                    }
//
//
//                    }
            }
        try {
            returnTile = world.worldCreation(Long.parseLong(sepSeed));
        } catch (IllegalArgumentException e) {
            returnTile = world.worldCreation(Long.parseLong("0"));
        }

        for (int x = 0; x < returnTile.length; x++) {
            for (int y = 0; y < returnTile[0].length; y++) {
                if (returnTile[x][y] == Tileset.AVATAR) {
                    startY = y;
                    startX = x;
                }
            }
        }

        while (!sepMovement.equals("")) {
            char placeholder = sepMovement.charAt(0);
            if (placeholder == 'w' && (startY + 1) < HEIGHT
                    && returnTile[startX][startY + 1] == Tileset.FLOOR) {
                returnTile = avatarMover(placeholder, returnTile, startX, startY, WIDTH, HEIGHT);
                startY++;
            } else if (placeholder == 'a' && (startX - 1) > 0
                    && returnTile[startX - 1][startY] == Tileset.FLOOR) {
                returnTile = avatarMover(placeholder, returnTile, startX, startY, WIDTH, HEIGHT);
                startX--;
            } else if (placeholder == 's' && (startY - 1) > 0
                    && returnTile[startX][startY - 1] == Tileset.FLOOR) {
                returnTile = avatarMover(placeholder, returnTile, startX, startY, WIDTH, HEIGHT);
                startY--;
            } else if (placeholder == 'd' && (startX + 1) < WIDTH
                    && returnTile[startX + 1][startY] == Tileset.FLOOR) {
                returnTile = avatarMover(placeholder, returnTile, startX, startY, WIDTH, HEIGHT);
                startX++;

            }
            if (sepMovement.length() == 1) {
                sepMovement = "";
            } else {
                sepMovement = sepMovement.substring(1);
            }

        }
          //  }
                //maybe not - 1 ??
        ter.renderFrame(returnTile);
        return returnTile;


        //Random random = new Random();
//        int startX = 0;
//        int startY = 0;
//        while (returnTile[startX][startY] != Tileset.FLOOR) {
//            startX = random.nextInt(returnTile.length);
//            startY = random.nextInt(returnTile[0].length);
//        }
//        returnTile[startX][startY] = Tileset.AVATAR;
        //peter.worldReturn(WIDTH, HEIGHT, returnTile, seedString);


//        } else {
//            String sepSeed = "";
//            String sepMovement = "";
//            boolean seenS = false;
//            for(int x = 0; x <input.length(); x ++){
//                if (seenS == false){
//                    sepSeed += input.charAt(x);
//                    if (input.charAt(x) == 'S'){
//                        seenS = true;
//                    }
//                } else {
//                    sepMovement += input.charAt(x);
//                }
//
//            }
//
//            TETile[][] savedWorld = interactWithInputString(sepSeed);
//            // IMPORTANT LINE BELOW
//            complexWorldGenerator peter1 = new complexWorldGenerator
//            (sepSeed, sepMovement, savedWorld);
//            //peter1.
//            return peter1.complexWorld();
//
//        }






        // passed in as an argument, and return a 2D tile representation of the
        // world that would have been drawn if the same inputs had been given
        // to interactWithKeyboard().




        //
        // See proj3.byow.InputDemo for a demo of how you can make a nice clean interface
        // that works for many different input types.




        //System.out.println(seed);


    }
    public static TETile[][] avatarMover(char placeholder, TETile[][] world,
                                         int x, int y, int width, int height) {
        if (placeholder == 'w' && (y + 1) < height && world[x][y + 1] == Tileset.FLOOR) {
            world[x][y] = Tileset.FLOOR;
            world[x][y + 1] = Tileset.AVATAR;
        } else if (placeholder == 'a' && (x - 1) > 0 && world[x - 1][y] == Tileset.FLOOR) {
            world[x][y] = Tileset.FLOOR;
            world[x - 1][y] = Tileset.AVATAR;
        } else if (placeholder == 's' && (y - 1) > 0 && world[x][y - 1] == Tileset.FLOOR) {
            world[x][y] = Tileset.FLOOR;
            world[x][y - 1] = Tileset.AVATAR;
        } else if (placeholder == 'd' && (x + 1) < width && world[x + 1][y] == Tileset.FLOOR) {
            world[x][y] = Tileset.FLOOR;
            world[x + 1][y] = Tileset.AVATAR;
        }

        return world;


    }
}