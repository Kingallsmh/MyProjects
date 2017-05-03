/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mb.battlesystem.environment;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.mb.CalcUtilities;
import com.mb.battlesystem.environment.AnimatedBGLayout.Pattern;
import java.util.Random;

/**
 *
 * @author Kyle
 */
public class AnimatedBGItem {
    TextureRegion[] imgList;
    int frame = 0;
    Rectangle box;
    float xVel, yVel;
    
    ItemAnimationPattern IAP;
    
    public AnimatedBGItem(Pattern pattern, Random rnd){
        
        IAP = new ItemAnimationPattern(this, pattern, rnd);
    }
    
    public void Animate(){
        float deltatime = CalcUtilities.CorrectTime();
        if(xVel < 0){
            frame = 0;
        }
        else{
            frame = 1;
        }
        box.x += xVel * deltatime;
        box.y += yVel * deltatime;
    }
    
    public void Render(SpriteBatch batch){
        IAP.Animate();
        Animate();
        batch.draw(imgList[frame], box.x, box.y);
    }
    
    public void ChangePattern(Pattern pattern){
        IAP.setPattern(pattern);
    }
    
    public Pattern GetPattern(){
        return IAP.getPattern();
    }
}
