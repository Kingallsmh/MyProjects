/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Animations;

import dodgeball.Game;
import static dodgeball.Game.HEIGHT;
import static dodgeball.Game.WIDTH;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author Kyle
 */
public class TitleScreen {
    private Game game;
    private String gameName = "DODGEBALL";
    private String start = "Press Z to start!";
    int ballHeight = 100;
    int ballWidth = 100;
    
    public TitleScreen(Game game){
        this.game = game;
    }
    
    
    public void TitleAnimation(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        
        g.setColor(Color.WHITE);
        g.fillRect(250, 110, 200, 90);
        
        g.setColor(Color.red);
        g.fillOval(200, 100, ballWidth, ballHeight);
        
        g.setColor(Color.white);
        g.drawString(start, 280, 250);
        
        g.setColor(Color.BLACK);
        g.drawString(gameName, 300, 160);
        
    }
      
}
