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
public class ShotAction {
    private Rectangle hitbox;
    private TextureRegion[] imgList;
    int frame = 0;
    TextureRegion currentImg;
    private Action action;
    private ShotType shotType;
    private SubType subType;
    
    float acceleration;
    private float accelerationRate; //Larger number equals faster to build up
    boolean waveUp = true;
    
    private float xSpeed;
    private float ySpeed;
    private float pushXAmount;
    private float pushYAmount;
    
    float time;
    private float maxTime;
    float angle;
    private boolean flipped;
    boolean launched = false;
    private boolean finished = false;
    private boolean phaseThrough = false;
    
    //Add to code
    float flightTime;
    private float maxFlightTime;
    
    Beast target;
    
    public enum ShotType{
        Straight, BuildUp, SlowDown, Boomerang, WavePath, Homing
    }
    
    public enum SubType{
        None, Burst, Tri, X, SpreadX
    }
    
    public ShotAction(Action action, ShotType type){
        this.action = action;
        this.flipped = action.isFlipped();
        this.shotType = type;
        this.subType = SubType.None;
    }
    
    public ShotAction(Action action, ShotAction a){
        this.action = action;
        this.hitbox = new Rectangle(a.hitbox);
        this.flipped = action.isFlipped();
        this.setImgList(a.imgList);
        this.currentImg = imgList[0];
        this.shotType = a.shotType;
        this.phaseThrough = a.phaseThrough;
        this.maxTime = a.maxTime;
        this.xSpeed = a.xSpeed;
        this.ySpeed = a.ySpeed;
        this.accelerationRate = a.accelerationRate;
        this.pushXAmount = a.pushXAmount;
        this.pushYAmount = a.pushYAmount;
        this.subType = a.subType;
    }
    
    public void CheckForAHit(ArrayList<Beast> beastList){
        for(int i = 0; i < beastList.size(); i++){
            if(beastList.get(i) != null){
                if(beastList.get(i).getTamer().getTeamNumber() != action.getOwner().getTamer().getTeamNumber()){
                    if(beastList.get(i).getHitbox().overlaps(hitbox) && beastList.get(i).IsDamagable()){
                        float SDMG = (float)action.getOwner().getStats().getSHOT()* (float)action.getSATK()/10;
                        float PDMG = (float)action.getOwner().getStats().getSHOT()* (float)action.getPATK()/10;
                        float BDMG = (float)action.getOwner().getStats().getSHOT()* (float)action.getBATK()/10;
                        beastList.get(i).TakeHit(SDMG, PDMG, BDMG, action.getType());
                        PushBeast(beastList.get(i), pushXAmount, pushYAmount);
                        finished = true;
                    }
                }
            }
        }
    }
    
    public void CheckEnvironmentCollision(ArrayList<Rectangle> environList, Rectangle[] stageEnds){
        if(!phaseThrough){
            for(int i = 0; i < environList.size(); i++){
                if(hitbox.overlaps(environList.get(i))){
                    finished = true;
                }
            }
        }
        for(int i = 0; i < stageEnds.length; i++){
            if(hitbox.overlaps(stageEnds[i])){
                finished = true;
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
    
    public void StraightLogic(){
        float deltatime = CalcUtilities.CorrectTime();
        if(!launched){
            if(flipped){
                hitbox.x = action.getOwner().getShootBoxL().x - hitbox.width;
            }
            else{
                hitbox.x = action.getOwner().getShootBoxR().x;
            }
            hitbox.y = action.getOwner().getHitbox().y  + action.getOwner().getHitbox().height/2;
            launched = true;
        }
        
        if(flipped){
                hitbox.x -= xSpeed * deltatime;
            }
            else{
                hitbox.x += xSpeed * deltatime;
            }
        
    }
    
    public void BuildUp(){
        float deltatime = CalcUtilities.CorrectTime();
        if(!launched){
            if(flipped){
                hitbox.x = action.getOwner().getShootBoxL().x - hitbox.width;
            }
            else{
                hitbox.x = action.getOwner().getShootBoxR().x;
            }
            hitbox.y = action.getOwner().getHitbox().y  + action.getOwner().getHitbox().height/2;
            launched = true;
        }
       
        if(flipped){
            if(acceleration > -xSpeed){
                acceleration -= accelerationRate * deltatime;
            }
                hitbox.x += acceleration * deltatime;
            }
            else{
                if(acceleration < xSpeed){
                    acceleration += accelerationRate * deltatime;
                }
                hitbox.x += acceleration * deltatime;
            }
    }
    
    public void SlowDownLogic(){
        float deltatime = CalcUtilities.CorrectTime();
        if(!launched){
            if(flipped){
                hitbox.x = action.getOwner().getShootBoxL().x - hitbox.width;
                acceleration = -xSpeed;
            }
            else{
                hitbox.x = action.getOwner().getShootBoxR().x;
                acceleration = xSpeed;
            }
            hitbox.y = action.getOwner().getHitbox().y  + action.getOwner().getHitbox().height/2;
            launched = true;
        }
       
        if(flipped){
            if(acceleration < -xSpeed/4){
                acceleration += accelerationRate * deltatime;
            }
                hitbox.x += acceleration * deltatime;
            }
            else{
                if(acceleration > xSpeed/4){
                    acceleration -= accelerationRate * deltatime;
                }
                hitbox.x += acceleration * deltatime;
            }
    }
    
    public void BoomerangLogic(){
        float deltatime = CalcUtilities.CorrectTime();
        if(!launched){
            if(flipped){
                hitbox.x = action.getOwner().getShootBoxL().x - hitbox.width;
                acceleration = -xSpeed;
            }
            else{
                hitbox.x = action.getOwner().getShootBoxR().x;
                acceleration = xSpeed;
            }
            hitbox.y = action.getOwner().getHitbox().y  + action.getOwner().getHitbox().height/2;
            launched = true;
        }
       
        if(flipped){
            if(acceleration < xSpeed){
                acceleration += accelerationRate * deltatime;
            }
            hitbox.x += acceleration * deltatime;  
        }
        else{
            if(acceleration > -xSpeed){
                acceleration -= accelerationRate * deltatime;
            }
            hitbox.x += acceleration * deltatime;
        }
    }
    
    public void WavePathLogic(){
        float deltatime = CalcUtilities.CorrectTime();
        if(!launched){
            if(flipped){
                hitbox.x = action.getOwner().getShootBoxL().x - hitbox.width;
            }
            else{
                hitbox.x = action.getOwner().getShootBoxR().x;
            }
            hitbox.y = action.getOwner().getHitbox().y  + action.getOwner().getHitbox().height/2;
            acceleration = ySpeed;
            launched = true;
        }
       
        if(flipped){
            hitbox.x += -xSpeed * deltatime;  
        }
        else{
            hitbox.x += xSpeed * deltatime;
        }
        
        if(waveUp){
            if(acceleration > -ySpeed){
                acceleration -= accelerationRate * deltatime;
            }
            else{
                waveUp = false;
            }
        }
        else{
            if(acceleration < ySpeed){
                acceleration += accelerationRate * deltatime;
            }
            else{
                waveUp = true;
            }
        }
        hitbox.y += acceleration * deltatime;
    }
    
    float xVel = 0;
    float yVel = 0;
    public void HomingLogic(ArrayList<Beast> beastList){
        float deltatime = CalcUtilities.CorrectTime();
        if(!launched){
            if(flipped){
                hitbox.x = action.getOwner().getShootBoxL().x - hitbox.width;
                xVel = -xSpeed * deltatime;
            }
            else{
                hitbox.x = action.getOwner().getShootBoxR().x;
                xVel = xSpeed * deltatime;
//                System.out.println("Begin xVel: " + xVel);
            }
            hitbox.y = action.getOwner().getHitbox().y  + action.getOwner().getHitbox().height/2;
            
            float distance = 0;
            for(int i = 0; i < beastList.size(); i++){
                if(beastList.get(i) != null){
                    if(beastList.get(i).getTamer().getTeamNumber() != action.getOwner().getTamer().getTeamNumber()){
                        float x = Math.abs((float) Math.pow(beastList.get(i).getHitbox().x - action.getOwner().getHitbox().x, 2));
                        float y = Math.abs((float) Math.pow(beastList.get(i).getHitbox().y - action.getOwner().getHitbox().y, 2));
                        float thisDist = (float) Math.sqrt(x+y);
                        if(thisDist < distance || distance == 0){
                            target = beastList.get(i);
                        }
                    }
                }
            }
            launched = true;
        }
        
        float xDifference = target.getHitbox().x + target.getHitbox().width/2 - hitbox.x;
        float yDifference = target.getHitbox().y + target.getHitbox().height/2 - hitbox.y;
        float totalDifference = (float) Math.sqrt(Math.pow(xDifference, 2) + Math.pow(yDifference, 2));
        float totalTime = totalDifference/(xSpeed * deltatime);
        float xForce = xDifference/totalTime;
        float yForce = yDifference/totalTime;
        angle = (float) ((Math.toDegrees(Math.atan2(yForce, xForce))));

        float maxXSpeed = Math.abs(xForce);
        float maxYSpeed = Math.abs(yForce);
        float accelRate = accelerationRate * deltatime;
        
            if(xVel < maxXSpeed && xForce >= 0){
                xVel += xForce * deltatime * accelRate;
                if(xVel > maxXSpeed){
                    xVel = maxXSpeed;
                }
            }
            else if(xVel > -maxXSpeed && xForce <= 0){
                xVel += xForce * deltatime * accelRate;
                if(xVel < -maxXSpeed){
                    xVel = -maxXSpeed;
                }
            }
            if(yVel < maxYSpeed && yForce >= 0){
                yVel += yForce * deltatime * accelRate;
                if(yVel > maxYSpeed){
                    yVel = maxYSpeed;
                }
            }
            else if(yVel > -maxYSpeed && yForce <= 0){
                yVel += yForce * deltatime * accelRate;
                if(yVel < -maxYSpeed){
                    yVel = -maxYSpeed;
                }
            }
//            System.out.println("Max xVel: " + maxXSpeed);
//            System.out.println("DeltaTime: " + deltatime);
            hitbox.x += xVel;
            hitbox.y += yVel;
    }
    
    public void Logic(ArrayList<Beast> beastList, ArrayList<Rectangle> environlist, Rectangle[] stageEnds){
        switch(subType){
            case Burst: 
        }
        
        switch(shotType){
            case Straight: StraightLogic();
                break;
            case BuildUp: BuildUp();
                break;
            case SlowDown: SlowDownLogic();
                break;
            case Boomerang: BoomerangLogic();
                break;
            case WavePath: WavePathLogic();
                break;
            case Homing: HomingLogic(beastList);
                break;
        }
        CheckForAHit(beastList);
        CheckEnvironmentCollision(environlist, stageEnds);
        Animate();
    }
    
    public void Animate(){
        if(time > maxTime){
            if(frame == 0){
                frame = 1;
            }
            else if(frame == 1){
                frame = 0;
            }
            currentImg = imgList[frame];
            time = 0;
        }
        else{
            time += Gdx.graphics.getDeltaTime();
        }
    }
    
    public void Render(SpriteBatch batch){
        float x = hitbox.x + hitbox.width/2 - currentImg.getRegionWidth()/2;
        float y = hitbox.y + hitbox.height/2 - currentImg.getRegionHeight()/2;
        if(shotType == ShotType.Homing){
            batch.draw(currentImg, x, y, currentImg.getRegionWidth()/2, 
                        currentImg.getRegionHeight()/2, currentImg.getRegionWidth(), 
                        currentImg.getRegionHeight(), 1, 1, angle);
        }
        else if(flipped){
            batch.draw(currentImg, x + currentImg.getRegionWidth(), y, -currentImg.getRegionWidth()/2, 
                        currentImg.getRegionHeight()/2, -currentImg.getRegionWidth(), 
                        currentImg.getRegionHeight(), 1, 1, angle);
        }
        else{
            batch.draw(currentImg, x, y, currentImg.getRegionWidth()/2, 
                        currentImg.getRegionHeight()/2, currentImg.getRegionWidth(), 
                        currentImg.getRegionHeight(), 1, 1, angle);
        }
        
        
//        RotateAction(batch, x, y);
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
    }

    /**
     * @return the action
     */
    public Action getAction() {
        return action;
    }

    /**
     * @param action the action to set
     */
    public void setAction(Action action) {
        this.action = action;
    }

    /**
     * @return the shotType
     */
    public ShotType getShotType() {
        return shotType;
    }

    /**
     * @param shotType the shotType to set
     */
    public void setShotType(ShotType shotType) {
        this.shotType = shotType;
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
     * @return the xSpeed
     */
    public float getxSpeed() {
        return xSpeed;
    }

    /**
     * @param xSpeed the xSpeed to set
     */
    public void setxSpeed(float xSpeed) {
        this.xSpeed = xSpeed;
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
     * @return the finished
     */
    public boolean isFinished() {
        return finished;
    }

    /**
     * @param finished the finished to set
     */
    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    /**
     * @return the accelerateRate
     */
    public float getAccelerationRate() {
        return accelerationRate;
    }

    /**
     * @param accelerationRate the accelerateRate to set
     */
    public void setAccelerationRate(float accelerationRate) {
        this.accelerationRate = accelerationRate;
    }

    /**
     * @return the phaseThrough
     */
    public boolean isPhaseThrough() {
        return phaseThrough;
    }

    /**
     * @param phaseThrough the phaseThrough to set
     */
    public void setPhaseThrough(boolean phaseThrough) {
        this.phaseThrough = phaseThrough;
    }

    /**
     * @return the maxFlightTime
     */
    public float getMaxFlightTime() {
        return maxFlightTime;
    }

    /**
     * @param maxFlightTime the maxFlightTime to set
     */
    public void setMaxFlightTime(float maxFlightTime) {
        this.maxFlightTime = maxFlightTime;
    }
}
