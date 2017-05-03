/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mb.entities.battle;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.mb.CalcUtilities;
import com.mb.battlesystem.BattleAnimations;
import com.mb.battlesystem.BattleInput;
import com.mb.battlesystem.BattlePhysics;
import com.mb.battlesystem.BattleStage;
import com.mb.battlesystem.Rules.ElementalType;
import com.mb.battlesystem.StatusSystem;
import com.mb.battlesystem.attacks.Action;
import com.mb.battlesystem.attacks.ActionLibrary;
import com.mb.entities.battle.BeastLibrary.BeastName;
import com.mb.player.AIControl;
import com.mb.player.BaseControl;
import com.mb.player.GamePlayer;

/**
 *
 * @author Kyle
 */
public class Beast {
    private BattleStage stage;
    private GamePlayer tamer;
    
    private String name; //Max 13 characters for a name
    private BeastName species;
    private Rectangle faceBox;
    private Rectangle hitbox;
    private Rectangle shootBoxR;
    private Rectangle shootBoxL;
    private Rectangle shootBoxU;
    private Rectangle shootBoxD;
    private float xDistance;
    private BaseControl control;
    private Stats stats;
    private BattlePhysics physics;
    private BattleAnimations animations;
    private BattleInput input;
    SpecialAbility ability;
    private StatusSystem battleStatus;
    
    public enum State{
        Idle, Walk, Melee, Shoot, Hurt, Dead
    }
    
    public Beast(GamePlayer tamer){
        this.tamer = tamer;
        input = new BattleInput(this);
        physics = new BattlePhysics(this);
        animations = new BattleAnimations(this);
        ability = new SpecialAbility();
        
        //TEST
        stats = new Stats();
        stats.setMaxHP(100);
        stats.setHP(stats.getMaxHP());
        stats.setMaxEP(30);
        stats.setEP(stats.getMaxEP());
        stats.setREC(10); //Really slow = 10/slow = 15
        stats.setSPD(15); //Slow = 50/Moderate = 80/Fast = 110/Speedy = 140
        stats.setJMP(27); //Minimum jump needed for 2 block high = 260/ Minimum jump needed for 3 block high = 320
        stats.setPHYS(20);
        stats.setSHOT(10);
        stats.setSDEF(10);
        stats.setPDEF(30);
        stats.setBDEF(20);
        control = new AIControl();
        hitbox = new Rectangle(32, 40, 20, 28);
        shootBoxR = new Rectangle(0, 0, 0, 0);
        shootBoxL = new Rectangle(0, 0, 0, 0);
        shootBoxU = new Rectangle(0, 0, 0, 0);
        shootBoxD = new Rectangle(0, 0, 0, 0);
    }
    
    public void CreateStatusSystem(int playerNum){
        TextureRegion[] imglist = new TextureRegion[3];
        imglist[0] = animations.getWalkAnim()[0];
        imglist[1] = animations.getWalkAnim()[1];
        imglist[2] = animations.getHurtAnim();
        this.battleStatus = new StatusSystem(playerNum, imglist, this);
    }
    
    public void SetLocation(float x, float y){
        hitbox.x = x;
        hitbox.y = y;
    }
    
    public void AddAction(Action action, int spot){
        action.setOwner(this);
        stats.AddAction(action, spot);
    }
    
    public void UseAction(int spot){
        if(stats.getActionList()[spot] == null){
            return;
        }
        if(stats.getActionList()[spot].getMelee() != null){
            animations.setState(State.Melee);
            if(stats.CanUseAction(stats.getActionList()[spot])){
                stage.AddToActionList(new Action(stats.getActionList()[spot]));
            }
        }
        if(stats.getActionList()[spot].getShot()!= null){
            animations.setState(State.Shoot);
            if(stats.CanUseAction(stats.getActionList()[spot])){
                stage.AddToActionList(new Action(stats.getActionList()[spot]));
            }
        }
        if(stats.getActionList()[spot].getWeapon()!= null){
            animations.setState(State.Melee);
            if(stats.CanUseAction(stats.getActionList()[spot])){
                stage.AddToActionList(new Action(stats.getActionList()[spot]));
            }
        }
    }
    
    public void TakeHit(float SDMG, float PDMG, float BDMG, ElementalType element){
        if(!animations.isPhased()){
            stats.CalcDamageForHit(SDMG, PDMG, BDMG, element);
            if(stats.getHP() <= 0){
                stats.setHP(0);
            }
            //Fix later ^|^|^|^|^
            else{
                animations.setPhased(true);
                animations.setState(State.Hurt);
                physics.setxVel(0);
            }
        }
    }
    
    public void LogicLoop(BattleStage stage){
        stats.Logic();
        if(!animations.isDeathOver()){
            input.InputLogic();
            ability.SpeciesAbilityLogic(this);
            physics.ApplyGravity();
            physics.DetectCollision(stage.GetHitboxList());
            hitbox.x += physics.getxVel() * CalcUtilities.CorrectTime();
            hitbox.y += physics.getyVel() * CalcUtilities.CorrectTime();
            animations.AnimationLogic();
        }
    }
    
    public void SwitchShootBox(boolean isFlipped){
        shootBoxL.x = hitbox.x - shootBoxR.width - xDistance;
        shootBoxL.y = hitbox.y + hitbox.height/2 - shootBoxR.height/2;
        shootBoxR.x = hitbox.x + hitbox.width + xDistance;
        shootBoxR.y = hitbox.y + hitbox.height/2 - shootBoxR.height/2;
        shootBoxU.x = hitbox.x + hitbox.width/2;
        shootBoxU.y = hitbox.y + hitbox.height + xDistance;
        shootBoxD.x = hitbox.x + hitbox.width/2;
        shootBoxD.y = hitbox.y - shootBoxD.height - xDistance;
    }
    
    public void Render(SpriteBatch batch){
        if(!animations.isDeathOver()){
            animations.Render(batch);
        }
    }
    
    public void DebugRender(ShapeRenderer draw){
        draw.setColor(1, 0, 0, 1);
        draw.rect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
        draw.setColor(1, 1, 0, 1);
        draw.rect(shootBoxR.x, shootBoxR.y, shootBoxR.width, shootBoxR.height);
        draw.setColor(1, 0, 0, 1);
    }
    
    public boolean IsDamagable(){
        return !animations.isPhased() && !animations.isDeathOver();
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the hitbox
     */
    public Rectangle getHitbox() {
        return hitbox;
    }

    /**
     * @param hitbox the hitbox to set
     */
    public void setHitbox(Rectangle hitbox) {
        this.hitbox = hitbox;
    }

    /**
     * @return the control
     */
    public BaseControl getControl() {
        return control;
    }

    /**
     * @param control the control to set
     */
    public void setControl(BaseControl control) {
        this.control = control;
    }

    /**
     * @return the physics
     */
    public BattlePhysics getPhysics() {
        return physics;
    }

    /**
     * @return the stats
     */
    public Stats getStats() {
        return stats;
    }

    /**
     * @param stats the stats to set
     */
    public void setStats(Stats stats) {
        this.stats = stats;
    }

    /**
     * @return the animations
     */
    public BattleAnimations getAnimations() {
        return animations;
    }

    /**
     * @param animations the animations to set
     */
    public void setAnimations(BattleAnimations animations) {
        this.animations = animations;
    }

    /**
     * @return the shootBoxR
     */
    public Rectangle getShootBoxR() {
        return shootBoxR;
    }

    /**
     * @param shootBox the shootBoxR to set
     */
    public void setShootBox(Rectangle shootBox) {
        this.shootBoxR = shootBox;
    }

    /**
     * @return the xDistance
     */
    public float getxDistance() {
        return xDistance;
    }

    /**
     * @param xDistance the xDistance to set
     */
    public void setxDistance(float xDistance) {
        this.xDistance = xDistance;
    }

    /**
     * @return the stage
     */
    public BattleStage getStage() {
        return stage;
    }

    /**
     * @param stage the stage to set
     */
    public void setStage(BattleStage stage) {
        this.stage = stage;
        physics.setGravity(stage.GetGravity());
    }

    /**
     * @return the shootBoxL
     */
    public Rectangle getShootBoxL() {
        return shootBoxL;
    }

    /**
     * @param shootBoxL the shootBoxL to set
     */
    public void setShootBoxL(Rectangle shootBoxL) {
        this.shootBoxL = shootBoxL;
    }

    /**
     * @return the tamer
     */
    public GamePlayer getTamer() {
        return tamer;
    }

    /**
     * @param tamer the tamer to set
     */
    public void setTamer(GamePlayer tamer) {
        this.tamer = tamer;
    }

    /**
     * @return the shootBoxU
     */
    public Rectangle getShootBoxU() {
        return shootBoxU;
    }

    /**
     * @param shootBoxU the shootBoxU to set
     */
    public void setShootBoxU(Rectangle shootBoxU) {
        this.shootBoxU = shootBoxU;
    }

    /**
     * @return the shootBoxD
     */
    public Rectangle getShootBoxD() {
        return shootBoxD;
    }

    /**
     * @param shootBoxD the shootBoxD to set
     */
    public void setShootBoxD(Rectangle shootBoxD) {
        this.shootBoxD = shootBoxD;
    }

    /**
     * @return the species
     */
    public BeastName getSpecies() {
        return species;
    }

    /**
     * @param species the species to set
     */
    public void setSpecies(BeastName species) {
        this.species = species;
    }

    /**
     * @return the input
     */
    public BattleInput getInput() {
        return input;
    }

    /**
     * @param input the input to set
     */
    public void setInput(BattleInput input) {
        this.input = input;
    }

    /**
     * @return the faceBox
     */
    public Rectangle getFaceBox() {
        return faceBox;
    }

    /**
     * @param faceBox the faceBox to set
     */
    public void setFaceBox(Rectangle faceBox) {
        this.faceBox = faceBox;
    }

    /**
     * @return the battleStatus
     */
    public StatusSystem getBattleStatus() {
        return battleStatus;
    }

    /**
     * @param battleStatus the battleStatus to set
     */
    public void setBattleStatus(StatusSystem battleStatus) {
        this.battleStatus = battleStatus;
    }
}
