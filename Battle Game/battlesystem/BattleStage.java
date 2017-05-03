/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mb.battlesystem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.mb.battlesystem.BattlePauseMenu.MenuState;
import com.mb.battlesystem.attacks.Action;
import com.mb.battlesystem.attacks.ActionLibrary;
import com.mb.battlesystem.attacks.ActionLibrary.MeleeName;
import com.mb.battlesystem.attacks.ActionLibrary.ShotName;
import com.mb.battlesystem.attacks.ActionLibrary.WeaponName;
import com.mb.battlesystem.attacks.MeleeAction;
import com.mb.battlesystem.environment.AnimatedBGLayout;
import com.mb.battlesystem.environment.AnimatedBGLayout.Pattern;
import com.mb.battlesystem.environment.AnimatedBackground;
import com.mb.battlesystem.environment.StageLayout;
import com.mb.battlesystem.environment.StageLayout.StageType;
import com.mb.entities.battle.Beast;
import com.mb.entities.battle.Beast.State;
import com.mb.entities.battle.BeastLibrary;
import com.mb.entities.battle.BeastLibrary.BeastName;
import com.mb.player.GamePlayer;
import com.mb.player.Player2Control;
import com.mb.player.PlayerControl;
import java.util.ArrayList;

/**
 *
 * @author Kyle
 */
public class BattleStage {
    StageLayout layout;
    private ArrayList<Action> actionList;
    private GamePlayer[] gamePlayerList;
    
    BattlePauseMenu pauseMenu;
    float waitTime, maxWaitTime = 0.5f;
    
    public enum BattleType{
        Duel, FreeForAll
    }
    
    public BattleStage(StageLayout layout){
        actionList = new ArrayList<Action>();
        this.layout = layout; 
    }
    
    public void SetupDuel(GamePlayer p1, GamePlayer p2){
        gamePlayerList = new GamePlayer[4];
        p1.setTeamNumber(1);
        gamePlayerList[0] = p1;
        SetupPlayer(gamePlayerList[0], 0);
        
        p2.setTeamNumber(2);
        gamePlayerList[3] = p2; 
        SetupPlayer(gamePlayerList[3], 3);
    }
    
    //Max 13 characters for a name
    public void SetupPlayer(GamePlayer p, int spot){
        //Purely testing code
        p.GetBeastInParty(0).setControl(p.getControls());
        p.GetBeastInParty(0).setStage(this);
        if(spot > 1){
            p.GetBeastInParty(0).getAnimations().setIsFlipped(true);
        }
        p.GetBeastInParty(0).CreateStatusSystem(spot);
        layout.SetBeastLocation(p.GetBeastInParty(0), spot);
    }
    
    public void SwitchBeast(GamePlayer p, int beastTo){
        if(p.GetBeastInParty(beastTo).getStats().getHP() <= 0){
            return;
        }
        if(!p.GetBeastInParty(0).getAnimations().IsActionAvailable() && !p.GetBeastInParty(0).getAnimations().isDeathOver()){
            return;
        }
        Beast oldB = p.GetBeastInParty(0);
        p.SwitchBeasts(beastTo, 0);
        p.GetBeastInParty(0).setControl(p.getControls());
        p.GetBeastInParty(0).setStage(this);
        if(oldB.getAnimations().isIsFlipped()){
            p.GetBeastInParty(0).getAnimations().setIsFlipped(true);
        }
        p.GetBeastInParty(0).CreateStatusSystem(oldB.getBattleStatus().getPlayerNum());
        p.GetBeastInParty(0).SetLocation(oldB.getHitbox().x, oldB.getHitbox().y);
    }
    
//    public boolean CanBeastFit(){
//        
//    }
    
    public ArrayList<Rectangle> GetHitboxList(){
        ArrayList<Rectangle> list = new ArrayList<Rectangle>();
        for(int i = 0; i < layout.getFloor().size(); i++){
            list.add(layout.getFloor().get(i).getHitbox());
        }
        for(int i = 0; i < this.GetStageContainer().length; i++){
            list.add(this.GetStageContainer()[i]);
        }
        return list;
    }
    
    public void CreatePauseMenu(GamePlayer p, MenuState forcedState){
        if(waitTime > maxWaitTime){
            this.pauseMenu = new BattlePauseMenu(p);
            pauseMenu.setCurrentState(forcedState);
            p.setPauseTimer(0);
            waitTime = 0;
        }
    }
    
    public void EndPauseMenu(GamePlayer p){
        this.pauseMenu = null;
        p.GetBeastInParty(0).getAnimations().setCDTime(0);
    }
    
    public void DealWithDeath(GamePlayer p){
        if(p.GetBeastInParty(0).getStats().getHP() <= 0){
            p.GetBeastInParty(0).getAnimations().setState(State.Dead);
        }
    }
    
    public void Render(SpriteBatch batch){
        //Test
        for(int i = 0; i < gamePlayerList.length; i++){
            if(gamePlayerList[i] != null){
                gamePlayerList[i].GamePlayerBattleLogic(this);
                DealWithDeath(gamePlayerList[i]);
                if(pauseMenu == null){
                    if(gamePlayerList[i].getPauseTimer() < gamePlayerList[i].getMaxPauseTime()){
                        gamePlayerList[i].setPauseTimer(gamePlayerList[i].getPauseTimer() + Gdx.graphics.getDeltaTime());
                    }
                }
            }
        }
        
        PauseMenuLogic();
        
        RenderStageElements(batch);
        RenderActionList(batch);
        RenderBeastList(batch);
        RenderStatusSystems(batch);
    }
    
    public void PauseMenuLogic(){
        if(pauseMenu != null){
            pauseMenu.PlayerBattlePause(this);
        }
        else{
            waitTime += Gdx.graphics.getDeltaTime();
        }
    }
    
    public void RenderStatusSystems(SpriteBatch batch){
        for(int i = 0; i < gamePlayerList.length; i++){
            if(gamePlayerList[i] != null){
                if(gamePlayerList[i].GetBeastInParty(0).getBattleStatus() != null){
                    gamePlayerList[i].GetBeastInParty(0).getBattleStatus().Logic();
                    gamePlayerList[i].GetBeastInParty(0).getBattleStatus().Render(batch);
                }
            }
        }
        if(pauseMenu != null){
            pauseMenu.Render(batch);
        }
    }
    
    public void RenderStageElements(SpriteBatch batch){
        batch.draw(layout.getBGImg(), 0, 0);
        if(layout.getAB() != null){
            layout.getAB().Render(batch);
        }
        for(int i = 0; i < layout.getFloor().size(); i++){
            layout.getFloor().get(i).Render(batch);
        }
    }
    
    public void RenderBeastList(SpriteBatch batch){
        for(int i = 0; i < gamePlayerList.length; i++){
            if(gamePlayerList[i] != null){
                if(pauseMenu == null){
                    gamePlayerList[i].GetBeastInParty(0).LogicLoop(this);
                }
                gamePlayerList[i].GetBeastInParty(0).Render(batch);
            }
        }
    }
    
    public void RenderActionList(SpriteBatch batch){
        boolean pause = false;
        if(pauseMenu != null){
            pause = true;
        }
        for(int i = 0; i < actionList.size(); i++){
            actionList.get(i).Render(batch, getBeastList(), layout.GetStageHitboxs(), this.GetStageContainer(), pause);
        }
        for(int i = 0; i < actionList.size(); i++){
            if(actionList.get(i).isIsFinished()){
                actionList.remove(i);
            }
        }
    }
    
    public void DebugRender(ShapeRenderer draw){
        draw.setColor(1, 1, 1, 1);
//        for(int x = 0; x < floor.size(); x++){
//            draw.rect(floor.get(x).getHitbox().x, floor.get(x).getHitbox().y, floor.get(x).getHitbox().width, floor.get(x).getHitbox().height);
//        }
        for(int i = 0; i < gamePlayerList.length; i++){
            if(gamePlayerList[i] != null){
                gamePlayerList[i].GetBeastInParty(0).DebugRender(draw);
            }
        }
        for(int i = 0; i < actionList.size(); i++){
            actionList.get(i).DebugRender(draw);
        }
        for(int i = 0; i < this.GetStageContainer().length; i++){
            draw.rect(this.GetStageContainer()[i].x, this.GetStageContainer()[i].y,
                    this.GetStageContainer()[i].width, this.GetStageContainer()[i].height);
        }
    }
    
    public void SetGravity(float gravity){
        layout.setGravity(gravity);
    }
    
    public float GetGravity(){
        return layout.getGravity();
    }
    
    public void AddToActionList(Action action){
        actionList.add(action);
    }
    
    public Rectangle[] GetStageContainer(){
        return layout.getStageContainer();
    }

    /**
     * @return the actionList
     */
    public ArrayList<Action> getActionList() {
        return actionList;
    }

    /**
     * @param actionList the actionList to set
     */
    public void setActionList(ArrayList<Action> actionList) {
        this.actionList = actionList;
    }

    /**
     * @return the beastList
     */
    public ArrayList<Beast> getBeastList() {
        ArrayList<Beast> beastList = new ArrayList<Beast>();
        for(int i = 0; i < gamePlayerList.length; i++){
            if(gamePlayerList[i] != null){
                beastList.add(gamePlayerList[i].GetBeastInParty(0));
            }
        }
        return beastList;
    }

    /**
     * @return the gamePlayerList
     */
    public GamePlayer[] getGamePlayerList() {
        return gamePlayerList;
    }

    /**
     * @param gamePlayerList the gamePlayerList to set
     */
    public void setGamePlayerList(GamePlayer[] gamePlayerList) {
        this.gamePlayerList = gamePlayerList;
    }
}
