/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.World;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.objects.Player;
import com.mygdx.game.objects.Player2;
import java.util.ArrayList;

/**
 *
 * @author Kyle
 */
public class MiningCamp extends Stage{
    
    String name;
    float gravity = 10;
    int w = 16;
    int h = 16;
    Tiles tile;
    
    public Player p1;
    public Player2 p2;
    
    ArrayList<Tiles> tileList;
    public ArrayList<Rectangle> collisionList;
    
    Texture TileSheet = new Texture("TestTiles.png");
    TextureRegion metal1, metal2, metal3, metal4, rock1, rock2;
    
    public MiningCamp(){
        metal1 = new TextureRegion(TileSheet, 0, 0, w, h);
        metal2 = new TextureRegion(TileSheet, w, 0, w, h);
        metal3 = new TextureRegion(TileSheet, w*2, 0, w, h);
        metal4 = new TextureRegion(TileSheet, w*3, 0, w, h);
        rock1 = new TextureRegion(TileSheet, w*8, 0, w, h);
        rock2 = new TextureRegion(TileSheet, w*9, 0, w, h);
        
        tileList = new ArrayList<Tiles>();
        collisionList = new ArrayList<Rectangle>();
        
        p1 = new Player(20, 50, this);
        collisionList.add(p1.CB);
        p2 = new Player2(50, 50, this);
        collisionList.add(p2.CB);
        //Container
        for(int i = 1; i < 9; i++){
            tile = new Tiles(false, metal3, 0, (i*16));
            tileList.add(tile);
            collisionList.add(tile.hitBox);
        }
        for(int i = 2; i < 9; i++){
            tile = new Tiles(false, metal3, 16*15, (i*16));
            tileList.add(tile);
            collisionList.add(tile.hitBox);
        }
        
        for(int i = 0; i < 16; i++){
            tile = new Tiles(false, metal1, (i*16), 16*9);
            tileList.add(tile);
            collisionList.add(tile.hitBox);
        }
        
        for(int i = 0; i < 10; i++){
            tile = new Tiles(false, metal1, (i*16), 0);
            tileList.add(tile);
            collisionList.add(tile.hitBox);
        }
        for(int i = 10; i < 16; i++){
            tile = new Tiles(false, metal1, (i*16), 16);
            tileList.add(tile);
            collisionList.add(tile.hitBox);
        }
        
        //Floating box
        for(int i = 4; i < 7; i++){
            tile = new Tiles(false, metal4, 16*6, 16*i);
            tileList.add(tile);
            collisionList.add(tile.hitBox);
        }
        
    }
    
    public void drawStage(SpriteBatch batch){
        for(int i = 0; i < tileList.size(); i++){
            batch.draw(tileList.get(i).img, tileList.get(i).hitBox.x, tileList.get(i).hitBox.y);
        }
        
    }
}
