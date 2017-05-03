/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mb.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mb.player.GamePlayer;
import com.mb.utilities.MapDesign;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kyle
 */
public class World {
    private Dungeon dungeon;
    GamePlayer p1;
    
    public World(GamePlayer p1){
        this.p1 = p1;
        SetupExistingDungeon("testDungeon");
        this.p1.getCharacter().getHitbox().x = 250;
        dungeon.AddGamePlayerToLayer(1, this.p1);
    }
    
    public void Render(SpriteBatch batch){
        dungeon.Logic();
        dungeon.Render(batch);
    }
    
    public void DebugRender(ShapeRenderer draw){
        dungeon.DebugRender(draw);
    }
    
    public void SetupExistingDungeon(String dungeonName){
        File f = new File("Maps/Layouts/" + dungeonName + ".dng");
        if(f.exists()){
            BlockLibrary lib = new BlockLibrary();
            dungeon = new Dungeon();
            dungeon.LoadDungeon(lib, LoadDungeon(dungeonName));
        }
        else{
            dungeon = new Dungeon();
            dungeon.AddDungeonLayer(0);
        }
    }
    
    public ArrayList<DungeonSaveOperator> LoadDungeon(String fileName){
        FileInputStream fos;
        try {
            fos = new FileInputStream("Maps/Layouts/" + fileName + ".dng");
            ObjectInputStream oos = new ObjectInputStream(fos);
            ArrayList<DungeonSaveOperator> d = (ArrayList<DungeonSaveOperator>) oos.readObject();
            return d;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MapDesign.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MapDesign.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MapDesign.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * @return the dungeon
     */
    public Dungeon getDungeon() {
        return dungeon;
    }

    /**
     * @param dungeon the dungeon to set
     */
    public void setDungeon(Dungeon dungeon) {
        this.dungeon = dungeon;
    }
}
