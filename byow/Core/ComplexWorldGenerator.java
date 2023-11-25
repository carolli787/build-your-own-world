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
import static byow.Core.Interact.returnTile;




public class ComplexWorldGenerator {
    public static int HEIGHT;
    public static int WIDTH;
    public static String instructions;
    public static FileWriter fw;
    public static File text = new File("file.txt");
    public static int startY;
    public static int startX;
    //public static String saveInfo = "";
    static TERenderer ter = new TERenderer();




    //public static TETile[][] returnTile;




    public static void complexWorldGenerator(String seedString, String i, TETile[][] re, int sx, int sy) throws IOException {




        //this.instructions = instructions;
        String saveInfo = seedString + i;
        returnTile = re;
        WIDTH = returnTile.length;
        HEIGHT = returnTile[0].length;
        instructions = i;
        startX = sx;
        startY = sy;
        boolean lights = false;
        complexWorld(); // now avatar is in right spot, now we need game to be interactive (reference PETER.JAVA)


        if(lights){
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


        if(lights){
            ter.renderFrame(returnTile);
        } else {
            ter.renderFrame(ComplexWorldGenerator.lightsoff(returnTile, startX, startY));
        }


        while (true) {
            if(lights){
                ter.renderFrame(returnTile);
            } else {
                ter.renderFrame(ComplexWorldGenerator.lightsoff(returnTile, startX, startY));
            }
            double mouseX2 = StdDraw.mouseX();
            double mouseY2 = StdDraw.mouseY();




            if (mouseX2 < WIDTH && mouseX2 > 0 && mouseY2 < HEIGHT && mouseY2 > 0 && returnTile[(int) mouseX2][(int) mouseY2] == Tileset.TREE) {
                StdDraw.setPenColor(Color.white);
                StdDraw.text(3, HEIGHT - 1, "tree");
                java.util.Date date = new java.util.Date();
                StdDraw.text(15, HEIGHT - 1, date.toString());
                StdDraw.show();
                StdDraw.pause(10);


                if (StdDraw.hasNextKeyTyped()) {
                    char placeholder = StdDraw.nextKeyTyped();
                    if (placeholder == 'w' && (startY + 1) < HEIGHT && returnTile[startX][startY + 1] == Tileset.FLOOR) {
                        returnTile = avatarMover(placeholder, returnTile, startX, startY, WIDTH, HEIGHT);
                        startY++;
                        saveInfo += "w";
                    } else if (placeholder == 'a' && (startX - 1) > 0 && returnTile[startX - 1][startY] == Tileset.FLOOR) {
                        returnTile = avatarMover(placeholder, returnTile, startX, startY, WIDTH, HEIGHT);
                        startX--;
                        saveInfo += "a";
                    } else if (placeholder == 's' && (startY - 1) > 0 && returnTile[startX][startY - 1] == Tileset.FLOOR) {
                        returnTile = avatarMover(placeholder, returnTile, startX, startY, WIDTH, HEIGHT);
                        startY--;
                        saveInfo += "s";
                    } else if (placeholder == 'd' && (startX + 1) < WIDTH && returnTile[startX + 1][startY] == Tileset.FLOOR) {
                        returnTile = avatarMover(placeholder, returnTile, startX, startY, WIDTH, HEIGHT);
                        startX++;
                        saveInfo += "d";
                    } else if(placeholder == ':'){
                        saveInfo += ":";
                    } else if(placeholder == 'Q' && saveInfo.charAt(saveInfo.length() - 1) == ':'){
                        text = new File("file.txt");
                        saveInfo = saveInfo.substring(0, saveInfo.length() - 1);
                        fw = new FileWriter("file.txt");
                        fw.write(saveInfo);
                        fw.close();
                        System.exit(1);
                    } else if(placeholder == 'l'){
                        lights = !lights;


                    }
                }
            } else if (mouseX2 < WIDTH && mouseX2 > 0 && mouseY2 < HEIGHT && mouseY2 > 0 && returnTile[(int) mouseX2][(int) mouseY2] == Tileset.WALL) {
                StdDraw.setPenColor(Color.white);
                StdDraw.text(3, HEIGHT - 1, "wall");
                java.util.Date date = new java.util.Date();
                StdDraw.text(15, HEIGHT - 1, date.toString());
                StdDraw.show();
                StdDraw.pause(10);


                if (StdDraw.hasNextKeyTyped()) {
                    char placeholder = StdDraw.nextKeyTyped();
                    if (placeholder == 'w' && (startY + 1) < HEIGHT && returnTile[startX][startY + 1] == Tileset.FLOOR) {
                        returnTile = avatarMover(placeholder, returnTile, startX, startY, WIDTH, HEIGHT);
                        startY++;
                        saveInfo += "w";
                    } else if (placeholder == 'a' && (startX - 1) > 0 && returnTile[startX - 1][startY] == Tileset.FLOOR) {
                        returnTile = avatarMover(placeholder, returnTile, startX, startY, WIDTH, HEIGHT);
                        startX--;
                        saveInfo += "a";
                    } else if (placeholder == 's' && (startY - 1) > 0 && returnTile[startX][startY - 1] == Tileset.FLOOR) {
                        returnTile = avatarMover(placeholder, returnTile, startX, startY, WIDTH, HEIGHT);
                        startY--;
                        saveInfo += "s";
                    } else if (placeholder == 'd' && (startX + 1) < WIDTH && returnTile[startX + 1][startY] == Tileset.FLOOR) {
                        returnTile = avatarMover(placeholder, returnTile, startX, startY, WIDTH, HEIGHT);
                        startX++;
                        saveInfo += "d";
                    } else if (placeholder == ':') {
                        saveInfo += ":";
                    } else if (placeholder == 'Q' && saveInfo.charAt(saveInfo.length() - 1) == ':') {
                        text = new File("file.txt");
                        saveInfo = saveInfo.substring(0, saveInfo.length() - 1);
                        fw = new FileWriter("file.txt");
                        fw.write(saveInfo);
                        fw.close();
                        System.exit(1);


                    }  else if(placeholder == 'l'){
                        lights = !lights;


                    }








                }
            } else if (mouseX2 < WIDTH && mouseX2 > 0 && mouseY2 < HEIGHT && mouseY2 > 0 && returnTile[(int) mouseX2][(int) mouseY2] == Tileset.GRASS) {




                StdDraw.setPenColor(Color.white);
                StdDraw.text(3, HEIGHT - 1, "grass");
                java.util.Date date = new java.util.Date();
                StdDraw.text(15, HEIGHT - 1, date.toString());
                StdDraw.show();
                StdDraw.pause(10);


                if (StdDraw.hasNextKeyTyped()) {
                    char placeholder = StdDraw.nextKeyTyped();
                    if (placeholder == 'w' && (startY + 1) < HEIGHT && returnTile[startX][startY + 1] == Tileset.FLOOR) {
                        returnTile = avatarMover(placeholder, returnTile, startX, startY, WIDTH, HEIGHT);
                        startY++;
                        saveInfo += "w";
                    } else if (placeholder == 'a' && (startX - 1) > 0 && returnTile[startX - 1][startY] == Tileset.FLOOR) {
                        returnTile = avatarMover(placeholder, returnTile, startX, startY, WIDTH, HEIGHT);
                        startX--;
                        saveInfo += "a";
                    } else if (placeholder == 's' && (startY - 1) > 0 && returnTile[startX][startY - 1] == Tileset.FLOOR) {
                        returnTile = avatarMover(placeholder, returnTile, startX, startY, WIDTH, HEIGHT);
                        startY--;
                        saveInfo += "s";
                    } else if (placeholder == 'd' && (startX + 1) < WIDTH && returnTile[startX + 1][startY] == Tileset.FLOOR) {
                        returnTile = avatarMover(placeholder, returnTile, startX, startY, WIDTH, HEIGHT);
                        startX++;
                        saveInfo += "d";
                    } else if(placeholder == ':'){
                        saveInfo += ":";
                    } else if(placeholder == 'Q' && saveInfo.charAt(saveInfo.length() - 1) == ':'){
                        text = new File("file.txt");
                        saveInfo = saveInfo.substring(0, saveInfo.length() - 1);
                        fw = new FileWriter("file.txt");
                        fw.write(saveInfo);
                        fw.close();
                        System.exit(1);
                    } else if(placeholder == 'l'){
                        lights = !lights;


                    }
                }
            } else if (mouseX2 < WIDTH && mouseX2 > 0 && mouseY2 < HEIGHT && mouseY2 > 0 && returnTile[(int) mouseX2][(int) mouseY2] == Tileset.FLOOR) {
                StdDraw.setPenColor(Color.white);
                StdDraw.text(3, HEIGHT - 1, "floor");
                java.util.Date date = new java.util.Date();
                StdDraw.text(15, HEIGHT - 1, date.toString());
                StdDraw.show();
                StdDraw.pause(10);

                if (StdDraw.hasNextKeyTyped()) {
                    char placeholder = StdDraw.nextKeyTyped();
                    if (placeholder == 'w' && (startY + 1) < HEIGHT && returnTile[startX][startY + 1] == Tileset.FLOOR) {
                        returnTile = avatarMover(placeholder, returnTile, startX, startY, WIDTH, HEIGHT);
                        startY++;
                        saveInfo += "w";
                    } else if (placeholder == 'a' && (startX - 1) > 0 && returnTile[startX - 1][startY] == Tileset.FLOOR) {
                        returnTile = avatarMover(placeholder, returnTile, startX, startY, WIDTH, HEIGHT);
                        startX--;
                        saveInfo += "a";
                    } else if (placeholder == 's' && (startY - 1) > 0 && returnTile[startX][startY - 1] == Tileset.FLOOR) {
                        returnTile = avatarMover(placeholder, returnTile, startX, startY, WIDTH, HEIGHT);
                        startY--;
                        saveInfo += "s";
                    } else if (placeholder == 'd' && (startX + 1) < WIDTH && returnTile[startX + 1][startY] == Tileset.FLOOR) {
                        returnTile = avatarMover(placeholder, returnTile, startX, startY, WIDTH, HEIGHT);
                        startX++;
                        saveInfo += "d";
                    } else if(placeholder == ':'){
                        saveInfo += ":";
                    } else if(placeholder == 'Q' && saveInfo.charAt(saveInfo.length() - 1) == ':'){
                        text = new File("file.txt");
                        saveInfo = saveInfo.substring(0, saveInfo.length() - 1);
                        fw = new FileWriter("file.txt");
                        fw.write(saveInfo);
                        fw.close();
                        System.exit(1);








                    } else if(placeholder == 'l'){
                        lights = !lights;


                    }




                    //method here
                }
            } else if (mouseX2 < WIDTH && mouseX2 > 0 && mouseY2 < HEIGHT && mouseY2 > 0 && returnTile[(int) mouseX2][(int) mouseY2] == Tileset.AVATAR) {
                //StdDraw.clear(Color.black);
                StdDraw.setPenColor(Color.white);
                StdDraw.text(3, HEIGHT - 1, "avatar");
                java.util.Date date = new java.util.Date();
                StdDraw.text(15, HEIGHT - 1, date.toString());
                StdDraw.show();
                StdDraw.pause(10);

                if (StdDraw.hasNextKeyTyped()) {
                    char placeholder = StdDraw.nextKeyTyped();
                    if (placeholder == 'w' && (startY + 1) < HEIGHT && returnTile[startX][startY + 1] == Tileset.FLOOR) {
                        returnTile = avatarMover(placeholder, returnTile, startX, startY, WIDTH, HEIGHT);
                        startY++;
                        saveInfo += "w";
                    } else if (placeholder == 'a' && (startX - 1) > 0 && returnTile[startX - 1][startY] == Tileset.FLOOR) {
                        returnTile = avatarMover(placeholder, returnTile, startX, startY, WIDTH, HEIGHT);
                        startX--;
                        saveInfo += "a";
                    } else if (placeholder == 's' && (startY - 1) > 0 && returnTile[startX][startY - 1] == Tileset.FLOOR) {
                        returnTile = avatarMover(placeholder, returnTile, startX, startY, WIDTH, HEIGHT);
                        startY--;
                        saveInfo += "s";
                    } else if (placeholder == 'd' && (startX + 1) < WIDTH && returnTile[startX + 1][startY] == Tileset.FLOOR) {
                        returnTile = avatarMover(placeholder, returnTile, startX, startY, WIDTH, HEIGHT);
                        startX++;
                        saveInfo += "d";
                    } else if(placeholder == ':'){
                        saveInfo += ":";
                    } else if(placeholder == 'Q' && saveInfo.charAt(saveInfo.length() - 1) == ':'){
                        text = new File("file.txt");
                        saveInfo = saveInfo.substring(0, saveInfo.length() - 1);
                        fw = new FileWriter("file.txt");
                        fw.write(saveInfo);
                        fw.close();
                        System.exit(1);








                    } else if(placeholder == 'l'){
                        lights = !lights;


                    }
                }

            } else {
                StdDraw.setPenColor(Color.white);
                StdDraw.text(5, HEIGHT - 1, "mouse out of bounds");
                StdDraw.show();
                StdDraw.pause(10);
//                System.out.println(4);
//                System.out.println(mouseX2);
//                System.out.println(mouseY2);

                StdDraw.setPenColor(Color.white);
                java.util.Date date = new java.util.Date();
                StdDraw.text(15, HEIGHT - 1, date.toString());
                StdDraw.show();
                StdDraw.pause(10);

                if (StdDraw.hasNextKeyTyped()) {
                    char placeholder = StdDraw.nextKeyTyped();
                    if (placeholder == 'w' && (startY + 1) < HEIGHT && returnTile[startX][startY + 1] == Tileset.FLOOR) {
                        returnTile = avatarMover(placeholder, returnTile, startX, startY, WIDTH, HEIGHT);
                        startY++;
                        saveInfo += "w";
                    } else if (placeholder == 'a' && (startX - 1) > 0 && returnTile[startX - 1][startY] == Tileset.FLOOR) {
                        returnTile = avatarMover(placeholder, returnTile, startX, startY, WIDTH, HEIGHT);
                        startX--;
                        saveInfo += "a";
                    } else if (placeholder == 's' && (startY - 1) > 0 && returnTile[startX][startY - 1] == Tileset.FLOOR) {
                        returnTile = avatarMover(placeholder, returnTile, startX, startY, WIDTH, HEIGHT);
                        startY--;
                        saveInfo += "s";
                    } else if (placeholder == 'd' && (startX + 1) < WIDTH && returnTile[startX + 1][startY] == Tileset.FLOOR) {
                        returnTile = avatarMover(placeholder, returnTile, startX, startY, WIDTH, HEIGHT);
                        startX++;
                        saveInfo += "d";
                    } else if(placeholder == ':'){
                        saveInfo += ":";
                    } else if(placeholder == 'Q' && saveInfo.charAt(saveInfo.length() - 1) == ':'){
                        saveInfo = saveInfo.substring(0, saveInfo.length() - 1);
                        text = new File("file.txt");
                        FileWriter fw = new FileWriter("file.txt");
                        fw.write(saveInfo);
                        fw.close();
                        System.exit(1);








                    } else if(placeholder == 'l'){
                        lights = !lights;


                    }




                }
            }
        }
        //fix returnTile by moving the avatar based on INSTRUCTIONS
//        Random random = new Random();
//        while (this.returnTile[startX][startY] != Tileset.FLOOR) {
//            startX = random.nextInt(this.returnTile.length);
//            startY = random.nextInt(this.returnTile[0].length);
//        }
//        this.returnTile[startX][startY] = Tileset.AVATAR;
    }




    public static TETile[][] complexWorld() {
        while(!instructions.equals("")) {
            char placeholder = instructions.charAt(0);
            if (placeholder == 'w' && (startY + 1) < HEIGHT && returnTile[startX][startY + 1] == Tileset.FLOOR) {
                returnTile = avatarMover(placeholder, returnTile, startX, startY, WIDTH, HEIGHT);
                startY++;
            } else if (placeholder == 'a' && (startX - 1) > 0 && returnTile[startX - 1][startY] == Tileset.FLOOR) {
                returnTile = avatarMover(placeholder, returnTile, startX, startY, WIDTH, HEIGHT);
                startX--;
            } else if (placeholder == 's' && (startY - 1) > 0 && returnTile[startX][startY - 1] == Tileset.FLOOR) {
                returnTile = avatarMover(placeholder, returnTile, startX, startY, WIDTH, HEIGHT);
                startY--;
            } else if (placeholder == 'd' && (startX + 1) < WIDTH && returnTile[startX + 1][startY] == Tileset.FLOOR) {
                returnTile = avatarMover(placeholder, returnTile, startX, startY, WIDTH, HEIGHT);
                startX++;
            } else {
                //System.out.println("shit hit the fan");
            }
            if(instructions.length() == 1){
                instructions = "";
            }else{
                instructions = instructions.substring(1);
            }
            ter.renderFrame(returnTile);


            //maybe not - 1 ??
        }
        return returnTile;
    }








    public static TETile[][] avatarMover(char placeholder, TETile[][] world, int x, int y, int WIDTH, int HEIGHT){
        if(placeholder == 'w' && (y + 1) < HEIGHT && world[x][y + 1] == Tileset.FLOOR){ // && (y + 1) < HEIGHT && world[x][y + 1] == Tileset.FLOOR
            world[x][y] = Tileset.FLOOR;
            world[x][y + 1] = Tileset.AVATAR;
        }
        else if(placeholder == 'a' && (x - 1) > 0 && world[x - 1][y] == Tileset.FLOOR ){ //&& (x - 1) > 0 && world[x - 1][y] == Tileset.FLOOR
            world[x][y] = Tileset.FLOOR;
            world[x - 1][y] = Tileset.AVATAR;
        }
        else if(placeholder == 's' && (y - 1) > 0 && world[x][y - 1] == Tileset.FLOOR){ //&& (y - 1) > 0 && world[x][y - 1] == Tileset.FLOOR
            world[x][y] = Tileset.FLOOR;
            world[x][y - 1] = Tileset.AVATAR;
        }
        else if(placeholder == 'd' && (x + 1) < WIDTH && world[x + 1][y] == Tileset.FLOOR){ //&& (x + 1) < WIDTH && world[x + 1][y] == Tileset.FLOOR
            world[x][y] = Tileset.FLOOR;
            world[x + 1][y] = Tileset.AVATAR;
        }
        return world;
    }


    public static TETile[][] lightsoff(TETile[][] returnTile, int startX, int startY) {
        TETile[][] blank = new TETile[returnTile.length][returnTile[0].length];
        for(int x = 0; x < returnTile.length; x ++){
            for(int y =0; y <returnTile[0].length; y++){
                blank[x][y] = Tileset.MOUNTAIN;
            }
        }


        for(int x = startX - 2; x <= startX + 2; x ++){
            for(int y = startY - 2; y <=startY + 2; y++){
                if( x >= 0 && x < returnTile.length && y >= 0 && y < returnTile[0].length){
                    blank[x][y] = returnTile[x][y];
                }


            }
        }
        for(int x = startX - 1; x <= startX + 1; x ++){
            if(x >= 0 && x < returnTile.length && startY + 3 >= 0 && startY + 3 < returnTile[0].length){
                blank[x][startY + 3] = returnTile[x][startY + 3];
            }
            if(x >= 0 && x < returnTile.length && startY - 3 >= 0 && startY - 3 < returnTile[0].length){
                blank[x][startY - 3] = returnTile[x][startY - 3];
            }
        }


        for(int y = startY - 1; y <= startY + 1; y ++){
            if(y >= 0 && y < returnTile[0].length && startX + 3 >= 0 && startX + 3 < returnTile.length){
                blank[startX + 3][y] = returnTile[startX + 3][y];
            }
            if(y >= 0 && y < returnTile[0].length && startX - 3 >= 0 && startX - 3 < returnTile.length){
                blank[startX - 3][y] = returnTile[startX - 3][y];
            }
        }
        if (startX + 4 >= 0 && startX + 4 < returnTile.length && startY >= 0 && startY < returnTile[0].length){
            blank[startX + 4][startY] = returnTile[startX + 4][startY];
        }
        if (startX - 4 >= 0 && startX - 4 < returnTile.length && startY >= 0 && startY < returnTile[0].length){
            blank[startX - 4][startY] = returnTile[startX - 4][startY];
        }
        if (startX >= 0 && startX < returnTile.length && startY + 4 >= 0 && startY + 4 < returnTile[0].length){
            blank[startX][startY + 4] = returnTile[startX][startY + 4];
        }
        if (startX >= 0 && startX < returnTile.length && startY - 4 >= 0 && startY - 4 < returnTile[0].length){
            blank[startX][startY - 4] = returnTile[startX][startY - 4];
        }
        return blank;


    }
}


















