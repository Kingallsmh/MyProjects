/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mb.entities.overworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.mb.CalcUtilities;
import com.mb.player.BaseControl;
import com.mb.world.Dungeon;
import com.mb.world.DungeonLayer;
import com.mb.world.IDraw;

/**
 *
 * @author Kyle
 */
public class WorldCharacter implements IDraw{
    String name;
    private BaseControl controller;
    private Rectangle hitbox;
    float speed = 50;
    private TextureRegion[] walkNImg;
    private TextureRegion[] walkEImg;
    private TextureRegion[] walkSImg;
    private TextureRegion[] walkWImg;
    private TextureRegion currentImg;
    int frame;
    float frameTime, maxFrameTime = 0.2f;
    
    Direction direction = Direction.South;
    
    public enum Direction{
        North, East, South, West;
    }
    
    public WorldCharacter(){
        
    }
    
    public void Logic(DungeonLayer dungeonLayer){
        ControlCharacter(dungeonLayer);
    }
    
    public void DirectionChange(){
        switch(direction){
            case South: currentImg = walkSImg[frame];
                break;
            case North: currentImg = walkNImg[frame];
                break;
            case East: currentImg = walkEImg[frame];
                break;
            case West: currentImg = walkWImg[frame];
                break;
        }
    }
    
    public void ControlCharacter(DungeonLayer dungeonLayer){
        controller.ListenForInput();
        float xSpd = 0, ySpd = 0;
        if(controller.isUp()){
            ySpd = speed * CalcUtilities.CorrectTime();
            direction = Direction.North;
        }
        if(controller.isRight()){
            xSpd = speed * CalcUtilities.CorrectTime();
            direction = Direction.East;
        }
        if(controller.isDown()){
            ySpd = -speed * CalcUtilities.CorrectTime();
            direction = Direction.South;
        }
        if(controller.isLeft()){
            xSpd = -speed * CalcUtilities.CorrectTime();
            direction = Direction.West;
        }
        Rectangle checkBoxX = new Rectangle(hitbox.x + xSpd, hitbox.y, hitbox.width, hitbox.height);
        Rectangle checkBoxY = new Rectangle(hitbox.x, hitbox.y + ySpd, hitbox.width, hitbox.height);
        if(!dungeonLayer.CheckCharCollisionOnFloor(checkBoxX)){
            MoveCharacter(xSpd, 0);
        }
        if(!dungeonLayer.CheckCharCollisionOnFloor(checkBoxY)){
            MoveCharacter(0, ySpd);
        }
        AnimationPlay(xSpd, ySpd);
        DirectionChange();
    }
    
    public void MoveCharacter(float xSpd, float ySpd){
        this.hitbox.x += xSpd;
        this.hitbox.y += ySpd;
    }
    
    public void AnimationPlay(float xSpd, float ySpd){
        if(xSpd != 0 || ySpd != 0){
            if(frameTime > maxFrameTime){
                if(frame < walkNImg.length-1){
                    frame += 1;
                }
                else{
                    frame = 0;
                }
                frameTime = 0;
            }
            else{
                frameTime += CalcUtilities.CorrectTime();
            }
        }
    }
    
    @Override
    public void Render(SpriteBatch batch){
        float x = hitbox.x - currentImg.getRegionWidth()/2 + hitbox.width/2;
        float y = hitbox.y - 1;
        batch.draw(currentImg, x, y);
    }
    
    public void DebugRender(ShapeRenderer draw){
        draw.rect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
    }

    /**
     * @return the hitbox
     */
    @Override
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
     * @return the controller
     */
    public BaseControl getController() {
        return controller;
    }

    /**
     * @param controller the controller to set
     */
    public void setController(BaseControl controller) {
        this.controller = controller;
    }

    /**
     * @return the walkNImg
     */
    public TextureRegion[] getWalkNImg() {
        return walkNImg;
    }

    /**
     * @param walkNImg the walkNImg to set
     */
    public void setWalkNImg(TextureRegion[] walkNImg) {
        this.walkNImg = walkNImg;
    }

    /**
     * @return the walkEImg
     */
    public TextureRegion[] getWalkEImg() {
        return walkEImg;
    }

    /**
     * @param walkEImg the walkEImg to set
     */
    public void setWalkEImg(TextureRegion[] walkEImg) {
        this.walkEImg = walkEImg;
    }

    /**
     * @return the walkSImg
     */
    public TextureRegion[] getWalkSImg() {
        return walkSImg;
    }

    /**
     * @param walkSImg the walkSImg to set
     */
    public void setWalkSImg(TextureRegion[] walkSImg) {
        this.walkSImg = walkSImg;
    }

    /**
     * @return the walkWImg
     */
    public TextureRegion[] getWalkWImg() {
        return walkWImg;
    }

    /**
     * @param walkWImg the walkWImg to set
     */
    public void setWalkWImg(TextureRegion[] walkWImg) {
        this.walkWImg = walkWImg;
    }

    /**
     * @return the currentImg
     */
    public TextureRegion getCurrentImg() {
        return currentImg;
    }

    /**
     * @param currentImg the currentImg to set
     */
    public void setCurrentImg(TextureRegion currentImg) {
        this.currentImg = currentImg;
    }   
}
