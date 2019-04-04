import java.awt.*;
import java.util.Random;

@SuppressWarnings("serial")
public class Draw extends ACanvas {

    byte block;
    private Random los = new Random();

    Draw() {
        super((byte)100, (byte)100);
        randomBlock();
    }

    @Override
    public void drawImage() {
        drawBlock();
    }

    public void randomBlock()
    {
        block = (byte)(los.nextInt(6)+1);
    }

    private void drawCube(byte x, byte y, byte k)
    {
        graphics.setColor(Blocks.COLOUR[k]);
        graphics.fillRect(x* Blocks.SIZE, y* Blocks.SIZE, Blocks.SIZE, Blocks.SIZE);
        graphics.setColor(Color.BLACK);
        graphics.drawRect(x* Blocks.SIZE, y* Blocks.SIZE, Blocks.SIZE-1, Blocks.SIZE-1);
    }

    private void drawBlock()
    {
        graphics.setColor(Blocks.COLOUR[0]);
        graphics.fillRect(0, 0, 4* Blocks.SIZE, 4* Blocks.SIZE);
        for (byte tx=0; tx<4; tx++)
            for (byte ty=0; ty<4; ty++)
                if (Blocks.BLOCKS[block][ty][tx]) drawCube(tx,ty, (byte) (block+1));
    }
}
