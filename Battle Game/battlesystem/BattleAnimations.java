/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mb.battlesystem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mb.CalcUtilities;
import com.mb.entities.battle.Beast;
import com.mb.entities.battle.Beast.State;

/**
 *
 * @author Kyle
 */
public class BattleAnimations {
    Beast beast;
    private TextureRegion[] walkAnim;
    private TextureRegion[] meleeAnim;
    private TextureRegion shootAnim;
    private TextureRegion hurtAnim;
    TextureRegion currentImg;
    
    float walkTime, maxWalkTime = 1f;
    private float meleeTime;
    private float maxMeleeTime = 0.5f;
    float shootTime, maxShootTime = 0.25f;
    float hurtTime, maxHurtTime = 1f;
    float deadTime, maxDeadTime = 0.2f;
    float phaseTime, maxPhaseTime = 0.05f;
    float alpha = 1;
    private float CDTime = 0.21f;
    float maxCDTime = 0.2f;
    
    private boolean deathOver = false;
    private boolean isFlipped = false;
    private boolean phased = false;
    private State state = State.Idle;
    
    public BattleAnimations(Beast beast){
        this.beast = beast;
    }
    
    public void StateControl(){
        switch(state){
            case Idle: AnimateIdle();
                break;
            case Walk: AnimateWalk();
                break;
            case Melee: AnimateMelee();
                break;
            case Shoot: AnimateShoot();
                break;
            case Hurt: HurtAnimation();
                break;
            case Dead: AnimateDead();
                break;
        }
    }
    
    public void AnimateDead(){
        currentImg = hurtAnim;
        if(deadTime > maxDeadTime && !deathOver){
            if(alpha <= 0.01){
                deathOver = true;
                alpha = 0;
            }
            else{
                alpha -= 0.2f;
                deadTime = 0;
            }
        }
        else{
            deadTime += CalcUtilities.CorrectTime();
        }
    }
    
    public void HurtAnimation(){
        currentImg = hurtAnim;
    }
    
    public void HurtLogic(){
        if(phased){
            if(hurtTime > maxHurtTime){
                phased = false;
                alpha = 1;
                phaseTime = 0;
                hurtTime = 0;
            }
            else{
                if(phaseTime > maxPhaseTime){
                    if(alpha == 1){
                        alpha = 0.5f;
                    }
                    else{
                        alpha = 1;
                    }
                    phaseTime = 0;
                }
                phaseTime += Gdx.graphics.getDeltaTime();
                hurtTime += Gdx.graphics.getDeltaTime();
            }
        }
        
        if(state == State.Hurt){
            if(hurtTime > maxHurtTime/2){
                state = State.Idle;
            }
        }
    }
    
    public void AnimateShoot(){
        currentImg = shootAnim;
    }
    
    public void ShootLogic(){
        if(state == State.Shoot){
            if(shootTime > maxShootTime){
                state = State.Idle;
                shootTime = 0;
                CDTime = 0;
            }
            else{
                shootTime += Gdx.graphics.getDeltaTime();
            }
        }
    }
    
    public void AnimateMelee(){
        if(meleeTime > maxMeleeTime/2){
            currentImg = meleeAnim[1];
        }
        else{
            currentImg = meleeAnim[0];
        }
    }
    
    public void MeleeLogic(){
        if(state == State.Melee){
            if(meleeTime > maxMeleeTime){
                state = State.Idle;
                meleeTime = 0;
                CDTime = 0;
            }
            else{
                meleeTime += Gdx.graphics.getDeltaTime();
            }
        }
    }
    
    public void AnimateWalk(){
        if(walkTime > maxWalkTime){
            if(currentImg == walkAnim[0]){
                currentImg = walkAnim[1];
            }
            else if(currentImg == walkAnim[1]){
                currentImg = walkAnim[0];
            }
            walkTime = 0;
        }
        walkTime += Gdx.graphics.getDeltaTime() * beast.getStats().getSPD()/3;
    }
    
    public void WalkLogic(){
        if(beast.getPhysics().getxVel() > 0 || beast.getPhysics().getxVel() < 0 || beast.getInput().isFloating()){
            if(state == State.Idle){
                state = State.Walk;
            }
        }
    }
    
    public void AnimateIdle(){
        currentImg = walkAnim[0];
    }
    
    public void IdleLogic(){
        if(beast.getPhysics().getxVel() == 0  && !beast.getInput().isFloating()){
            if(state == State.Walk){
                state = State.Idle;
                walkTime = 0;
            }
        }
    }
    
    public void AnimationLogic(){
        if(state == State.Idle || state == State.Walk){
            if(beast.getControl().isLeft()){
                isFlipped = true;
            }
            else if(beast.getControl().isRight()){
                isFlipped = false;
            }
        }
        
        beast.SwitchShootBox(isFlipped);
        
        IdleLogic();
        WalkLogic();
        MeleeLogic();
        ShootLogic();
        HurtLogic();
        StateControl();
        if(CDTime < maxCDTime){
            CDTime += Gdx.graphics.getDeltaTime();
        }
    }
    
    public void Render(SpriteBatch batch){
        batch.setColor(1, 1, 1, alpha);
        float imgX = beast.getHitbox().x + (beast.getHitbox().width/2) - (currentImg.getRegionWidth()/2);
        if(isFlipped){
            batch.draw(currentImg, imgX + currentImg.getRegionWidth(), beast.getHitbox().y - 1, -currentImg.getRegionWidth(), currentImg.getRegionHeight());
        }
        else{
            batch.draw(currentImg, imgX, beast.getHitbox().y - 1, currentImg.getRegionWidth(), currentImg.getRegionHeight());
        }
        batch.setColor(1, 1, 1, 1);
    }
    
    public boolean IsActionAvailable(){
        return state != State.Melee && state != State.Shoot && state != State.Hurt && state != State.Dead && CDTime > maxCDTime;
    }

    /**
     * @param walkAnim the walkAnim to set
     */
    public void setWalkAnim(TextureRegion[] walkAnim) {
        this.walkAnim = walkAnim;
        this.currentImg = walkAnim[0];
    }

    /**
     * @param meleeAnim the meleeAnim to set
     */
    public void setMeleeAnim(TextureRegion[] meleeAnim) {
        this.meleeAnim = meleeAnim;
    }

    /**
     * @param shootAnim the shootAnim to set
     */
    public void setShootAnim(TextureRegion shootAnim) {
        this.shootAnim = shootAnim;
    }

    /**
     * @param hurtAnim the hurtAnim to set
     */
    public void setHurtAnim(TextureRegion hurtAnim) {
        this.hurtAnim = hurtAnim;
    }

    /**
     * @return the isFlipped
     */
    public boolean isIsFlipped() {
        return isFlipped;
    }

    /**
     * @param isFlipped the isFlipped to set
     */
    public void setIsFlipped(boolean isFlipped) {
        this.isFlipped = isFlipped;
    }

    /**
     * @return the meleeTime
     */
    public float getMeleeTime() {
        return meleeTime;
    }

    /**
     * @param meleeTime the meleeTime to set
     */
    public void setMeleeTime(float meleeTime) {
        this.meleeTime = meleeTime;
    }

    /**
     * @return the maxMeleeTime
     */
    public float getMaxMeleeTime() {
        return maxMeleeTime;
    }

    /**
     * @param maxMeleeTime the maxMeleeTime to set
     */
    public void setMaxMeleeTime(float maxMeleeTime) {
        this.maxMeleeTime = maxMeleeTime;
    }

    /**
     * @return the state
     */
    public State getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * @return the phased
     */
    public boolean isPhased() {
        return phased;
    }

    /**
     * @param phased the phased to set
     */
    public void setPhased(boolean phased) {
        this.phased = phased;
    }

    /**
     * @return the walkAnim
     */
    public TextureRegion[] getWalkAnim() {
        return walkAnim;
    }

    /**
     * @return the hurtAnim
     */
    public TextureRegion getHurtAnim() {
        return hurtAnim;
    }

    /**
     * @return the CDTime
     */
    public float getCDTime() {
        return CDTime;
    }

    /**
     * @param CDTime the CDTime to set
     */
    public void setCDTime(float CDTime) {
        this.CDTime = CDTime;
    }

    /**
     * @return the deathOver
     */
    public boolean isDeathOver() {
        return deathOver;
    }

    /**
     * @param deathOver the deathOver to set
     */
    public void setDeathOver(boolean deathOver) {
        this.deathOver = deathOver;
    }
}
