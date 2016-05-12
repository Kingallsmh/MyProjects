/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.ScreenSetup;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import game.monsters.PlayerMonster;
import game.objects.AnimatedObject;
import game.screens.GameInstructionScreen;
import game.screens.MainScreen;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kyle
 */
public class MainSetup {
    
    //Menu utils
    MainScreen mScreen;
    public AnimatedObject animObject;
    public float timer = 0;
    public int seconds = 0;
    int animTimer = 0;
    int waitTimer = 120;
    int w = 16;
    int c = 16;
    boolean feeding;
    boolean fullFood = false;
    boolean petting;
    public boolean fullHappy = false;
    boolean saving;
    public Vector3 vector;
    Texture menuStyle, menuVisual;
    Texture treeBG;
    TextureRegion foodBtn, petBtn, saveBtn, strGame, conGame, aglGame;
    TextureRegion fpBar, hpBar;
    TextureRegion heart, note, cant, upgrade;
    
    //Menu numbers and information
    public String monsterName;
    String monsterSpecies;
    
    //Player
    public PlayerMonster critter;
    boolean pointAvail = false;
    Rectangle feedBtnHit;
    Rectangle petBtnHit;
    Rectangle saveBtnHit;
    Rectangle upStr, upCon, upAgl;
    Rectangle strGameBtn, conGameBtn, aglGameBtn;
    
    //Saving/Loading utils
    Properties prop;
    OutputStream output;
    InputStream input;
    
    public MainSetup(MainScreen mScreen){
        this.mScreen = mScreen;
        
        //Menu visuals
        menuStyle = new Texture("SimpleSmallStyle.png");
        menuVisual = new Texture("SimpleSmallTools.png");
        treeBG = new Texture("TreeBG.png");
        
        foodBtn = new TextureRegion(menuVisual, 0, 0, w*2, w);
        petBtn = new TextureRegion(menuVisual, 0, c, w*2, w);
        saveBtn = new TextureRegion(menuVisual, 0, c*2, w*2, w);
        strGame = new TextureRegion(menuVisual, 0, c*4, w*2, w);
        conGame = new TextureRegion(menuVisual, c*2, c*4, w*2, w);
        aglGame = new TextureRegion(menuVisual, 0, c*5, w*2, w);
        cant = new TextureRegion(menuVisual, c*2, 0, w*2, w);
        heart = new TextureRegion(menuVisual, c*2, c, w, w);
        note = new TextureRegion(menuVisual, c*3, c, w, w);
        upgrade = new TextureRegion(menuVisual, 0, c*3, w, w);
        
        hpBar = new TextureRegion(menuVisual, c*2, c*3, 29, w);
        fpBar = new TextureRegion(menuVisual, c*2, c*2, 29, w);
        
        //Player monster
        prop = new Properties();
        vector = new Vector3(0, 0, 0);
        critter = new PlayerMonster();
        feedBtnHit = new Rectangle(2, 45, 32, 16);
        petBtnHit = new Rectangle(2, 28, 32, 16);
        saveBtnHit = new Rectangle(2, 2, 32, 16);
        upStr = new Rectangle(116, 99, 10, 8);
        upCon = new Rectangle(85, 77, 10, 8);
        upAgl = new Rectangle(116, 77, 10, 8);
        strGameBtn = new Rectangle(94, 19, 32, 16);
        conGameBtn = new Rectangle(61, 2, 32, 16);
        aglGameBtn = new Rectangle(94, 2, 32, 16);
        
        critter.LoadMonster(prop, input);
    }
    
    public void UpdateStats(){
        this.monsterName = critter.name;
        timer += 1;
        seconds = ((int)timer) / 60;
        if(seconds > 120 && critter.hunger > 0){
            critter.hunger -= 1;
            timer = 0;
        }
        if(animTimer < waitTimer){
            animTimer += 1;
        }
        
    }
    
    public void UpdatePostion(){
        if(Gdx.input.isTouched()){
            vector.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            mScreen.cam.unproject(vector);
        }
    }
    
    public void FeedBtn(){
        if(critter.hunger < critter.maxHunger && critter.currentFood > 0){
            if(feedBtnHit.contains(vector.x, vector.y) && animTimer == waitTimer){
            vector.x = 0;
            vector.y = 0;
            animTimer = 0;
            pointAvail = true;
            critter.interaction = true;
            critter.animTimer = 0;
            critter.hunger += 1;
            critter.currentFood -= 1;
            animObject = new AnimatedObject(heart, 0);
            }
            else if(feedBtnHit.contains(vector.x, vector.y) && animTimer < waitTimer){
            vector.x = 0;
            vector.y = 0;
            }
            fullFood = false;
        }
        else if(critter.hunger == critter.maxHunger){
            fullFood = true;
        }
        
    }
    
    public void PetBtn(){
        if(critter.happiness < critter.maxHappiness){
            if(petBtnHit.contains(vector.x, vector.y) && animTimer == waitTimer){
            vector.x = 0;
            vector.y = 0;
            animTimer = 0;
            critter.interaction = true;
            critter.animTimer = 0;
            critter.happiness += 1;
            animObject = new AnimatedObject(note, 1);
            }
            else if(petBtnHit.contains(vector.x, vector.y) && animTimer < waitTimer){
            vector.x = 0;
            vector.y = 0;
            }
            fullHappy = false;
        }
        else if(critter.happiness == critter.maxHappiness){
            fullHappy = true;
        }
    }
    
    public void saveBtn(){
          
        if(saveBtnHit.contains(vector.x, vector.y) && animTimer == waitTimer){
            vector.x = 0;
            vector.y = 0;
            try {
                critter.SaveMonster(prop, output);
            } catch (IOException ex) {
                Logger.getLogger(MainSetup.class.getName()).log(Level.SEVERE, null, ex);
            }
            critter.interaction = true;
            critter.animTimer = 0;
            
            animObject = new AnimatedObject(note, 1);
            }
            else if(saveBtnHit.contains(vector.x, vector.y) && animTimer < waitTimer){
            vector.x = 0;
            vector.y = 0;
            }
    }
    
    public void PlayMiniGame(){
        if(strGameBtn.contains(vector.x, vector.y) || animTimer == waitTimer){
            mScreen.ch.setScreen(new GameInstructionScreen(critter, mScreen));
        }
        if(conGameBtn.contains(vector.x, vector.y) || animTimer == waitTimer){
            mScreen.ch.setScreen(new GameInstructionScreen(critter, mScreen));
        }
        if(aglGameBtn.contains(vector.x, vector.y) || animTimer == waitTimer){
            mScreen.ch.setScreen(new GameInstructionScreen(critter, mScreen));
        }
    }
    
    public void UpgradeStr(){
        if(upStr.contains(vector.x, vector.y)){
            pointAvail = false;
            critter.strLvl += 1;
        }
    }
    
    public void UpgradeCon(){
        if(upCon.contains(vector.x, vector.y)){
            pointAvail = false;
            critter.conLvl += 1;
        }
    }
    
    public void UpgradeAgl(){
        if(upAgl.contains(vector.x, vector.y)){
            pointAvail = false;
            critter.aglLvl += 1;
        }
    }
    
    
    public void RenderSetup(SpriteBatch batch) {
        //Draw the main style
        batch.draw(menuStyle, 0, 0);
        
        //Draw background
        batch.draw(treeBG, 2, 62);
        //Draw the monster on top of the BG
        critter.RenderMonster(batch);
        
        //Draw the buttons
        batch.draw(foodBtn, feedBtnHit.x, feedBtnHit.y);
        batch.draw(petBtn, petBtnHit.x, petBtnHit.y);
        batch.draw(saveBtn, saveBtnHit.x, saveBtnHit.y);
        batch.draw(strGame, strGameBtn.x, strGameBtn.y);
        batch.draw(conGame, conGameBtn.x, conGameBtn.y);
        batch.draw(aglGame, aglGameBtn.x, aglGameBtn.y);
        
        //if you can no longer use the buttons
        if(fullFood || critter.currentFood == 0 || pointAvail){
            batch.draw(cant, feedBtnHit.x, feedBtnHit.y);
        }
        if(fullHappy){
            batch.draw(cant, petBtnHit.x, petBtnHit.y);
        }
        
        //Draw the bars
        batch.draw(fpBar, 36, 36, fpBar.getRegionWidth()*(critter.hunger / critter.maxHunger), fpBar.getRegionHeight());
        batch.draw(hpBar, 36, 19, hpBar.getRegionWidth()*(critter.happiness / critter.maxHappiness), hpBar.getRegionHeight());
        
        UpdateStats();
        UpdatePostion();
        if(pointAvail){
            UpgradeStr();
            UpgradeCon();
            UpgradeAgl();
            batch.draw(upgrade, upStr.x, upStr.y - 8);
            batch.draw(upgrade, upCon.x, upCon.y - 8);
            batch.draw(upgrade, upAgl.x, upAgl.y - 8);
            batch.draw(cant, petBtnHit.x, petBtnHit.y);
            batch.draw(cant, saveBtnHit.x, saveBtnHit.y);
        }else{
            //PlayMiniGame();
            FeedBtn();
            PetBtn();
            saveBtn();
        }
        
        if(animObject != null){
            animObject.RenderObject(batch, this);
        }
    }
   
    
    
}
