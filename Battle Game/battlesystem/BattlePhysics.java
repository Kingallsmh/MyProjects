/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mb.battlesystem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.mb.CalcUtilities;
import com.mb.entities.battle.Beast;
import java.util.ArrayList;

/**
 *
 * @author Kyle
 */
public class BattlePhysics {
    Beast beast;
    private float gravity;
    private float xVel;
    private float yVel;
    private boolean grounded;
    private boolean ceiling;
    private boolean rightWall;
    private boolean leftWall;
    
    public BattlePhysics(Beast beast){
        this.beast = beast;
    }
    
    public void ApplyGravity(){
        if(gravity != 0){
            if(yVel > -gravity*25){
                yVel -= gravity;
            }
        }
    }
    
     public void DetectCollision(ArrayList<Rectangle> objectList){
        Rectangle boxX = new Rectangle(beast.getHitbox());
        Rectangle boxY = new Rectangle(beast.getHitbox());  
        
        ResetTouchingTerrain();
        
        boxY.y += yVel * CalcUtilities.CorrectTime();
        boxY.width -= 2;
        boxY.x += 1;
        ArrayList<Rectangle> boxYList = CheckForCollisionInList(boxY, objectList);
        if(boxYList != null && !boxYList.isEmpty()){
            for(int i = 0; i < boxYList.size(); i++){
                if(boxYList.get(i) != null){
                    HandleYCollision(boxY, boxYList.get(i));
                }
            }
        }
        
        boxX.x += xVel * CalcUtilities.CorrectTime();
        ArrayList<Rectangle> boxXList = CheckForCollisionInList(boxX, objectList);
        if(boxXList != null && !boxXList.isEmpty()){
            for(int i = 0; i < boxXList.size(); i++){
                if(boxXList.get(i) != null){
                    HandleXCollision(boxX, boxXList.get(i));
                }
            }
        }
    }
    
    public void ResetTouchingTerrain(){
        grounded = false;
        ceiling = false;
        leftWall = false;
        rightWall = false;
    }
    
    public void HandleYCollision(Rectangle hitbox, Rectangle hitbox2){
        if(hitbox.overlaps(hitbox2)){
            if(hitbox.y > hitbox2.y){
                grounded = true;
                if(yVel < -1){
                    beast.getHitbox().y = (hitbox2.y + hitbox2.height);
                    yVel = 0;
                }
                else{
                    yVel = 0;
                }
            }
            else{
                ceiling = true;
                if(yVel > 1){
                    beast.getHitbox().y = (hitbox2.y - hitbox.height);
                    yVel = 0;
                }
                else{
                    yVel = 0;
                }
            }
        }
    }
    
    public void HandleXCollision(Rectangle hitbox, Rectangle hitbox2){
        if(hitbox.overlaps(hitbox2)){
            if(hitbox.x > hitbox2.x){
                leftWall = true;
                if(beast.getHitbox().x - (hitbox2.x + hitbox2.width) > 1){
                    beast.getHitbox().x = (hitbox2.x + hitbox2.width);
                    xVel = 0;
                }
                else{
                    xVel = 0;
                }
            }
            else{
                rightWall = true;
                if(xVel > 1){
                    beast.getHitbox().x = (hitbox2.x - hitbox.width);
                    xVel = 0;
                }
                else{
                    xVel = 0;
                }
            }
            xVel = 0;
        }
    }
    
    public ArrayList<Rectangle> CheckForCollisionInList(Rectangle box, ArrayList<Rectangle> objectList){
        ArrayList<Rectangle> list = new ArrayList<Rectangle>();
        for(int x = 0; x < objectList.size(); x++){
                if(objectList.get(x) != null && box.overlaps(objectList.get(x))){
                    list.add(objectList.get(x));
                }
        }
        return list;
    }

    /**
     * @return the xVel
     */
    public float getxVel() {
        return xVel;
    }

    /**
     * @param xVel the xVel to set
     */
    public void setxVel(float xVel) {
        this.xVel = xVel;
    }

    /**
     * @return the yVel
     */
    public float getyVel() {
        return yVel;
    }

    /**
     * @param yVel the yVel to set
     */
    public void setyVel(float yVel) {
        this.yVel = yVel;
    }

    /**
     * @return the grounded
     */
    public boolean isGrounded() {
        return grounded;
    }

    /**
     * @return the ceiling
     */
    public boolean isCeiling() {
        return ceiling;
    }

    /**
     * @return the rightWall
     */
    public boolean isRightWall() {
        return rightWall;
    }

    /**
     * @return the leftWall
     */
    public boolean isLeftWall() {
        return leftWall;
    }

    /**
     * @return the gravity
     */
    public float getGravity() {
        return gravity;
    }

    /**
     * @param gravity the gravity to set
     */
    public void setGravity(float gravity) {
        this.gravity = gravity;
    }
}

//Old code *****
//    public void DetectCollision(Rectangle[][] objectList){
//        Rectangle boxX = new Rectangle(beast.getHitbox());
//        Rectangle boxY = new Rectangle(beast.getHitbox());  
//        
//        ResetTouchingTerrain();
//        
//        boxY.y += yVel;
//        boxY.width -= 2;
//        boxY.x += 1;
//        ArrayList<Rectangle> boxYList = CheckForCollisionInList(boxY, objectList);
//        if(boxYList != null && !boxYList.isEmpty()){
//            for(int i = 0; i < boxYList.size(); i++){
//                if(boxYList.get(i) != null){
//                    HandleYCollision(boxY, boxYList.get(i));
//                }
//            }
//        }
//        
//        boxX.x += xVel;
//        ArrayList<Rectangle> boxXList = CheckForCollisionInList(boxX, objectList);
//        if(boxXList != null && !boxXList.isEmpty()){
//            for(int i = 0; i < boxXList.size(); i++){
//                if(boxXList.get(i) != null){
//                    HandleXCollision(boxX, boxXList.get(i));
//                }
//            }
//        }
//    }

//public ArrayList<Rectangle> CheckForCollisionInList(Rectangle box, Rectangle[][] objectList){
//        ArrayList<Rectangle> list = new ArrayList<Rectangle>();
//        float s = 16;
//        int xLoc = (int) (box.x/s - 1);
//        int yLoc = (int) (box.y/s - 1);
//        int wLoc = (int) (box.x/s + box.width/s + 1);
//        int hLoc = (int) (box.y/s + box.height/s + 1);
//        for(int x = 0; x < objectList.length; x++){
//            for(int y = 0; y < objectList[x].length; y++){
//                if(objectList[x][y] != null && box.overlaps(objectList[x][y])){
//                    list.add(objectList[x][y]);
//                }
//            }
//        }
//        return list;
//    }
