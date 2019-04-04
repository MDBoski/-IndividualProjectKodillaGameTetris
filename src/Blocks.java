import java.awt.*;

public class Blocks {

    final static short SIZE = 25;
    final static Color[] COLOUR = {new Color(176, 196, 222), Color.MAGENTA, Color.YELLOW, Color.BLUE, Color.ORANGE, Color.RED, Color.CYAN, Color.GREEN, Color.WHITE};
    final static boolean[][][] BLOCKS =
            {
                    {
                            {false, false, false, false},
                            {true,  true,   true, false},
                            {false, false,  true, false},
                            {false, false, false, false}
                    },
                    {
                            {false, false, false, false},
                            {true,   true,  true, false},
                            {false,  true, false, false},
                            {false, false, false, false}
                    },
                    {
                            {false, false, false, false},
                            {false, false,  true, false},
                            { true,  true,  true, false},
                            {false, false, false, false}
                    },
                    {
                            {false, false, false, false},
                            { true,  true,  true,  true},
                            {false, false, false, false},
                            {false, false, false, false}
                    },
                    {
                            {false, false, false, false},
                            {false, true,   true, false},
                            {false, true,   true, false},
                            {false, false, false, false}
                    },
                    {
                            {false, false, false, false},
                            {false,  true,  true, false},
                            { true,  true, false, false},
                            {false, false, false, false}
                    },
                    {
                            {false, false, false, false},
                            { true,  true, false, false},
                            {false,  true,  true, false},
                            {false, false, false, false}
                    }

            };

}