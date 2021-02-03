package fr.xelow.pingpong;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.awt.*;
import java.io.File;
import java.util.Random;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Ball implements Runnable
{
    int x, y, xDirection, yDirection;
    Rectangle ball;

    Paddle p1 = new Paddle(10,25,1);
    Paddle p2 = new Paddle(485,25,2);

    int p1Score, p2Score;
    Image BallImage;

    public Ball(int x, int y)
    {
        p1Score = p2Score = 0;
        this.x = x;
        this.y = y;
        Random r = new Random();
        int rDir = r.nextInt(1);
        if(rDir == 0) {rDir--;}
        setXDirection(rDir);
        int yrDir = r.nextInt(1);
        if(yrDir ==0){yrDir--;}
        setYDirection(yrDir);
        ball = new Rectangle(this.x,this.y, 15, 15);
    }

    public void setXDirection(int xdir) { xDirection = xdir;}
    public void setYDirection(int ydir) { yDirection = ydir;}
    public void draw(Graphics g)
    {

        g.setColor(Color.WHITE);
        g.fillRect(ball.x, ball.y, ball.width, ball.height);
    }

    public void collision()
    {
        if(ball.intersects(p1.paddle))
        {
            setXDirection(+1);
        }
        if(ball.intersects(p2.paddle))
        {
            setXDirection(-1);
        }
    }

    public void move()
    {
        collision();
        ball.x += xDirection;
        ball.y += yDirection;
        if(ball.x <= 5)
        {
            setXDirection(+1);
            p2Score++;
        }
        if(ball.x >= 485)
        {
            setXDirection(-1);
            p1Score++;


        }
        if(ball.y <= 10)
        {
            setYDirection(+1);

        }
        if(ball.y >= 390)
        {
            setYDirection(-1);

        }

    }

    @Override
    public void run()
    {
        try
        {
            while (true)
            {
                move();
                Thread.sleep(8);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


}
