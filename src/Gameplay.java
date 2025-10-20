import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Gameplay extends JLayeredPane implements ActionListener{
    //0 = coins
    private String upgradeIDPath = "upgradeData.csv";
    private int[] statArray = new int[5];
    private List<Item> itemList = new ArrayList<Item>();
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
        loadItems();
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
        statDisplay.setForeground(Color.GREEN);
        statDisplay.setHorizontalAlignment(JLabel.CENTER);
        statDisplay.setBounds(900,0,100,30);
        statDisplay.setEditable(false);
        statDisplay.setVisible(true);
        statDisplay.setBackground(Color.BLACK);
        add(statDisplay);
    }

    public void loadItems() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(upgradeIDPath));
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                String[] item = line.split(",");
                itemList.add(new Item(item[0],item[1],Integer.parseInt(item[2]),Integer.parseInt(item[3]),Integer.parseInt(item[4]),0,Integer.parseInt(item[5])));
            }
            br.close();
        }
        catch (IOException e) {
            System.out.println("Error opening upgrade ID file");
        }
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public int[] getStatArray() {
        return statArray;
    }
}
