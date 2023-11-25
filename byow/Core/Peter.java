package byow.Core;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import edu.princeton.cs.algs4.StdDraw;

import java.awt.Color;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class Peter {
    static int WIDTH;
    static int HEIGHT;
    static TERenderer ter = new TERenderer();
    static FileWriter fw;

    static String saveInfo = "";

    static File text = new File("file.txt");

    public static void worldReturn(int w, int h, TETile[][] returnTile, String seed)
            throws IOException {
        boolean lights = false;
        saveInfo += seed;
        WIDTH = w;
        HEIGHT = h;
        int startX = 0;
        int startY = 0;
        for (int x = 0; x < returnTile.length; x++) {
            for (int y = 0; y < returnTile[0].length; y++) {
                if (returnTile[x][y] == Tileset.AVATAR) {
                    startX = x;
                    startY = y;

                }
            }
        }
        if (lights) {
            ter.renderFrame(returnTile);
        } else {
            ter.renderFrame(ComplexWorldGenerator.lightsoff(returnTile, startX, startY));
        }
        StdDraw.clear(Color.black);
        StdDraw.setPenColor(Color.white);
        double mouseX = StdDraw.mouseX();
        double mouseY = StdDraw.mouseY();
        StdDraw.text(returnTile.length / 2, returnTile[0].length - 2, "peter");
        StdDraw.show();
        StdDraw.pause(1000);
        if (lights) {
            ter.renderFrame(returnTile);
        } else {
            ter.renderFrame(ComplexWorldGenerator.lightsoff(returnTile, startX, startY));
        }
        double mouseX2 = -1;
        double mouseY2 = -1;
        while (true) {
            if (lights) {
                ter.renderFrame(returnTile);
            } else {
                ter.renderFrame(ComplexWorldGenerator.lightsoff(returnTile, startX, startY));
            }
            mouseX2 = StdDraw.mouseX();
            mouseY2 = StdDraw.mouseY();
            if (mouseX2 < WIDTH && mouseX2 > 0 && mouseY2 < HEIGHT && mouseY2 > 0
                    && returnTile[(int) mouseX2][(int) mouseY2] == Tileset.TREE) {
                StdDraw.setPenColor(Color.white);
                StdDraw.text(3, HEIGHT - 1, "tree");
                java.util.Date date = new java.util.Date();
                StdDraw.text(15, HEIGHT - 1, date.toString());
                StdDraw.show();
                StdDraw.pause(10);
                if (StdDraw.hasNextKeyTyped()) {
                    char placeholder = StdDraw.nextKeyTyped();
                    if (placeholder == 'w' && (startY + 1) < HEIGHT
                            && returnTile[startX][startY + 1] == Tileset.FLOOR) {
                        returnTile = avatarMover(placeholder, returnTile, startX, startY,
                                WIDTH, HEIGHT);
                        startY++;
                        saveInfo += "w";
                    } else if (placeholder == 'a' && (startX - 1) > 0
                            && returnTile[startX - 1][startY] == Tileset.FLOOR) {
                        returnTile = avatarMover(placeholder, returnTile, startX, startY,
                                WIDTH, HEIGHT);
                        startX--;
                        saveInfo += "a";
                    } else if (placeholder == 's' && (startY - 1) > 0
                            && returnTile[startX][startY - 1] == Tileset.FLOOR) {
                        returnTile = avatarMover(placeholder, returnTile, startX, startY,
                                WIDTH, HEIGHT);
                        startY--;
                        saveInfo += "s";
                    } else if (placeholder == 'd' && (startX + 1) < WIDTH
                            && returnTile[startX + 1][startY] == Tileset.FLOOR) {
                        returnTile = avatarMover(placeholder, returnTile, startX, startY,
                                WIDTH, HEIGHT);
                        startX++;
                        saveInfo += "d";
                    } else if (placeholder == ':') {
                        saveInfo += ":";
                    } else if ((placeholder == 'Q'
                            && saveInfo.charAt(saveInfo.length() - 1) == ':') || (placeholder == 'q'
                            && saveInfo.charAt(saveInfo.length() - 1) == ':')){
                        text = new File("file.txt");
                        saveInfo = saveInfo.substring(0, saveInfo.length() - 1);
                        fw = new FileWriter("file.txt");
                        fw.write(saveInfo);
                        fw.close();
                        System.exit(1);
                    } else if (placeholder == 'l') {
                        lights = !lights;
                    }
                }
            } else if (mouseX2 < WIDTH && mouseX2 > 0 && mouseY2 < HEIGHT
                    && mouseY2 > 0 && returnTile[(int) mouseX2][(int) mouseY2] == Tileset.WALL) {
                StdDraw.setPenColor(Color.white);
                StdDraw.text(3, HEIGHT - 1, "wall");
                java.util.Date date = new java.util.Date();
                StdDraw.text(15, HEIGHT - 1, date.toString());
                StdDraw.show();
                StdDraw.pause(10);
                if (StdDraw.hasNextKeyTyped()) {
                    char placeholder = StdDraw.nextKeyTyped();
                    if (placeholder == 'w' && (startY + 1) < HEIGHT
                            && returnTile[startX][startY + 1] == Tileset.FLOOR) {
                        returnTile = avatarMover(placeholder, returnTile, startX, startY,
                                WIDTH, HEIGHT);
                        startY++;
                        saveInfo += "w";
                    } else if (placeholder == 'a' && (startX - 1) > 0
                            && returnTile[startX - 1][startY] == Tileset.FLOOR) {
                        returnTile = avatarMover(placeholder, returnTile, startX, startY,
                                WIDTH, HEIGHT);
                        startX--;
                        saveInfo += "a";
                    } else if (placeholder == 's' && (startY - 1) > 0
                            && returnTile[startX][startY - 1] == Tileset.FLOOR) {
                        returnTile = avatarMover(placeholder, returnTile, startX, startY,
                                WIDTH, HEIGHT);
                        startY--;
                        saveInfo += "s";
                    } else if (placeholder == 'd' && (startX + 1) < WIDTH
                            && returnTile[startX + 1][startY] == Tileset.FLOOR) {
                        returnTile = avatarMover(placeholder, returnTile, startX, startY,
                                WIDTH, HEIGHT);
                        startX++;
                        saveInfo += "d";
                    } else if (placeholder == ':') {
                        saveInfo += ":";
                    } else if ((placeholder == 'Q'
                            && saveInfo.charAt(saveInfo.length() - 1) == ':') || (placeholder == 'q'
                            && saveInfo.charAt(saveInfo.length() - 1) == ':')) {
                        text = new File("file.txt");
                        saveInfo = saveInfo.substring(0, saveInfo.length() - 1);
                        fw = new FileWriter("file.txt");
                        fw.write(saveInfo);
                        fw.close();
                        System.exit(1);
                    } else if (placeholder == 'l') {
                        lights = !lights;
                    }
                }
            } else if (mouseX2 < WIDTH && mouseX2 > 0 && mouseY2 < HEIGHT && mouseY2 > 0
                    && returnTile[(int) mouseX2][(int) mouseY2] == Tileset.GRASS) {
                StdDraw.setPenColor(Color.white);
                StdDraw.text(3, HEIGHT - 1, "grass");
                java.util.Date date = new java.util.Date();
                StdDraw.text(15, HEIGHT - 1, date.toString());
                StdDraw.show();
                StdDraw.pause(10);
                if (StdDraw.hasNextKeyTyped()) {
                    char placeholder = StdDraw.nextKeyTyped();
                    if (placeholder == 'w' && (startY + 1) < HEIGHT
                            && returnTile[startX][startY + 1] == Tileset.FLOOR) {
                        returnTile = avatarMover(placeholder, returnTile, startX, startY,
                                WIDTH, HEIGHT);
                        startY++;
                        saveInfo += "w";
                    } else if (placeholder == 'a' && (startX - 1) > 0
                            && returnTile[startX - 1][startY] == Tileset.FLOOR) {
                        returnTile = avatarMover(placeholder, returnTile, startX, startY,
                                WIDTH, HEIGHT);
                        startX--;
                        saveInfo += "a";
                    } else if (placeholder == 's' && (startY - 1) > 0
                            && returnTile[startX][startY - 1] == Tileset.FLOOR) {
                        returnTile = avatarMover(placeholder, returnTile, startX, startY,
                                WIDTH, HEIGHT);
                        startY--;
                        saveInfo += "s";
                    } else if (placeholder == 'd' && (startX + 1) < WIDTH
                            && returnTile[startX + 1][startY] == Tileset.FLOOR) {
                        returnTile = avatarMover(placeholder, returnTile, startX, startY,
                                WIDTH, HEIGHT);
                        startX++;
                        saveInfo += "d";
                    } else if (placeholder == ':') {
                        saveInfo += ":";
                    } else if ((placeholder == 'Q'
                            && saveInfo.charAt(saveInfo.length() - 1) == ':') || (placeholder == 'q'
                            && saveInfo.charAt(saveInfo.length() - 1) == ':')){
                        text = new File("file.txt");
                        saveInfo = saveInfo.substring(0, saveInfo.length() - 1);
                        fw = new FileWriter("file.txt");
                        fw.write(saveInfo);
                        fw.close();
                        System.exit(1);
                    } else if (placeholder == 'l') {
                        lights = !lights;
                    }
                }
            } else if (mouseX2 < WIDTH && mouseX2 > 0 && mouseY2 < HEIGHT
                    && mouseY2 > 0 && returnTile[(int) mouseX2][(int) mouseY2] == Tileset.FLOOR) {
                StdDraw.setPenColor(Color.white);
                StdDraw.text(3, HEIGHT - 1, "floor");
                java.util.Date date = new java.util.Date();
                StdDraw.text(15, HEIGHT - 1, date.toString());
                StdDraw.show();
                StdDraw.pause(10);
                if (StdDraw.hasNextKeyTyped()) {
                    char placeholder = StdDraw.nextKeyTyped();
                    if (placeholder == 'w' && (startY + 1) < HEIGHT
                            && returnTile[startX][startY + 1] == Tileset.FLOOR) {
                        returnTile = avatarMover(placeholder, returnTile, startX, startY,
                                WIDTH, HEIGHT);
                        startY++;
                        saveInfo += "w";
                    } else if (placeholder == 'a' && (startX - 1) > 0
                            && returnTile[startX - 1][startY] == Tileset.FLOOR) {
                        returnTile = avatarMover(placeholder, returnTile, startX, startY,
                                WIDTH, HEIGHT);
                        startX--;
                        saveInfo += "a";
                    } else if (placeholder == 's' && (startY - 1) > 0
                            && returnTile[startX][startY - 1] == Tileset.FLOOR) {
                        returnTile = avatarMover(placeholder, returnTile, startX, startY,
                                WIDTH, HEIGHT);
                        startY--;
                        saveInfo += "s";
                    } else if (placeholder == 'd' && (startX + 1) < WIDTH
                            && returnTile[startX + 1][startY] == Tileset.FLOOR) {
                        returnTile = avatarMover(placeholder, returnTile, startX, startY,
                                WIDTH, HEIGHT);
                        startX++;
                        saveInfo += "d";
                    } else if (placeholder == ':') {
                        saveInfo += ":";
                    } else if ((placeholder == 'Q'
                            && saveInfo.charAt(saveInfo.length() - 1) == ':') || (placeholder == 'q'
                            && saveInfo.charAt(saveInfo.length() - 1) == ':')){
                        text = new File("file.txt");
                        saveInfo = saveInfo.substring(0, saveInfo.length() - 1);
                        fw = new FileWriter("file.txt");
                        fw.write(saveInfo);
                        fw.close();
                        System.exit(1);


                    } else if (placeholder == 'l') {
                        lights = !lights;
                    }
                }
            } else if (mouseX2 < WIDTH && mouseX2 > 0 && mouseY2 < HEIGHT && mouseY2 > 0
                    && returnTile[(int) mouseX2][(int) mouseY2] == Tileset.AVATAR) {
                StdDraw.setPenColor(Color.white);
                StdDraw.text(3, HEIGHT - 1, "avatar");
                java.util.Date date = new java.util.Date();
                StdDraw.text(15, HEIGHT - 1, date.toString());
                StdDraw.show();
                StdDraw.pause(10);
                System.out.println(4);
                System.out.println(mouseX2);
                System.out.println(mouseY2);
                if (StdDraw.hasNextKeyTyped()) {
                    char placeholder = StdDraw.nextKeyTyped();
                    if (placeholder == 'w' && (startY + 1) < HEIGHT
                            && returnTile[startX][startY + 1] == Tileset.FLOOR) {
                        returnTile = avatarMover(placeholder, returnTile, startX, startY,
                                WIDTH, HEIGHT);
                        startY++;
                        saveInfo += "w";
                    } else if (placeholder == 'a' && (startX - 1) > 0
                            && returnTile[startX - 1][startY] == Tileset.FLOOR) {
                        returnTile = avatarMover(placeholder, returnTile, startX, startY,
                                WIDTH, HEIGHT);
                        startX--;
                        saveInfo += "a";
                    } else if (placeholder == 's' && (startY - 1) > 0
                            && returnTile[startX][startY - 1] == Tileset.FLOOR) {
                        returnTile = avatarMover(placeholder, returnTile, startX, startY,
                                WIDTH, HEIGHT);
                        startY--;
                        saveInfo += "s";
                    } else if (placeholder == 'd' && (startX + 1) < WIDTH
                            && returnTile[startX + 1][startY] == Tileset.FLOOR) {
                        returnTile = avatarMover(placeholder, returnTile,
                                startX, startY, WIDTH, HEIGHT);
                        startX++;
                        saveInfo += "d";
                    } else if (placeholder == ':') {
                        saveInfo += ":";
                    } else if ((placeholder == 'Q'
                            && saveInfo.charAt(saveInfo.length() - 1) == ':') || (placeholder == 'q'
                            && saveInfo.charAt(saveInfo.length() - 1) == ':')) {
                        text = new File("file.txt");
                        saveInfo = saveInfo.substring(0, saveInfo.length() - 1);
                        fw = new FileWriter("file.txt");
                        fw.write(saveInfo);
                        fw.close();
                        System.exit(1);
                    } else if (placeholder == 'l') {
                        lights = !lights;
                    }
                }
            } else {
                StdDraw.setPenColor(Color.white);
                StdDraw.text(5, HEIGHT - 1, "mouse out of bounds");

                java.util.Date date = new java.util.Date();
                StdDraw.text(15, HEIGHT - 1, date.toString());
                StdDraw.show();
                StdDraw.pause(10);
                System.out.println(4);
                System.out.println(mouseX2);
                System.out.println(mouseY2);
                if (StdDraw.hasNextKeyTyped()) {
                    char placeholder = StdDraw.nextKeyTyped();
                    if (placeholder == 'w' && (startY + 1) < HEIGHT
                            && returnTile[startX][startY + 1] == Tileset.FLOOR) {
                        returnTile = avatarMover(placeholder, returnTile, startX, startY,
                                WIDTH, HEIGHT);
                        startY++;
                        saveInfo += "w";
                    } else if (placeholder == 'a' && (startX - 1) > 0
                            && returnTile[startX - 1][startY] == Tileset.FLOOR) {
                        returnTile = avatarMover(placeholder, returnTile, startX, startY,
                                WIDTH, HEIGHT);
                        startX--;
                        saveInfo += "a";
                    } else if (placeholder == 's' && (startY - 1) > 0
                            && returnTile[startX][startY - 1] == Tileset.FLOOR) {
                        returnTile = avatarMover(placeholder, returnTile, startX, startY,
                                WIDTH, HEIGHT);
                        startY--;
                        saveInfo += "s";
                    } else if (placeholder == 'd' && (startX + 1) < WIDTH
                            && returnTile[startX + 1][startY] == Tileset.FLOOR) {
                        returnTile = avatarMover(placeholder, returnTile, startX,
                                startY, WIDTH, HEIGHT);
                        startX++;
                        saveInfo += "d";
                    } else if (placeholder == ':') {
                        saveInfo += ":";
                    } else if ((placeholder == 'Q'
                            && saveInfo.charAt(saveInfo.length() - 1) == ':') || (placeholder == 'q'
                            && saveInfo.charAt(saveInfo.length() - 1) == ':')){
                        saveInfo = saveInfo.substring(0, saveInfo.length() - 1);
                        text = new File("file.txt");
                        FileWriter fWrite = new FileWriter("file.txt");
                        fWrite.write(saveInfo);
                        fWrite.close();
                        System.exit(1);
                    } else if (placeholder == 'l') {
                        lights = !lights;
                    }
                }
            }
        }

    }
    //interactWithInputString(seedString);
    //RESUME HERE: now seedString will have a seed (w/ S at the start and N at the end)

    public static String movements() {
        return Peter.saveInfo;
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



