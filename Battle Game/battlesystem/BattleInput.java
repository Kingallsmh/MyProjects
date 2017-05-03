/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mb.battlesystem;

import com.badlogic.gdx.Gdx;
import com.mb.CalcUtilities;
import com.mb.entities.battle.Beast;
import com.mb.entities.battle.Beast.State;

/**
 *
 * @author Kyle
 */
public class BattleInput {
    Beast beast;
    private boolean floating = false;
    float floatxSpeed;
    float floatySpeed;
    
    public BattleInput(Beast beast){
        this.beast = beast;
    }
    
    public void InputLogic(){
        beast.getControl().ListenForInput();
        if(beast.getAnimations().getState() != State.Hurt && beast.getAnimations().getState() != State.Dead){
            if(floating){
                FloatingMovement();
            }
            else{
                MovementInput();
            }
        }
        if(beast.getAnimations().IsActionAvailable()){
            ActionInput();
        }
    }
    
    public void MovementInput(){
        if(beast.getControl().isLeft()){
            beast.getPhysics().setxVel(-beast.getStats().getSPD()* 5);
        }
        else if(beast.getControl().isRight()){
            beast.getPhysics().setxVel(beast.getStats().getSPD()* 5);
        }
        else{
            beast.getPhysics().setxVel(0);
        }
        
        if(beast.getControl().isUp()){
            if(beast.getPhysics().isGrounded()){
                beast.getPhysics().setyVel(beast.getStats().getJMP()* 10);
            }
        }
        else{
            if(!beast.getPhysics().isGrounded() && beast.getPhysics().getyVel() > 0){
                beast.getPhysics().setyVel(beast.getPhysics().getyVel() - (beast.getStats().getJMP()/5));
            }
        }
    }
    
    public void FloatingMovement(){
        if(beast.getControl().isLeft()){
            beast.getPhysics().setxVel(-beast.getStats().getSPD()* 5);
        }
        else if(beast.getControl().isRight()){
            beast.getPhysics().setxVel(beast.getStats().getSPD()* 5);
        }
        else{
            beast.getPhysics().setxVel(0);
        }
        if(beast.getControl().isUp()){
            beast.getPhysics().setyVel(beast.getStats().getSPD()* 5);
        }
        else if(beast.getControl().isDown()){
            beast.getPhysics().setyVel(-beast.getStats().getSPD()* 5);
        }
        else{
            beast.getPhysics().setyVel(0);
        }
    }
    
    public void ActionInput(){
        if(beast.getControl().isButton1()){
            beast.UseAction(0);
        }
        else if(beast.getControl().isButton2()){
            beast.UseAction(1);
        }
        else if(beast.getControl().isButton3()){
            beast.UseAction(2);
        }
        else if(beast.getControl().isButton4()){
            beast.UseAction(3);
        }
    }

    /**
     * @return the floating
     */
    public boolean isFloating() {
        return floating;
    }

    /**
     * @param floating the floating to set
     */
    public void setFloating(boolean floating) {
        this.floating = floating;
    }
}