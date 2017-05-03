/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mb.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mb.Config;
import com.mb.utilities.MapDesign;
import com.mb.world.Overworld;

/**
 *
 * @author Kyle
 */
public class GameScreen implements Screen{

    SpriteBatch batch;
    ShapeRenderer draw;
    private OrthographicCamera cam;
    
    Overworld world;
    
    //MAP CREATION
    MapDesign mapDesign;

    public GameScreen(){
        draw = new ShapeRenderer();
        batch = new SpriteBatch();
        cam = new OrthographicCamera();
        cam.setToOrtho(false, Config.cameraWidth, Config.cameraHeight);
        
        world = new Overworld();
//        mapDesign = new MapDesign(this);
    }
    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        cam.update();
	batch.begin();
	batch.setProjectionMatrix(cam.combined);
        
        //Object Render
        world.Render(batch);
//        mapDesign.Render(batch);
        batch.end();
        
        draw.begin(ShapeRenderer.ShapeType.Line);
        draw.setProjectionMatrix(cam.combined);
        draw.setColor(1, 1, 1, 1);
        
        //Debug draw
//        world.DebugRender(draw);
//        mapDesign.DebugRender(draw);
        
        draw.end();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }

    /**
     * @return the cam
     */
    public OrthographicCamera getCam() {
        return cam;
    }

    /**
     * @param cam the cam to set
     */
    public void setCam(OrthographicCamera cam) {
        this.cam = cam;
    }
    
}
