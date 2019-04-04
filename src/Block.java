public class Block {

    public boolean[][] tab = new boolean[4][4];
    private boolean[][] tabE = new boolean[4][4];
    byte aBlock;

    Block()
    {
        setBlock((byte) 0);
    }

    public void setBlock(byte k)
    {
        aBlock = k;
        for (byte x=0; x<4; x++)
            for (byte y=0; y<4; y++)
                tab[y][x] = Blocks.BLOCKS[aBlock][x][y];
    }

    public void rotation()
    {
        for (byte x=0; x<4; x++) for (byte y=0; y<4; y++) tabE[x][y] = tab[x][y];
        for (byte x=0; x<4; x++) for (byte y=0; y<4; y++) tab[3-y][x] = tabE[x][y];
    }

    public void reverseRotation()
    {
        for (byte x=0; x<4; x++) for (byte y=0; y<4; y++) tab[x][y] = tabE[x][y];
    }

}
