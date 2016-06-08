/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.World;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author Kyle
 */
public class Tiles {
    public boolean passable;
    public TextureRegion img;
    public Rectangle hitBox;
    float w = 16;
    float h = 16;
    
    public Tiles(boolean canPass, TextureRegion img, float x, float y){
        passable = canPass;
        this.img = img;
        
        if(!passable){
            hitBox = new Rectangle();
            hitBox.height = w;
            hitBox.width = h;
            hitBox.x = x;
            hitBox.y = y;
        }
    }
}
