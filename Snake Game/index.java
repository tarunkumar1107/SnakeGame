import javax.swing.*;
import java.awt.*;

public class SnakeGame {
    JFrame frame;
    SnakeGame()
    {
        frame=new JFrame("Snake Game");
        frame.setBounds(10,10,905,700);

        Gamepanel panel=new Gamepanel();
        panel.setBounds(10,10,905,700);
        panel.setBackground(Color.gray);
        frame.add(panel);
//        frame.setLayout(null);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    public static void main(String agr[])
    {
        SnakeGame snake=new SnakeGame();
    }
}
