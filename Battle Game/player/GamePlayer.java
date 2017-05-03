/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mb.player;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mb.battlesystem.BattlePauseMenu;
import com.mb.battlesystem.BattlePauseMenu.MenuState;
import com.mb.battlesystem.BattleStage;
import com.mb.battlesystem.attacks.ActionLibrary;
import com.mb.entities.battle.Beast;
import com.mb.entities.battle.BeastLibrary;
import com.mb.entities.overworld.WorldCharacter;
import java.util.ArrayList;

/**
 *
 * @author Kyle
 */
public class GamePlayer {
    String name;
    int currency;
    private int teamNumber;
    private BaseControl controls;
    
    private float pauseTimer = 10;
    private float maxPauseTime = 10;
    
    //Testing
    private WorldCharacter character;
    private Beast[] beastParty;
    
    public GamePlayer(String name){
        this.name = name;
        beastParty = new Beast[5];
    }
    
    public void GamePlayerBattleLogic(BattleStage battle){
        if(controls.buttonStart && pauseTimer >= maxPauseTime){
            battle.CreatePauseMenu(this, MenuState.Paused);
        }
        if(beastParty[0].getAnimations().isDeathOver()){
            boolean aBeastLives = false;
            for(int i = 0; i < beastParty.length ; i++){
                aBeastLives = true;
            }
            if(aBeastLives){
                battle.CreatePauseMenu(this, MenuState.Switch);
            }
            else{
                //Player loses battle
            }
        }
    }
    
    public String AddBeastToParty(Beast beast){
        for(int i = 0; i < beastParty.length; i++){
            if(beastParty[i] == null){
                beastParty[i] = beast;
                return "Beast added!";
            }
        }
        return "No room for beast...";
    }
    
    public void SwitchBeasts(int from, int to){
        if(beastParty[from] == null || beastParty[to] == null){
            return;
        }
        Beast b = beastParty[to];
        beastParty[to] = beastParty[from];
        beastParty[from] = b;
    }
    
    public void RemoveBeastFromParty(Beast beast){
        for(int i = 0; i < beastParty.length; i++){
            if(beastParty[i] == beast){
                beastParty[i] = null;
                return;
            }
        }
    }
    
    public void RemoveBeastFromParty(int spot){
        beastParty[spot] = null;
    }
    
    public Beast GetBeastInParty(int num){
        return beastParty[num];
    }
    
    public TextureRegion[] GetFaceImg(int num){
        TextureRegion[] imgList = new TextureRegion[3];
        imgList[0] = new TextureRegion(beastParty[num].getAnimations().getWalkAnim()[0], 
                (int)beastParty[num].getFaceBox().x, 64 - 30 -(int)beastParty[num].getFaceBox().y, 30, 30);
        imgList[1] = new TextureRegion(beastParty[num].getAnimations().getWalkAnim()[1], 
                (int)beastParty[num].getFaceBox().x, 64 - 30 -(int)beastParty[num].getFaceBox().y, 30, 30);
        imgList[2] = new TextureRegion(beastParty[num].getAnimations().getHurtAnim(), 
                (int)beastParty[num].getFaceBox().x, 64 - 30 -(int)beastParty[num].getFaceBox().y, 30, 30);
        return imgList;
    }

    /**
     * @return the controls
     */
    public BaseControl getControls() {
        return controls;
    }

    /**
     * @param controls the controls to set
     */
    public void setControls(BaseControl controls) {
        this.controls = controls;
    }

    /**
     * @return the beastParty
     */
    public Beast[] getBeastParty() {
        return beastParty;
    }

    /**
     * @param beastParty the beastParty to set
     */
    public void setBeastParty(Beast[] beastParty) {
        this.beastParty = beastParty;
    }

    /**
     * @return the teamNumber
     */
    public int getTeamNumber() {
        return teamNumber;
    }

    /**
     * @param teamNumber the teamNumber to set
     */
    public void setTeamNumber(int teamNumber) {
        this.teamNumber = teamNumber;
    }

    /**
     * @return the pauseTimer
     */
    public float getPauseTimer() {
        return pauseTimer;
    }

    /**
     * @param pauseTimer the pauseTimer to set
     */
    public void setPauseTimer(float pauseTimer) {
        this.pauseTimer = pauseTimer;
    }

    /**
     * @return the maxPauseTime
     */
    public float getMaxPauseTime() {
        return maxPauseTime;
    }

    /**
     * @param maxPauseTime the maxPauseTime to set
     */
    public void setMaxPauseTime(float maxPauseTime) {
        this.maxPauseTime = maxPauseTime;
    }

    /**
     * @return the character
     */
    public WorldCharacter getCharacter() {
        return character;
    }

    /**
     * @param character the character to set
     */
    public void setCharacter(WorldCharacter character) {
        this.character = character;
    }
}
