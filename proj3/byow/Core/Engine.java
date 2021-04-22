package byow.Core;
import byow.InputDemo.InputSource;
import byow.InputDemo.KeyboardInputSource;
import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import edu.princeton.cs.introcs.StdDraw;
import org.antlr.v4.runtime.misc.Utils;

import java.io.File;
import java.util.Random;
import java.util.*;


public class Engine {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;

    private long SEED;
    private Random RANDOM = new Random(SEED);

    public static final File CWD = new File(System.getProperty("user.dir"));

    private char current;


    /**
     * Method used for exploring a fresh world. This method should handle all inputs,
     * including inputs from the main menu.
     */
    public void drawMenu() {
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(0.5, 0.7, "CS61B: THE GAME");
        StdDraw.text(0.5, 0.5, "New Game (N)");
        StdDraw.text(0.5, 0.45, "Load Game (L)");
        StdDraw.text(0.5, 0.4, "Quit (Q)");
    }

    public void drawSeedMenu() {
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(0.5, 0.7, "ENTER SEED");
        StdDraw.text(0.5, 0.6, "Press 'S' To Start");
        StdDraw.text(0.5, 0.5, String.valueOf(SEED));
    }

    public void persistence() {

    }

    public void interactWithKeyboard() {
        drawMenu();
        InputSource inputSource = new KeyboardInput();
        while (inputSource.possibleNextInput()) {
            current = inputSource.getNextKey();
            if (current == 'N') {
                break;
            }
        }
        drawSeedMenu();
        while (inputSource.possibleNextInput()) {
            current = inputSource.getNextKey();
            if (current == 'S') {
                break;
            }
            SEED = (SEED * 10) + current;
            drawSeedMenu();
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
     * In other words, both of these calls:
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
        // TODO: Fill out this method so that it run the engine using the input
        // passed in as an argument, and return a 2D tile representation of the
        // world that would have been drawn if the same inputs had been given
        // to interactWithKeyboard().
        //
        // See proj3.byow.InputDemo for a demo of how you can make a nice clean interface
        // that works for many different input types.

        this.SEED = Long.parseLong(input.substring(1, input.length() - 1));
        this.RANDOM = new Random(SEED);

        TETile[][] finalWorldFrame = null;
        return finalWorldFrame;
    }

    //Randomizes the creation of rooms and hallways to ensure pseudorandomness of our world

    private void randomize() {
        int item = RANDOM.nextInt();
        switch (item) {

        }
    }
}
