/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mb.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.mb.entities.overworld.WorldCharacter;
import java.util.ArrayList;

/**
 *
 * @author Kyle
 */
public class DungeonLayer{
    Dungeon dungeon;
    ArrayList<Block[][]> blockList;
    ArrayList<Block> detailList;
    ArrayList<WorldCharacter> charaList;
    ArrayList<IDraw> drawList;
    
    public DungeonLayer(Dungeon dungeon){
        this.dungeon = dungeon;
        blockList = new ArrayList<Block[][]>();
        blockList.add(new Block[dungeon.getWidth()][dungeon.getHeight()]);
        charaList = new ArrayList<WorldCharacter>();
        detailList = new ArrayList<Block>();
        drawList = new ArrayList<IDraw>();
    }
    
    public void LayerLogic(){
        for(int i = 0; i < charaList.size(); i++){
            charaList.get(i).Logic(this);
        }
    }
    
    public boolean CheckCharCollisionOnFloor(Rectangle hitbox){
        return CollisionDetection(hitbox);
    }
    
    public boolean CollisionDetection(Rectangle hitbox){
        for(int i = 0; i < blockList.size(); i++){
            for(int x = 0; x < blockList.get(i).length; x++){
                for(int y = 0; y < blockList.get(i)[x].length; y++){
                    if(blockList.get(i)[x][y] != null && !blockList.get(i)[x][y].isPassable()){
                        if(hitbox.overlaps(blockList.get(i)[x][y].getHitbox())){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    public void AddWorldCharacter(WorldCharacter character){
        charaList.add(character);
        AddToDrawList(character);
    }
    
    public void RemoveWorldCharacter(WorldCharacter character){
        charaList.remove(character);
        RemoveFromDrawList(character);
    }
    
    public void AddToDrawList(IDraw drawable){
        drawList.add(drawable);
    }
    
    public void RemoveFromDrawList(IDraw drawable){
        drawList.remove(drawable);
    }
    
    public void OrderDrawList(){
        if(drawList.size() > 0){
            //Don't go out of bounds of drawList
            for(int i = 0; i < drawList.size() - 1; i++){
                //If the first element is lower then second, switch
                if(drawList.get(i).getHitbox().y < drawList.get(i+1).getHitbox().y){
                    IDraw draw1 = drawList.get(i);
                    IDraw draw2 = drawList.get(i+1);
                    drawList.set(i+1, draw1);
                    drawList.set(i, draw2);
                }
            }
        }
    }
    
    public void Render(SpriteBatch batch){
        for(int i = 0; i < blockList.size(); i++){
            RenderLayer(blockList.get(i), batch);
        }
        OrderDrawList();
        for(int i = 0; i < drawList.size(); i++){
            drawList.get(i).Render(batch);
        }
    }
    
    public void RenderLayer(Block[][] blockList, SpriteBatch batch){
        for(int x = 0; x < blockList.length; x++){
            for(int y = 0; y < blockList[x].length; y++){
                if(blockList[x][y] != null){
                    blockList[x][y].Render(batch);
                }
            }
        }
    }
    
    public void DebugRender(ShapeRenderer draw){
        for(int i = 0; i < blockList.size(); i++){
            DebugLayer(blockList.get(i), draw);
        }
        for(int i = 0; i < charaList.size(); i++){
            charaList.get(i).DebugRender(draw);
        }
    }
    
    public void DebugLayer(Block[][] blockList, ShapeRenderer draw){
        for(int x = 0; x < blockList.length; x++){
            for(int y = 0; y < blockList[x].length; y++){
                if(blockList[x][y] != null){
                    blockList[x][y].DebugRender(draw);
                }
            }
        }
    }
    
    public void AddToBlockList(int x, int y, BlockLibrary.BlockName blockname, BlockLibrary lib, int layerNum){
        blockList.get(layerNum)[x][y] = lib.CreateBlock(blockname, x, y);
    }
    
    public void AddToBlockList(int x, int y, Block block, int layerNum){
        blockList.get(layerNum)[x][y] = block;
    }
    
    public void RemoveBlock(int x, int y, int layerNum){
        blockList.get(layerNum)[x][y] = null;
    }
    
    public void AddDetailBlock(Block block){
        detailList.add(block);
        drawList.add(block);
    }
    
    public void RemoveDetailBlock(Block block){
        detailList.remove(block);
        drawList.remove(block);
    }
    
    public void RemoveDetailBlock(float x, float y){
        for(int i = 0; i < detailList.size(); i++){
            if(detailList.get(i).getHitbox().contains(x, y)){
                drawList.remove(detailList.get(i));
                detailList.remove(i);
                break;
            }
        }
    }
    
    public void AddGroupOfBlocksToList(int x, int y, int width, int height, BlockLibrary.BlockName blockname, BlockLibrary lib, int layerNum){
        for(int xp = 0; xp < width; xp++){
            for(int yp = 0; yp < height; yp++){
                blockList.get(layerNum)[x + xp][y + yp] = lib.CreateBlock(blockname, x + xp, y + yp);
            }
        }
    }
    
    public void AddLayerToDungeon(int layer, boolean mini){
        if(blockList.size() < layer+1){
            if(mini){
                blockList.add(new Block[dungeon.getWidth()*2][dungeon.getHeight()*2]);
            }
            else{
                blockList.add(new Block[dungeon.getWidth()][dungeon.getHeight()]);
            }
        }
    }
    
    public int GetLayerWidth(int layer){
        return blockList.get(layer).length;
    }
    
    public int GetLayerHeight(int layer){
        return blockList.get(layer)[0].length;
    }
    
    public void LoadLayers(BlockLibrary lib, DungeonSaveOperator DSO){
        blockList = DSO.getBlockList();
        for(int i = 0; i < blockList.size(); i++){
            for(int x = 0; x < blockList.get(i).length; x++){
                for(int y = 0; y < blockList.get(i)[x].length; y++){
                    if(blockList.get(i)[x][y] != null){
                       blockList.get(i)[x][y] = lib.LoadingImageProcess(blockList.get(i)[x][y]);
                    }
                }
            }
        }
        detailList = new ArrayList<Block>();
        ArrayList<Block> newList = DSO.getDetailList();
        for(int i = 0; i < newList.size(); i++){
            detailList.add(lib.LoadingImageProcess(newList.get(i)));
        }
        for(int i = 0; i < detailList.size(); i++){
            drawList.add(detailList.get(i));
        }
    }
}
