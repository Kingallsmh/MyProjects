/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mb.battlesystem.environment;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.mb.battlesystem.environment.AnimatedBGLayout.Pattern;
import java.util.Random;

/**
 *
 * @author Kyle
 */
//Have objects move about the stage that do not affect the players or objects, purely visual
//Have visual effects like a heat haze or underwater, a sort of filter
public class AnimatedBackground {
    Texture AnimBGSheet = new Texture("AnimatedBG.png");
    AnimatedBGLayout BGLayout;
    int s = 16;
    private int itemSize;
    Random rnd;
    
    float time, maxTime;
    
    public enum ItemType{
        BigGreenLeafs, BigYellowLeafs, SmallGreenLeafs, SmallYellowLeafs
    }
    
    public AnimatedBackground(Pattern pattern){
        rnd = new Random();
        BGLayout = new AnimatedBGLayout(this, pattern);
//        maxTime = 20;
        maxTime = rnd.nextInt(100 - 30 + 1) + 30;
    }
    
    public void ChangeWeather(){
        if(BGLayout.itemList != null && !BGLayout.itemList.isEmpty()){
            BGLayout.SwitchPattern();
        }
    }
    
    public void Render(SpriteBatch batch){
        if(time > maxTime){
            ChangeWeather();
//            maxTime = 20;
            maxTime = rnd.nextInt(100 - 30 + 1) + 30;
            time = 0;
        }
        BGLayout.Render(batch);
        time += Gdx.graphics.getDeltaTime();
    }
    
    public AnimatedBGItem AddItem(Pattern pattern, ItemType item, float x, float y){
        AnimatedBGItem BGItem = new AnimatedBGItem(pattern, rnd);
        switch(item){
            case BigGreenLeafs: 
                BGItem.imgList = new TextureRegion[2];
                BGItem.imgList[0] = GetImg(0, 0, 2, 2);
                BGItem.imgList[1] = GetImg(0, 2, 2, 2);
                BGItem.box = new Rectangle(x, y, 1, 1);
                break;
            case BigYellowLeafs: 
                BGItem.imgList = new TextureRegion[2];
                BGItem.imgList[0] = GetImg(2, 0, 2, 2);
                BGItem.imgList[1] = GetImg(2, 2, 2, 2);
                BGItem.box = new Rectangle(x, y, 1, 1);
                break;
            case SmallGreenLeafs: 
                BGItem.imgList = new TextureRegion[2];
                BGItem.imgList[0] = GetImg(0, 4, 1, 1);
                BGItem.imgList[1] = GetImg(0, 5, 1, 1);
                BGItem.box = new Rectangle(x, y, 1, 1);
                break;
            case SmallYellowLeafs: 
                BGItem.imgList = new TextureRegion[2];
                BGItem.imgList[0] = GetImg(2, 4, 1, 1);
                BGItem.imgList[1] = GetImg(2, 5, 1, 1);
                BGItem.box = new Rectangle(x, y, 1, 1);
                break;
        }
        return BGItem;
    }
    
    public TextureRegion GetImg(int x, int y, int width, int height){
        return new TextureRegion(AnimBGSheet, s*x, s*y, s*width, s*height);
    }

    /**
     * @return the itemSize
     */
    public int getItemSize() {
        return itemSize;
    }

    /**
     * @param itemSize the itemSize to set
     */
    public void setItemSize(int itemSize) {
        this.itemSize = itemSize;
    }
}
