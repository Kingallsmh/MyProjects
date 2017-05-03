/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mb.battlesystem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.math.Rectangle;
import com.mb.entities.battle.Beast;

/**
 *
 * @author Kyle
 */
public class StatusSystem {
    BitmapFont font14, font16;
    Beast owner;
    private int playerNum;
    Texture statusSheet = new Texture("StatusSheet.png");
    TextureRegion statusBox, healthBar, energyBar, gridImg, pauseImg, pauseWord;
    private TextureRegion walk1;
    private TextureRegion walk2;
    private TextureRegion hurt;
    TextureRegion currentImg;
    
    float time, maxTime = 0.3f;
    
    Rectangle statusArea, healthBox, energyBox, characterArea, nameBox, pauseBox;
    
    public StatusSystem(int playerNum, TextureRegion[] imgList, Beast owner){
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/OrangeKid.ttf"));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = 13;
        parameter.color = Color.WHITE;
        parameter.borderWidth = 1;
        parameter.borderColor = Color.BLACK;
        
        font14 = generator.generateFont(parameter); // font size 12 pixels
        generator.dispose(); // don't forget to dispose to avoid memory leaks!
        
        this.owner = owner;
        this.playerNum = playerNum;
        this.walk1 = imgList[0];
        this.walk2 = imgList[1];
        this.hurt = imgList[2];
        SizeCharacterImg();
        GetStatusImg();
        StatusSetup();
    }
    
    public void SizeCharacterImg(){
        this.walk1 = new TextureRegion(walk1, (int)owner.getFaceBox().x, 64 - 30 -(int)owner.getFaceBox().y, 30, 30);
        this.walk2 = new TextureRegion(walk2, (int)owner.getFaceBox().x, 64 - 30 -(int)owner.getFaceBox().y, 30, 30);
        this.hurt = new TextureRegion(hurt, (int)owner.getFaceBox().x, 64 - 30 -(int)owner.getFaceBox().y, 30, 30);
        currentImg = walk1;
    }
    
    public void StatusSetup(){
        switch(playerNum){
            case 0: statusArea = new Rectangle(0, 320 - 54, 96, 54);
                healthBox = new Rectangle(36, 320 - 54 + 25, 49, 8);
                energyBox = new Rectangle(36, 320 - 54 + 14, 49, 8);
                characterArea = new Rectangle(1, 320 - 54 + 1, 30, 30);
                nameBox = new Rectangle(1, 320 - 54 + 35, 94, 18);
                pauseBox = new Rectangle(36, 320 - 54 + 3, 49, 8);
                break;
            case 1: statusArea = new Rectangle(100, 320 - 54, 96, 54);
                healthBox = new Rectangle(136, 320 - 54 + 25, 49, 8);
                energyBox = new Rectangle(136, 320 - 54 + 14, 49, 8);
                characterArea = new Rectangle(101, 320 - 54 + 1, 30, 30);
                nameBox = new Rectangle(101, 320 - 54 + 35, 94, 18);
                pauseBox = new Rectangle(136, 320 - 54 + 3, 49, 8);
                break;
            case 3: statusArea = new Rectangle(480 - 96, 320 - 54 + 0, 96, 54);
                healthBox = new Rectangle(480 - 36 - 49, 320 - 54 + 25, 49, 8);
                energyBox = new Rectangle(480 - 36 - 49, 320 - 54 + 14, 49, 8);
                characterArea = new Rectangle(480 - 1 - 30, 320 - 54 + 1, 30, 30);
                nameBox = new Rectangle(480 - 1 - 94, 320 - 54 + 35, 94, 18);
                pauseBox = new Rectangle(480 - 36 - 49, 320 - 54 + 3, 49, 8);
                break;
            case 2: statusArea = new Rectangle(480 - 100 - 96, 320 - 54 + 0, 96, 54);
                healthBox = new Rectangle(480 - 100 - 36 - 49, 320 - 54 + 25, 49, 8);
                energyBox = new Rectangle(480 - 100 - 36 - 49, 320 - 54 + 14, 49, 8);
                characterArea = new Rectangle(480 - 100 - 1 - 30, 320 - 54 + 1, 30, 30);
                nameBox = new Rectangle(480 - 100 - 1 - 94, 320 - 54 + 35, 94, 18);
                pauseBox = new Rectangle(480 - 100 - 36 - 49, 320 - 54 + 3, 49, 8);
                break;
        }
    }
    
    public void Logic(){
        
    }
    
    public void Animate(){
        if(time > maxTime){
            if(currentImg == walk1){
                currentImg = walk2;
            }
            else{
                currentImg = walk1;
            }
            time = 0;
        }
        else{
            time += Gdx.graphics.getDeltaTime() * ((float)this.owner.getStats().getHP()/(float)this.owner.getStats().getMaxHP());
        }
        
    }
    
    public void Render(SpriteBatch batch){
        Animate();
        batch.draw(statusBox, statusArea.x, statusArea.y);
        int flipNum = 1;
        float extraWidth = 0;
        float imgFlipWidth = 0;
        
        if(playerNum > 1){
            flipNum = -1;
            extraWidth = healthBox.width;
            imgFlipWidth = 30;
        }
        
        int healthPercent = (int) ((int)((float)owner.getStats().getHP()/2)/((float)owner.getStats().getMaxHP()/2) * 100);
        batch.draw(healthBar, healthBox.x + extraWidth, healthBox.y, flipNum * healthBar.getRegionWidth() * healthPercent/100, healthBar.getRegionHeight());
        batch.draw(gridImg, healthBox.x, healthBox.y);
        
        int energyPercent = (int) ((int)((float)owner.getStats().getEP()/1)/((float)owner.getStats().getMaxEP()/1) * 100);
        batch.draw(energyBar, energyBox.x + extraWidth, energyBox.y, flipNum * energyBar.getRegionWidth() * energyPercent/100, energyBar.getRegionHeight());
        batch.draw(gridImg, energyBox.x, energyBox.y);
        
        float pausePercent = owner.getTamer().getPauseTimer()/owner.getTamer().getMaxPauseTime();
        batch.draw(pauseImg, pauseBox.x + extraWidth, pauseBox.y, flipNum * pauseImg.getRegionWidth() * pausePercent, pauseImg.getRegionHeight());
                
        batch.draw(currentImg, characterArea.x  + imgFlipWidth, characterArea.y, flipNum * currentImg.getRegionWidth(), currentImg.getRegionHeight());
        font14.draw(batch, owner.getName(), nameBox.x, nameBox.y + 13, nameBox.width, 1, false);
    }
    
    public void GetStatusImg(){
        if(playerNum == 0 || playerNum == 1){
            statusBox = new TextureRegion(statusSheet, 0, 0, 96, 54);
        }
        else if(playerNum == 2 || playerNum == 3){
            statusBox = new TextureRegion(statusSheet, 96, 0, 96, 54);
        }
        healthBar = new TextureRegion(statusSheet, 48, 56, 49, 8);
        gridImg = new TextureRegion(statusSheet, 0, 56, 48, 8);
        energyBar = new TextureRegion(statusSheet, 48, 67, 49, 8);
        pauseImg = new TextureRegion(statusSheet, 48, 78, 49, 8);
        pauseWord = new TextureRegion(statusSheet, 0, 78, 49, 8);
    }

    /**
     * @return the playerNum
     */
    public int getPlayerNum() {
        return playerNum;
    }

    /**
     * @param playerNum the playerNum to set
     */
    public void setPlayerNum(int playerNum) {
        this.playerNum = playerNum;
    }

    /**
     * @return the walk1
     */
    public TextureRegion getWalk1() {
        return walk1;
    }

    /**
     * @return the walk2
     */
    public TextureRegion getWalk2() {
        return walk2;
    }

    /**
     * @return the hurt
     */
    public TextureRegion getHurt() {
        return hurt;
    }
}
