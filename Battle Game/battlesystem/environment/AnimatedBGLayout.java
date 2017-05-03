/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mb.battlesystem.environment;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mb.battlesystem.environment.AnimatedBackground.ItemType;
import java.util.ArrayList;

/**
 *
 * @author Kyle
 */
public class AnimatedBGLayout {
    AnimatedBackground aBG;
    Pattern pattern;
    ArrayList<AnimatedBGItem> itemList;
    
    public enum Pattern{
        FallingLeafs, BlowingLeafs, LightWeather, HardWind
    }
    
    public AnimatedBGLayout(AnimatedBackground aBG, Pattern pattern){
        this.aBG = aBG;
        this.pattern = pattern;
        itemList = new ArrayList<AnimatedBGItem>();
        LoadInItems(pattern);
    }
    
    public void LoadInItems(Pattern pattern){
        switch(pattern){
            case FallingLeafs: itemList.add(aBG.AddItem(pattern, ItemType.BigGreenLeafs, 2*32, 14*32));
                itemList.add(aBG.AddItem(pattern, ItemType.BigGreenLeafs, 4*32, 17*32));
                itemList.add(aBG.AddItem(pattern, ItemType.BigYellowLeafs, 8*32, 10*32));
                itemList.add(aBG.AddItem(pattern, ItemType.BigGreenLeafs, 3*32, 12*32));
                itemList.add(aBG.AddItem(pattern, ItemType.BigGreenLeafs, 4*32, 15*32));
                itemList.add(aBG.AddItem(pattern, ItemType.BigYellowLeafs, 9*32, 11*32));
                itemList.add(aBG.AddItem(pattern, ItemType.BigGreenLeafs, 12*32, 15*32));
                itemList.add(aBG.AddItem(pattern, ItemType.BigGreenLeafs, 4*32, 12*32));
                itemList.add(aBG.AddItem(pattern, ItemType.BigYellowLeafs, 1*32, 11*32));
                itemList.add(aBG.AddItem(pattern, ItemType.BigGreenLeafs, 1*32, 17*32));
                itemList.add(aBG.AddItem(pattern, ItemType.BigGreenLeafs, 14*32, 20*32));
                itemList.add(aBG.AddItem(pattern, ItemType.BigGreenLeafs, 0*32, 16*32));
                itemList.add(aBG.AddItem(pattern, ItemType.BigGreenLeafs, 13*32, 12*32));
                itemList.add(aBG.AddItem(pattern, ItemType.BigGreenLeafs, 0*32, 22*32));
                itemList.add(aBG.AddItem(pattern, ItemType.BigYellowLeafs, 11*32, 16*32));
                itemList.add(aBG.AddItem(pattern, ItemType.BigGreenLeafs, 7*32, 10*32));
                itemList.add(aBG.AddItem(pattern, ItemType.BigGreenLeafs, 4*32, 16*32));
                itemList.add(aBG.AddItem(pattern, ItemType.BigGreenLeafs, 8*32, 12*32));
                itemList.add(aBG.AddItem(pattern, ItemType.BigGreenLeafs, 6*32, 22*32));
                itemList.add(aBG.AddItem(pattern, ItemType.BigYellowLeafs, 5*32, 16*32));
                itemList.add(aBG.AddItem(pattern, ItemType.BigGreenLeafs, 8*32, 10*32));
                itemList.add(aBG.AddItem(pattern, ItemType.SmallGreenLeafs, 6*32, 21*32));
                itemList.add(aBG.AddItem(pattern, ItemType.SmallGreenLeafs, 7*32, 12*32));
                itemList.add(aBG.AddItem(pattern, ItemType.SmallYellowLeafs, 5*32, 16*32));
                itemList.add(aBG.AddItem(pattern, ItemType.SmallGreenLeafs, 4*32, 21*32));
                itemList.add(aBG.AddItem(pattern, ItemType.SmallGreenLeafs, 13*32, 12*32));
                itemList.add(aBG.AddItem(pattern, ItemType.SmallYellowLeafs, 0*32, 16*32));
                itemList.add(aBG.AddItem(pattern, ItemType.SmallGreenLeafs, 11*32, 21*32));
                itemList.add(aBG.AddItem(pattern, ItemType.SmallYellowLeafs, 3*32, 10*32));
                itemList.add(aBG.AddItem(pattern, ItemType.SmallGreenLeafs, 5*32, 21*32));
                itemList.add(aBG.AddItem(pattern, ItemType.SmallGreenLeafs, 10*32, 12*32));
                itemList.add(aBG.AddItem(pattern, ItemType.SmallYellowLeafs, 6*32, 16*32));
                itemList.add(aBG.AddItem(pattern, ItemType.SmallGreenLeafs, 18*32, 21*32));
                itemList.add(aBG.AddItem(pattern, ItemType.SmallYellowLeafs, 4*32, 10*32));
                break;
            case BlowingLeafs: itemList.add(aBG.AddItem(pattern, ItemType.BigGreenLeafs, 20*32, 10*32));
                itemList.add(aBG.AddItem(pattern, ItemType.BigGreenLeafs, 24*32, 11*32));
                itemList.add(aBG.AddItem(pattern, ItemType.BigYellowLeafs, 15*32, 10*32));
                itemList.add(aBG.AddItem(pattern, ItemType.BigGreenLeafs, 16*32, 12*32));
                itemList.add(aBG.AddItem(pattern, ItemType.BigGreenLeafs, 17*32, 13*32));
                itemList.add(aBG.AddItem(pattern, ItemType.BigGreenLeafs, 22*32, 11*32));
                itemList.add(aBG.AddItem(pattern, ItemType.BigYellowLeafs, 16*32, 12*32));
                itemList.add(aBG.AddItem(pattern, ItemType.BigGreenLeafs, 18*32, 12*32));
                itemList.add(aBG.AddItem(pattern, ItemType.BigGreenLeafs, 16*32, 7*32));
                itemList.add(aBG.AddItem(pattern, ItemType.BigGreenLeafs, 17*32, 6*32));
                itemList.add(aBG.AddItem(pattern, ItemType.BigYellowLeafs, 16*32, 8*32));
                itemList.add(aBG.AddItem(pattern, ItemType.BigGreenLeafs, 17*32, 4*32));
                itemList.add(aBG.AddItem(pattern, ItemType.BigGreenLeafs, 22*32, 3*32));
                itemList.add(aBG.AddItem(pattern, ItemType.BigGreenLeafs, 16*32, 1*32));
                itemList.add(aBG.AddItem(pattern, ItemType.BigYellowLeafs, 16*32, 2*32));
                itemList.add(aBG.AddItem(pattern, ItemType.BigGreenLeafs, 18*32, 2*32));
                itemList.add(aBG.AddItem(pattern, ItemType.BigGreenLeafs, 16*32, 4*32));
                itemList.add(aBG.AddItem(pattern, ItemType.BigGreenLeafs, 17*32, 6*32));
                itemList.add(aBG.AddItem(pattern, ItemType.BigYellowLeafs, 16*32, 5*32));
                itemList.add(aBG.AddItem(pattern, ItemType.BigGreenLeafs, 20*32, 0*32));
                itemList.add(aBG.AddItem(pattern, ItemType.SmallGreenLeafs, 17*32, 0*32));
                itemList.add(aBG.AddItem(pattern, ItemType.SmallGreenLeafs, 19*32, 2*32));
                itemList.add(aBG.AddItem(pattern, ItemType.SmallYellowLeafs, 17*32, 4*32));
                itemList.add(aBG.AddItem(pattern, ItemType.SmallGreenLeafs, 24*32, 6*32));
                itemList.add(aBG.AddItem(pattern, ItemType.SmallYellowLeafs, 19*32, 8*32));
                itemList.add(aBG.AddItem(pattern, ItemType.SmallGreenLeafs, 16*32, 10*32));
                itemList.add(aBG.AddItem(pattern, ItemType.SmallGreenLeafs, 22*32, 12*32));
                itemList.add(aBG.AddItem(pattern, ItemType.SmallYellowLeafs, 15*32, 14*32));
                itemList.add(aBG.AddItem(pattern, ItemType.SmallGreenLeafs, 20*32, 16*32));
                itemList.add(aBG.AddItem(pattern, ItemType.SmallYellowLeafs, 18*32, 18*32));
        }
    }
    
    public void SwitchPattern(){
        if(pattern == Pattern.FallingLeafs){
            for(int i = 0; i < itemList.size(); i++){
                itemList.get(i).ChangePattern(Pattern.BlowingLeafs);
            }
            pattern = Pattern.BlowingLeafs;
        }
        else if(pattern == Pattern.BlowingLeafs){
            for(int i = 0; i < itemList.size(); i++){
                itemList.get(i).ChangePattern(Pattern.FallingLeafs);
            }
            pattern = Pattern.FallingLeafs;
        }
    }
    
    public void Render(SpriteBatch batch){
        for(int i = 0; i < itemList.size(); i++){
            itemList.get(i).Render(batch);
        }
    }
}
