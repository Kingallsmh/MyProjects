/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mb.world;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author Kyle
 */
public class BlockLibrary {
    int s8 = 8, s16 = 16, s32 = 32;
    Texture fieldMapImg;
    Texture objectImg;
    
    
    public enum BlockName{
        Grass1, Grass2, Grass3, Grass4, GrassAnim, Dirt, WaterAnim, OceanAnim, Stone;
        
        public BlockName getNext() {
            if(this.ordinal() < BlockName.values().length - 1){
                return BlockName.values()[this.ordinal() + 1];
            }
            else{
                return BlockName.values()[this.ordinal()];
            }
        }
        
        public BlockName getPrevious() {
            if(this.ordinal() > 0){
                return BlockName.values()[this.ordinal() - 1];
            }
            else{
                return BlockName.values()[this.ordinal()];
            }
        }
    }
    
    public enum MiniBlockName{
        GrassCovering, MiniGrass, DirtCovering, MiniDirt, StoneCovering, MiniStone, 
        BushBlock, BushInside1, BushInside2, BushInside3, BushInside4, Cliff, CliffInside;
        
        public MiniBlockName getNext() {
            if(this.ordinal() < MiniBlockName.values().length - 1){
                return MiniBlockName.values()[this.ordinal() + 1];
            }
            else{
                return MiniBlockName.values()[this.ordinal()];
            }
        }
        
        public MiniBlockName getPrevious() {
            if(this.ordinal() > 0){
                return MiniBlockName.values()[this.ordinal() - 1];
            }
            else{
                return MiniBlockName.values()[this.ordinal()];
            }
        }
    }
    
    public enum Section{
        TopL, Top1, Top2, TopR,
        Mid1L, Mid11, Mid12, Mid1R,
        Mid2L, Mid21, Mid22, Mid2R,
        BotL, Bot1, Bot2, BotR;
        
        public Section getNext() {
            if(this.ordinal() < Section.values().length - 1){
                return Section.values()[this.ordinal() + 1];
            }
            else{
                return Section.values()[this.ordinal()];
            }
        }
        
        public Section getPrevious() {
            if(this.ordinal() > 0){
                return Section.values()[this.ordinal() - 1];
            }
            else{
                return Section.values()[this.ordinal()];
            }
        }
    }
    
    public enum ObjectName{
        SmallRock1, SmallRock2, Boulder, SmallFlower, Flower, Lilypad, Conch, Shell,
        Tree, AppleTree, Bush, LargeTree;
        
        public ObjectName getNext() {
            if(this.ordinal() < ObjectName.values().length - 1){
                return ObjectName.values()[this.ordinal() + 1];
            }
            else{
                return ObjectName.values()[this.ordinal()];
            }
        }
        
        public ObjectName getPrevious() {
            if(this.ordinal() > 0){
                return ObjectName.values()[this.ordinal() - 1];
            }
            else{
                return ObjectName.values()[this.ordinal()];
            }
        }
    }
    
    public BlockLibrary(){
        fieldMapImg = new Texture("Maps/FieldSet.png");
        objectImg = new Texture("Maps/ObjectSheet.png");
    }
    
    public Block CreateBlock(BlockName blockname, int x, int y){
        Block b = new Block();
        b.setBlockName(blockname);
        switch(blockname){
            case Grass1: b.setHitbox(new Rectangle(x*s16, y*s16, s16, s16));
                b.setImgList(GetBlockImg(fieldMapImg, 0, 0, 1));
                b.setPassable(true);
                break;
            case Grass2: b.setHitbox(new Rectangle(x*s16, y*s16, s16, s16));
                b.setImgList(GetBlockImg(fieldMapImg, 1, 0, 1));
                b.setPassable(true);
                break;
            case Grass3: b.setHitbox(new Rectangle(x*s16, y*s16, s16, s16));
                b.setImgList(GetBlockImg(fieldMapImg, 2, 0, 1));
                b.setPassable(true);
                break;
            case Grass4: b.setHitbox(new Rectangle(x*s16, y*s16, s16, s16));
                b.setImgList(GetBlockImg(fieldMapImg, 3, 0, 1));
                b.setPassable(true);
                break;
            case GrassAnim: b.setHitbox(new Rectangle(x*s16, y*s16, s16, s16));
                b.setImgList(GetBlockImg(fieldMapImg, 4, 0, 2));
                b.setPassable(true);
                b.setMaxTime(0.7f);
                break;
            case Dirt: b.setHitbox(new Rectangle(x*s16, y*s16, s16, s16));
                b.setImgList(GetBlockImg(fieldMapImg, 5, 0, 1));
                b.setPassable(true);
                break;
            case WaterAnim: b.setHitbox(new Rectangle(x*s16, y*s16, s16, s16));
                b.setImgList(GetBlockImg(fieldMapImg, 6, 0, 2));
                b.setPassable(false);
                b.setMaxTime(0.4f);
                break;
            case OceanAnim: b.setHitbox(new Rectangle(x*s16, y*s16, s16, s16));
                b.setImgList(GetBlockImg(fieldMapImg, 7, 0, 2));
                b.setPassable(false);
                b.setMaxTime(0.4f);
                break;
            case Stone: b.setHitbox(new Rectangle(x*s16, y*s16, s16, s16));
                b.setImgList(GetBlockImg(fieldMapImg, 8, 0, 1));
                b.setPassable(true);
                break;
        }
        return b;
    }
    
    public Block CreateBlock(MiniBlockName blockname, Section section, int x, int y){
        Block b = new Block();
        b.setHitbox(new Rectangle(x*s8, y*s8, s8, s8));
        b.setMiniBlockName(blockname);
        b.setSection(section);
        b.setPassable(true);
        switch(blockname){
            case MiniGrass: b.setImgList(GetMiniBlock(fieldMapImg, 0, 0, 1));
                break;
            case MiniDirt: b.setImgList(GetMiniBlock(fieldMapImg, 10, 0, 1));
                break;
            case MiniStone: b.setImgList(GetMiniBlock(fieldMapImg, 16, 0, 1));
                break;
            case BushInside1: b.setImgList(GetMiniBlock(fieldMapImg, 4, 8, 1));
                break;
            case BushInside2: b.setImgList(GetMiniBlock(fieldMapImg, 5, 8, 1));
                break;
            case BushInside3: b.setImgList(GetMiniBlock(fieldMapImg, 4, 9, 1));
                break;
            case BushInside4: b.setImgList(GetMiniBlock(fieldMapImg, 5, 9, 1));
                break;
            case CliffInside: b.setImgList(GetMiniBlock(fieldMapImg, 10, 8, 1));
                break;
            
            case GrassCovering: b.setPassable(true);
                b.setImgList(this.GetCoveringSection(section, 0, 4));
                break;
            case DirtCovering: b.setPassable(true);
                b.setImgList(this.GetCoveringSection(section, 4, 4));
                break;
            case StoneCovering: b.setPassable(true);
                b.setImgList(this.GetCoveringSection(section, 8, 4));
                break;
            case BushBlock: b.setPassable(false);
                b.setImgList(this.GetCoveringSection(section, 0, 8));
                break;
            case Cliff: b.setPassable(false);
                b.setImgList(this.GetCoveringSection(section, 6, 8));
                break;
        }
        return b;
    }
    
    public Block CreateBlock(ObjectName blockname, int x, int y){
        Block b = null;
        switch(blockname){
            case SmallRock1: b = new Block(1);
                b.getImgList()[0] = GetObjectImg(objectImg, 0, 0, 2, 2);
                b.setHitbox(new Rectangle(x*s8, y*s8, s8*2, s8*2));
                b.setPassable(true);
                break;
            case SmallRock2: b = new Block(1);
                b.getImgList()[0] = GetObjectImg(objectImg, 2, 0, 2, 2);
                b.setHitbox(new Rectangle(x*s8, y*s8, s8*2, s8*2));
                b.setPassable(true);
                break;
            case Boulder: b = new Block(1);
                b.getImgList()[0] = GetObjectImg(objectImg, 0, 2, 2, 2);
                b.setHitbox(new Rectangle(x*s8, y*s8, s8*2, s8));
                b.setPassable(false);
                break;
            case SmallFlower: b = new Block(1);
                b.getImgList()[0] = GetObjectImg(objectImg, 2, 2, 2, 2);
                b.setHitbox(new Rectangle(x*s8, y*s8, s8*2, s8*2));
                b.setPassable(true);
                break;
            case Flower: b = new Block(1);
                b.getImgList()[0] = GetObjectImg(objectImg, 0, 4, 2, 2);
                b.setHitbox(new Rectangle(x*s8, y*s8, s8*2, s8*2));
                b.setPassable(true);
                break;
            case Lilypad: b = new Block(1);
                b.getImgList()[0] = GetObjectImg(objectImg, 2, 4, 2, 2);
                b.setHitbox(new Rectangle(x*s8, y*s8, s8*2, s8*2));
                b.setPassable(true);
                break;
            case Conch: b = new Block(1);
                b.getImgList()[0] = GetObjectImg(objectImg, 0, 6, 2, 2);
                b.setHitbox(new Rectangle(x*s8, y*s8, s8*2, s8*2));
                b.setPassable(true);
                break;
            case Shell: b = new Block(1);
                b.getImgList()[0] = GetObjectImg(objectImg, 2, 6, 2, 2);
                b.setHitbox(new Rectangle(x*s8, y*s8, s8*2, s8*2));
                b.setPassable(true);
                break;
            case Tree: b = new Block(1);
                b.getImgList()[0] = GetObjectImg(objectImg, 4, 0, 4, 4);
                b.setHitbox(new Rectangle(x*s8, y*s8, s8*4, s8*4));
                b.setPassable(false);
                break;
            case AppleTree: b = new Block(1);
                b.getImgList()[0] = GetObjectImg(objectImg, 8, 0, 4, 4);
                b.setHitbox(new Rectangle(x*s8, y*s8, s8*4, s8*4));
                b.setPassable(false);
                break;
            case Bush: b = new Block(1);
                b.getImgList()[0] = GetObjectImg(objectImg, 4, 4, 3, 3);
                b.setHitbox(new Rectangle(x*s8, y*s8, s8*3, s8*2));
                b.setPassable(false);
                break;
            case LargeTree: b = new Block(1);
                b.getImgList()[0] = GetObjectImg(objectImg, 12, 0, 8, 8);
                b.setHitbox(new Rectangle(x*s8, y*s8, s8*4, s8*6));
                b.setPassable(false);
                break;
        }
        b.setObjectName(blockname);
        return b;
    }
    
    public TextureRegion GetObjectImg(Texture img, int x, int y, int width, int height){
        return new TextureRegion(img, s8*x, s8*y, s8*width, s8*height);
    }
    
    public TextureRegion[] GetBlockImg(Texture img, int x, int y, int num){
        TextureRegion[] imgList = new TextureRegion[num];
        for(int i = 0; i < num; i++){
            imgList[i] = new TextureRegion(img, (x*s16), ((y*s16) + (i*s16)), s16, s16);
        }
        return imgList;
    }
    
    public TextureRegion[] GetMiniBlock(Texture img, int x, int y, int num){
        TextureRegion[] imgList = new TextureRegion[num];
        for(int i = 0; i < num; i++){
            imgList[i] = new TextureRegion(img, (x*s8), ((y*s8) + (i*s8)), s8, s8);
        }
        return imgList;
    }
    
    public TextureRegion[] GetCoveringSection(Section section, int x, int y){
        switch(section){
                    case TopL: return GetMiniBlock(fieldMapImg, 0 + x, 0 + y, 1);
                    case Top1: return GetMiniBlock(fieldMapImg, 1 + x, 0 + y, 1);
                    case Top2: return GetMiniBlock(fieldMapImg, 2 + x, 0 + y, 1);
                    case TopR: return GetMiniBlock(fieldMapImg, 3 + x, 0 + y, 1);
                    case Mid1L: return GetMiniBlock(fieldMapImg, 0 + x, 1 + y, 1);
                    case Mid11: return GetMiniBlock(fieldMapImg, 1 + x, 1 + y, 1);
                    case Mid12: return GetMiniBlock(fieldMapImg, 2 + x, 1 + y, 1);
                    case Mid1R: return GetMiniBlock(fieldMapImg, 3 + x, 1 + y, 1);
                    case Mid2L: return GetMiniBlock(fieldMapImg, 0 + x, 2 + y, 1);
                    case Mid21: return GetMiniBlock(fieldMapImg, 1 + x, 2 + y, 1);
                    case Mid22: return GetMiniBlock(fieldMapImg, 2 + x, 2 + y, 1);
                    case Mid2R: return GetMiniBlock(fieldMapImg, 3 + x, 2 + y, 1);
                    case BotL: return GetMiniBlock(fieldMapImg, 0 + x, 3 + y, 1);
                    case Bot1: return GetMiniBlock(fieldMapImg, 1 + x, 3 + y, 1);
                    case Bot2: return GetMiniBlock(fieldMapImg, 2 + x, 3 + y, 1);
                    case BotR: return GetMiniBlock(fieldMapImg, 3 + x, 3 + y, 1);
        }
        return null;
    }
    
    public TextureRegion GetImg(Texture img, int x, int y){
        return new TextureRegion(img, (x*s8), (y*s8), s8, s8);
    }
    
    public Block LoadingImageProcess(Block b){
        if(b.getBlockName() != null){
            int x = (int) (b.getHitbox().x/s16);
            int y = (int) (b.getHitbox().y/s16);
            b = CreateBlock(b.getBlockName(), x, y);
            return b;
        }
        else if(b.getMiniBlockName() != null){
            int x = (int) (b.getHitbox().x/s8);
            int y = (int) (b.getHitbox().y/s8);
            b = CreateBlock(b.getMiniBlockName(), b.getSection(), x, y);
            return b;
        }
        else if(b.getObjectName() != null){
            int x = (int) (b.getHitbox().x/s8);
            int y = (int) (b.getHitbox().y/s8);
            b = CreateBlock(b.getObjectName(), x, y);
            return b;
        }
        return null;
    }
}
