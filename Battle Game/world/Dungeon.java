/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mb.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.mb.player.GamePlayer;
import com.mb.world.BlockLibrary.BlockName;
import java.util.ArrayList;

/**
 *
 * @author Kyle
 * 
 * - If arraylist is used, easier to go through list of blocks but not specific in list to location
 * but can move things to outside it's spot and can delete from list.
 * - if array[][] then more things to implement to avoid crashes but location in grid and placement hand in hand.
 * Can't use things of varying sizes very well. Maybe use bottom left corner as representation
 */
public class Dungeon{

    private String name = "testDungeon";
    private int width;
    private int height;
    private ArrayList<DungeonLayer> layerList;
    
    public Dungeon(){
        layerList = new ArrayList<DungeonLayer>();
//        blockList = new Block[width][height];
    }
    
    public void Logic(){
        for(int i = 0; i < layerList.size(); i++){
            layerList.get(i).LayerLogic();
        }
    }    
    
    public void AddGamePlayerToLayer(int layerToAdd, GamePlayer player){
        layerList.get(layerToAdd).AddWorldCharacter(player.getCharacter());
    }
    
    public void Render(SpriteBatch batch){
        for(int i = 0; i < layerList.size(); i++){
            layerList.get(i).Render(batch);
        }
    }
    
    public void DebugRender(ShapeRenderer draw){
        for(int i = 0; i < layerList.size(); i++){
            layerList.get(i).DebugRender(draw);
        }
    }
    
    public void AddDungeonLayer(int dunLayer){
        if(layerList.size() < dunLayer+1){
            layerList.add(new DungeonLayer(this));
        }
    }

    /**
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * @return the layerList
     */
    public ArrayList<DungeonLayer> getLayerList() {
        return layerList;
    }
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    public ArrayList<DungeonSaveOperator> DSOListCreate(){
        ArrayList<DungeonSaveOperator> DSOList = new ArrayList<DungeonSaveOperator>();
        for(int i = 0; i < layerList.size(); i++){
            DSOList.add(new DungeonSaveOperator(layerList.get(i)));
        }
        return DSOList;
    }
    
    public void LoadDungeon(BlockLibrary lib, ArrayList<DungeonSaveOperator> DSOList){
        this.width = DSOList.get(0).getBlockList().get(0).length;
        this.height = DSOList.get(0).getBlockList().get(0)[0].length;
        for(int i = 0; i < DSOList.size(); i++){
            layerList.add(new DungeonLayer(this));
            layerList.get(i).LoadLayers(lib, DSOList.get(i));
        }
    }
}
