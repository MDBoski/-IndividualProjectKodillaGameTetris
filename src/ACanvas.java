import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class ACanvas extends Canvas {

    BufferedImage image;
    Graphics2D graphics;

    ACanvas(short WIDTH, short HEIGHT)
    {
        super();
        setSize(WIDTH, HEIGHT);
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        graphics = (Graphics2D) image.getGraphics();
    }

    public abstract void drawImage();

    private void screen()
    {
        Graphics g = getGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
    }

    public void run()
    {
        drawImage();
        screen();
    }

}
