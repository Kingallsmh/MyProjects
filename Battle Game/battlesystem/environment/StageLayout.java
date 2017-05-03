/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mb.battlesystem.environment;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mb.battlesystem.environment.AnimatedBGLayout.Pattern;
import com.mb.battlesystem.environment.StageBlockLibrary.DesertBlock;
import com.mb.battlesystem.environment.StageBlockLibrary.ForestBlock;
import com.mb.entities.battle.Beast;
import java.util.ArrayList;

/**
 *
 * @author Kyle
 */
public class StageLayout {
    private AnimatedBackground AB;
    private float gravity = 8; //Force like underwater = 4, Normal = 8, Heavy = 10
    private ArrayList<StageBlock> floor;
    StageBlockLibrary blockLib;
    private Texture BGImg;
    private Vector2[] start1;
    private Vector2[] start2;
    private Rectangle[] StageContainer;
    
    public enum StageType{
        Desert, Forest
    }
    
    public StageLayout(StageType type, int layoutNum){
        blockLib = new StageBlockLibrary();
        floor = new ArrayList<StageBlock>();
        switch(type){
            case Desert: ChooseDesertStage(layoutNum);
                break;
            case Forest: ChooseForestStage(layoutNum);
                break;
        }
    }
    
    public void ChooseForestStage(int layoutNum){
        switch(layoutNum){
            case 0: SetupForestStageDefault();
                BGImg = new Texture("ForestBackground.png");
                AB = new AnimatedBackground(Pattern.FallingLeafs);
                start1 = new Vector2[2];
                start2 = new Vector2[2];
                start1[0] = new Vector2(10, 64);
                start1[1] = new Vector2(74, 64);
                start2[1] = new Vector2(14*32 - 10, 64);
                start2[0] = new Vector2(14*32 - 74, 64);
                this.StageContainer = new Rectangle[4];
                StageContainer[0] = new Rectangle(0, -64, 480, 64);
                StageContainer[1] = new Rectangle(480, 0, 64, 320);
                StageContainer[2] = new Rectangle(0, 320, 480, 64);
                StageContainer[3] = new Rectangle(-64, 0, 64, 320);
                break;
            case 1: SetupForestStage1();
                BGImg = new Texture("ForestBackground.png");
                AB = new AnimatedBackground(Pattern.FallingLeafs);
                start1 = new Vector2[2];
                start2 = new Vector2[2];
                start1[0] = new Vector2(42, 32);
                start1[1] = new Vector2(74, 32);
                start2[1] = new Vector2(14*32 - 42, 64);
                start2[0] = new Vector2(14*32 - 74, 64);
                this.StageContainer = new Rectangle[4];
                StageContainer[0] = new Rectangle(0, -64, 480, 64);
                StageContainer[1] = new Rectangle(480, 0, 64, 320);
                StageContainer[2] = new Rectangle(0, 320, 480, 64);
                StageContainer[3] = new Rectangle(-64, 0, 64, 320);
                break;
        }
    }
    
    public void SetupForestStageDefault(){
        for(int i = 0; i < 15; i ++){
            floor.add(blockLib.GetForestStageBlock(ForestBlock.GROUND, i*32, 32));
            floor.add(blockLib.GetForestStageBlock(ForestBlock.GROUNDFILL, i*32, 0));
        }
    }
    
    public void SetupForestStage1(){
        floor.add(blockLib.GetForestStageBlock(ForestBlock.StoneWallDetail, 0*32, 1*32));
        floor.add(blockLib.GetForestStageBlock(ForestBlock.VENDBOT, 0*32, 4*32));
        floor.add(blockLib.GetForestStageBlock(ForestBlock.VMIDDLE, 0*32, 5*32));
        floor.add(blockLib.GetForestStageBlock(ForestBlock.VENDTOP, 0*32, 6*32));
        floor.add(blockLib.GetForestStageBlock(ForestBlock.VENDBOT, 0*32, 7*32));
        floor.add(blockLib.GetForestStageBlock(ForestBlock.VENDTOP, 0*32, 8*32));
        
        floor.add(blockLib.GetForestStageBlock(ForestBlock.BrokenTreeDetail, 1*32, 5*32));
        floor.add(blockLib.GetForestStageBlock(ForestBlock.ENDL, 1*32, 4*32));
        floor.add(blockLib.GetForestStageBlock(ForestBlock.ENDR, 2*32, 4*32));
        floor.add(blockLib.GetForestStageBlock(ForestBlock.PLATENDL, 1*32, 4*32-16));
        floor.add(blockLib.GetForestStageBlock(ForestBlock.PLATMID, 1*32+16, 4*32-16));
        floor.add(blockLib.GetForestStageBlock(ForestBlock.PLATENDR, 2*32, 4*32-16));
        floor.add(blockLib.GetForestStageBlock(ForestBlock.PLATENDL, 2*32+16, 4*32-16));
        floor.add(blockLib.GetForestStageBlock(ForestBlock.PLATMID, 3*32, 4*32-16));
        floor.add(blockLib.GetForestStageBlock(ForestBlock.PLATENDR, 3*32+16, 4*32-16));
        
        floor.add(blockLib.GetForestStageBlock(ForestBlock.SQUARE, 6*32, 5*32));
        floor.add(blockLib.GetForestStageBlock(ForestBlock.PLATENDL, 7*32, 5*32+16));
        floor.add(blockLib.GetForestStageBlock(ForestBlock.PLATENDR, 7*32+16, 5*32+16));
        floor.add(blockLib.GetForestStageBlock(ForestBlock.SQUARE, 8*32, 5*32));
        
        for(int i = 0; i < 5; i ++){
            floor.add(blockLib.GetForestStageBlock(ForestBlock.GROUND, i*32, 0));
        }
        floor.add(blockLib.GetForestStageBlock(ForestBlock.ENDL, 0*32, 9*32));
        floor.add(blockLib.GetForestStageBlock(ForestBlock.MIDDLE, 1*32, 9*32));
        floor.add(blockLib.GetForestStageBlock(ForestBlock.MIDDLE, 2*32, 9*32));
        floor.add(blockLib.GetForestStageBlock(ForestBlock.MIDDLE, 3*32, 9*32));
        floor.add(blockLib.GetForestStageBlock(ForestBlock.MIDDLE, 4*32, 9*32));
        floor.add(blockLib.GetForestStageBlock(ForestBlock.MIDDLE, 5*32, 9*32));
        floor.add(blockLib.GetForestStageBlock(ForestBlock.ENDR, 6*32, 9*32));
        floor.add(blockLib.GetForestStageBlock(ForestBlock.VENDBOT, 7*32, 9*32));
        floor.add(blockLib.GetForestStageBlock(ForestBlock.ENDL, 8*32, 9*32));
        floor.add(blockLib.GetForestStageBlock(ForestBlock.MIDDLE, 9*32, 9*32));
        floor.add(blockLib.GetForestStageBlock(ForestBlock.MIDDLE, 10*32, 9*32));
        floor.add(blockLib.GetForestStageBlock(ForestBlock.MIDDLE, 11*32, 9*32));
        floor.add(blockLib.GetForestStageBlock(ForestBlock.MIDDLE, 12*32, 9*32));
        floor.add(blockLib.GetForestStageBlock(ForestBlock.MIDDLE, 13*32, 9*32));
        floor.add(blockLib.GetForestStageBlock(ForestBlock.ENDR, 14*32, 9*32));
        
        floor.add(blockLib.GetForestStageBlock(ForestBlock.GROUNDENDR, 5*32, 0));
        floor.add(blockLib.GetForestStageBlock(ForestBlock.LOGENDL, 5*32, 32));
        floor.add(blockLib.GetForestStageBlock(ForestBlock.LOGMID, 6*32, 32));
        floor.add(blockLib.GetForestStageBlock(ForestBlock.LOGMID, 7*32, 32));
        floor.add(blockLib.GetForestStageBlock(ForestBlock.LOGMID, 8*32, 32));
        floor.add(blockLib.GetForestStageBlock(ForestBlock.LOGENDR, 9*32, 32));
        floor.add(blockLib.GetForestStageBlock(ForestBlock.GROUNDENDL, 9*32, 0));
        for(int i = 10; i < 15; i ++){
            floor.add(blockLib.GetForestStageBlock(ForestBlock.GROUND, i*32, 0));
        }
        
        floor.add(blockLib.GetForestStageBlock(ForestBlock.StoneWallDetail, 14*32, 1*32));
        floor.add(blockLib.GetForestStageBlock(ForestBlock.VENDBOT, 14*32, 4*32));
        floor.add(blockLib.GetForestStageBlock(ForestBlock.VMIDDLE, 14*32, 5*32));
        floor.add(blockLib.GetForestStageBlock(ForestBlock.VENDTOP, 14*32, 6*32));
        floor.add(blockLib.GetForestStageBlock(ForestBlock.VENDBOT, 14*32, 7*32));
        floor.add(blockLib.GetForestStageBlock(ForestBlock.VENDTOP, 14*32, 8*32));
        
        floor.add(blockLib.GetForestStageBlock(ForestBlock.BrokenTreeDetail, 13*32+16, 5*32));
        floor.add(blockLib.GetForestStageBlock(ForestBlock.ENDL, 12*32, 4*32));
        floor.add(blockLib.GetForestStageBlock(ForestBlock.ENDR, 13*32, 4*32));
        floor.add(blockLib.GetForestStageBlock(ForestBlock.PLATENDL, 11*32, 4*32-16));
        floor.add(blockLib.GetForestStageBlock(ForestBlock.PLATMID, 11*32+16, 4*32-16));
        floor.add(blockLib.GetForestStageBlock(ForestBlock.PLATENDR, 12*32, 4*32-16));
        floor.add(blockLib.GetForestStageBlock(ForestBlock.PLATENDL, 12*32+16, 4*32-16));
        floor.add(blockLib.GetForestStageBlock(ForestBlock.PLATMID, 13*32, 4*32-16));
        floor.add(blockLib.GetForestStageBlock(ForestBlock.PLATENDR, 13*32+16, 4*32-16));
    }
    
    public void ChooseDesertStage(int layoutNum){
        switch(layoutNum){
            case 0: SetupDesertStageDefault();
                BGImg = new Texture("TempleBackground.png");
                start1 = new Vector2[2];
                start2 = new Vector2[2];
                start1[0] = new Vector2(10, 64);
                start1[1] = new Vector2(74, 64);
                start2[1] = new Vector2(14*32 - 10, 64);
                start2[0] = new Vector2(14*32 - 74, 64);
                this.StageContainer = new Rectangle[4];
                StageContainer[0] = new Rectangle(0, -64, 480, 64);
                StageContainer[1] = new Rectangle(480, 0, 64, 320);
                StageContainer[2] = new Rectangle(0, 320, 480, 64);
                StageContainer[3] = new Rectangle(-64, 0, 64, 320);
                break;
            case 1: SetupDesertStage1();
                BGImg = new Texture("TempleBackground.png");
                start1 = new Vector2[2];
                start2 = new Vector2[2];
                start1[0] = new Vector2(26, 32);
                start1[1] = new Vector2(74, 64);
                start2[1] = new Vector2(14*32 - 26, 64);
                start2[0] = new Vector2(14*32 - 74, 64);
                this.StageContainer = new Rectangle[4];
                StageContainer[0] = new Rectangle(0, -64, 480, 64);
                StageContainer[1] = new Rectangle(480, 0, 64, 320);
                StageContainer[2] = new Rectangle(0, 320, 480, 64);
                StageContainer[3] = new Rectangle(-64, 0, 64, 320);
                break;
        }
    }
    public void SetupDesertStageDefault(){
        for(int i = 0; i < 15; i ++){
            floor.add(blockLib.GetDesertStageBlock(DesertBlock.GROUND, i*32, 32));
            floor.add(blockLib.GetDesertStageBlock(DesertBlock.GROUNDFILL, i*32, 0));
        }
    }
    
    //Fits 32 * 32 through all parts
    public void SetupDesertStage1(){
        for(int i = 0; i < 10; i ++){
            floor.add(blockLib.GetDesertStageBlock(StageBlockLibrary.DesertBlock.GROUND, i*32, 0));
        }
        floor.add(blockLib.GetDesertStageBlock(StageBlockLibrary.DesertBlock.TallPoleDetail, 0, 1*32));
        floor.add(blockLib.GetDesertStageBlock(StageBlockLibrary.DesertBlock.SMALLPLATL, 0, 4*32));
        floor.add(blockLib.GetDesertStageBlock(StageBlockLibrary.DesertBlock.SMALLPLATMID, 16, 4*32));
        floor.add(blockLib.GetDesertStageBlock(StageBlockLibrary.DesertBlock.SMALLPLATR, 32, 4*32));
        floor.add(blockLib.GetDesertStageBlock(StageBlockLibrary.DesertBlock.CryptBoxDetail, 3*32, 1*32));
        floor.add(blockLib.GetDesertStageBlock(StageBlockLibrary.DesertBlock.VENDTOP, 4*32, 3*32));
        floor.add(blockLib.GetDesertStageBlock(StageBlockLibrary.DesertBlock.VMIDDLE, 4*32, 2*32));
        floor.add(blockLib.GetDesertStageBlock(StageBlockLibrary.DesertBlock.VENDBOT, 4*32, 1*32));
        floor.add(blockLib.GetDesertStageBlock(StageBlockLibrary.DesertBlock.BrokenPoleDetail, 5*32, 1*32));
        for(int i = 10; i < 15; i++){
            floor.add(blockLib.GetDesertStageBlock(StageBlockLibrary.DesertBlock.SQUARE, i*32, 0));
        }
        floor.add(blockLib.GetDesertStageBlock(StageBlockLibrary.DesertBlock.VSMALLTOP, 8*32, 4*32));
        floor.add(blockLib.GetDesertStageBlock(StageBlockLibrary.DesertBlock.VSMALLMID, 8*32, 3*32 + 16));
        floor.add(blockLib.GetDesertStageBlock(StageBlockLibrary.DesertBlock.VSMALLBOT, 8*32, 3*32));
        floor.add(blockLib.GetDesertStageBlock(StageBlockLibrary.DesertBlock.BRICKSQUARE, 9*32-16, 4*32));
        floor.add(blockLib.GetDesertStageBlock(StageBlockLibrary.DesertBlock.BRICKENDL, 10*32-16, 4*32));
        floor.add(blockLib.GetDesertStageBlock(StageBlockLibrary.DesertBlock.BRICKMID, 11*32-16, 4*32));
        floor.add(blockLib.GetDesertStageBlock(StageBlockLibrary.DesertBlock.BRICKENDR, 12*32-16, 4*32));
        floor.add(blockLib.GetDesertStageBlock(StageBlockLibrary.DesertBlock.SMALLPLATL, 9*32, 1*32));
        floor.add(blockLib.GetDesertStageBlock(StageBlockLibrary.DesertBlock.SMALLPLATR, 9*32+16, 1*32));
        floor.add(blockLib.GetDesertStageBlock(StageBlockLibrary.DesertBlock.ENDL, 10*32, 1*32));
        floor.add(blockLib.GetDesertStageBlock(StageBlockLibrary.DesertBlock.MIDDLE, 11*32, 1*32));
        floor.add(blockLib.GetDesertStageBlock(StageBlockLibrary.DesertBlock.MIDDLE, 12*32, 1*32));
        floor.add(blockLib.GetDesertStageBlock(StageBlockLibrary.DesertBlock.MIDDLE, 13*32, 1*32));
        floor.add(blockLib.GetDesertStageBlock(StageBlockLibrary.DesertBlock.ENDR, 14*32, 1*32));
        
        floor.add(blockLib.GetDesertStageBlock(StageBlockLibrary.DesertBlock.BRICKVENDTOP, 14*32, 4*32));
        floor.add(blockLib.GetDesertStageBlock(StageBlockLibrary.DesertBlock.BRICKVMIDDLE, 14*32, 3*32));
        floor.add(blockLib.GetDesertStageBlock(StageBlockLibrary.DesertBlock.BRICKVENDBOT, 14*32, 2*32));
        floor.add(blockLib.GetDesertStageBlock(StageBlockLibrary.DesertBlock.BRICKVENDTOP, 14*32, 7*32));
        floor.add(blockLib.GetDesertStageBlock(StageBlockLibrary.DesertBlock.BRICKVMIDDLE, 14*32, 6*32));
        floor.add(blockLib.GetDesertStageBlock(StageBlockLibrary.DesertBlock.BRICKVENDBOT, 14*32, 5*32));
        floor.add(blockLib.GetDesertStageBlock(StageBlockLibrary.DesertBlock.BRICKVENDTOP, 14*32, 9*32));
        floor.add(blockLib.GetDesertStageBlock(StageBlockLibrary.DesertBlock.BRICKVENDBOT, 14*32, 8*32));
        floor.add(blockLib.GetDesertStageBlock(StageBlockLibrary.DesertBlock.BRICKENDR, 13*32, 9*32));
        floor.add(blockLib.GetDesertStageBlock(StageBlockLibrary.DesertBlock.BRICKMID, 12*32, 9*32));
        floor.add(blockLib.GetDesertStageBlock(StageBlockLibrary.DesertBlock.BRICKMID, 11*32, 9*32));
        floor.add(blockLib.GetDesertStageBlock(StageBlockLibrary.DesertBlock.BRICKMID, 10*32, 9*32));
        floor.add(blockLib.GetDesertStageBlock(StageBlockLibrary.DesertBlock.BRICKENDL, 9*32, 9*32));
        floor.add(blockLib.GetDesertStageBlock(StageBlockLibrary.DesertBlock.BRICKENDR, 8*32, 9*32));
        floor.add(blockLib.GetDesertStageBlock(StageBlockLibrary.DesertBlock.BRICKMID, 7*32, 9*32));
        floor.add(blockLib.GetDesertStageBlock(StageBlockLibrary.DesertBlock.BRICKENDL, 6*32, 9*32));
        floor.add(blockLib.GetDesertStageBlock(StageBlockLibrary.DesertBlock.BRICKENDR, 5*32, 9*32));
        floor.add(blockLib.GetDesertStageBlock(StageBlockLibrary.DesertBlock.BRICKMID, 4*32, 9*32));
        floor.add(blockLib.GetDesertStageBlock(StageBlockLibrary.DesertBlock.BRICKENDL, 3*32, 9*32));
        floor.add(blockLib.GetDesertStageBlock(StageBlockLibrary.DesertBlock.BRICKENDR, 2*32, 9*32));
        floor.add(blockLib.GetDesertStageBlock(StageBlockLibrary.DesertBlock.BRICKENDL, 1*32, 9*32));
        floor.add(blockLib.GetDesertStageBlock(StageBlockLibrary.DesertBlock.BRICKVENDTOP, 0*32, 9*32));
        floor.add(blockLib.GetDesertStageBlock(StageBlockLibrary.DesertBlock.BRICKVENDBOT, 0*32, 8*32));
        floor.add(blockLib.GetDesertStageBlock(StageBlockLibrary.DesertBlock.SMALLPLATL, 0*32, 8*32-16));
        floor.add(blockLib.GetDesertStageBlock(StageBlockLibrary.DesertBlock.SMALLPLATR, 1*32-16, 8*32-16));
        floor.add(blockLib.GetDesertStageBlock(StageBlockLibrary.DesertBlock.TallPoleDetail, 0*32, 5*32-16));
        
        floor.add(blockLib.GetDesertStageBlock(StageBlockLibrary.DesertBlock.PACKEDBRICK, 7*32, 6*32+16));
        floor.add(blockLib.GetDesertStageBlock(StageBlockLibrary.DesertBlock.PACKEDBRICK, 6*32, 6*32+16));
        floor.add(blockLib.GetDesertStageBlock(StageBlockLibrary.DesertBlock.PACKEDBRICK, 5*32, 6*32+16));
        
    }
    
    public ArrayList<Rectangle> GetStageHitboxs(){
        ArrayList<Rectangle> boxList = new ArrayList<Rectangle>();
        for(int i = 0; i < getFloor().size(); i++){
            boxList.add(getFloor().get(i).getHitbox());
        }
        return boxList;
    }
    
    public void SetBeastLocation(Beast beast, int spot){
        switch(spot){
            case 0: beast.SetLocation(start1[0].x, start1[0].y);
                break;
            case 1: beast.SetLocation(start1[1].x, start1[1].y);
                break;
            case 2: beast.SetLocation(start2[0].x, start2[0].y);
                break;
            case 3: beast.SetLocation(start2[1].x, start2[1].y);
                break;
        }
    }

    /**
     * @return the floor
     */
    public ArrayList<StageBlock> getFloor() {
        return floor;
    }

    /**
     * @param floor the floor to set
     */
    public void setFloor(ArrayList<StageBlock> floor) {
        this.floor = floor;
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

    /**
     * @return the BGImg
     */
    public Texture getBGImg() {
        return BGImg;
    }

    /**
     * @param BGImg the BGImg to set
     */
    public void setBGImg(Texture BGImg) {
        this.BGImg = BGImg;
    }

    /**
     * @return the StageContainer
     */
    public Rectangle[] getStageContainer() {
        return StageContainer;
    }

    /**
     * @param StageContainer the StageContainer to set
     */
    public void setStageContainer(Rectangle[] StageContainer) {
        this.StageContainer = StageContainer;
    }

    /**
     * @return the AB
     */
    public AnimatedBackground getAB() {
        return AB;
    }

    /**
     * @param AB the AB to set
     */
    public void setAB(AnimatedBackground AB) {
        this.AB = AB;
    }

    /**
     * @return the start1
     */
    public Vector2[] getStart1() {
        return start1;
    }

    /**
     * @return the start2
     */
    public Vector2[] getStart2() {
        return start2;
    }
}
