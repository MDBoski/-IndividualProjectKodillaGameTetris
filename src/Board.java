import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

@SuppressWarnings("serial")
public class Board extends ACanvas implements MouseListener, KeyListener {

    final static short width = Blocks.SIZE * 10;
    final static short height = Blocks.SIZE * 20;
    byte[][] tab = new byte[12][22];
    Block block = new Block();
    byte blockX, blockY;
    boolean kLeft, kRight, kUp, kDown;
    short speed, speedMax;
    boolean speedKey;
    Sound sBlock, sRotation, sStrap;
    boolean gamePlay, pause;
    Color sColour;

    Board() {
        super(width, height);addMouseListener(this);addKeyListener(this);
        sBlock = new Sound("block.wav");
        sRotation = new Sound("rotation.wav");
        sStrap = new Sound("line.wav");
        for (byte x=0; x<12; x++) {tab[x][0]=1;tab[x][21]=1;}
        for (byte y=0; y<22; y++) {tab[0][y]=1;tab[11][y]=1;}
        blockX = 4;
        blockY = 0;
        speedMax = (short) (21 - Tetris.level);
        gamePlay = false;
        pause = false;
        graphics.setFont(new Font("System", Font.BOLD, 18));
        sColour = Color.WHITE;
    }

    @Override
    public void drawImage() {
        if (gamePlay)
        {
            key();
            cmpBoard();
            drawBoard();
            drawBlock(blockX, blockY);
            if (!pause)
            {
                if (speed<speedMax) speed++;else
                {
                    speed=0;
                    if (isBlockBoard(blockX, (byte)(blockY+1))) blockY++; else
                    {
                        blockEnd();
                        newBlock();
                    }
                }
            }
            else
            {
                graphics.setColor(Color.BLACK);
                graphics.drawString("Pause", 101, 496);
                graphics.setColor(sColour);
                graphics.drawString("Pause", 100, 495);
            }
        }
        else
        {
            graphics.setColor(Blocks.COLOUR[0]);
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(Color.BLACK);
            graphics.drawString("PLAY", 100, 496);


        }
    }

    public void newBlock()
    {
        sBlock.play();
        blockX = 4;
        blockY = 0;
        speedMax = (short) (20 - Tetris.level);
        if (speedMax<0) speedMax=0;
        block.setBlock(Tetris.draw.block);
        Tetris.draw.randomBlock();
        Tetris.points+= block.aBlock;
        Tetris.lPoints.setText(String.valueOf(Tetris.points));
    }

    public void blockEnd()
    {
        for (byte xx=0; xx<4; xx++)
            for (byte yy=0; yy<4; yy++)
                if (block.tab[xx][yy]) tab[xx+blockX][yy+blockY]=(byte)(block.aBlock+1);
    }

    private void drawBoard()
    {
        for (byte x=1; x<11; x++)
            for (byte y=1; y<21; y++)
            {
                graphics.setColor(Blocks.COLOUR[tab[x][y]]);
                graphics.fillRect((x* Blocks.SIZE)- Blocks.SIZE, (y* Blocks.SIZE)- Blocks.SIZE, Blocks.SIZE, Blocks.SIZE);
                graphics.setColor(Color.BLACK);
                if (tab[x][y] > 0)
                    graphics.drawRect((x * Blocks.SIZE) - Blocks.SIZE, (y * Blocks.SIZE) - Blocks.SIZE, Blocks.SIZE - 1, Blocks.SIZE-1);
            }
    }
    private void drawCube(byte x, byte y, byte k)
    {
        graphics.setColor(Blocks.COLOUR[k]);
        graphics.fillRect((x* Blocks.SIZE)- Blocks.SIZE, (y* Blocks.SIZE)- Blocks.SIZE, Blocks.SIZE, Blocks.SIZE);
        graphics.setColor(Color.BLACK);
        graphics.drawRect((x* Blocks.SIZE)- Blocks.SIZE, (y* Blocks.SIZE)- Blocks.SIZE, Blocks.SIZE-1, Blocks.SIZE-1);
    }

    private boolean isLines(byte y)
    {
        for (byte x=1; x<11; x++) {if (tab[x][y]==0) return false;}
        return true;
    }
    private boolean isFull()
    {
        for (byte x=1; x<11; x++) {if (tab[x][1]!=0) return true;}
        return false;
    }

    private void setLines(byte y)
    {
        sStrap.play();
        for (byte x=1; x<11; x++) tab[x][y]=8;
        Tetris.lines++;Tetris.lLines.setText(String.valueOf(Tetris.lines));
        Tetris.points+=(Tetris.level * 10);
        Tetris.lPoints.setText(String.valueOf(Tetris.points));
        if (Tetris.lines==(Tetris.level*Tetris.level))
        {
            Tetris.level++;
            Tetris.lLevel.setText(String.valueOf(Tetris.level));
            if (speedMax>0) speedMax--;
        }
    }

    private void downBoard(byte y)
    {
        for (byte ty=y; ty>1; ty--)
            for (byte x=1; x<11; x++) tab[x][ty]=tab[x][ty-1];
        for (byte x=1; x<11; x++) tab[x][1]=0;
    }

    private void cmpBoard()
    {
        for (byte y=1; y<21; y++)
        {
            if (tab[1][y]==8) downBoard(y);
            if (isLines(y)) setLines(y);
        }
        if (isFull())
        {
            gamePlay = false;
            Tetris.level=1;
            Tetris.lines=0;
            Tetris.points=0;
            blockX = 4;
            blockY = 0;
            speedMax = (short) (21 - Tetris.level);
            for (byte x=1; x<11; x++) for (byte y=1; y<21; y++) tab[x][y]=0;
        }
    }

    public void drawBlock(byte x, byte y)
    {
        for (byte tx=0; tx<4; tx++)
            for (byte ty=0; ty<4; ty++)
                if (block.tab[tx][ty]) drawCube((byte)(tx+x),(byte) (ty+y), (byte) (block.aBlock+1));
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        if (!gamePlay)
        {
            gamePlay=true;
            Tetris.lPoints.setText(String.valueOf(Tetris.points));
            Tetris.lLines.setText(String.valueOf(Tetris.lines));
            Tetris.lLevel.setText(String.valueOf(Tetris.level));
        }else
            pause=!pause;
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @SuppressWarnings("static-access")
    @Override
    public void keyPressed(KeyEvent e) {
        int k = e.getKeyCode();
        if (k==e.VK_UP) kUp = true;
        if (k==e.VK_DOWN) kDown = true;
        if (k==e.VK_LEFT) kLeft = true;
        if (k==e.VK_RIGHT) kRight = true;
    }

    @SuppressWarnings("static-access")
    @Override
    public void keyReleased(KeyEvent e) {
        int k = e.getKeyCode();
        if (k==e.VK_UP) kUp = false;
        if (k==e.VK_DOWN) kDown = false;
        if (k==e.VK_LEFT) kLeft = false;
        if (k==e.VK_RIGHT) kRight = false;
    }

    private boolean isBlockBoard(byte x, byte y)
    {
        for (byte xx=0; xx<4; xx++)
            for (byte yy=0; yy<4; yy++)
                if (block.tab[xx][yy] && tab[xx+x][yy+y]>0) return false;
        return true;
    }

    private boolean moveLeft()
    {
        if (!isBlockBoard((byte)(blockX-1), blockY)) return false;
        return true;
    }
    private boolean moveRight()
    {
        if (!isBlockBoard((byte)(blockX+1), blockY)) return false;
        return true;
    }

    private void key()
    {
        speedKey=!speedKey;
        if (kUp && speedKey) {
            block.rotation();sRotation.play();if (!isBlockBoard(blockX, blockY)) block.reverseRotation();}
        if (kLeft && moveLeft()) blockX--;
        if (kRight && moveRight()) blockX++;
        if (kDown && speedMax>0) {speedMax=0;Tetris.points+=5;Tetris.lPoints.setText(String.valueOf(Tetris.points));}
    }

}
