/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mb.entities.battle;

import com.mb.battlesystem.attacks.Action;
import com.mb.battlesystem.attacks.ActionSave;
import com.mb.entities.battle.BeastLibrary.BeastName;
import java.io.Serializable;

/**
 *
 * @author Kyle
 */
public class BeastSave implements Serializable{
    private BeastName bName;
    private String name;
    private int lv;
    private int MaxHP;
    private int MaxEP;
    private int REC;
    private int PHYS;
    private int SHOT;
    private int SDEF;
    private int PDEF;
    private int BDEF;
    private int SPD;
    private int JMP;
    private ActionSave aList[];
    
    public BeastSave(Beast beast){
        this.bName = beast.getSpecies();
        this.name = beast.getName();
        this.lv = beast.getStats().getLv();
        this.MaxHP = beast.getStats().getMaxHP();
        this.MaxEP = beast.getStats().getMaxEP();
        this.REC = beast.getStats().getREC();
        this.PHYS = beast.getStats().getPHYS();
        this.SHOT = beast.getStats().getSHOT();
        this.SDEF = beast.getStats().getSDEF();
        this.PDEF = beast.getStats().getPDEF();
        this.BDEF = beast.getStats().getBDEF();
        this.SPD = beast.getStats().getSPD();
        this.JMP = beast.getStats().getJMP();
        
        aList = new ActionSave[4];
        for(int i = 0; i < aList.length; i++){
            aList[i] = new ActionSave(beast.getStats().getActionList()[i], i);
        }
    }

    /**
     * @return the bName
     */
    public BeastName getbName() {
        return bName;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
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
     * @return the MaxHP
     */
    public int getMaxHP() {
        return MaxHP;
    }

    /**
     * @return the MaxEP
     */
    public int getMaxEP() {
        return MaxEP;
    }

    /**
     * @return the REC
     */
    public int getREC() {
        return REC;
    }

    /**
     * @return the PHYS
     */
    public int getPHYS() {
        return PHYS;
    }

    /**
     * @return the SHOT
     */
    public int getSHOT() {
        return SHOT;
    }

    /**
     * @return the SDEF
     */
    public int getSDEF() {
        return SDEF;
    }

    /**
     * @return the PDEF
     */
    public int getPDEF() {
        return PDEF;
    }

    /**
     * @return the BDEF
     */
    public int getBDEF() {
        return BDEF;
    }

    /**
     * @return the SPD
     */
    public int getSPD() {
        return SPD;
    }

    /**
     * @return the JMP
     */
    public int getJMP() {
        return JMP;
    }

    /**
     * @return the aList
     */
    public ActionSave[] getaList() {
        return aList;
    }
}
