/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mb.battlesystem.attacks;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.mb.battlesystem.Rules.ElementalType;
import com.mb.entities.battle.Beast;
import java.util.ArrayList;

/**
 *
 * @author Kyle
 */
public class Action {
    private Beast owner;
    private String name;
    private boolean flipped = false;
    private boolean isFinished = false;
    
    private ElementalType type;
    private int EPUsage;
    private int SATK;
    private int PATK;
    private int BATK;
    
    private MeleeAction melee;
    private ArrayList<MeleeAction> addedMeleeList;
    boolean meleeGo = false;
    private ShotAction shot;
    private ArrayList<ShotAction> addedShotList;
    boolean shotGo = false;
    private WeaponAction weapon;
    boolean weaponGo = false;
    
    
    public enum AttackType{
        Melee, Shot, Weapon
    }
    
    public Action(){
    }
    
    public Action(Action a){
        this.owner = a.owner;
        this.flipped = owner.getAnimations().isIsFlipped();
        if(a.melee != null){
            this.melee = new MeleeAction(this, a.melee);
        }
        if(a.shot != null){
            this.shot = new ShotAction(this, a.shot);
        }
        if(a.weapon != null){
            this.weapon = new WeaponAction(this, a.weapon);
        }
        this.type = a.type;
        this.SATK = a.SATK;
        this.PATK = a.PATK;
        this.BATK = a.BATK;
        addedMeleeList = new ArrayList<MeleeAction>();
    }
    
    public void Render(SpriteBatch batch, ArrayList<Beast> beastList, ArrayList<Rectangle> environlist, Rectangle[] stageEnds, boolean pause){
        if(melee != null){
            if(owner.getAnimations().getMeleeTime() > owner.getAnimations().getMaxMeleeTime()/2){
                meleeGo = true;
            }
        }
        else if(shot != null){
            shotGo = true;
        }
        else if(weapon != null){
            weaponGo = true;
        }
        
        if(meleeGo){
            if(melee != null){
                if(!pause){
                    melee.Logic(beastList);
                }
                melee.Render(batch);
            }
            RenderExtraMeleeList(batch, beastList, pause);
        }
        if(shotGo){
            if(shot != null){
                if(!pause){
                    shot.Logic(beastList, environlist, stageEnds);
                }
                shot.Render(batch);
            }
            RenderExtraShotList(batch, beastList, environlist, stageEnds, pause);
        }
        if(weaponGo){
            if(weapon != null){
                if(!pause){
                    weapon.Logic(beastList);
                }
                weapon.Render(batch, owner.getHitbox(), flipped);
            }
        }
    }
    
    public void RenderExtraMeleeList(SpriteBatch batch, ArrayList<Beast> beastList, boolean pause){
        if(addedMeleeList != null && !addedMeleeList.isEmpty()){
            for(int i = 0; i < addedMeleeList.size(); i++){
                if(!pause){
                    addedMeleeList.get(i).Logic(beastList);
                }
                addedMeleeList.get(i).Render(batch);
            }
        }
    }
    
    public void RenderExtraShotList(SpriteBatch batch, ArrayList<Beast> beastList, ArrayList<Rectangle> environlist, Rectangle[] stageEnds, boolean pause){
        if(addedShotList != null && !addedShotList.isEmpty()){
            for(int i = 0; i < addedShotList.size(); i++){
                if(!pause){
                    addedShotList.get(i).Logic(beastList, environlist, stageEnds);
                }
                addedShotList.get(i).Render(batch);
            }
        }
    }
    
    public void DebugRender(ShapeRenderer draw){
        if(melee != null){
            melee.DebugRender(draw);
        }
        if(addedMeleeList != null && !addedMeleeList.isEmpty()){
            for(int i = 0; i < addedMeleeList.size(); i++){
                addedMeleeList.get(i).DebugRender(draw);
            }
        }
        if(shot != null){
            shot.DebugRender(draw);
        }
        if(addedShotList != null && !addedShotList.isEmpty()){
            for(int i = 0; i < addedShotList.size(); i++){
                addedShotList.get(i).DebugRender(draw);
            }
        }
        if(weapon != null){
            weapon.DebugRender(draw);
        }
    }

    /**
     * @return the owner
     */
    public Beast getOwner() {
        return owner;
    }

    /**
     * @param owner the owner to set
     */
    public void setOwner(Beast owner) {
        this.owner = owner;
    }

    /**
     * @return the isFinished
     */
    public boolean isIsFinished() {
        for(int i = 0; i < addedMeleeList.size(); i++){
            if(addedMeleeList.get(i).time > addedMeleeList.get(i).getMaxTime()){
                addedMeleeList.remove(i);
            }
        }
        if(melee != null){
            if(melee.time > melee.getMaxTime()){
                melee = null;
            }
            else{
                return false;
            }
        }
        if(addedMeleeList != null && !addedMeleeList.isEmpty()){
            return false;
        }
        if(shot != null){
            return shot.isFinished();
        }
        if(weapon != null){
            return owner.getAnimations().IsActionAvailable();
        }
        return true;
    }

    /**
     * @return the flipped
     */
    public boolean isFlipped() {
        return flipped;
    }

    /**
     * @param flipped the flipped to set
     */
    public void setFlipped(boolean flipped) {
        this.flipped = flipped;
    }

    /**
     * @return the melee
     */
    public MeleeAction getMelee() {
        return melee;
    }

    /**
     * @param melee the melee to set
     */
    public void setMelee(MeleeAction melee) {
        this.melee = melee;
    }

    /**
     * @return the shot
     */
    public ShotAction getShot() {
        return shot;
    }

    /**
     * @param shot the shot to set
     */
    public void setShot(ShotAction shot) {
        this.shot = shot;
    }

    /**
     * @return the addedMeleeList
     */
    public ArrayList<MeleeAction> getAddedMeleeList() {
        return addedMeleeList;
    }

    /**
     * @param addedMeleeList the addedMeleeList to set
     */
    public void setAddedMeleeList(ArrayList<MeleeAction> addedMeleeList) {
        this.addedMeleeList = addedMeleeList;
    }

    /**
     * @return the SATK
     */
    public int getSATK() {
        return SATK;
    }

    /**
     * @param SATK the SATK to set
     */
    public void setSATK(int SATK) {
        this.SATK = SATK;
    }

    /**
     * @return the PATK
     */
    public int getPATK() {
        return PATK;
    }

    /**
     * @param PATK the PATK to set
     */
    public void setPATK(int PATK) {
        this.PATK = PATK;
    }

    /**
     * @return the BATK
     */
    public int getBATK() {
        return BATK;
    }

    /**
     * @param BATK the BATK to set
     */
    public void setBATK(int BATK) {
        this.BATK = BATK;
    }

    /**
     * @return the type
     */
    public ElementalType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(ElementalType type) {
        this.type = type;
    }

    /**
     * @return the EPUsage
     */
    public int getEPUsage() {
        return EPUsage;
    }

    /**
     * @param EPUsage the EPUsage to set
     */
    public void setEPUsage(int EPUsage) {
        this.EPUsage = EPUsage;
    }

    /**
     * @return the weapon
     */
    public WeaponAction getWeapon() {
        return weapon;
    }

    /**
     * @param weapon the weapon to set
     */
    public void setWeapon(WeaponAction weapon) {
        this.weapon = weapon;
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
}
