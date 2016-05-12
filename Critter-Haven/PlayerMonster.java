/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.monsters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import game.ScreenSetup.MainSetup;
import java.io.FileInputStream;
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
public class PlayerMonster {
    
    public String name;
    public int monNumber = 0;
    public int level;
    public int strLvl = 1;
    public int conLvl = 1;
    public int aglLvl = 1;
    
    public int currentFood = 2;
    public int maxExp = 5;
    public int happiness = 5;
    public float maxHappiness = 5;
    public int hunger = 0;
    public float maxHunger = 5;
    public boolean interaction = false;
    public int animTimer = 0;
    int w = 64, c = 64;
    Texture mPic;
    TextureRegion mIdle1, mIdle2, mHap1, mHap2, mAtk1, mAtk2 , mCurrent;
    
    
    public PlayerMonster(){
        //TEMP** ** **
        name = "LightEgg";
        mPic = new Texture("LightEggSheet.png");
        mIdle1 = new TextureRegion(mPic, 0, 0, w, w);
        mIdle2 = new TextureRegion(mPic, c, 0, w, w);
        mHap1 = new TextureRegion(mPic, c*2, 0, w, w);
        mHap2 = new TextureRegion(mPic, c*3, 0, w, w);
        mAtk1 = new TextureRegion(mPic, c*4, 0, w, w);
        mAtk2 = new TextureRegion(mPic, c*5, 0, w, w);
        mCurrent = mIdle1;
    }
    
    public void EvolveMonster(){
        //To evolve the egg, max out hunger
        if(strLvl + conLvl + aglLvl == maxExp){
            Evolution evo = new Evolution(this);
            evo.EvoPlayerMon();            
            UpdateMonsterPic();
            
            
            //Reset stats
            level += 1;
            maxHunger = 5 * level;
            
            maxHappiness = 10 * level;
            
            maxExp = 20 * level;
        }
        
    }
    
    public void UpdateMonsterPic(){
        mIdle1 = new TextureRegion(mPic, 0, 0, w, w);
        mIdle2 = new TextureRegion(mPic, c, 0, w, w);
        mHap1 = new TextureRegion(mPic, c*2, 0, w, w);
        mHap2 = new TextureRegion(mPic, c*3, 0, w, w);
        mAtk1 = new TextureRegion(mPic, c*4, 0, w, w);
        mAtk2 = new TextureRegion(mPic, c*5, 0, w, w);
        mCurrent = mIdle1;
    }
    
    public void RenderMonster(SpriteBatch batch){
        batch.draw(mCurrent, 2, 62);
        //If there is an interaction i.e. button pressed
        if(interaction){
            HappyAnim();
        }
        else{
            IdleAnim();
        }
        
        EvolveMonster();
    }
    
    public void IdleAnim(){
        if(animTimer < 30){
            mCurrent = mIdle1;
        }
        else if(animTimer >= 30){
            mCurrent = mIdle2;
        }
        
        animTimer += 1;
        if(animTimer > 60){
            animTimer = 0;
        }  
    }
    
    public void HappyAnim(){
        if(animTimer < 20 || animTimer >= 40){
            mCurrent = mHap1;
        }
        else if(animTimer >= 20){
            mCurrent = mHap2;
        }
        
        animTimer += 1;
        if(animTimer > 60){
            interaction = false;
            animTimer = 0;
        }  
    }
    
    public void LoadMonster(Properties prop, InputStream input){
        
        try {
            input = new FileInputStream("player.properties");
            prop.load(input);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PlayerMonster.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PlayerMonster.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(input != null){
            
            this.name = prop.getProperty("name");
            this.hunger = Integer.parseInt(prop.getProperty("hunger"));
            this.happiness = Integer.parseInt(prop.getProperty("happy"));
            this.strLvl = Integer.parseInt(prop.getProperty("str"));
            this.conLvl = Integer.parseInt(prop.getProperty("con"));
            this.aglLvl = Integer.parseInt(prop.getProperty("agl"));
            this.level = Integer.parseInt(prop.getProperty("lvl"));
            this.monNumber = Integer.parseInt(prop.getProperty("MonsterNum"));
            this.currentFood = Integer.parseInt(prop.getProperty("food"));

            Evolution evo = new Evolution(this);
            evo.EvolveMonList(monNumber);
            UpdateMonsterPic();

            if(level > 0){
                maxHunger = 5 * level;
                maxHappiness = 10 * level;
                maxExp = 20 * level;
            }

        }
        
        
        
        
        
    }
    
    public void SaveMonster(Properties prop, OutputStream output) throws IOException{
        
        try {
            output = new FileOutputStream("player.properties");
            
            prop.setProperty("name", name);
            prop.setProperty("hunger", String.valueOf(hunger));
            prop.setProperty("happy", String.valueOf(happiness));
            prop.setProperty("str", String.valueOf(strLvl));
            prop.setProperty("con", String.valueOf(conLvl));
            prop.setProperty("agl", String.valueOf(aglLvl));
            prop.setProperty("lvl", String.valueOf(level));
            prop.setProperty("MonsterNum", String.valueOf(monNumber));
            prop.setProperty("food", String.valueOf(currentFood));
            
            prop.store(output, null);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainSetup.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
}
