/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Player;

import dodgeball.Game;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

/**
 *
 * @author Kyle
 */
public class PlayerChar {
    private PlayerControl playerControl;
    private Game game;
    
    int xCord = 150;
    int yCord = 150;
    int playerWidth = 20;
    int playerHeight = 20;
    int playerSpeed = 4;
    public Rectangle playerCol;
    
    public PlayerChar(Game game){
        this.game = game;
        playerControl = game.playerControl;
    }
    //Controls for the player.
    public void PlayerMove(){
    //Move up
      if(playerControl.up){
          if(yCord > 10){  
          yCord -= playerSpeed;
          }              
        }
      //Move down
        if(playerControl.down){
            if(yCord < 390 - playerHeight)
                yCord += playerSpeed;
        }
      //Move left
        if(playerControl.left){
            if(xCord > 10){
                xCord -= playerSpeed;
            }
        }
      //Move right
        if(playerControl.right){
            if(xCord < 630 - playerWidth){
                xCord += playerSpeed;
            }
        }
//        if(playerControl.z){
//            playerWidth = 35;
//            playerHeight = 5;
//        }
//        if(playerControl.x){
//            playerWidth = 5;
//            playerHeight = 35;
//        }
         playerCol = new Rectangle(xCord, yCord, playerWidth, playerHeight);
         //System.out.println(playerCol);   
    }
    
        
    public void renderChar(Graphics g){
        PlayerMove();
        
        g.setColor(Color.WHITE);
        g.fillRect(xCord, yCord, playerWidth, playerHeight);
               
    }
    
    
    
}
