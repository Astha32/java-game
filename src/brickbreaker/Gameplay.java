/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brickbreaker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Gameplay extends JPanel implements KeyListener ,ActionListener{
    
   // private static int highscore = 0;
    private boolean play=false;
    private int score =0;
    private int totalBricks=21;
    private Timer timer;
    private int delay=8;
    private int playerX=310;
   // private int highscore=0;
    
    private int ballposX=120;
    private int ballposY=350;
    private int balldirX=-1;
    private int balldirY=-2;
   
    private mapgenerator map;
   // private file hscore;
    public Gameplay()
    {
        map=new mapgenerator(3,7);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }
    public void paint(Graphics g){
        //background
        g.setColor(Color.black);
        g.fillRect(1,1,692, 592);
        
        //map
        map.draw((Graphics2D ) g);
        
        //borders
        g.setColor(Color.yellow);
        g.fillRect(0,0,3,592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(691,0,3,592);
        
        //score
        g.setColor(Color.white);
        g.setFont(new Font("sherif", Font.BOLD,25));
        g.drawString(""+score,592,30);
        
        
        //padle
        g.setColor(Color.green);
        g.fillRect(playerX, 550, 100, 8);
        
        //ball
        g.setColor(Color.yellow);
        g.fillOval(ballposX, ballposY, 20, 30);
       
        if(totalBricks==0)
        {
              g.setColor(Color.white);
        g.setFont(new Font("sherif", Font.BOLD,25));
        g.drawString("YOU WON  SCORE:"+score,192,300);
        g.setFont(new Font("sherif", Font.BOLD,20));
        g.drawString("Press enter to restart",230,450);  
        //hscore=new file(score);
        }
        else
        {
             if(ballposY>570)
        {
            play=false;
            balldirX=0;balldirY=0;
            g.setColor(Color.white);
        g.setFont(new Font("sherif", Font.BOLD,25));
        g.drawString("Game over  SCORE:"+score,192,300);
        // hscore=new file(score);   
         
        g.setColor(Color.red);
        g.setFont(new Font("sherif", Font.BOLD,20));
        g.drawString("Press enter to restart",230,450);
        
        }
        }
        g.dispose();
    }


    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
         if(playerX >= 600){
         playerX = 600;
         }
         else {
         moveRight();
         }
         
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
         if(playerX <= 10){
         playerX = 10;
         }
         else {
         moveLeft();
         }
         
        }
        if(e.getKeyCode() == KeyEvent.VK_ENTER)
        {
           if(!play)
           {
               play=true;          
            score=0;
            totalBricks=21;
            ballposX=120;
            ballposY=350;
            balldirX=-1;
            balldirY=-2;
            map=new mapgenerator(3,7);
        }
    }
    }
     
      public void moveRight(){
    play = true;
    playerX += 20;
    
    }
    public void moveLeft(){
    play = true;
    playerX -= 20;
   
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       timer.start();
       if(play){
           if(new Rectangle(ballposX,ballposY,20,20).intersects(new Rectangle(playerX,550,100,8)))
           {
               balldirY=-balldirY;
           }
           A: for(int i=0;i<map.map.length;i++)
            {
            for(int j=0;j<map.map[0].length;j++)
            {
               if(map.map[i][j]>0)
               {
                   int brickposX=j*map.brickwidth +80;
                   int brickposY=i*map.brickheight +50;
                   int brickWidth=map.brickwidth;int brickHeight=map.brickheight;
                   Rectangle rect=new Rectangle(brickposX,brickposY,brickWidth,brickHeight);
                    Rectangle ballrect=new Rectangle(ballposX,ballposY,20,20);
                    Rectangle brickrect=rect;
                    
                    if(ballrect.intersects(brickrect))
                    {
                        map.setBrickValue(0, i, j);
                        score+=5;
                        totalBricks--;
                        if(ballposX +19<=brickrect.x || ballposX +1>= brickrect.x +brickrect.width)
                        {
                         balldirX=-balldirX;   
                        }
                        else
                            balldirY=-balldirY;
                    
                    break A;
               }}}}
       ballposX += balldirX;
       ballposY += balldirY;
       
       if(ballposX < 0){
       balldirX = -balldirX;
       }
       if(ballposY<0){
       balldirY = -balldirY;
       }
       if(ballposX>670){
       balldirX = -balldirX;
       }   }
       repaint();
    
}
   
  //  public void keyReleased(KeyEvent e) {
   // }

    
   
   
    
    }

