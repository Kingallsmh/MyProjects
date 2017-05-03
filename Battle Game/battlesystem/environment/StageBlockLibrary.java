/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mb.battlesystem.environment;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author Kyle
 */
public class StageBlockLibrary {
    Texture desertImg = new Texture("DesertStage.png");
    Texture forestImg = new Texture("ForestStage.png");
    int s = 16;
    
    
    public enum DesertBlock{
        GROUND, GROUNDFILL, SQUARE, ENDL, MIDDLE, ENDR, VENDTOP, VMIDDLE, VENDBOT, 
        TallPoleDetail, CryptBoxDetail, BrokenPoleDetail,
        SMALLPLATL, SMALLPLATMID, SMALLPLATR, VSMALLTOP, VSMALLMID, VSMALLBOT, 
        BRICKSQUARE, BRICKENDL, BRICKMID, BRICKENDR, BRICKVENDTOP, BRICKVMIDDLE, BRICKVENDBOT, PACKEDBRICK
    }
    
    public enum ForestBlock{
        GROUND, GROUNDENDR, GROUNDENDL, GROUNDFILL, SQUARE, ENDL, MIDDLE, ENDR, VENDTOP, VMIDDLE, VENDBOT,
        LOGENDL, LOGMID, LOGENDR, StoneWallDetail, BrokenTreeDetail , PLATENDL, PLATMID, PLATENDR
    }
    
    public StageBlockLibrary(){
    }
    
    public StageBlock GetForestStageBlock(ForestBlock block, float x, float y){
        StageBlock b = new StageBlock();
        
        switch(block){
            case GROUND: b.setImg(GetBlockImg(forestImg, 2, 2, 2, 2));
                b.setHitbox(new Rectangle(x, y, 2*s, 2*s));
                break;
            case GROUNDFILL: b.setImg(GetBlockImg(forestImg, 2, 4, 2, 2));
                b.setHitbox(new Rectangle(x, y, 2*s, 2*s));
                break;
            case GROUNDENDL: b.setImg(GetBlockImg(forestImg, 4, 6, 2, 2));
                b.setHitbox(new Rectangle(x, y, 2*s, 2*s));
                break;
            case GROUNDENDR: b.setImg(GetBlockImg(forestImg, 2, 6, 2, 2));
                b.setHitbox(new Rectangle(x, y, 2*s, 2*s));
                break;
            case SQUARE: b.setImg(GetBlockImg(forestImg, 0, 0, 2, 2));
                b.setHitbox(new Rectangle(x, y, 2*s, 2*s));
                break;
            case ENDL: b.setImg(GetBlockImg(forestImg, 2, 0, 2, 2));
                b.setHitbox(new Rectangle(x, y, 2*s, 2*s));
                break;
            case MIDDLE: b.setImg(GetBlockImg(forestImg, 4, 0, 2, 2));
                b.setHitbox(new Rectangle(x, y, 2*s, 2*s));
                break;
            case ENDR: b.setImg(GetBlockImg(forestImg, 6, 0, 2, 2));
                b.setHitbox(new Rectangle(x, y, 2*s, 2*s));
                break;
            case VENDTOP: b.setImg(GetBlockImg(forestImg, 0, 2, 2, 2));
                b.setHitbox(new Rectangle(x, y, 2*s, 2*s));
                break;
            case VMIDDLE: b.setImg(GetBlockImg(forestImg, 0, 4, 2, 2));
                b.setHitbox(new Rectangle(x, y, 2*s, 2*s));
                break;
            case VENDBOT: b.setImg(GetBlockImg(forestImg, 0, 6, 2, 2));
                b.setHitbox(new Rectangle(x, y, 2*s, 2*s));
                break;
            case LOGENDL: b.setImg(GetBlockImg(forestImg, 8, 0, 2, 2));
                b.setHitbox(new Rectangle(x, y, 2*s, 2*s));
                break;
            case LOGMID: b.setImg(GetBlockImg(forestImg, 10, 0, 2, 2));
                b.setHitbox(new Rectangle(x, y, 2*s, 2*s));
                break;
            case LOGENDR: b.setImg(GetBlockImg(forestImg, 12, 0, 2, 2));
                b.setHitbox(new Rectangle(x, y, 2*s, 2*s));
                break;
            case StoneWallDetail: b.setImg(GetBlockImg(forestImg, 8, 2, 2, 6));
                b.setHitbox(new Rectangle(x, y, 2*s, 6*s));
                break;
            case BrokenTreeDetail: b.setImg(GetBlockImg(forestImg, 4, 2, 1, 2));
                b.setHitbox(new Rectangle(x, y, 1*s, 2*s));
                break;
            case PLATENDL: b.setImg(GetBlockImg(forestImg, 5, 2, 1, 1));
                b.setHitbox(new Rectangle(x, y, 1*s, 1*s));
                break;
            case PLATMID: b.setImg(GetBlockImg(forestImg, 6, 2, 1, 1));
                b.setHitbox(new Rectangle(x, y, 1*s, 1*s));
                break;
            case PLATENDR: b.setImg(GetBlockImg(forestImg, 7, 2, 1, 1));
                b.setHitbox(new Rectangle(x, y, 1*s, 1*s));
                break;
        }
        return b;
    }
    
    public StageBlock GetDesertStageBlock(DesertBlock block, float x, float y){
        StageBlock b = new StageBlock();
        
        switch(block){
            case GROUND: b.setImg(GetBlockImg(desertImg, 4, 6, 2, 2));
                b.setHitbox(new Rectangle(x, y, 2*s, 2*s));
                break;
            case GROUNDFILL: b.setImg(GetBlockImg(desertImg, 2, 6, 2, 2));
                b.setHitbox(new Rectangle(x, y, 2*s, 2*s));
                break;
            case SQUARE: b.setImg(GetBlockImg(desertImg, 0, 0, 2, 2));
                b.setHitbox(new Rectangle(x, y, 2*s, 2*s));
                break;
            case ENDL: b.setImg(GetBlockImg(desertImg, 2, 0, 2, 2));
                b.setHitbox(new Rectangle(x, y, 2*s, 2*s));
                break;
            case MIDDLE: b.setImg(GetBlockImg(desertImg, 4, 0, 2, 2));
                b.setHitbox(new Rectangle(x, y, 2*s, 2*s));
                break;
            case ENDR: b.setImg(GetBlockImg(desertImg, 6, 0, 2, 2));
                b.setHitbox(new Rectangle(x, y, 2*s, 2*s));
                break;
            case SMALLPLATL: b.setImg(GetBlockImg(desertImg, 2, 2, 1, 1));
                b.setHitbox(new Rectangle(x, y, s, s));
                break;
            case SMALLPLATMID: b.setImg(GetBlockImg(desertImg, 3, 2, 1, 1));
                b.setHitbox(new Rectangle(x, y, s, s));
                break;
            case SMALLPLATR: b.setImg(GetBlockImg(desertImg, 4, 2, 1, 1));
                b.setHitbox(new Rectangle(x, y, s, s));
                break;   
            case TallPoleDetail: b.setImg(GetBlockImg(desertImg, 6, 2, 1, 6));
                b.setHitbox(new Rectangle(x, y, s, 6*s));
                break; 
            case CryptBoxDetail: b.setImg(GetBlockImg(desertImg, 3, 3, 2, 2));
                b.setHitbox(new Rectangle(x, y, 2*s, 2*s));
                break;
            case BrokenPoleDetail: b.setImg(GetBlockImg(desertImg, 5, 3, 1, 2));
                b.setHitbox(new Rectangle(x, y, 1*s, 2*s));
                break;
            case VENDTOP: b.setImg(GetBlockImg(desertImg, 0, 2, 2, 2));
                b.setHitbox(new Rectangle(x, y, 2*s, 2*s));
                break;
            case VENDBOT: b.setImg(GetBlockImg(desertImg, 0, 6, 2, 2));
                b.setHitbox(new Rectangle(x, y, 2*s, 2*s));
                break;
            case VMIDDLE: b.setImg(GetBlockImg(desertImg, 0, 4, 2, 2));
                b.setHitbox(new Rectangle(x, y, 2*s, 2*s));
                break;
            case VSMALLTOP: b.setImg(GetBlockImg(desertImg, 2, 3, 1, 1));
                b.setHitbox(new Rectangle(x, y, 1*s, 1*s));
                break;
            case VSMALLMID: b.setImg(GetBlockImg(desertImg, 2, 4, 1, 1));
                b.setHitbox(new Rectangle(x, y, 1*s, 1*s));
                break;
            case VSMALLBOT: b.setImg(GetBlockImg(desertImg, 2, 5, 1, 1));
                b.setHitbox(new Rectangle(x, y, 1*s, 1*s));
                break;
            case BRICKSQUARE: b.setImg(GetBlockImg(desertImg, 8, 0, 2, 2));
                b.setHitbox(new Rectangle(x, y, 2*s, 2*s));
                break;
            case BRICKENDL: b.setImg(GetBlockImg(desertImg, 10, 0, 2, 2));
                b.setHitbox(new Rectangle(x, y, 2*s, 2*s));
                break;
            case BRICKMID: b.setImg(GetBlockImg(desertImg, 12, 0, 2, 2));
                b.setHitbox(new Rectangle(x, y, 2*s, 2*s));
                break;
            case BRICKENDR: b.setImg(GetBlockImg(desertImg, 14, 0, 2, 2));
                b.setHitbox(new Rectangle(x, y, 2*s, 2*s));
                break;
            case BRICKVENDTOP: b.setImg(GetBlockImg(desertImg, 8, 2, 2, 2));
                b.setHitbox(new Rectangle(x, y, 2*s, 2*s));
                break;
            case BRICKVMIDDLE: b.setImg(GetBlockImg(desertImg, 8, 4, 2, 2));
                b.setHitbox(new Rectangle(x, y, 2*s, 2*s));
                break;
            case BRICKVENDBOT: b.setImg(GetBlockImg(desertImg, 8, 6, 2, 2));
                b.setHitbox(new Rectangle(x, y, 2*s, 2*s));
                break;
            case PACKEDBRICK: b.setImg(GetBlockImg(desertImg, 10, 2, 2, 1));
                b.setHitbox(new Rectangle(x, y, 2*s, 1*s));
                break;
        }
        return b;
    }
    
    //DO NOT USE FLOATS
    public TextureRegion GetBlockImg(Texture img, int x, int y, int width, int height){
        return new TextureRegion(img, x*s, y*s, width*s, height*s);
    }
}
