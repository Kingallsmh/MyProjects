/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Screens.StageScreen;
import com.mygdx.game.World.MiningCamp;
import java.util.ArrayList;

/**
 *
 * @author Kyle
 */
public class Player2 {
    MiningCamp mCamp;
    
    //Stats
    int hp;
    int maxHp;
    int atk;
    int def;
    int spd;
    int jmp;
    int rld;
    
    //Booleans for states
    public boolean falling = true;
    public boolean balloon = false;
    boolean ceilingCol = false;
    boolean rightWallCol = false;
    boolean leftWallCol = false;
    boolean facingRight = true;
    
    public float x;
    public float y;
    public float XVelocity = 0;
    public float YVelocity = 0;
    float xSpeed = 55;
    float gravity = 90;
    
    public Rectangle CB;
    public Rectangle NB;
    public Rectangle EB;
    public Rectangle SB;
    public Rectangle WB;
    
    //Image needs
    int w = 32;
    int h = 32;
    int runAnimTimer = 0;
    int crawlTimer = 0;
    int balloonTimer = 0;
    Texture ACSheet = new Texture("AbsorbCore2.png");
    TextureRegion idle1, idle2, run1, run2, run3, jump, crawl1, crawl2, cCrawl1, cCrawl2, ballooned1, ballooned2 ,current;
    
    public Player2(float x, float y, MiningCamp mCamp){
        this.mCamp = mCamp;
        this.x = x;
        this.y = y;
        
        //HitBox initialization
        CB = new Rectangle(x + 11, y, 10, 20);
        NB = new Rectangle(CB.x,CB.y + CB.height, CB.width-2, 1);
        EB = new Rectangle(CB.x + CB.width, CB.y+1, 1, CB.height-2);
        SB = new Rectangle(CB.x, CB.y, CB.width-2, 1);
        WB = new Rectangle(CB.x-1, CB.y+1, 1, CB.height-2);
        
        
        
        idle1 = new TextureRegion(ACSheet, 0, 0, w, h);
        idle2 = new TextureRegion(ACSheet, w, 0, w, h);
        run1 = new TextureRegion(ACSheet, w*2, 0, w, h);
        run2 = new TextureRegion(ACSheet, w*3, 0, w, h);
        run3 = new TextureRegion(ACSheet, w*4, 0, w, h);
        jump = new TextureRegion(ACSheet, 0, h, w, h);
        crawl1 = new TextureRegion(ACSheet, 0, h*4, w, h);
        crawl2 = new TextureRegion(ACSheet, w, h*4, w, h);
        cCrawl1 = new TextureRegion(ACSheet, w*2, h*4, w, h);
        cCrawl2 = new TextureRegion(ACSheet, w*3, h*4, w, h);
        ballooned1 = new TextureRegion(ACSheet, w, h, w, h);
        ballooned2 = new TextureRegion(ACSheet, w*2, h, w, h);
        current = idle1;
    }
    
    public void updatePositioning(){
        CB.x = this.x + 11;
        CB.y = this.y + 1;
        NB.x = CB.x+1;
        NB.y = CB.y+CB.height;
        EB.x = CB.x+CB.width;
        EB.y = CB.y+1;
        SB.x = CB.x+1;
        SB.y = CB.y-1;
        WB.x = CB.x-1;
        WB.y = CB.y+1;
    }
    
    public void Draw(SpriteBatch batch){
        current = DrawAnim();
        if(!facingRight){
            batch.draw(current, x + current.getRegionWidth(), y, -current.getRegionWidth(), current.getRegionHeight());
        }
        else{
            batch.draw(current, x, y);
        }
        
    }
    
    public void updateSpeed(){
        if(YVelocity == 0){
            falling = false;
        }
    }
                
    public void Input(){
        updateSpeed();
        updatePositioning();
        falling = GravityFall(mCamp.collisionList);
        rightWallCol = RightWallCollide(mCamp.collisionList);
        leftWallCol = LeftWallCollide(mCamp.collisionList);
        ceilingCol = ceilingCollision(mCamp.collisionList);
        if(!falling){
            Jump();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D) && Gdx.input.isKeyPressed(Input.Keys.A)){
        }
        else{
            BalloonJump();
            Move();
            WallControls();
        }
        VerticalHandling();
    }
    public void WallControls(){
        if(Gdx.input.isKeyPressed(Input.Keys.D) && !leftWallCol){
            XVelocity = 0;
            x += 1;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A) && !rightWallCol){
            XVelocity = 0;
            x -= 1;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A) && !leftWallCol){
            facingRight = false;
            YVelocity = 0;
            falling = false;
            if(Gdx.input.isKeyPressed(Input.Keys.S)){
                YVelocity = -xSpeed;
                crawlTimer += 1;
            }
            else if(Gdx.input.isKeyPressed(Input.Keys.W)){
                YVelocity = xSpeed;
                crawlTimer += 1;
                if(!GravityFall(mCamp.collisionList)){
                    y += 1;
                }
            }
            else{
                crawlTimer = 0;
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D) && !rightWallCol){
            facingRight = true;
            YVelocity = 0;
            falling = false;
            if(Gdx.input.isKeyPressed(Input.Keys.S)){
                YVelocity = -xSpeed;
                crawlTimer += 1;
            }
            else if(Gdx.input.isKeyPressed(Input.Keys.W)){
                YVelocity = xSpeed;
                crawlTimer += 1;
                if(!GravityFall(mCamp.collisionList)){
                    y += 1;
                }
            }
            else{
                crawlTimer = 0;
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S) && !ceilingCol){
            y = this.y - 1;
            YVelocity = -1;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.D) && !ceilingCol){
            crawlTimer += 1;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.A) && !ceilingCol){
            crawlTimer += 1;
        }
        if(crawlTimer > 20){
            crawlTimer = 0;
        }
    }
    
    public void Move(){
        if(Gdx.input.isKeyPressed(Input.Keys.A) && leftWallCol){
            XVelocity = -xSpeed * Gdx.graphics.getDeltaTime();
            facingRight = false;
            runAnimTimer += 1;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.D) && rightWallCol){
            XVelocity = xSpeed * Gdx.graphics.getDeltaTime();
            facingRight = true;
            runAnimTimer += 1;
        }
        else{
            XVelocity = 0;
            runAnimTimer = 0;
        }
        if(runAnimTimer > 40){
            runAnimTimer = 0;
        }
        
        x += XVelocity;
        
    }
    
    public void VerticalHandling(){
        if(falling){
            this.YVelocity -= gravity * Gdx.graphics.getDeltaTime();
        }
        this.y += YVelocity * Gdx.graphics.getDeltaTime();
    }
    
    public boolean GravityFall(ArrayList<Rectangle> collisionList){
        for(int i = 0; i < collisionList.size(); i++){
            if(SB.overlaps(collisionList.get(i))){
                y = collisionList.get(i).y + collisionList.get(i).height - 1;
                YVelocity = 0;
            return false;
            }
        }
        return true;
    }
    
    public boolean RightWallCollide(ArrayList<Rectangle> collisionList){
        for(int i = 0; i < collisionList.size(); i++){
            if(EB.overlaps(collisionList.get(i))){
                x = collisionList.get(i).x - collisionList.get(i).width - 5;
                XVelocity = 0;
            return false;
            }
        }
        return true;
    }
    
    public boolean LeftWallCollide(ArrayList<Rectangle> collisionList){
        for(int i = 0; i < collisionList.size(); i++){
            if(WB.overlaps(collisionList.get(i))){
                x = collisionList.get(i).x + 5;
                XVelocity = 0;
            return false;
            }
        }
        return true;
    }
    
    public boolean ceilingCollision(ArrayList<Rectangle> collisionList){
        for(int i = 0; i < collisionList.size(); i++){
            if(NB.overlaps(collisionList.get(i))){
                y = collisionList.get(i).y - CB.height - 1;
                YVelocity = 0;
            return false;
            }
        }
        return true;
    }
    
    public void Jump(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.W)){
            YVelocity = 80;
        }        
    }
    
    public void BalloonJump(){
        if(!ceilingCol || !leftWallCol || !rightWallCol || !falling){
                balloon = false;
                balloonTimer = 0;
        }
        else if(Gdx.input.isKeyJustPressed(Input.Keys.W) && !balloon && falling){
            YVelocity = 80;
            balloon = true;
        }
        if(balloon){
            balloonTimer += 1;
        }
    }
    
    public TextureRegion DrawAnim(){
        if(!ceilingCol){
            if(crawlTimer > 10){
                return cCrawl2;
            }
            return cCrawl1;    
        }
        if(balloon){
            if(balloonTimer < 8){
                return ballooned1;
            }
            return ballooned2;
        }
        else if(falling){
            return jump;
        }
        else if(XVelocity > 0 || XVelocity < 0){
            if(runAnimTimer > 20 && runAnimTimer < 30){
                return run3;
            }
            else if(runAnimTimer > 10 || runAnimTimer > 30){
                return run2;
            }   
            return run1;
        }
        else if(!leftWallCol || !rightWallCol){
            if(crawlTimer > 10){
                return crawl2;
            }
            return crawl1;
        }
        
        return idle1;
    }
    
}
