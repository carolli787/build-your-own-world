package byow.Core;
import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class world {
    public static int startX;


    public static int startY;
    public static final int WIDTH = 60; //change to 80
    public static final int HEIGHT = 30;
    public static int[] roomHolder = new int[8]; // 0 : 1x1 | 1 : 1x2 | 2 : 1y1 | 3 : 1y2 | 4 : 2x1 | 5 : 2x2 | 6 : 2y1 | 7 : 2y2
    public static TETile[][] returnTile = new TETile[WIDTH][HEIGHT];
    public static TETile[][] worldCreation(Long seed) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);


        for (int i = 0; i < 8; i++) {
            roomHolder[i] = -1;
        }
        for (int x = 0; x < WIDTH; x++) {
            returnTile[x][HEIGHT - 2] = Tileset.MOUNTAIN;
            returnTile[x][HEIGHT - 1] = Tileset.MOUNTAIN;
        }
        Random random = new Random(seed);


        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT - 2; y++) {
                int randy13 = random.nextInt(2);
                if (randy13 == 0) {
                    returnTile[x][y] = Tileset.TREE;
                } else {
                    returnTile[x][y] = Tileset.GRASS;
                }
            }
        }
        int carol = HEIGHT * WIDTH / 72 + random.nextInt(HEIGHT * WIDTH / 72); //HEIGHT * WIDTH / 36
        while (carol > 0) {
            returnTile = roomBuilder(random, returnTile);
            carol--;
        }


        ter.renderFrame(returnTile);
        int avatX = 0;
        int avatY = 0;
        while (returnTile[avatX][avatY] != Tileset.FLOOR) {
            avatX = random.nextInt(returnTile.length);
            avatY = random.nextInt(returnTile[0].length);
        }
        returnTile[avatX][avatY] = Tileset.AVATAR;
        return returnTile;


    }
    public static boolean allClear(int startX, int startY, int testX, int testY, TETile[][] returnTile) { // inclusive of each pair of coordinates


        if (startX > testX && startY > testY) {
            //boolean chester = true;
            for (int i = startY; i >= testY; i--) {
                if (returnTile[testX][i] != Tileset.TREE && returnTile[testX][i] != Tileset.GRASS) {
                    return false;
                }
            }
            for (int j = testX; j <= startX; j++) {
                if (returnTile[j][testY] != Tileset.TREE && returnTile[j][testY] != Tileset.GRASS) {
                    return false;
                }
            }
            return true;
        } else if (startX > testX && startY < testY) {
            //boolean chester = true;
            for (int i = startX; i >= testX; i--) {
                if (returnTile[i][testY] != Tileset.TREE && returnTile[i][testY] != Tileset.GRASS) {
                    return false;
                }
            }
            for (int j = testY; j >= startY; j--) {
                if (returnTile[testX][j] != Tileset.TREE && returnTile[testX][j] != Tileset.GRASS) {
                    return false;
                }
            }
            return true;
        } else if (startX < testX && startY < testY) {
            //boolean chester = true;
            for (int i = startX; i <= testX; i++) {
                if (returnTile[i][testY] != Tileset.TREE && returnTile[i][testY] != Tileset.GRASS) {
                    return false;
                }
            }
            for (int j = testY; j >= startY; j--) {
                if (returnTile[testX][j] != Tileset.TREE && returnTile[testX][j] != Tileset.GRASS) {
                    return false;
                }
            }
            return true;
        } else if (startX < testX && startY > testY) {
            //boolean chester = true;
            for (int i = startY; i >= testY; i--) {
                if (returnTile[testX][i] != Tileset.TREE && returnTile[testX][i] != Tileset.GRASS) {
                    return false;
                }
            }
            for (int j = testX; j >= startX; j--) {
                if (returnTile[j][testY] != Tileset.TREE && returnTile[j][testY] != Tileset.GRASS) {
                    return false;
                }
            }
            return true;
        }
        //System.out.println("this shouldn't print");
        return true;
    }


    public static TETile[][] roomBuilder(Random random, TETile[][] returnTile) {
        int checkWidth = -1;
        int checkHeight = -1;
        int maxDir = -1;
        String Dir = "";
        int tracker = 0;
        int[] maxDirs = new int[4];
        maxDirs[0] = -1;
        maxDirs[1] = -1;
        maxDirs[2] = -1;
        maxDirs[3] = -1;
        while (maxDir == -1 && tracker < 20) {
            //System.out.println("0");
            checkWidth = random.nextInt(WIDTH);
            checkHeight = random.nextInt(HEIGHT - 2);
            Dir = "peter";
            int increment = 1;
            if (returnTile[checkWidth][checkHeight] == Tileset.TREE || returnTile[checkWidth][checkHeight] == Tileset.GRASS) {
                if (checkWidth + 1 < WIDTH && checkHeight + 1 < HEIGHT - 2 && allClear(checkWidth, checkHeight, checkWidth + 1, checkHeight + 1, returnTile)) { //returnTile[checkWidth + 1][checkHeight + 1] == Tileset.TREE
                    while (allClear(checkWidth, checkHeight, checkWidth + increment, checkHeight + increment, returnTile)) { //returnTile[checkWidth + increment][checkHeight + increment] == Tileset.TREE
                        //System.out.println("1");
                        increment++;
                        if (!(checkWidth + increment < WIDTH) ||
                                !(checkWidth + increment >= 0) ||
                                !(checkHeight + increment < HEIGHT - 2) ||
                                !(checkHeight + increment >= 0)) {
                            break;
                        }
                    }
//                    if (maxDir < increment && increment > 8) {
//                        maxDir = increment - 1;
//                        Dir = "right up";
//                    }
                    if (increment > 8) {
                        int randy10 = random.nextInt(2);
                        if (randy10 == 1 || Dir.equals("peter")) {
                            maxDir = increment - 1;
                            Dir = "right up";
                        }
                    }
                }
                increment = 1;
                if (checkWidth + 1 < WIDTH && checkHeight > 0 && allClear(checkWidth, checkHeight, checkWidth + 1, checkHeight - 1, returnTile)) {
                    while (allClear(checkWidth, checkHeight, checkWidth + increment, checkHeight - increment, returnTile)) {
                        increment++;
                        if (!(checkWidth + increment < WIDTH) ||
                                !(checkWidth + increment >= 0) ||
                                !(checkHeight - increment < HEIGHT - 2) ||
                                !(checkHeight - increment >= 0)) {
                            break;
                        }
                    }
                    if (increment > 8) {
                        int randy12 = random.nextInt(2);
                        if (randy12 == 1 || Dir.equals("peter")) {
                            maxDir = increment - 1;
                            Dir = "right down";
                        }
                    }
                }
                increment = 1;
                if (checkWidth > 0 && checkHeight > 0 && allClear(checkWidth, checkHeight, checkWidth - 1, checkHeight - 1, returnTile)) {
                    while (allClear(checkWidth, checkHeight, checkWidth - increment, checkHeight - increment, returnTile)) {
                        increment++;
                        if (!(checkWidth - increment < WIDTH) ||
                                !(checkWidth - increment >= 0) ||
                                !(checkHeight - increment < HEIGHT - 2) ||
                                !(checkHeight - increment >= 0)) {
                            break;
                        }
                    }


                    if (increment > 8) {
                        int randy9 = random.nextInt(2);
                        if (randy9 == 1 || Dir.equals("peter")) {
                            maxDir = increment - 1;
                            Dir = "left down";
                        }
                    }
                }
                increment = 1;
                if (checkWidth > 0 && checkHeight + 1 < HEIGHT - 2 && allClear(checkWidth, checkHeight, checkWidth - 1, checkHeight + 1, returnTile)) {
                    while (allClear(checkWidth, checkHeight, checkWidth - increment, checkHeight + increment, returnTile)) {
                        increment++;
                        if (!(checkWidth - increment < WIDTH) ||
                                !(checkWidth - increment >= 0) ||
                                !(checkHeight + increment < HEIGHT - 2) ||
                                !(checkHeight + increment >= 0)) {
                            break;
                        }
                    }
                    if (increment > 8) {
                        int randy11 = random.nextInt(2);
                        if (randy11 == 1 || Dir.equals("peter")) {
                            maxDir = increment - 1;
                            Dir = "left up";
                        }
                    }
                }
            }
            tracker++;
        }
        if (maxDir == -1) {
            return returnTile;
        }
        int roomX = 6 + random.nextInt(maxDir / 2 - 3);
        int roomY = 6 + random.nextInt(maxDir / 2 - 3);
        if (Dir.equals("right up")) {
            if (roomHolder[0] >= 0) {
                roomHolder[4] = roomHolder[0];
                roomHolder[5] = roomHolder[1];
                roomHolder[6] = roomHolder[2];
                roomHolder[7] = roomHolder[3];
            }
            roomHolder[0] = checkWidth + 1;
            roomHolder[1] = checkWidth + roomX - 1;
            roomHolder[2] = checkHeight + 1;
            roomHolder[3] = checkHeight + roomY - 1;
            for (int x = checkWidth; x <= checkWidth + roomX; x++) {
                for (int y = checkHeight; y <= checkHeight + roomY; y++) {
                    if (x == checkWidth || x == checkWidth + roomX || y == checkHeight || y == checkHeight + roomY) {
                        returnTile[x][y] = Tileset.WALL;
                    } else {
                        returnTile[x][y] = Tileset.FLOOR;
                    }
                }
            }
            if (roomHolder[0] != -1 && roomHolder[4] != -1) {
                returnTile = hallBuilder(random, returnTile, roomHolder);
            }




        } else if (Dir.equals("right down")) {
            if (roomHolder[0] >= 0) {
                roomHolder[4] = roomHolder[0];
                roomHolder[5] = roomHolder[1];
                roomHolder[6] = roomHolder[2];
                roomHolder[7] = roomHolder[3];
            }
            roomHolder[0] = checkWidth + 1;
            roomHolder[1] = checkWidth + roomX - 1;
            roomHolder[2] = checkHeight - roomY + 1;
            roomHolder[3] = checkHeight - 1;
            for (int x = checkWidth; x <= checkWidth + roomX; x++) {
                for (int y = checkHeight; y >= checkHeight - roomY; y--) {
                    if (x == checkWidth || x == checkWidth + roomX || y == checkHeight || y == checkHeight - roomY) {
                        returnTile[x][y] = Tileset.WALL;
                    } else {
                        returnTile[x][y] = Tileset.FLOOR;
                    }
                }
            }
            if (roomHolder[0] != -1 && roomHolder[4] != -1) {
                returnTile = hallBuilder(random, returnTile, roomHolder);
            }
        } else if (Dir.equals("left down")) {
            if (roomHolder[0] >= 0) {
                roomHolder[4] = roomHolder[0];
                roomHolder[5] = roomHolder[1];
                roomHolder[6] = roomHolder[2];
                roomHolder[7] = roomHolder[3];
            }
            roomHolder[0] = checkWidth - roomX + 1;
            roomHolder[1] = checkWidth - 1;
            roomHolder[2] = checkHeight - roomY + 1;
            roomHolder[3] = checkHeight - 1;
            for (int x = checkWidth; x >= checkWidth - roomX; x--) {
                for (int y = checkHeight; y >= checkHeight - roomY; y--) {
                    if (x == checkWidth || x == checkWidth - roomX || y == checkHeight || y == checkHeight - roomY) {
                        returnTile[x][y] = Tileset.WALL;
                    } else {
                        returnTile[x][y] = Tileset.FLOOR;
                    }
                }
            }
            if (roomHolder[0] != -1 && roomHolder[4] != -1) {
                returnTile = hallBuilder(random, returnTile, roomHolder);
            }
        } else if (Dir.equals("left up")) {
            if (roomHolder[0] >= 0) {
                roomHolder[4] = roomHolder[0];
                roomHolder[5] = roomHolder[1];
                roomHolder[6] = roomHolder[2];
                roomHolder[7] = roomHolder[3];
            }
            roomHolder[0] = checkWidth - roomX + 1;
            roomHolder[1] = checkWidth - 1;
            roomHolder[2] = checkHeight + 1;
            roomHolder[3] = checkHeight + roomY - 1;
            for (int x = checkWidth; x >= checkWidth - roomX; x--) {
                for (int y = checkHeight; y <= checkHeight + roomY; y++) {
                    if (x == checkWidth || x == checkWidth - roomX || y == checkHeight || y == checkHeight + roomY) {
                        returnTile[x][y] = Tileset.WALL;
                    } else {
                        returnTile[x][y] = Tileset.FLOOR;
                    }
                }
            }
            if (roomHolder[0] != -1 && roomHolder[4] != -1) {
                returnTile = hallBuilder(random, returnTile, roomHolder);
            }
        }
        //add avarat
        return returnTile;
    }


    public static TETile[][] hallBuilder(Random random, TETile[][] returnTile, int[] roomHolder) {
        //if R2 is left OR right of R1
        if ((roomHolder[2] <= roomHolder[7] && roomHolder[2] >= roomHolder[6]) ||
                (roomHolder[3] <= roomHolder[7] && roomHolder[3] >= roomHolder[6]) ||
                (roomHolder[6] <= roomHolder[3] && roomHolder[6] >= roomHolder[2]) ||
                (roomHolder[7] <= roomHolder[3] && roomHolder[7] >= roomHolder[2])) {
            List<Integer> vals = new ArrayList<>();
            for (int y = roomHolder[2]; y <= roomHolder[3]; y++) {
                if (y >= roomHolder[6] && y <= roomHolder[7]) {
                    vals.add(y);
                }
            }
            int randMaker = vals.get(random.nextInt(vals.size()));
            int randMaker2 = -1;
            if (vals.contains(randMaker + 1)) {
                randMaker2 = randMaker + 1;
            } else if (vals.contains(randMaker - 1)) {
                randMaker2 = randMaker - 1;
            }
            int randy6 = random.nextInt(2);
            if (roomHolder[1] < roomHolder[4]) {
                //build hall from square1 to square2 from left to right
                for (int x = roomHolder[1]; x <= roomHolder[4]; x++) {
                    returnTile = encompass(returnTile, x, randMaker);
                    if (randy6 == 1 && randMaker2 != -1) {
                        returnTile = encompass(returnTile, x, randMaker2);
                    }
                }
            } else {
                //build hall from square1 to square2 from right to left
                for (int x = roomHolder[0]; x >= roomHolder[5]; x--) {
                    returnTile = encompass(returnTile, x, randMaker);
                    if (randy6 == 1 && randMaker2 != -1) {
                        returnTile = encompass(returnTile, x, randMaker2);
                    }
                }
            }
            //else if R2 is above or below R1
        } else if ((roomHolder[0] <= roomHolder[5] && roomHolder[0] >= roomHolder[4]) ||
                (roomHolder[1] <= roomHolder[5] && roomHolder[1] >= roomHolder[4]) ||
                (roomHolder[4] <= roomHolder[1] && roomHolder[4] >= roomHolder[0]) ||
                (roomHolder[5] <= roomHolder[1] && roomHolder[5] >= roomHolder[0])) {
            List<Integer> vals = new ArrayList<>();
            for (int x = roomHolder[0]; x <= roomHolder[1]; x++) {
                if (x >= roomHolder[4] && x <= roomHolder[5]) {
                    vals.add(x);
                }
            }
            int randMaker = vals.get(random.nextInt(vals.size()));
            int randMaker2 = -1;
            if (vals.contains(randMaker + 1)) {
                randMaker2 = randMaker + 1;
            } else if (vals.contains(randMaker - 1)) {
                randMaker2 = randMaker - 1;
            }
            int randy7 = random.nextInt(2);
            if (roomHolder[3] < roomHolder[6]) {
                //build hall from square1 to up square2
                for (int y = roomHolder[3]; y <= roomHolder[6]; y++) {
                    returnTile = encompass(returnTile, randMaker, y);
                    if (randy7 == 1 && randMaker2 != -1) {
                        returnTile = encompass(returnTile, randMaker2, y);
                    }
                }
            } else {
                //build hall from square2 up to square1
                for (int y = roomHolder[7]; y <= roomHolder[2]; y++) {
                    returnTile = encompass(returnTile, randMaker, y);
                    if (randy7 == 1 && randMaker2 != -1) {
                        returnTile = encompass(returnTile, randMaker2, y);
                    }
                }
            }
        } else {  //RE CONVEINE HERE
            if (roomHolder[1] < roomHolder[4] && roomHolder[3] < roomHolder[6]) { //sqr2 is up and right of sqr1
                int randy = random.nextInt(2);
                int[] destinations = new int[4]; // 0 : x1 | 1 : y1 | 2 : x2 | 3 : y2
                if (randy == 0) {
                    //pick value from top of yellow
                    destinations[0] = roomHolder[0] + random.nextInt(roomHolder[1] - roomHolder[0]);
                    destinations[1] = roomHolder[3] + 1;
                } else {
                    //pick value from right of yellow
                    destinations[0] = roomHolder[1] + 1;
                    destinations[1] = roomHolder[2] + random.nextInt(roomHolder[3] - roomHolder[2]);
                }
                int randy2 = random.nextInt(2);
                if (randy2 == 0) {
                    //left of pink
                    destinations[2] = roomHolder[4] - 1;
                    destinations[3] = roomHolder[6] + random.nextInt(roomHolder[7] - roomHolder[6]);
                } else {
                    //bottom of pink
                    destinations[2] = roomHolder[4] + random.nextInt(roomHolder[5] - roomHolder[4]);
                    destinations[3] = roomHolder[6] - 1;
                }
                startX = 0;
                startY = 0;
                int diffX = destinations[2] - destinations[0];
                int diffY = destinations[3] - destinations[1];
                returnTile = encompass(returnTile, destinations[0], destinations[1]);
                while (startX < diffX && startY < diffY) {
                    int randy3 = random.nextInt(2);
                    if (randy3 == 0) {
                        //build hall one unit up
                        startY++;
                        returnTile = encompass(returnTile, destinations[0] + startX, destinations[1] + startY);
                    } else {
                        //build hall one unit right
                        startX++;
                        returnTile = encompass(returnTile, destinations[0] + startX, destinations[1] + startY);
                    }
                }
                if (startX == diffX) {
                    //keep building up
                    while (startY < diffY) {
                        startY++;
                        returnTile = encompass(returnTile, destinations[0] + startX, destinations[1] + startY);
                    }
                } else {
                    //keep building right
                    while (startX < diffX) {
                        startX++;
                        returnTile = encompass(returnTile, destinations[0] + startX, destinations[1] + startY);
                    }
                }
            } else if (roomHolder[2] > roomHolder[7] && roomHolder[1] < roomHolder[4]) { //sqr2 is down and right of sqr1
                int randy = random.nextInt(2);
                int[] destinations = new int[4]; // 0 : x1 | 1 : y1 | 2 : x2 | 3 : y2
                if (randy == 0) {
                    //pick value from bottom of R1
                    destinations[0] = roomHolder[0] + random.nextInt(roomHolder[1] - roomHolder[0]);
                    destinations[1] = roomHolder[2] - 1;
                } else {
                    //pick value from right of R1
                    destinations[0] = roomHolder[1] + 1;
                    destinations[1] = roomHolder[2] + random.nextInt(roomHolder[3] - roomHolder[2]);
                }
                int randy2 = random.nextInt(2);
                if (randy2 == 0) {
                    //pick value from left of R2
                    destinations[2] = roomHolder[4] - 1;
                    destinations[3] = roomHolder[6] + random.nextInt(roomHolder[7] - roomHolder[6]);
                } else {
                    //pick value from top of R2
                    destinations[2] = roomHolder[4] + random.nextInt(roomHolder[5] - roomHolder[4]);
                    destinations[3] = roomHolder[7] + 1;
                }
                int startX = 0;
                int startY = 0;
                int diffX = destinations[2] - destinations[0];
                int diffY = destinations[1] - destinations[3];
                returnTile = encompass(returnTile, destinations[0], destinations[1]);
                while (startX < diffX && startY < diffY) {
                    int randy3 = random.nextInt(2);
                    if (randy3 == 0) {
                        //build hall one unit up
                        startY++;
                        returnTile = encompass(returnTile, destinations[0] + startX, destinations[1] - startY);
                    } else {
                        //build hall one unit right
                        startX++;
                        returnTile = encompass(returnTile, destinations[0] + startX, destinations[1] - startY);
                    }
                }
                if (startX == diffX) {
                    //keep building up
                    while (startY < diffY ) {
                        startY++;
                        returnTile = encompass(returnTile, destinations[0] + startX, destinations[1] - startY);
                    }
                } else {
                    //keep building right
                    while (startX < diffX) {
                        startX++;
                        returnTile = encompass(returnTile, destinations[0] + startX, destinations[1] - startY);
                    }
                }
            } else if (roomHolder[2] > roomHolder[7] && roomHolder[5] < roomHolder[0]) { //sqr2 is down and left of sqr1
                int randy = random.nextInt(2);
                int[] destinations = new int[4]; // 0 : x1 | 1 : y1 | 2 : x2 | 3 : y2
                if (randy == 0) {
                    //pick value from bottom of R1
                    destinations[0] = roomHolder[0] + random.nextInt(roomHolder[1] - roomHolder[0]);
                    destinations[1] = roomHolder[2] - 1;
                } else {
                    //pick value from left of R1
                    destinations[0] = roomHolder[0] - 1;
                    destinations[1] = roomHolder[2] + random.nextInt(roomHolder[3] - roomHolder[2]);
                }
                int randy2 = random.nextInt(2);
                if (randy2 == 0) {
                    //pick value from right of R2
                    destinations[2] = roomHolder[5] + 1;
                    destinations[3] = roomHolder[6] + random.nextInt(roomHolder[7] - roomHolder[6]);
                } else {
                    //pick value from top of R2
                    destinations[2] = roomHolder[4] + random.nextInt(roomHolder[5] - roomHolder[4]);
                    destinations[3] = roomHolder[7] + 1;
                }
                int startX = 0;
                int startY = 0;
                int diffX = destinations[0] - destinations[2];
                int diffY = destinations[1] - destinations[3];
                returnTile = encompass(returnTile, destinations[0], destinations[1]);
                while (startX < diffX && startY < diffY) {
                    int randy3 = random.nextInt(2);
                    if (randy3 == 0) {
                        //build hall one unit down
                        startY++;
                        returnTile = encompass(returnTile, destinations[0] - startX, destinations[1] - startY);
                    } else {
                        //build hall one unit left
                        startX++;
                        returnTile = encompass(returnTile, destinations[0] - startX, destinations[1] - startY);
                    }
                }
                if (startX == diffX) {
                    //keep building up
                    while (startY < diffY) {
                        startY++;
                        returnTile = encompass(returnTile, destinations[0] - startX, destinations[1] - startY);
                    }
                } else {
                    //keep building right
                    while (startX < diffX) {
                        startX++;
                        returnTile = encompass(returnTile, destinations[0] - startX, destinations[1] - startY);
                    }
                }
            } else { //sqr2 is up and left of sqr1
                int randy = random.nextInt(2);
                int[] destinations = new int[4]; // 0 : x1 | 1 : y1 | 2 : x2 | 3 : y2
                if (randy == 0) {
                    //pick value from top of R1 *
                    destinations[0] = roomHolder[0] + random.nextInt(roomHolder[1] - roomHolder[0]);
                    destinations[1] = roomHolder[3] + 1;
                } else {
                    //pick value from left of R1
                    destinations[0] = roomHolder[0] - 1;
                    destinations[1] = roomHolder[2] + random.nextInt(roomHolder[3] - roomHolder[2]);
                }
                int randy2 = random.nextInt(2);
                if (randy2 == 0) {
                    //pick value from right of R2
                    destinations[2] = roomHolder[5] + 1;
                    destinations[3] = roomHolder[6] + random.nextInt(roomHolder[7] - roomHolder[6]);
                } else {
                    //pick value from bottom of R2 *
                    destinations[2] = roomHolder[4] + random.nextInt(roomHolder[5] - roomHolder[4]);
                    destinations[3] = roomHolder[6] - 1;
                }
                int startX = 0;
                int startY = 0;
                int diffX = destinations[0] - destinations[2];
                int diffY = destinations[3] - destinations[1];
                returnTile = encompass(returnTile, destinations[0], destinations[1]);
                while (startX < diffX && startY < diffY) {
                    int randy3 = random.nextInt(2);
                    if (randy3 == 0) {
                        //build hall one unit up *
                        startY++;
                        returnTile = encompass(returnTile, destinations[0] - startX, destinations[1] + startY);
                    } else {
                        //build hall one unit left
                        startX++;
                        returnTile = encompass(returnTile, destinations[0] - startX, destinations[1] + startY);
                    }
                }
                if (startX == diffX) {
                    //keep building up *
                    while (startY < diffY) {
                        startY++;
                        returnTile = encompass(returnTile, destinations[0] - startX, destinations[1] + startY);
                    }
                } else {
                    //keep building left *
                    while (startX < diffX) {
                        startX++;
                        returnTile = encompass(returnTile, destinations[0] - startX, destinations[1] + startY);
                    }
                }
            }








        }
        return returnTile;
    }




    public static TETile[][] encompass(TETile[][] returnTile, int x, int y) {
        if (x > 0 && x < WIDTH - 1 && y > 0 && y < HEIGHT - 3) {
            returnTile[x][y] = Tileset.FLOOR;
            if (x - 1 >= 0 && y + 1 < HEIGHT - 2 && returnTile[x - 1][y + 1] != Tileset.FLOOR) {
                returnTile[x - 1][y + 1] = Tileset.WALL;
            }
            if (y + 1 < HEIGHT - 2 && returnTile[x][y + 1] != Tileset.FLOOR) {
                returnTile[x][y + 1] = Tileset.WALL;
            }
            if (x + 1 < WIDTH && y + 1 < HEIGHT - 2 && returnTile[x + 1][y + 1] != Tileset.FLOOR) {
                returnTile[x + 1][y + 1] = Tileset.WALL;
            }
            if (x + 1 < WIDTH && returnTile[x + 1][y] != Tileset.FLOOR) {
                returnTile[x + 1][y] = Tileset.WALL;
            }
            if (x + 1 < WIDTH && y - 1 >= 0 && returnTile[x + 1][y - 1] != Tileset.FLOOR) {
                returnTile[x + 1][y - 1] = Tileset.WALL;
            }
            if (y - 1 >= 0 && returnTile[x][y - 1] != Tileset.FLOOR) {
                returnTile[x][y - 1] = Tileset.WALL;
            }
            if (x - 1 >= 0 && y - 1 >= 0 && returnTile[x - 1][y - 1] != Tileset.FLOOR) {
                returnTile[x - 1][y - 1] = Tileset.WALL;
            }
            if (x - 1 >= 0 && returnTile[x - 1][y] != Tileset.FLOOR) {
                returnTile[x - 1][y] = Tileset.WALL;
            }
        }
        return returnTile;
    }
    public static int getStartX(){
        return startX;
    }


    public static int getStartY(){
        return startY;
    }


}














































