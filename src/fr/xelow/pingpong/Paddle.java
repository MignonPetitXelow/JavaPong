package fr.xelow.pingpong;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Paddle implements Runnable
{
    int x, y, yDirection, id;
    Rectangle paddle;
    public Paddle(int x, int y, int id)
    {
        this.x = x;
        this.y = y;
        this.id = id;
        paddle = new Rectangle(x, y, 10, 50);
    }

    public void keyPressed(KeyEvent e)
    {
        switch (id)
        {
            case 1:
                if(e.getKeyCode() == e.VK_Z) { setYDirection(-1); }
                if(e.getKeyCode() == e.VK_S) { setYDirection(+1); }
                break;
            case 2:
                if(e.getKeyCode() == e.VK_UP) { setYDirection(-1); }
                if(e.getKeyCode() == e.VK_DOWN) { setYDirection(+1); }
                break;
            default:
                System.out.println("Enter correct id.");
                break;
        }
    }
    public void keyReleased(KeyEvent e)
    {
        switch (id)
        {
            case 1:
                if(e.getKeyCode() == e.VK_Z) { setYDirection(0); }
                if(e.getKeyCode() == e.VK_S) { setYDirection(0); }
                break;
            case 2:
                if(e.getKeyCode() == e.VK_UP) { setYDirection(0); }
                if(e.getKeyCode() == e.VK_DOWN) { setYDirection(0); }
                break;
            default:
                System.out.println("Enter correct id.");
                break;
        }
        if(e.getKeyCode() == e.VK_ESCAPE){System.exit(0);}
    }

    public void setYDirection(int ydir) { yDirection = ydir; }

    public void move()
    {
        paddle.y += yDirection;
        if(paddle.y <= 10){ paddle.y = 10; }
        if(paddle.y >= 340) { paddle.y = 340; }
    }

    public void draw(Graphics g)
    {
        switch (id)
        {
            case 1:
                g.setColor(Color.cyan);
                g.fillRect(paddle.x, paddle.y, paddle.width, paddle.height);
                break;
            case 2:
                g.setColor(Color.pink);
                g.fillRect(paddle.x, paddle.y, paddle.width, paddle.height);
                break;
            default:
                System.out.println("Enter correct id.");
                break;
        }
    }

    public void run()
    {
        try {
            while (true)
            {
                move();
                Thread.sleep(7);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
