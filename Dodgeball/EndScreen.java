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
public class EndScreen {
    
    private Game game;
    private GameScreen gamescreen;
    
    public EndScreen(Game game, GameScreen gamescreen){
        this.game = game;
        this.gamescreen = gamescreen;
    }
    
    public void EndAnimation(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        
        g.setColor(Color.WHITE);
        g.drawString("Your score: " + gamescreen.endTime + " seconds!", 200, 160);
        g.drawString("Press X to go back to the title screen", 200, 180);
        g.drawString("Press Esc to exit the game. Your score will not be saved :(", 200, 200);
    }
}
