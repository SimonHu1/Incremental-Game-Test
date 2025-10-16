import javax.swing.*;
import java.awt.*;
public class Game extends JFrame
{
    private Gameplay gameplay;
    public Game()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920,1080);
        setPreferredSize(new Dimension(1920,1080));
        setLayout(null);
        setResizable(true);
        gameplay = new Gameplay();
        add()
    }
}
