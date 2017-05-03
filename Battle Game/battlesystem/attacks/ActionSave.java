/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mb.battlesystem.attacks;

/**
 *
 * @author Kyle
 */
public class ActionSave {
    int slotNum;
    MeleeAction melee;
    ShotAction shot;
    WeaponAction weapon;
    
    public ActionSave(Action a, int slotNum){
        this.slotNum = slotNum;
        this.melee = a.getMelee();
        this.shot = a.getShot();
        this.weapon = a.getWeapon();
    }
}
