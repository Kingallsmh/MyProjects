/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import game.ScreenSetup.MainSetup;

/**
 *
 * @author Kyle
 */
public class AnimatedObject {
    TextureRegion aObject;
    int animationStyle;
    
    //Picture location
    float x;
    float y;
    int animTimer = 0;
    int maxTimer = 60;
    
    public AnimatedObject(TextureRegion aObject, int animationStyle){
        this.aObject = aObject;
        this.animationStyle = animationStyle;
        
        //Pick location based on animation picked
        if(animationStyle == 0){
           y = 64;
           x = 2;
        }
        else if(animationStyle == 1){
           y = 100;
           x = 48; 
        }
    }
    
    public void RisingObject(){
        y += 40 * Gdx.graphics.getDeltaTime();
    }
    
    public void FlutteringObject(){
        if(animTimer < 10 || animTimer >= 20 && animTimer <= 30 || animTimer >= 40 && animTimer <= 50){
            x += 15 * Gdx.graphics.getDeltaTime();
        }
        else{
            x -= 15 * Gdx.graphics.getDeltaTime();
        }
        
        if(animTimer < 5 || animTimer >= 10 && animTimer <= 15 || animTimer >= 20 && animTimer <= 25
            || animTimer >= 30 && animTimer <= 35 || animTimer >= 40 && animTimer <= 45   
            || animTimer >= 50 && animTimer <= 55 ){
            y -= 10 * Gdx.graphics.getDeltaTime();
        }else{
            y += 10 * Gdx.graphics.getDeltaTime();
        }
        
    }
    
    public void RenderObject(SpriteBatch batch, MainSetup mSetup){
        if(animationStyle == 0){
            RisingObject();
        }
        else if(animationStyle == 1){
            FlutteringObject();
        }
        
        batch.draw(aObject, x, y);
        animTimer += 1;
        destroyObject(mSetup);
    }
    
    public void destroyObject(MainSetup mSetup){
        if(animTimer >= maxTimer){
            mSetup.animObject = null;
        }
    }
}
