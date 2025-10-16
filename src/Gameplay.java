import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Gameplay extends JLayeredPane implements ActionListener{
    public Gameplay()
    {
        setFocusable(true);
        addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e)
            {
                System.out.print("a");
            }
            public void keyReleased(KeyEvent e)
            {
                System.out.print("e");
            }
        });
    }
}
