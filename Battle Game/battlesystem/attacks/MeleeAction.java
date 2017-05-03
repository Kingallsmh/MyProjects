/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mb.battlesystem.attacks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.mb.CalcUtilities;
import com.mb.entities.battle.Beast;
import java.util.ArrayList;

/**
 *
 * @author Kyle
 */
public class MeleeAction{
    Action action;
    private Rectangle hitbox;
    private TextureRegion[] imgList;
    TextureRegion currentImg;
    MeleeType meleeType;
    private SubType subType;
    
    float time;
    private float maxTime;
    private float xSpeed;
    private float ySpeed;
    private float pushXAmount;
    private float pushYAmount;
    float distance = 0;
    boolean subLaunched = false;
    boolean launched = false;
    boolean harmless = false;
    boolean flipped = false;
    float angle = 0;
    boolean waveSent = false;
    private int maxWaves;
    int waveNum;
    
    // IDEAS :::::::::::
    //MeleeType += Wave, Attacks sequentially
    //New Atk = Sonics, so a scream or an air bomb type idea
    //new Atk = Pillar of light, Opposite of Nightmare Surprise
    //Should try to find a way to swing weapons as an atk maybe?
    //IDEAS END ::::::::
    
    WeaponAction weapon;
    
    public enum MeleeType{
        Standard, Float, Wave
    }
    
    public enum SubType{
        None, Reversed, SpreadX, Upwards, Downwards, SpreadY, SpreadXY
    }
    
    public MeleeAction(Action action, MeleeType type){
        this.action = action;
        this.flipped = action.isFlipped();
        this.meleeType = type;
        this.subType = SubType.None;
    }
    
    public MeleeAction(Action action, MeleeAction a){
        this.action = action;
        this.hitbox = new Rectangle(a.hitbox);
        this.flipped = action.isFlipped();
        this.setImgList(a.imgList);
        this.currentImg = imgList[0];
        this.meleeType = a.meleeType;
        this.maxTime = a.maxTime;
        this.xSpeed = a.xSpeed;
        this.ySpeed = a.ySpeed;
        this.pushXAmount = a.pushXAmount;
        this.pushYAmount = a.pushYAmount;
        this.subType = a.subType;
        this.maxWaves = a.maxWaves;
    }
    
    public void CheckForAHit(ArrayList<Beast> beastList){
        for(int i = 0; i < beastList.size(); i++){
            if(beastList.get(i) != null){
                if(beastList.get(i).getTamer().getTeamNumber() != action.getOwner().getTamer().getTeamNumber()){
                    if(beastList.get(i).getHitbox().overlaps(hitbox)){
                        float SDMG = (float)action.getOwner().getStats().getPHYS() * (float)action.getSATK()/10;
                        float PDMG = (float)action.getOwner().getStats().getPHYS() * (float)action.getPATK()/10;
                        float BDMG = (float)action.getOwner().getStats().getPHYS() * (float)action.getBATK()/10;
                        beastList.get(i).TakeHit(SDMG, PDMG, BDMG, action.getType());
                        PushBeast(beastList.get(i), pushXAmount, pushYAmount);
                    }
                }
            }
        }
    }
    
    public void PushBeast(Beast beast, float xPush, float yPush){
        if(flipped){
            beast.getPhysics().setxVel(-xPush);
        }
        else{
            beast.getPhysics().setxVel(xPush);
        }
         beast.getPhysics().setyVel(yPush);
    }
    
    public void SpreadXYLogic(){
        if(!subLaunched){
            //Up
            angle = 90;
            hitbox = new Rectangle(0, 0, hitbox.height, hitbox.width);
            subLaunched = true;
            //Down
            MeleeAction D = new MeleeAction(action, this);
            D.flipped = !flipped;
            D.angle = -angle;
            D.subType = SubType.Downwards;
            D.setHitbox(new Rectangle(0, 0, hitbox.height, hitbox.width));
            action.getAddedMeleeList().add(D);
            //Right
            MeleeAction R = new MeleeAction(action, this);
            R.flipped = flipped;
            R.angle = 0;
            R.subType = SubType.None;
            R.setHitbox(new Rectangle(0, 0, hitbox.height, hitbox.width));
            action.getAddedMeleeList().add(R);
            //Left
            MeleeAction L = new MeleeAction(action, this);
            L.flipped = flipped;
            L.angle = 0;
            L.subType = SubType.Reversed;
            L.setHitbox(new Rectangle(0, 0, hitbox.height, hitbox.width));
            action.getAddedMeleeList().add(L);
        }
    }
    
    public void SpreadYLogic(){
        if(!subLaunched){
            angle = 90;
            hitbox = new Rectangle(0, 0, hitbox.height, hitbox.width);
            subLaunched = true;
            MeleeAction melee = new MeleeAction(action, this);
            melee.flipped = !flipped;
            melee.angle = -angle;
            melee.subType = SubType.Downwards;
            melee.setHitbox(new Rectangle(0, 0, hitbox.height, hitbox.width));
            action.getAddedMeleeList().add(melee);
        }
    }
    
    public void DownwardsLogic(){
        if(!subLaunched){
            angle = -90;
            hitbox = new Rectangle(0, 0, hitbox.height, hitbox.width);
            subLaunched = true;
        }
    }
    
    public void UpwardsLogic(){
        if(!subLaunched){
            angle = 90;
            hitbox = new Rectangle(0, 0, hitbox.height, hitbox.width);
            subLaunched = true;
        }
    }
    
    public void ReversedLogic(){
        if(!subLaunched){
            flipped = !flipped;
            subLaunched = true;
        }
    }
    
    public void SpreadXLogic(){
        if(!subLaunched){
            MeleeAction melee = new MeleeAction(action, this);
            melee.subType = SubType.Reversed;
            action.getAddedMeleeList().add(melee);
            subLaunched = true;
        }
    }
    
    public void WaveLogic() {
        if(!launched){
            if(flipped){
                hitbox.x = action.getOwner().getShootBoxL().x - hitbox.width;
            }
            else{
                hitbox.x = action.getOwner().getShootBoxR().x;
            }
            hitbox.y = action.getOwner().getHitbox().y;
            launched = true;
            waveNum = 1;
        }
        if(!waveSent){
            if(flipped){
                if(time >= maxTime - 0.1f && waveNum < maxWaves ){
                    MeleeAction a = new MeleeAction(action, this);
                    a.hitbox.x = hitbox.x - hitbox.width - xSpeed;
                    a.waveNum = waveNum + 1; 
                    a.launched = true;
                    a.subLaunched = true;
                    a.flipped = flipped;
                    action.getAddedMeleeList().add(a);
                    waveSent = true;
                }
            }
            else{
                if(time >= maxTime - 0.1f && waveNum < maxWaves){
                    MeleeAction a = new MeleeAction(action, this);
                    a.hitbox.x = hitbox.x + hitbox.width + xSpeed;
                    a.waveNum = waveNum + 1; 
                    a.launched = true;
                    a.subLaunched = true;
                    a.flipped = flipped;
                    action.getAddedMeleeList().add(a);
                    waveSent = true;
                }
            }
        }
        
        time += Gdx.graphics.getDeltaTime();
        Animate();
    }
    
    public void StandardLogic(){
        if(flipped){
            if(angle == 90){
                hitbox.x = action.getOwner().getShootBoxU().x - hitbox.width/2;
                hitbox.y = action.getOwner().getShootBoxU().y  + distance;
            }
            else if(angle == -90){
                hitbox.x = action.getOwner().getShootBoxD().x - hitbox.width/2;
                hitbox.y = action.getOwner().getShootBoxD().y - hitbox.height  - distance;
            }
            else{
                hitbox.x = action.getOwner().getShootBoxL().x - hitbox.width - distance;
                hitbox.y = action.getOwner().getShootBoxL().y  - hitbox.height/2;
            }
        }
        else{
            if(angle == 90){
                hitbox.x = action.getOwner().getShootBoxU().x - hitbox.width/2;
                hitbox.y = action.getOwner().getShootBoxU().y  + distance;
            }
            else if(angle == -90){
                hitbox.x = action.getOwner().getShootBoxD().x - hitbox.width/2;
                hitbox.y = action.getOwner().getShootBoxD().y - hitbox.height  - distance;
            }
            else{
                hitbox.x = action.getOwner().getShootBoxR().x + distance;
                hitbox.y = action.getOwner().getShootBoxR().y  - hitbox.height/2;
            }
        }
        
        distance += CalcUtilities.CorrectTime() * xSpeed;
        
        time += Gdx.graphics.getDeltaTime();
        Animate();
    }
    
    public void FloatLogic(){
        float deltatime = CalcUtilities.CorrectTime();
        if(!launched){
            if(flipped){
                if(angle == 90){
                    hitbox.x = action.getOwner().getShootBoxU().x - hitbox.width/2;
                    hitbox.y = action.getOwner().getShootBoxU().y  + distance;
                }
                else if(angle == -90){
                    hitbox.x = action.getOwner().getShootBoxD().x - hitbox.width/2;
                    hitbox.y = action.getOwner().getShootBoxD().y - hitbox.height  - distance;
                }
                else{
                    hitbox.x = action.getOwner().getShootBoxL().x - hitbox.width - distance;
                    hitbox.y = action.getOwner().getShootBoxL().y  - hitbox.height/2;
                }
            }
            else{
                if(angle == 90){
                    hitbox.x = action.getOwner().getShootBoxU().x - hitbox.width/2;
                    hitbox.y = action.getOwner().getShootBoxU().y  + distance;
                }
                else if(angle == -90){
                    hitbox.x = action.getOwner().getShootBoxD().x - hitbox.width/2;
                    hitbox.y = action.getOwner().getShootBoxD().y - hitbox.height  - distance;
                }
                else{
                    hitbox.x = action.getOwner().getShootBoxR().x + distance;
                    hitbox.y = action.getOwner().getShootBoxR().y  - hitbox.height/2;
                }
            }
            launched = true;
        }
        if(angle == 90){
            if(flipped){
                hitbox.x -= deltatime * ySpeed;
            }
            else{
                hitbox.x += deltatime * ySpeed;
            }
            hitbox.y += deltatime * xSpeed;
        }
        else if(angle == -90){
            if(flipped){
                hitbox.x -= deltatime * ySpeed;
            }
            else{
                hitbox.x += deltatime * ySpeed;
            }
            hitbox.y -= deltatime * xSpeed;
        }
        else{
            if(flipped){
                hitbox.x -= deltatime * xSpeed;
            }
            else{
                hitbox.x += deltatime * xSpeed;
            }
            hitbox.y += deltatime * ySpeed;
        }        
           
        time += Gdx.graphics.getDeltaTime();
        Animate();
    }
    
//    public void WeaponLogic(){
//        if(weapon == null){
//            weapon = new WeaponAction();
//        }
//        weapon.Logic(flipped);
//    }
//    
//    public void WeaponAnimate(SpriteBatch batch){
//        weapon.Render(batch, currentImg, this.action.getOwner().getHitbox(), flipped);
//    }
    
    public void Logic(ArrayList<Beast> beastList) {
        switch(subType){
            case None: 
                break;
            case Reversed: ReversedLogic();
                break;
            case SpreadX: SpreadXLogic();
                break;
            case Upwards: UpwardsLogic();
                break;
            case Downwards: DownwardsLogic();
                break;
            case SpreadY: SpreadYLogic();
                break;
            case SpreadXY: SpreadXYLogic();
                break;
        }
        switch(meleeType){
            case Standard: StandardLogic();
                break;
            case Float: FloatLogic();
                break;
            case Wave: WaveLogic();
                break;
        }
        if(!harmless){
            CheckForAHit(beastList);
        }
    }
    
    public void Animate(){
        if(imgList.length > 1){
            if(time > maxTime/3){
                currentImg = imgList[1];
                harmless = false;
            }
        }
    }

    public void Render(SpriteBatch batch) {
        //float imgX = beast.getHitbox().x + (beast.getHitbox().width/2) - (currentImg.getRegionWidth()/2);
        float x = hitbox.x + hitbox.width/2 - currentImg.getRegionWidth()/2;
        float y = hitbox.y + hitbox.height/2 - currentImg.getRegionHeight()/2;
        RotateAction(batch, x, y);
    }
    
    public void RotateAction(SpriteBatch batch, float x, float y){
        if(flipped){
            if(angle == 90){
                //(Gdx.graphics.getWidth() - sprite.getRegionWidth()) / 2.0f,(Gdx.graphics.getHeight() - sprite.getRegionHeight())/2
                batch.draw(currentImg, x, y - currentImg.getRegionHeight(), currentImg.getRegionWidth()/2, 
                        currentImg.getRegionHeight()/2, -currentImg.getRegionWidth(), 
                        currentImg.getRegionHeight(), 1, 1, -angle);
            }
            else if(angle == -90){
                batch.draw(currentImg, x, y + currentImg.getRegionHeight(), currentImg.getRegionWidth()/2, 
                        currentImg.getRegionHeight()/2, -currentImg.getRegionWidth(), 
                        currentImg.getRegionHeight(), 1, 1, -angle);
            }
            else{
                batch.draw(currentImg, x + currentImg.getRegionWidth(), y, -currentImg.getRegionWidth(), currentImg.getRegionHeight());
            }
        }
        else{
            if(angle == 90){
                batch.draw(currentImg, x, y, currentImg.getRegionWidth()/2, 
                        currentImg.getRegionHeight()/2, currentImg.getRegionWidth(), 
                        currentImg.getRegionHeight(), 1, 1, angle);
            }
            else if(angle == -90){
                batch.draw(currentImg, x, y, currentImg.getRegionWidth()/2, 
                        currentImg.getRegionHeight()/2, currentImg.getRegionWidth(), 
                        currentImg.getRegionHeight(), 1, 1, angle);
            }
            else{
                batch.draw(currentImg, x, y, currentImg.getRegionWidth(), currentImg.getRegionHeight());
            }
        }
    }
    
    public void DebugRender(ShapeRenderer draw){
        draw.setColor(0, 1, 0, 1);
        draw.rect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
    }

    /**
     * @return the imgList
     */
    public TextureRegion[] getImgList() {
        return imgList;
    }

    /**
     * @param imgList the imgList to set
     */
    public void setImgList(TextureRegion[] imgList) {
        this.imgList = imgList;
        if(imgList.length > 1){
            harmless = true;
        }
    }

    /**
     * @return the maxTime
     */
    public float getMaxTime() {
        return maxTime;
    }

    /**
     * @param maxTime the maxTime to set
     */
    public void setMaxTime(float maxTime) {
        this.maxTime = maxTime;
    }

    /**
     * @return the xSpeed
     */
    public float getXSpeed() {
        return xSpeed;
    }

    /**
     * @param speed the xSpeed to set
     */
    public void setXSpeed(float speed) {
        this.xSpeed = speed;
    }

    /**
     * @return the ySpeed
     */
    public float getySpeed() {
        return ySpeed;
    }

    /**
     * @param ySpeed the ySpeed to set
     */
    public void setySpeed(float ySpeed) {
        this.ySpeed = ySpeed;
    }

    /**
     * @return the subType
     */
    public SubType getSubType() {
        return subType;
    }

    /**
     * @param subType the subType to set
     */
    public void setSubType(SubType subType) {
        this.subType = subType;
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
     * @return the pushXAmount
     */
    public float getPushXAmount() {
        return pushXAmount;
    }

    /**
     * @param pushXAmount the pushXAmount to set
     */
    public void setPushXAmount(float pushXAmount) {
        this.pushXAmount = pushXAmount;
    }

    /**
     * @return the pushYAmount
     */
    public float getPushYAmount() {
        return pushYAmount;
    }

    /**
     * @param pushYAmount the pushYAmount to set
     */
    public void setPushYAmount(float pushYAmount) {
        this.pushYAmount = pushYAmount;
    }    

    /**
     * @return the maxWaves
     */
    public int getMaxWaves() {
        return maxWaves;
    }

    /**
     * @param maxWaves the maxWaves to set
     */
    public void setMaxWaves(int maxWaves) {
        this.maxWaves = maxWaves;
    }
}
