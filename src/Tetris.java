import javax.swing.*;
import java.awt.*;

public class Tetris extends JPanel implements Runnable {

    static Tetris tetris = new Tetris();
    static JFrame window = new JFrame("Tetris");
    static Thread thread = new Thread(tetris);
    static Board board = new Board();
    static Draw draw = new Draw();
    boolean start = false;
    short acceleration = 30;
    static int lines = 0, points = 0, level = 1;
    static JLabel lPoints, lLines, lLevel, lPointsy2, lLines2, lLeve2;
    static Color cPoints = new Color(255,128,255);
    static Color cLines = new Color(255,255,0);
    static Color cLevel = new Color(0,255,255);
    static Font part1, part2;

    Tetris()
    {
        super();
        setBackground(new Color(224,255,255));
        setLayout(null);
        start = true;
        part1 = new Font("System", Font.BOLD, 20);
        part2 = new Font("System", Font.BOLD, 10);
    }

    public static void main(String[] args) {
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.add(tetris);
        window.setSize(388,555);
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        lPoints = new JLabel("0", JLabel.RIGHT);
        lPoints.setForeground(cPoints);lPoints.setFont(part1);
        lPoints.setBounds(270, 300, 100, 20);tetris.add(lPoints);
        lPointsy2 = new JLabel("POINTS", JLabel.RIGHT);
        lPointsy2.setBounds(270, 290, 100, 10);lPointsy2.setForeground(cPoints);
        lPointsy2.setFont(part2);tetris.add(lPointsy2);
        lLines = new JLabel("0", JLabel.RIGHT);
        lLines.setForeground(cLines);lLines.setFont(part1);
        lLines.setBounds(270, 230, 100, 20);tetris.add(lLines);
        lLines2 = new JLabel("LINES", JLabel.RIGHT);
        lLines2.setBounds(270, 220, 100, 10);lLines2.setForeground(cLines);
        lLines2.setFont(part2);tetris.add(lLines2);
        lLevel = new JLabel("1", JLabel.RIGHT);
        lLevel.setForeground(cLevel);lLevel.setFont(part1);
        lLevel.setBounds(270, 160, 100, 20);tetris.add(lLevel);
        lLeve2 = new JLabel("LEVEL", JLabel.RIGHT);
        lLeve2.setBounds(270, 150, 100, 10);lLeve2.setForeground(cLevel);
        lLeve2.setFont(part2);tetris.add(lLeve2);
        board.setLocation(10, 10);tetris.add(board);
        draw.setLocation(270, 10);tetris.add(draw);
        window.setVisible(true);
        thread.start();
    }

    @SuppressWarnings("static-access")
    @Override
    public void run() {
        long wait, startTime, cycleTime;
        while (start)
        {
            startTime = System.nanoTime();
            board.run();
            draw.run();
            cycleTime = System.nanoTime() - startTime;
            wait = acceleration - cycleTime / 1000000;
            if (wait<=0) wait = 5;
            try {thread.sleep(wait);} catch (InterruptedException e) {e.printStackTrace();}
        }
    }
}
