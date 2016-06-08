/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dodgeball;

import Animations.EndScreen;
import Animations.GameScreen;
import Animations.TitleScreen;
import Player.PlayerControl;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import java.awt.event.KeyEvent;

import java.awt.event.KeyListener;
import javax.swing.JPanel;


/**
 *
 * @author Kyle
 */
public class Game extends Canvas implements Runnable{
    
    public static JFrame frame;
    
    
    
    boolean isRunning = false;
    //Start at startScreen
    boolean startScreen = true;
    boolean gameScreen = false;
    boolean endingScreen = false;
    
    public static final int  WIDTH = 640, HEIGHT = 400;
    public static final String title = "B.E.A.S.T.";
    
    private Thread thread;
    public PlayerControl playerControl;
    private TitleScreen titleAnim;
    private GameScreen gameAnim;
    
    public Game(){
        Dimension dimension = new Dimension(Game.WIDTH, Game.HEIGHT);
        setPreferredSize(dimension);
        setMinimumSize(dimension);
        setMaximumSize(dimension);
        this.playerControl = new PlayerControl();
        this.titleAnim = new TitleScreen(this);
        
    }
    
    public synchronized void start(){
        if(isRunning) return;
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }
    public synchronized void stop(){
        if(!isRunning) return;
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void tick(){
        playerControl.KeyTick();
        
    }
    
    public PlayerControl getPlayerControl(){
        return playerControl;
    }
    
    private void render(){
        
       BufferStrategy bs = getBufferStrategy();
        if(bs == null){
            createBufferStrategy(3);
            return;
        } 
        
        Graphics g = bs.getDrawGraphics();
        //Clear screen**********************
        g.clearRect(0, 0, WIDTH, HEIGHT);
        //Draw here*************************
       if(startScreen){
       titleAnim.TitleAnimation(g);
            if(playerControl.z){
                startScreen = false;
                gameScreen = true;
                this.gameAnim = new GameScreen(this);
            }
       }
       
       if(gameScreen){
           gameAnim.drawGame(g);
           if(gameAnim.timerEnd){
               endingScreen = true;
               gameScreen = false;
           }
       }
       
       if(endingScreen){
           EndScreen end = new EndScreen(this, gameAnim);
           end.EndAnimation(g);
           if(playerControl.x){
               endingScreen = false;
               startScreen = true;
            }
           if(playerControl.esc){
               System.exit(0);
           }
       }
        g.dispose();
        bs.show();
    }
    
    @Override
    public void run() {
        init();
        
        int fps = 0;
        double timer = System.currentTimeMillis();
        
        long lastTime = System.nanoTime();
        double targetTick = 60;
        double delta = 0;
        double ns = 1000000000/targetTick;
        
        while(isRunning){
            long now = System.nanoTime();
            delta += (now - lastTime)/ns;
            lastTime = now;
            
            while(delta >= 1){
                tick();
                render();
                fps++;
                delta--;
                }//end of tick, render while
            
            if(System.currentTimeMillis() - timer >= 1000){
                //To see the fps
                //System.out.println(fps);
                fps = 0;
                timer += 1000;
                }
            }//end of isRunning while
        stop();
    }//end of run()
    
    public static void main(String[] args){
        Game game = new Game();
        game.start();
    }
    
    public void init(){
        
        frame = new JFrame(title);
        frame.add(this);
        frame.setResizable(false);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
                
        
        frame.setVisible(true);
        frame.addKeyListener(playerControl);  
   }
    
   
    
    
}
