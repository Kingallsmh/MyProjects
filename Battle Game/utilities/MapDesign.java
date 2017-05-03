/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mb.utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.mb.Config;
import com.mb.screens.GameScreen;
import com.mb.world.Block;
import com.mb.world.BlockLibrary;
import com.mb.world.BlockLibrary.MiniBlockName;
import com.mb.world.BlockLibrary.BlockName;
import com.mb.world.BlockLibrary.ObjectName;
import com.mb.world.BlockLibrary.Section;
import com.mb.world.Dungeon;
import com.mb.world.DungeonSaveOperator;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kyle
 */
public class MapDesign implements InputProcessor{
    BitmapFont font14;
    int s = 16;
    GameScreen screen;
    Vector3 mousePoint;
    Dungeon dungeon;
    int currentFloor = 0;
    
    int layerNum = 0;
    int xGrid;
    int yGrid;
    boolean touched = false;
    
    Block currentBlock;
    BlockLibrary lib;
    BlockName tile;
    MiniBlockName miniblock;
    Section section;
    ObjectName object;
    
    DesignMode mode = DesignMode.TileMode;
    
    public enum DesignMode{
        TileMode, ObjectMode;
        
        public DesignMode getNext() {
            if(this.ordinal() < DesignMode.values().length - 1){
                return DesignMode.values()[this.ordinal() + 1];
            }
            else{
                return DesignMode.values()[0];
            }
        }
    }
    
    public MapDesign(GameScreen screen){
        this.screen = screen;
        Gdx.input.setInputProcessor(this);
        lib = new BlockLibrary();
        tile = BlockName.Grass1;
        miniblock = MiniBlockName.GrassCovering;
        section = Section.TopL;
        object = ObjectName.SmallRock1;
        mousePoint = new Vector3();
        LoadPreviousDungeon("testDungeon");
        CreateNewBlock();
        
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/OrangeKid.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 11;
        parameter.color = Color.WHITE;
        parameter.borderWidth = 1;
        
        font14 = generator.generateFont(parameter);
        generator.dispose(); // don't forget to dispose to avoid memory leaks!
    }
    
    public void LoadPreviousDungeon(String filename){
        File f = new File("Maps/Layouts/" + filename + ".dng");
        if(f.exists()){
            dungeon = new Dungeon();
            dungeon.LoadDungeon(lib, LoadDungeon(filename));
        }
        else{
            dungeon = new Dungeon();
            dungeon.setWidth(20);
            dungeon.setHeight(20);
            dungeon.AddDungeonLayer(currentFloor);
        }
    }
    
    public void DesignLogic(){
        
    }
    
    public void DisplayLogic(SpriteBatch batch){
        font14.draw(batch, "Name: " + dungeon.getName(), Config.cameraWidth - 120, Config.cameraHeight - 10);
        font14.draw(batch, "Mode: " + mode, Config.cameraWidth - 100, Config.cameraHeight - 30);
        font14.draw(batch, "Floor: " + currentFloor, Config.cameraWidth - 100, Config.cameraHeight - 50);
        font14.draw(batch, "Layer: " + layerNum, Config.cameraWidth - 100, Config.cameraHeight - 70);
        
        font14.draw(batch, "L & R arrows = change pieces", Config.cameraWidth - 120, 20);
        font14.draw(batch, "U & D arrows = change layer", Config.cameraWidth - 120, 40);
        font14.draw(batch, "Enter = save", Config.cameraWidth - 120, 60);
        font14.draw(batch, "Numpad 1 & 2 = change section", Config.cameraWidth - 120, 80);
    }
    
    public void CreateNewBlock(){
        switch(mode){
            case TileMode: if(layerNum > 0){
                    currentBlock = lib.CreateBlock(miniblock, section, xGrid, yGrid);
                    SnapLocation(8);
                }
                else{
                    currentBlock = lib.CreateBlock(tile, xGrid, yGrid);
                    SnapLocation(16);
                }
                break;
            case ObjectMode: currentBlock = lib.CreateBlock(object, xGrid, yGrid);
                SnapLocation(8);
                break;
        }
        
    }
    
    public void Render(SpriteBatch batch){
        dungeon.Render(batch);
        currentBlock.Render(batch);
        DisplayLogic(batch);
    }
    
    public void DebugRender(ShapeRenderer draw){
        currentBlock.DebugRender(draw);
        dungeon.DebugRender(draw);
    }
    
    public void LayerKeyDown(int keycode){
        if(layerNum == 0){
            if(keycode == Keys.RIGHT){
                this.tile = tile.getNext();
                this.CreateNewBlock();
            }
            if(keycode == Keys.LEFT){
                this.tile = tile.getPrevious();
                this.CreateNewBlock();
            }
        }
        else if(layerNum > 0){
            if(keycode == Keys.RIGHT){
                this.miniblock = miniblock.getNext();
                this.CreateNewBlock();
            }
            if(keycode == Keys.LEFT){
                this.miniblock = miniblock.getPrevious();
                this.CreateNewBlock();
            }
            if(keycode == Keys.NUMPAD_2){
                this.section = section.getNext();
                this.CreateNewBlock();
            }
            if(keycode == Keys.NUMPAD_1){
                this.section = section.getPrevious();
                this.CreateNewBlock();
            }
        }
    }
    
    public void ObjectKeyDown(int keycode){
        if(keycode == Keys.RIGHT){
                this.object = object.getNext();
                this.CreateNewBlock();
            }
            if(keycode == Keys.LEFT){
                this.object = object.getPrevious();
                this.CreateNewBlock();
            }
    }

    @Override
    public boolean keyDown(int keycode) {
        switch(mode){
            case TileMode: LayerKeyDown(keycode);
                break;
            case ObjectMode: ObjectKeyDown(keycode);
                break;
        }
        //For changing layers
        //Different layers allows for detailed maps
        if(keycode == Keys.UP){
            layerNum += 1;
            dungeon.getLayerList().get(currentFloor).AddLayerToDungeon(layerNum, layerNum > 0);
            CreateNewBlock();
        }
        if(keycode == Keys.DOWN){
            if(layerNum > 0){
                layerNum += -1;
                CreateNewBlock();
            }
        }
        //For changing floors
        //A different floor other then the one that an entity is on does not interact with
        //entity, thus gives illusion of height
        if(keycode == Keys.X){
            currentFloor += 1;
            layerNum = 0;
            dungeon.AddDungeonLayer(currentFloor);
            CreateNewBlock();
        }
        if(keycode == Keys.Z){
            if(currentFloor > 0){
                currentFloor += -1;
                layerNum = 0;
                CreateNewBlock();
            }
        }
        
        if(keycode == Keys.SPACE){
            this.mode = mode.getNext();
            CreateNewBlock();
        }
        if(keycode == Keys.ENTER){
            SaveDungeon(dungeon);
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(button == 0 && !touched){
            switch(mode){
                case TileMode: dungeon.getLayerList().get(currentFloor).AddToBlockList(xGrid, yGrid, currentBlock, layerNum);
                    break;
                case ObjectMode: dungeon.getLayerList().get(currentFloor).AddDetailBlock(currentBlock);
                    break;
            }
            CreateNewBlock();
            touched = true;
        }
        if(button == 1 && !touched){
            switch(mode){
                case TileMode: dungeon.getLayerList().get(currentFloor).RemoveBlock(xGrid, yGrid, layerNum);
                    break;
                case ObjectMode: dungeon.getLayerList().get(currentFloor).RemoveDetailBlock(mousePoint.x, mousePoint.y);
                    break;
            }
            
            touched = true;
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(touched){
            touched = false;
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }
    
    public void SnapLocation(int s){
        if(mousePoint.x/s < dungeon.getLayerList().get(currentFloor).GetLayerWidth(layerNum)){
            xGrid = (int)mousePoint.x/s;
            currentBlock.getHitbox().x = xGrid*s;
        }
        if(mousePoint.y/s < dungeon.getLayerList().get(currentFloor).GetLayerHeight(layerNum)){
            yGrid = (int)mousePoint.y/s;
            currentBlock.getHitbox().y = yGrid*s;
        }
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        mousePoint.set(screenX, screenY, 0);
        screen.getCam().unproject(mousePoint);
        switch(mode){
            case TileMode: if(layerNum == 0){
                    SnapLocation(16);
                }
                else if(layerNum > 0){
                    SnapLocation(8);
                }
                break;
            case ObjectMode: SnapLocation(8);
            break;
        }
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
    
    //Saving and loading maps
    public void SaveDungeon(Dungeon dungeon){
        FileOutputStream fos;
        try {
            fos = new FileOutputStream("Maps/Layouts/" + dungeon.getName() + ".dng");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(dungeon.DSOListCreate());
            oos.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MapDesign.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MapDesign.class.getName()).log(Level.SEVERE, null, ex);
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
}
