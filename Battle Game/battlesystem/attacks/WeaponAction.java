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
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.mb.entities.battle.Beast;
import java.util.ArrayList;

/**
 *
 * @author Kyle
 */
public class WeaponAction {
    Action action;
    private TextureRegion img;
    Rectangle tempbox;
    Polygon hitpoly;
    private float angle = 0;
    private AttackType type = AttackType.Slice;
    
    private float pushXAmount;
    private float pushYAmount;
    
    float time, maxTime = 0.01f;
    private float swingSpeed = 1;
    private float maxSwingAngle = 135;
    private float stabSpeed;
    float xDistance;    
    
    private boolean finished = false;
    boolean launched = false;
    
    public enum AttackType{
        Slice, Stab
    }
    
    public WeaponAction(Action action, AttackType type){
        this.action = action;
        this.type = type;
    }
    
    public WeaponAction(Action action, WeaponAction weapon){
        this.action = action;
//        System.out.println("x: " + weapon.hitpoly.getBoundingRectangle().width + " y: " + weapon.hitpoly.getBoundingRectangle().height);
        this.tempbox = new Rectangle(weapon.tempbox);
        this.hitpoly = new Polygon(weapon.hitpoly.getTransformedVertices());
        this.img = weapon.img;
        this.maxSwingAngle = weapon.maxSwingAngle;
        this.swingSpeed = weapon.swingSpeed;
        this.type = weapon.type;
        this.angle = weapon.angle;
        this.stabSpeed = weapon.stabSpeed;
    }
    
    public void CheckForAHit(ArrayList<Beast> beastList){
        for(int i = 0; i < beastList.size(); i++){
            if(beastList.get(i) != null){
                if(beastList.get(i).getTamer().getTeamNumber() != action.getOwner().getTamer().getTeamNumber()){
                    Polygon nPoly = new Polygon(new float[]{
                        0, 0,
                        beastList.get(i).getHitbox().width, 0,
                        beastList.get(i).getHitbox().width, beastList.get(i).getHitbox().height,
                        0, beastList.get(i).getHitbox().height
                    });
                    nPoly.setPosition(beastList.get(i).getHitbox().x, beastList.get(i).getHitbox().y);
                    if(Intersector.overlapConvexPolygons(hitpoly, nPoly)){
                        float SDMG = (float)action.getOwner().getStats().getPHYS() * (float)action.getSATK()/10;
                        float PDMG = (float)action.getOwner().getStats().getPHYS() * (float)action.getPATK()/10;
                        float BDMG = (float)action.getOwner().getStats().getPHYS() * (float)action.getBATK()/10;
                        beastList.get(i).TakeHit(SDMG, PDMG, BDMG, action.getType());
                        PushBeast(beastList.get(i), pushXAmount, pushYAmount);
    //                    finished = true;
                    }
                }
            }
        }
    }
    
    public void PushBeast(Beast beast, float xPush, float yPush){
        if(action.isFlipped()){
            beast.getPhysics().setxVel(-xPush);
        }
        else{
            beast.getPhysics().setxVel(xPush);
        }
         beast.getPhysics().setyVel(yPush);
    }
    
    public void SliceLogic(){
        float x = action.getOwner().getHitbox().x + action.getOwner().getHitbox().width/2 - tempbox.width/2;
        float y = action.getOwner().getHitbox().y + action.getOwner().getHitbox().height/2;
        if(action.isFlipped()){
            hitpoly.setPosition(x - action.getOwner().getHitbox().width/2, y);
        }
        else{
            hitpoly.setPosition(x + action.getOwner().getHitbox().width/2, y);
        }
        
        hitpoly.setOrigin(tempbox.width/2, -4);
        hitpoly.setRotation(angle);
    }
    
    public void SliceAnimate(){
        if(time > maxTime && !finished){
            if(action.isFlipped()){
                angle += swingSpeed;
                if(angle >= maxSwingAngle){
                    finished = true;
                }
            }
            if(!action.isFlipped()){
                angle -= swingSpeed;
                if(angle <= -maxSwingAngle){
                    finished = true;
                }
            }
        }
        else{
            time += Gdx.graphics.getDeltaTime();
        }
    }
    
    public void RenderSlice(SpriteBatch batch, Rectangle beastbox, boolean flipped){
        float x = beastbox.x + beastbox.width/2 - img.getRegionWidth()/2;
        float y = beastbox.y + beastbox.height/2 + tempbox.height/2 - img.getRegionHeight()/2;
        if(flipped){
            batch.draw(img, x + img.getRegionWidth() - beastbox.width/2, y, -img.getRegionWidth()/2, 
                -4 + (img.getRegionHeight()/2 - tempbox.height/2), -img.getRegionWidth(), 
                img.getRegionHeight(), 1, 1, angle);
        }
        else{
            batch.draw(img, x + beastbox.width/2, y, img.getRegionWidth()/2, 
                -4 + (img.getRegionHeight()/2 - tempbox.height/2), img.getRegionWidth(), 
                img.getRegionHeight(), 1, 1, angle);
        }
    }
    
    public void StabLogic(){
        float x = action.getOwner().getHitbox().x + action.getOwner().getHitbox().width/2 - tempbox.width/2;
        float y = action.getOwner().getHitbox().y + action.getOwner().getHitbox().height/2;
        if(action.isFlipped()){
            hitpoly.setPosition(x + xDistance, y);
        }
        else{
            hitpoly.setPosition(x + xDistance, y);
        }
        
        hitpoly.setOrigin(tempbox.width/2, 0);
        hitpoly.setRotation(angle);
        
    }
    
    public void StabAnimate(){
        if(!launched){
            if(action.isFlipped()){
                angle = 90;
                xDistance = action.getOwner().getHitbox().width/2;
            }
            else{
                angle = -90;
                xDistance = -action.getOwner().getHitbox().width/2;
            }
            launched = true;
        }
        if(time > maxTime && !finished){
            if(action.isFlipped()){
                xDistance -= stabSpeed;
                if(xDistance <= -action.getOwner().getHitbox().width){
                    finished = true;
                }
            }
            if(!action.isFlipped()){
                xDistance += stabSpeed;
                if(xDistance >= action.getOwner().getHitbox().width){
                    finished = true;
                }
            }
        }
        else{
            time += Gdx.graphics.getDeltaTime();
        }
    }
    
    public void RenderStab(SpriteBatch batch, Rectangle beastbox, boolean flipped){
//        float x = action.getOwner().getHitbox().x + action.getOwner().getHitbox().width/2 - tempbox.width/2;
//        float y = action.getOwner().getHitbox().y + action.getOwner().getHitbox().height/2;
        float x = beastbox.x + beastbox.width/2 - img.getRegionWidth()/2;
        float y = beastbox.y + beastbox.height/2 + tempbox.height/2 - img.getRegionHeight()/2;
        if(flipped){
            batch.draw(img, x + img.getRegionWidth() + xDistance, y, -img.getRegionWidth()/2, 
                0 + (img.getRegionHeight()/2 - tempbox.height/2), -img.getRegionWidth(), 
                img.getRegionHeight(), 1, 1, angle);
        }
        else{
            batch.draw(img, x + xDistance, y, img.getRegionWidth()/2, 
                0 + (img.getRegionHeight()/2 - tempbox.height/2), img.getRegionWidth(), 
                img.getRegionHeight(), 1, 1, angle);
        }
    }
    
    public void Logic(ArrayList<Beast> beastList){
        Animate();
        switch(type){
            case Slice: SliceLogic();
                break;
            case Stab: StabLogic();
                break;
        }   
        
        CheckForAHit(beastList);
    }
    
    public void Animate(){
        switch(type){
            case Slice: SliceAnimate();
                break;
            case Stab: StabAnimate();
                break;
        } 
    }
    
    public void Render(SpriteBatch batch, Rectangle beastbox, boolean flipped){
        switch(type){
            case Slice: RenderSlice(batch, beastbox, flipped);
                break;
            case Stab: RenderStab(batch, beastbox, flipped);
                break;
        }
    }
    
    public void DebugRender(ShapeRenderer draw){
        draw.setColor(0, 0, 1, 1);
        draw.rect(hitpoly.getBoundingRectangle().x, hitpoly.getBoundingRectangle().y,
                hitpoly.getBoundingRectangle().width, hitpoly.getBoundingRectangle().height);
        draw.setColor(1, 0, 1, 1);
        draw.polygon(hitpoly.getTransformedVertices());
    }

    /**
     * @return the img
     */
    public TextureRegion getImg() {
        return img;
    }

    /**
     * @param img the img to set
     */
    public void setImg(TextureRegion img) {
        this.img = img;
    }

    /**
     * @return the hitbox
     */
    public Rectangle getHitbox() {
        return hitpoly.getBoundingRectangle();
    }

    /**
     * @param hitbox the hitbox to set
     */
    public void setHitbox(Rectangle hitbox) {
        tempbox = new Rectangle(hitbox);
        hitpoly = new Polygon(new float[]{
            0, 0,
            hitbox.width, 0,
            hitbox.width, hitbox.height,
            0, hitbox.height
        });
    }

    /**
     * @return the angle
     */
    public float getAngle() {
        return angle;
    }

    /**
     * @param angle the angle to set
     */
    public void setAngle(float angle) {
        this.angle = angle;
    }

    /**
     * @return the type
     */
    public AttackType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(AttackType type) {
        this.type = type;
    }

    /**
     * @return the swingSpeed
     */
    public float getSwingSpeed() {
        return swingSpeed;
    }

    /**
     * @param swingSpeed the swingSpeed to set
     */
    public void setSwingSpeed(float swingSpeed) {
        this.swingSpeed = swingSpeed;
    }

    /**
     * @return the maxSwingAngle
     */
    public float getMaxSDistance() {
        return maxSwingAngle;
    }

    /**
     * @param maxSDistance the maxSwingAngle to set
     */
    public void setMaxSDistance(float maxSDistance) {
        this.maxSwingAngle = maxSDistance;
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
     * @return the stabSpeed
     */
    public float getStabSpeed() {
        return stabSpeed;
    }

    /**
     * @param stabSpeed the stabSpeed to set
     */
    public void setStabSpeed(float stabSpeed) {
        this.stabSpeed = stabSpeed;
    }
}
