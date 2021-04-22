package byow.lab12;
import org.junit.Test;
import static org.junit.Assert.*;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;

    private static final long SEED = 2873123;
    private static final Random RANDOM = new Random(SEED);

    public static void drawRow(TETile[][] tiles, Position p, TETile t, int length) {
        for (int dx = 0; dx < length; dx++) {
            tiles[p.x +dx][p.y] = t;
        }
    }

    public static void addHexHelper(TETile[][] tiles, Position p, TETile t, int blank, int numtiles) {
        Position start = p.shift(blank, 0);
        drawRow(tiles, start, t, numtiles);

        //Draw remaining
        if (blank > 0) {
            Position nextPosition = p.shift(0, -1);
            addHexHelper(tiles, nextPosition, t,blank - 1, numtiles + 2);
        }

        //Draw reflected row
        Position startReflection = start.shift(0, -(2*blank + 1));
        drawRow(tiles, startReflection, t, numtiles);
    }

    public static void addHex(TETile[][] tiles, Position p, TETile t, int size) {
        if (size < 2) {
            return;
        }

        addHexHelper(tiles, p, t, size - 1, size);
    }

    /**
     * Fills the given 2D array of tiles with RANDOM tiles.
     * @param tiles
     */
    public static void fillWithNothing(TETile[][] tiles) {
        int height = tiles[0].length;
        int width = tiles.length;
        for (int x = 0; x < width; x += 1) {
            for (int y = 0; y < height; y += 1) {
                tiles[x][y] = Tileset.NOTHING;
            }
        }
    }

    /**
     * Finds random biome tile
     */
    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(5);
        switch (tileNum) {
            case 0: return Tileset.GRASS;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.SAND;
            case 3: return Tileset.MOUNTAIN;
            case 4: return Tileset.TREE;
            default: return Tileset.NOTHING;
        }
    }

    /**
     * Adds column of hexagons at position P and hexagons are size SIZE
     */
    public static void addHexCol(TETile[][] tiles, Position p, int size, int num) {
        if (num < 1) {
            return;
        }
        //Draw current hexagon
        addHex(tiles, p, randomTile(), size);

        //Draw n - 1 hexagon below that current hexagon
        if (num > 1) {
            Position bottom = getBottom(p, size);
            addHexCol(tiles, bottom, size, num - 1);
        }
    }

    /**
     * Gets the bottom neighbor of a hexagon based off of size of hexagon
     */
    public static Position getBottom(Position p, int size) {
        return p.shift(0, -2 * size);
    }

    /**
     * Gets the top right neighbor of a hexagon
     */
    public static Position getTopRight(Position p, int size) {
        return p.shift(2*size - 1, size);
    }

    /**
     * Gets the bottom right neighbor of a hexagon
     */
    public static Position getBottomRight(Position p, int size) {
        return p.shift(2*size - 1, -size);
    }

    private static class Position {
        int x;
        int y;

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Position shift(int dx, int dy) {
            return new Position(this.x + dx, this.y + dy);
        }
    }

    public static void drawWorld(TETile[][] tiles, Position p, int hexSize, int boardSize) {

        addHexCol(tiles, p, hexSize, boardSize);

        //Build up to the right
        for (int i = 1; i < boardSize; i++) {
            p = getTopRight(p, hexSize);
            addHexCol(tiles, p, hexSize, boardSize + i);
        }

        //Build down to the right
        for (int i = boardSize - 2; i >= 0; i--) {
            p = getBottomRight(p, hexSize);
            addHexCol(tiles, p, hexSize, boardSize + i);
        }

    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] world = new TETile[WIDTH][HEIGHT];
        fillWithNothing(world);
        Position starting = new Position(12, 34);
        drawWorld(world, starting, 3, 3);

        ter.renderFrame(world);
    }
}
