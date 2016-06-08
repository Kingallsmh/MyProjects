/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Animations;

import dodgeball.Game;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/**
 *
 * @author Kyle
 */
public class Dodgeballs {
    
    private GameScreen gamescreen;
    int yCord;
    int xCord;
    int dWidth = 30;
    int dHeight = 30;
    int dYSpeed;
    int dXSpeed;
    Rectangle DBallCol;
    
    public Dodgeballs(GameScreen gamescreen){
        this.gamescreen = gamescreen;
        DBallStartPOS();
    }
    
    public void dodgeballMove(){
        //if the yCord is past the line, reverse dYSpeed
        if(yCord < 10 || yCord > 390 - dHeight){
            dYSpeed = dYSpeed * -1;
        }
        yCord += dYSpeed;
        
        //if the xCord is past the line, reverse dXspeed
        if(xCord < 10 || xCord > 630 - dWidth){
            dXSpeed = dXSpeed * -1;
        }
        xCord += dXSpeed;
        //System.out.println(dYSpeed + " , " + dXSpeed);
        DBallCol = new Rectangle(xCord, yCord, dWidth, dHeight);
    }
    
    public void dodgeballRender(Graphics g){
        dodgeballMove();
        g.setColor(Color.red);
        g.fillOval(xCord, yCord, dWidth, dHeight);
                
    }
    
    public void DBallStartPOS(){
        //Use Random to make the dodgeball go at different speeds and start at random
        //locations within the area.
        Random intR = new Random();
                          
        int wallDec = intR.nextInt((4 - 1)+ 1) + 1;
        //North Wall -Min yCord -Any xCord
        if(wallDec == 1){
            yCord = 10;
            xCord = intR.nextInt(((630 - dWidth) - 10)+ 1) + 10;
            
            dYSpeed = intR.nextInt((4 - 1)+ 1) + 1;
            dXSpeed = intR.nextInt((4 - -4)+ 1) + -4;
            
        }
        //South Wall -Max yCord -Any xCord
        if(wallDec == 2){      //((max - min)+ 1)+ min
            yCord = (390 - dHeight);
            xCord = intR.nextInt(((630 - dWidth) - 10)+ 1) + 10;
            
            dYSpeed = intR.nextInt((-1 - -4)+ 1) + -4;
            dXSpeed = intR.nextInt((4 - -4)+ 1) + -4;
            
        }
        //West Wall -Any yCord -Min xCord    
        if(wallDec == 3){      //((max - min)+ 1)+ min
            yCord = intR.nextInt(((390 - dHeight) - 10)+ 1) + 10;
            xCord = 10;
            
            dYSpeed = intR.nextInt((4 - -4)+ 1) + -4;
            dXSpeed = intR.nextInt((4 - 1)+ 1) + 1;
            
        }
        //East Wall -Any yCord -Max xCord
        if(wallDec == 4){      //((max - min)+ 1)+ min
            yCord = intR.nextInt(((390 - dHeight) - 10)+ 1) + 10;
            xCord = (630 - dWidth);
            
            dYSpeed = intR.nextInt((4 - -4)+ 1) + -4;
            dXSpeed = intR.nextInt((-1 - -4)+ 1) + -4;
            
        }
        
    }
}
