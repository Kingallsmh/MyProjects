/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Animations;

import Player.PlayerChar;
import dodgeball.Game;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JOptionPane;

/**
 *
 * @author Kyle Ingalls
 */
public class GameScreen {
    
    private Game game;
    public PlayerChar PChar;
    public Dodgeballs DBall;
    public Dodgeballs[] DBalls = new Dodgeballs[100];
    boolean timerStarted = false;
    public boolean timerEnd = false;
    long gameTimer;
    long secsPassed;
    public long endTime;
    int DBallCount = 0;
    int nextDBall = 1;
    boolean firstBall = false;
    
    public GameScreen(Game game){
        this.game = game;
        PChar = new PlayerChar(game);
        DBall = new Dodgeballs(this);
    }
    
    public void drawGame(Graphics g){
        if(!timerEnd){
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, 640,400);

            g.setColor(Color.BLUE);
            g.drawRect(10, 10, game.getWidth() - 20, game.getHeight() - 20);

            PChar.renderChar(g);
            DBall.dodgeballRender(g);
            StartDodge();

            if(firstBall){
                for(int i = 0; i < DBallCount; i++){
                    DBalls[i].dodgeballRender(g);
                }
            }
            CollisionCheck();
            WinGame();
            g.setColor(Color.YELLOW);
            g.drawString(String.valueOf(secsPassed), 10, 10);
        }
    }
    
    public void StartDodge(){
        MyTimer();
        if(secsPassed == (5 * DBallCount)){
            DBallCount += 1;
            if(DBallCount == nextDBall){
                System.out.println("here");                        
                DBalls[DBallCount - 1] = new Dodgeballs(this);
                nextDBall += 1;
                firstBall = true;
            }
        }
        
        
    }
    
    public void CollisionCheck(){
        if(PChar.playerCol.intersects(DBall.DBallCol)){
            if(!timerEnd){
               endTime = secsPassed; 
               timerEnd = true;  
            }
            
           
        }
        if(firstBall){
            for(int i = 0; i < DBallCount; i++){
                if(PChar.playerCol.intersects(DBalls[i].DBallCol)){
                    if(!timerEnd){
                        endTime = secsPassed; 
                        timerEnd = true;  
                        }
            System.out.println("OOPS!!!");
                    
                }
            }
        }
        
    }
    
    public void MyTimer(){
        if(!timerStarted){
        gameTimer = System.nanoTime();
        System.out.println(gameTimer/1000000000);
        timerStarted = true;
        }
        
        long gameTimer2 = System.nanoTime();
        secsPassed = (gameTimer2/1000000000) - (gameTimer/1000000000);
        System.out.println(secsPassed);
    }
    
    public void WinGame(){
        if(DBallCount == 99){
            timerEnd = true;
            JOptionPane.showMessageDialog(game, "My god, you have accomplished the impossible!");
        }
    }
    
    
}
