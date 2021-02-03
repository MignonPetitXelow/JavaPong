package fr.xelow.pingpong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Pong extends JFrame
{

    int gWidth = 500;
    int gHeight = 400;
    Dimension screensize = new Dimension(gWidth, gHeight);


    Image dbImage;
    Graphics dbGraphics;

    static Ball b = new Ball(250,250);

    public Pong()
    {
        this.setTitle("Ping.. Pong!");
        this.setSize(screensize);
        this.setResizable(false);
        this.setUndecorated(true);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setBackground(Color.BLACK);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("resources/icon.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(new AL());
    }

    public static void main(String[] args)
    {
        Pong pg = new Pong();

        Thread ball = new Thread(b);
        Thread p1 = new Thread(b.p1);
        Thread p2 = new Thread(b.p2);
        ball.start();
        p1.start();
        p2.start();
    }

    @Override
    public void paint(Graphics g)
    {
        dbImage = createImage(getWidth(), getHeight());
        dbGraphics = dbImage.getGraphics();
        draw(dbGraphics);
        g.drawImage(dbImage, 0,0,this);
    }

    public void draw(Graphics g)
    {
        b.draw(g);
        b.p1.draw(g);
        b.p2.draw(g);

        g.setColor(Color.white);
        g.drawString(""+b.p1Score, 125,20);
        g.drawString(""+b.p2Score, 375,20);

        repaint();
    }

    public class AL extends KeyAdapter
    {
        @Override
        public void keyPressed(KeyEvent e)
        {
            b.p1.keyPressed(e);
            b.p2.keyPressed(e);
        }
        @Override
        public void keyReleased(KeyEvent e)
        {
            b.p1.keyReleased(e);
            b.p2.keyReleased(e);
        }
    }
}
