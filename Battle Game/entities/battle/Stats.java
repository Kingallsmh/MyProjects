/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mb.entities.battle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mb.CalcUtilities;
import com.mb.battlesystem.Rules.ElementalType;
import com.mb.battlesystem.Rules.StatusEffect;
import com.mb.battlesystem.attacks.Action;

/**
 *
 * @author Kyle
 */
public class Stats {
    private Action[] actionList;
    private StatusEffect status = StatusEffect.NONE;
    private ElementalType type;
    private TextureRegion elementImg;
    private int lv;
    private int HP;
    private int MaxHP;
    private int EP;
    private int MaxEP;
    private int REC;
    private int PHYS;
    private int SHOT;
    private int SDEF;
    private int PDEF;
    private int BDEF;
    private int SPD;
    private int JMP;
    
    float time, maxTime = 100;
    private boolean EfficientUser = false;
    
    public Stats(){
        actionList = new Action[4];
    }
    
    public void Logic(){
        RecoverEP();
    }
    
    public void RecoverEP(){
        if(this.EP < MaxEP){
            if(time > maxTime){
                EP ++;
                time = 0;
            }
            time += REC * 5 * CalcUtilities.CorrectTime();
        }
        if(EP > MaxEP){
            EP = MaxEP;
        }
    }
    
    public void CalcDamageForHit(float SDMG, float PDMG, float BDMG, ElementalType element){
//        System.out.println("Slash: " + SDMG + " Pierce: " + PDMG + " Blunt: " + BDMG);
        float sTotal = ((SDMG/(SDEF)));
        float pTotal = ((PDMG/(PDEF)));
        float bTotal = ((BDMG/(BDEF)));
        int total = (int)(ElementalAttackAdvantage(element)*(sTotal + pTotal + bTotal));
//        System.out.println("S Dmg: " + sTotal + " P Dmg: " + pTotal + " B Dmg: " + bTotal);
//        System.out.println("ElementalDmg: " + ElementalAttackAdvantage(element));
//        System.out.println("Dmg total: " + total);
        if(total < 1){
            total = 1;
        }
        this.HP -= total;
    }
    
    public boolean CanUseAction(Action a){
        int eu = a.getEPUsage();
        if(EfficientUser && this.type == a.getType()){
            eu = a.getEPUsage()/2;
//            System.out.println("New EP: " + this.EP + " - " + eu);
        }
        if(eu <= EP){
            SubtractEP(eu);
            return true;
        }
        else{
            return false;
        }
    }
    
    public void SubtractEP(int EPUsage){
        EP -= EPUsage;
    }
    
    public float ElementalAttackAdvantage(ElementalType type){
//        System.out.println("Element: " + type);
        switch(type){
            //Attacking = Neutral
            case NEUTRAL: 
                switch(this.type){
                    case DARK: return 0.5f;
                    case LIGHT: return 1.5f;
                }
                break;
            //Attacking = Earth
            case EARTH:
                switch(this.type){
                    case DARK: return 1.5f;
                    case LIGHT: return 1.5f;
                    case ELECTRICITY: return 1.5f;
                    case FIRE: return 0.5f;
                    case WATER: return 0.5f;
                    case WIND: return 0.5f;
                    case EARTH: return 0.8f;
                }
                break;
            //Attacking = Nature
            case NATURE:
                switch(this.type){
                    case DARK: return 1.5f;
                    case LIGHT: return 1.5f;
                    case WATER: return 1.5f;
                    case FIRE: return 0.5f;
                    case WIND: return 0.5f;
                    case ELECTRICITY: return 0.5f;
                    case NATURE: return 0.8f;
                }
                break;
            //Attacking = Dark
            case DARK:
                switch(this.type){
                    case NEUTRAL: return 1.5f;
                    case FIRE: return 1.5f;
                    case ELECTRICITY: return 1.5f;
                    case LIGHT: return 0.5f;
                    case DARK: return 0.8f;
                }
                break;
            //Attacking = Light
            case LIGHT:
                switch(this.type){
                    case DARK: return 1.5f;
                    case WATER: return 1.5f;
                    case WIND: return 1.5f;
                    case NEUTRAL: return 0.5f;
                    case LIGHT: return 0.8f;
                }
                break;
            //Attacking = Water
            case WATER:
                switch(this.type){
                    case FIRE: return 1.5f;
                    case EARTH: return 1.5f;
                    case ELECTRICITY: return 1.5f;
                    case LIGHT: return 0.5f;
                    case WIND: return 0.5f;
                    case NATURE: return 0.5f;
                    case WATER: return 0.8f;
                }
                break;
            //Attacking = Wind
            case WIND:
                switch(this.type){
                    case FIRE: return 1.5f;
                    case EARTH: return 1.5f;
                    case NATURE: return 1.5f;
                    case ELECTRICITY: return 0.5f;
                    case LIGHT: return 0.5f;
                    case WATER: return 0.5f;
                    case WIND: return 0.8f;
                }
                break;
            //Attacking = Electricity
            case ELECTRICITY:
                switch(this.type){
                    case WIND: return 1.5f;
                    case WATER: return 1.5f;
                    case NATURE: return 1.5f;
                    case DARK: return 0.5f;
                    case FIRE: return 0.5f;
                    case EARTH: return 0.5f;
                    case ELECTRICITY: return 0.8f;
                }
                break;
            //Attacking = Fire
            case FIRE:
                switch(this.type){
                    case WIND: return 1.5f;
                    case EARTH: return 1.5f;
                    case NATURE: return 1.5f;
                    case DARK: return 0.5f;
                    case ELECTRICITY: return 0.5f;
                    case WATER: return 0.5f;
                    case FIRE: return 0.8f;
                }
                break;
        }
        return 1;
    }
    
    public void AddAction(Action action, int spot){
        actionList[spot] = action;
    }

    /**
     * @return the HP
     */
    public int getHP() {
        return HP;
    }

    /**
     * @param HP the HP to set
     */
    public void setHP(int HP) {
        this.HP = HP;
    }

    /**
     * @return the MaxHP
     */
    public int getMaxHP() {
        return MaxHP;
    }

    /**
     * @param MaxHP the MaxHP to set
     */
    public void setMaxHP(int MaxHP) {
        this.MaxHP = MaxHP;
    }

    /**
     * @return the EP
     */
    public int getEP() {
        return EP;
    }

    /**
     * @param EP the EP to set
     */
    public void setEP(int EP) {
        this.EP = EP;
    }

    /**
     * @return the MaxEP
     */
    public int getMaxEP() {
        return MaxEP;
    }

    /**
     * @param maxEP the MaxEP to set
     */
    public void setMaxEP(int maxEP) {
        this.MaxEP = maxEP;
    }

    /**
     * @return the REC
     */
    public int getREC() {
        return REC;
    }

    /**
     * @param REC the REC to set
     */
    public void setREC(int REC) {
        this.REC = REC;
    }

    /**
     * @return the PHYS
     */
    public int getPHYS() {
        return PHYS;
    }

    /**
     * @param PHYS the PHYS to set
     */
    public void setPHYS(int PHYS) {
        this.PHYS = PHYS;
    }

    /**
     * @return the SHOT
     */
    public int getSHOT() {
        return SHOT;
    }

    /**
     * @param SHOT the SHOT to set
     */
    public void setSHOT(int SHOT) {
        this.SHOT = SHOT;
    }

    /**
     * @return the SPD
     */
    public int getSPD() {
        return SPD;
    }

    /**
     * @param SPD the SPD to set
     */
    public void setSPD(int SPD) {
        this.SPD = SPD;
    }

    /**
     * @return the JMP
     */
    public int getJMP() {
        return JMP;
    }

    /**
     * @param JMP the JMP to set
     */
    public void setJMP(int JMP) {
        this.JMP = JMP;
    }

    /**
     * @return the actionList
     */
    public Action[] getActionList() {
        return actionList;
    }

    /**
     * @param actionList the actionList to set
     */
    public void setActionList(Action[] actionList) {
        this.actionList = actionList;
    }

    /**
     * @return the SDEF
     */
    public int getSDEF() {
        return SDEF;
    }

    /**
     * @param SDEF the SDEF to set
     */
    public void setSDEF(int SDEF) {
        this.SDEF = SDEF;
    }

    /**
     * @return the PDEF
     */
    public int getPDEF() {
        return PDEF;
    }

    /**
     * @param PDEF the PDEF to set
     */
    public void setPDEF(int PDEF) {
        this.PDEF = PDEF;
    }

    /**
     * @return the BDEF
     */
    public int getBDEF() {
        return BDEF;
    }

    /**
     * @param BDEF the BDEF to set
     */
    public void setBDEF(int BDEF) {
        this.BDEF = BDEF;
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
     * @return the status
     */
    public StatusEffect getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(StatusEffect status) {
        this.status = status;
    }

    /**
     * @return the EfficientUser
     */
    public boolean isEfficientUser() {
        return EfficientUser;
    }

    /**
     * @param EfficientUser the EfficientUser to set
     */
    public void setEfficientUser(boolean EfficientUser) {
        this.EfficientUser = EfficientUser;
    }

    /**
     * @return the lv
     */
    public int getLv() {
        return lv;
    }

    /**
     * @param lv the lv to set
     */
    public void setLv(int lv) {
        this.lv = lv;
    }

    /**
     * @return the elementImg
     */
    public TextureRegion getElementImg() {
        return elementImg;
    }

    /**
     * @param elementImg the elementImg to set
     */
    public void setElementImg(TextureRegion elementImg) {
        this.elementImg = elementImg;
    }
    
    
}
