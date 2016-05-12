/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author Kyle
 */
public class Pegs {
    
    Rectangle spot;
    TextureRegion colourChoice;
    int choiceNum;
    
    public Pegs(float x, float y, GamePieces gp, int num){
        spot = new Rectangle(x, y, 15, 16);
        colourChoice = gp.noP;
        choiceNum = num;
    }
    
    public Pegs(float x, float y, TextureRegion tx, int num){
        spot = new Rectangle(x, y, 15, 16);
        colourChoice = tx;
        choiceNum = num;
    }
    
    public void RenderSpot(SpriteBatch batch){
        batch.draw(colourChoice, 5, 5);
    }
    
    public void changeSpot(TextureRegion c, int num){
        colourChoice = c;
        choiceNum = num;
    }
    
    
}
