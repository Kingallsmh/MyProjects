/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mb.battlesystem.environment;

import com.badlogic.gdx.Gdx;
import com.mb.battlesystem.Rules.Direction;
import com.mb.battlesystem.environment.AnimatedBGLayout.Pattern;
import java.util.Random;

/**
 *
 * @author Kyle
 */
public class ItemAnimationPattern {
    AnimatedBGItem item;
    private Pattern pattern;
    
    float time, maxTime;
    float gravityPull;
    float maxXVel;
    float speed;
    Direction direct;
    Random rnd;
    
    public ItemAnimationPattern(AnimatedBGItem item, Pattern pattern, Random rnd){
        this.item = item;
        this.pattern = pattern;
        this.rnd = rnd;
        direct = Direction.EAST;
        switch(this.pattern){
            case FallingLeafs: SetupFallingLeafNumbers(rnd);
                break;
            case BlowingLeafs: SetupBlowingLeafNumbers(rnd);
                break;
        }
    }
    
    public void Animate(){
        switch(pattern){
            case FallingLeafs: FallingLeafAnimation();
                break;
            case BlowingLeafs: BlowingLeafAnimation();
                break;
        }
    }
    
    public void SetupBlowingLeafNumbers(Random rnd){
        gravityPull = rnd.nextInt(28 - 12 + 1) + 12;
        speed = (float)(rnd.nextInt(12 - 1+ 1) + 1)/10;
        maxTime = rnd.nextInt(10 - 3+ 1) + 3;
        maxXVel = rnd.nextInt(200 - 80 + 1) + 80;
    }
    
    public void BlowingLeafAnimation(){
        if(item.box.x < -64){
            item.box.x = 500;
            SetupBlowingLeafNumbers(rnd);
        }
        if(item.box.y < -64){
            item.box.y = 500;
            SetupBlowingLeafNumbers(rnd);
        }
        
        if(item.xVel > -maxXVel){
            item.xVel += -speed;
        }
        
        item.yVel = -gravityPull;
    }
    
    //random.nextInt(max - min + 1) + min
    public void SetupFallingLeafNumbers(Random rnd){
        speed = (float)(rnd.nextInt(4 - 1+ 1) + 2)/10;
        maxTime = rnd.nextInt(7 - 2+ 1) + 2;
        gravityPull = rnd.nextInt(18 - 2 + 1) + 2;
        maxXVel = rnd.nextInt(90 - 10 + 1) + 10;
    }
    
    public void FallingLeafAnimation(){
        if(time > maxTime){
            if(direct == Direction.WEST){
                direct = Direction.EAST;
            }
            else if(direct == Direction.EAST){
                direct = Direction.WEST;
            }
            time = 0;
        }
        else{
            if(direct == Direction.WEST){
                if(time > maxTime/2){
                    if(item.xVel < maxXVel){
                        item.xVel += speed;
                    }
                    else if(item.xVel > maxXVel){
                        item.xVel -= speed;
                    }
                }
                else{
                    if(item.xVel > -maxXVel){
                        item.xVel -= speed;
                    }
                    else if(item.xVel < -maxXVel){
                        item.xVel += speed;
                    }
                }
                
            }
            else if(direct == Direction.EAST){
                if(time < maxTime/2){
                    if(item.xVel < maxXVel){
                        item.xVel += speed;
                    }
                    else if(item.xVel >maxXVel){
                        item.xVel -= speed;
                    }
                }
                else{
                    if(item.xVel > -maxXVel){
                        item.xVel -= speed;
                    }
                    else if(item.xVel < -maxXVel){
                        item.xVel += speed;
                    }
                }
            }
            time += Gdx.graphics.getDeltaTime();
        }
        item.yVel = -gravityPull;
        if(item.box.y < -64){
            item.box.y = 10 * 32;
            SetupFallingLeafNumbers(rnd);
        }
        
        if(item.box.x < -100){
            item.box.x = 15*32;
            SetupFallingLeafNumbers(rnd);
        }
        else if(item.box.x > 550){
            item.box.x = -32;
            SetupFallingLeafNumbers(rnd);
        }
    }

    /**
     * @return the pattern
     */
    public Pattern getPattern() {
        return pattern;
    }

    /**
     * @param pattern the pattern to set
     */
    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
        switch(pattern){
            case FallingLeafs: SetupFallingLeafNumbers(rnd);
                break;
            case BlowingLeafs: SetupBlowingLeafNumbers(rnd);
                break;
        }
    }
}
