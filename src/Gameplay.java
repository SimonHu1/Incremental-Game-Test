import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Gameplay extends JLayeredPane implements ActionListener{
    //0 = coins
    private int[] statArray = new int[5];
    private double ticks = 0.0;
    private Timer timer;
    long lastTime = System.nanoTime();
    private JTextField statDisplay = new JTextField();
    public Gameplay()
    {

        timer = new Timer(8,this);
        timer.start();
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
        initializeStats();
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setFont(new Font("Times New Roman", Font.BOLD, 20));
        g2d.fillRect(0, 0, getWidth(), getHeight());
        setBackground(Color.BLACK);
        g2d.dispose();
    }

    public void actionPerformed(ActionEvent e)
    {
        update();
        repaint();
    }

    public void update()
    {
        long now = System.nanoTime();
        double timeDiff = (now - lastTime)/1000000000.0;
        lastTime = now;

        ticks += timeDiff * 100;
        if(ticks>99.9)
        {
            ticks = 0;
            statArray[0] += 1;
            statDisplay.setText("$"+statArray[0]);
        }

        //Used to debug and test tickspeed
        //statDisplay.setText(statArray[0]+" & "+(int)ticks);
    }

    public void initializeStats()
    {
        statDisplay.setText(statArray[0]+"");
        statDisplay.setFont(new Font("Times New Roman", Font.BOLD, 20));
        statDisplay.setForeground(Color.RED);
        statDisplay.setHorizontalAlignment(JLabel.CENTER);
        statDisplay.setBounds(900,100,100,100);
        statDisplay.setEditable(false);
        statDisplay.setVisible(true);
        statDisplay.setBackground(Color.LIGHT_GRAY);
        add(statDisplay);
    }
}
