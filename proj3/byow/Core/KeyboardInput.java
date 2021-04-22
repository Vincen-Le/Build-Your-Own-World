package byow.Core;

import byow.InputDemo.InputSource;
import edu.princeton.cs.introcs.StdDraw;

public class KeyboardInput implements InputSource {

    public char getNextKey() {
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char c = Character.toUpperCase(StdDraw.nextKeyTyped());
                return c;
            }
        }
    }

    public boolean possibleNextInput() {
        return true;
    }
}