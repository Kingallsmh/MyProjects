/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mb.entities.overworld;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author Kyle
 */
public class WorldCharLibrary {
    int s = 32;
    Texture charSheet = new Texture("CharacterSheet.png");
    
    public enum WorldCharacters{
        Main
    }
    
    public WorldCharacter CreateWorldChar(WorldCharacters name){
        WorldCharacter c = new WorldCharacter();
        switch(name){
            case Main: c.setWalkSImg(GetImgGroups(0, 0, s));
                c.setWalkNImg(GetImgGroups(0, 1, s));
                c.setWalkWImg(GetImgGroups(0, 2, s));
                c.setWalkEImg(GetImgGroups(0, 3, s));
                c.setCurrentImg(c.getWalkSImg()[0]);
                c.setHitbox(new Rectangle(0, 0, 12, 10));
        }
        return c;
    }
    
    public TextureRegion[] GetImgGroups(int x, int y, int size){
        TextureRegion[] imgList = new TextureRegion[2];
        for(int i = 0; i < imgList.length; i++){
            imgList[i] = new TextureRegion(charSheet, x*size + i*size, y*size, size, size);
        }
        return imgList;
    }
}
