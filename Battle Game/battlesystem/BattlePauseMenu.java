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
import com.badlogic.gdx.math.Rectangle;
import com.mb.Config;
import com.mb.entities.battle.Beast;
import com.mb.player.GamePlayer;

/**
 *
 * @author Kyle
 */
public class BattlePauseMenu {
    BitmapFont font14, font12;
    int s = 32;
    Texture shader = new Texture("BlackPage.png");
    Texture menuSheet = new Texture("PausedMenuSheet.png");
    TextureRegion itemImg, switchImg, runImg, highlightImg, plusImg;
    TextureRegion switchMenuImg, switchArrowImg, SwitchShader;
    TextureRegion[] elementList;
    GamePlayer p;
    private MenuState currentState = MenuState.Paused;
    
    Rectangle plusBox, switchBox, itemBox, runBox, highlightBox;
    Rectangle switchMenuBox, switchArrowBox;
    
    public enum MenuState{
        Paused, Switch, Item, Run
    }
    
    private boolean pausedPlayer = false;
    float timePaused = 0, maxWaitTime = 0.5f;
    
    public BattlePauseMenu(GamePlayer p){
        this.p = p;
        SetupImages();
        SetupLocations();
        
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/OrangeKid.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 13;
        parameter.color = Color.WHITE;
        parameter.borderWidth = 1;
        parameter.borderColor = Color.BLACK;        
        font14 = generator.generateFont(parameter);
        
        FreeTypeFontGenerator.FreeTypeFontParameter parameter2 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter2.size = 13;
        parameter2.color = Color.BLACK;
        font12 = generator.generateFont(parameter2);
        generator.dispose(); // don't forget to dispose to avoid memory leaks!
    }
    
    public void PauseMenuLogic(BattleStage battle){
        switch(currentState){
            case Paused: PausedLogic(battle);
                break;
            case Switch: SwitchCharacterLogic(battle);
                break;
            case Item:
                break;
            case Run:
                break;
        }
    }
    
    public void PausedLogic(BattleStage battle){
        if(p.getControls().isButtonStart()){
            if(timePaused >= maxWaitTime){
                timePaused = 0;
                battle.EndPauseMenu(p);
            }
        }
//        if(p.getControls().isLeft()){
//            if(timePaused >= maxWaitTime){
//                timePaused = 0;
//                currentState = MenuState.Item;
//            }
//        }
//        if(p.getControls().isRight()){
//            if(timePaused >= maxWaitTime){
//                timePaused = 0;
//                currentState = MenuState.Run;
//            }
//        }
        if(p.getControls().isDown()){
            if(timePaused >= maxWaitTime){
                timePaused = 0;
                currentState = MenuState.Switch;
            }
        }
    }
    
    public void SwitchCharacterLogic(BattleStage battle){
        if(p.getControls().isUp() && p.GetBeastInParty(1) != null){
            if(timePaused >= maxWaitTime){
                battle.SwitchBeast(p, 1);
                timePaused = 0;
            }
        }
        if(p.getControls().isRight() && p.GetBeastInParty(2) != null){
            if(timePaused >= maxWaitTime){
                battle.SwitchBeast(p, 2);
                timePaused = 0;
            }
        }
        if(p.getControls().isDown() && p.GetBeastInParty(3) != null){
            if(timePaused >= maxWaitTime){
                battle.SwitchBeast(p, 3);
                timePaused = 0;
            }
        }
        if(p.getControls().isLeft() && p.GetBeastInParty(4) != null){
            if(timePaused >= maxWaitTime){
                battle.SwitchBeast(p, 4);
                timePaused = 0;
            }
        }
        if(p.getControls().isButton1()){
            if(timePaused >= maxWaitTime){
                battle.EndPauseMenu(p);
                timePaused = 0;
            }
        }
        if(p.getControls().isButton2()){
            if(timePaused >= maxWaitTime){
                currentState = MenuState.Paused;
                timePaused = 0;
            }
        }
        if(p.getControls().isButtonStart()){
            if(timePaused >= maxWaitTime){
                battle.EndPauseMenu(p);
                timePaused = 0;
            }
        }
    }
    
    public void PlayerBattlePause(BattleStage battle){
        p.getControls().ListenForInput();
        PauseMenuLogic(battle);
        if(timePaused < maxWaitTime){
            timePaused += Gdx.graphics.getDeltaTime();
        }
    }
    
    public void RenderPaused(SpriteBatch batch){
        batch.draw(plusImg, plusBox.x, plusBox.y);
        batch.draw(itemImg, itemBox.x, itemBox.y);
        batch.draw(runImg, runBox.x, runBox.y);
        batch.draw(switchImg, switchBox.x, switchBox.y);
    }
    
    float time, maxTime = 0.5f;
    boolean animOn = false;
    public void SwitchAnimate(){
        if(time >= maxTime){
            animOn = !animOn;
            time = 0;
        }
        else{
            time += Gdx.graphics.getDeltaTime();
        }
    }
    
    public void RenderSwitched(SpriteBatch batch){
        batch.draw(switchMenuImg, switchMenuBox.x, switchMenuBox.y);
        
        if(animOn){
            for(int i = 0; i < 4; i++){
                if(p.GetBeastInParty(i+1) != null && p.GetBeastInParty(i+1).getStats().getHP() > 0){
                    batch.draw(switchArrowImg, switchArrowBox.x, switchArrowBox.y - (i*s) + 1, s/2, s/2, s, s, 1, 1, i*-90);
                    batch.draw(p.GetFaceImg(i+1)[1], switchArrowBox.x + s + 1, switchArrowBox.y - (i*s) + 1);
                }
            }
        }
        else{
            for(int i = 0; i < 4; i++){
                if(p.GetBeastInParty(i+1) != null && p.GetBeastInParty(i+1).getStats().getHP() > 0){
                    batch.draw(p.GetFaceImg(i+1)[0], switchArrowBox.x + s + 1, switchArrowBox.y - (i*s) + 1);
                }
            }
        }
        for(int i = 0; i < 4; i++){
            if(p.GetBeastInParty(i+1) != null){
                font12.draw(batch, "Lv: " + p.GetBeastInParty(i+1).getStats().getLv(), switchArrowBox.x + s*2, switchArrowBox.y - (i*s) + 30);
                font14.draw(batch, p.GetBeastInParty(i+1).getName(), switchArrowBox.x + s*4, switchArrowBox.y - (i*s) + 28);
                font12.draw(batch, "HP: " + p.GetBeastInParty(i+1).getStats().getHP() + "/" + p.GetBeastInParty(i+1).getStats().getMaxHP(),
                        switchArrowBox.x + s*2, switchArrowBox.y - (i*s) + 20);
                font12.draw(batch, "EP: " + p.GetBeastInParty(i+1).getStats().getEP() + "/" + p.GetBeastInParty(i+1).getStats().getMaxEP(),
                        switchArrowBox.x + s*2, switchArrowBox.y - (i*s) + 10);
                RenderElement(p.GetBeastInParty(i+1), switchArrowBox.x + s*3 + s/2, switchArrowBox.y - (i*s) + 16, batch);
                
                if(p.GetBeastInParty(i+1).getStats().getHP() <= 0){
                    batch.draw(p.GetFaceImg(i+1)[2], switchArrowBox.x + s + 1, switchArrowBox.y - (i*s) + 1);
                    batch.setColor(1, 1, 1, 0.5f);
                    batch.draw(SwitchShader, switchArrowBox.x + 3, switchArrowBox.y  - (i*s) + 1);
                    batch.setColor(1, 1, 1, 1);
                }
            }
            else{
                batch.setColor(1, 1, 1, 0.5f);
                batch.draw(SwitchShader, switchArrowBox.x + 3, switchArrowBox.y  - (i*s) + 1);
                batch.setColor(1, 1, 1, 1);
            }
        }
    }
    
    public void RenderElement(Beast b, float x, float y, SpriteBatch batch){
        switch(b.getStats().getType()){
            case DARK: batch.draw(elementList[0], x, y);
                break;
            case LIGHT: batch.draw(elementList[1], x, y);
                break;
            case WATER: batch.draw(elementList[2], x, y);
                break;
            case FIRE: batch.draw(elementList[3], x, y);
                break;
            case NEUTRAL: batch.draw(elementList[4], x, y);
                break;
            case EARTH: batch.draw(elementList[5], x, y);
                break;
            case NATURE: batch.draw(elementList[6], x, y);
                break;
            case ELECTRICITY: batch.draw(elementList[7], x, y);
                break;
            case WIND: batch.draw(elementList[8], x, y);
                break;
        }
    }
    
    public void Render(SpriteBatch batch){
        batch.setColor(1, 1, 1, 0.5f);
        batch.draw(shader, 0, 0);
        batch.setColor(1, 1, 1, 1);
        
        switch(currentState){
            case Paused: RenderPaused(batch);
                break;
            case Switch: SwitchAnimate();
                RenderSwitched(batch);
                break;
        }
    }
    
    public void SetupLocations(){
        Rectangle drawRect = p.GetBeastInParty(0).getBattleStatus().statusArea;
        float xPos = drawRect.x + drawRect.width/2 - plusImg.getRegionWidth()/2;
        float yPos = drawRect.y- plusImg.getRegionHeight()/2 - 20;
        plusBox = new Rectangle(xPos, yPos, s, s);
        itemBox = new Rectangle(xPos, yPos, s, s);
        itemBox.x = plusBox.x - s;
        switchBox = new Rectangle(xPos, yPos, s, s);
        switchBox.y = plusBox.y - s;
        runBox = new Rectangle(xPos, yPos, s, s);
        runBox.x = plusBox.x + s;
        
        //Switch menu locations
        switchMenuBox = new Rectangle(0, 0, s*7, s*4);
        switchMenuBox.x = Config.cameraWidth/2 - switchMenuBox.width/2;
        switchMenuBox.y = Config.cameraHeight/2 - switchMenuBox.height/2;
        switchArrowBox = new Rectangle(switchMenuBox.x, switchMenuBox.y + s*3 + 2, s, s);
    }
    
    public void SetupImages(){
        plusImg = new TextureRegion(menuSheet, s*4, 0, s, s);
        itemImg = new TextureRegion(menuSheet, 0, 0, s, s);
        switchImg = new TextureRegion(menuSheet, s, 0, s, s);
        runImg = new TextureRegion(menuSheet, s*2, 0, s, s);
        switchMenuImg = new TextureRegion(menuSheet, 0, s, s*7, (s*4) + 4);
        switchArrowImg = new TextureRegion(menuSheet, s*3, 0, s, s);
        SwitchShader = new TextureRegion(menuSheet, 0, s*6, 218, 30);
        
        elementList = new TextureRegion[9];
        for(int i = 0; i < 5; i++){
            elementList[i] = new TextureRegion(menuSheet, s*5 + (s/2 * i), 0, s/2, s/2);
        }
        for(int i = 0; i < 4; i++){
            elementList[i+5] = new TextureRegion(menuSheet, s*5 + (s/2 * i), s/2, s/2, s/2);
        }
    }

    /**
     * @return the currentState
     */
    public MenuState getCurrentState() {
        return currentState;
    }

    /**
     * @param currentState the currentState to set
     */
    public void setCurrentState(MenuState currentState) {
        this.currentState = currentState;
    }
}
