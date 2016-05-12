/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import game.CritterHaven;
import game.ScreenSetup.MainSetup;

/**
 *
 * @author Kyle
 */
public class MainScreen implements Screen{
    public CritterHaven ch;
    public OrthographicCamera cam;
    public SpriteBatch batch;
    ShapeRenderer testing;
    BitmapFont font, font16;
    MainSetup mSetup;
    

    public MainScreen(CritterHaven cH){
        this.ch = cH;
        this.cam = cH.cam;
        this.batch = cH.batch;
        testing = new ShapeRenderer();
        font = new BitmapFont();
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Pixeltype.ttf"));
        font16 = generator.generateFont(16);
        generator.dispose();
        mSetup = new MainSetup(this);
    }
    
    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        cam.update();
	batch.begin();
	batch.setProjectionMatrix(cam.combined);
        
        //UpdateCamera();
                //Draw here*********
        //Farthest Layer
        mSetup.RenderSetup(batch);
        
        //Middle Layer
        font16.setColor(Color.BLACK);
        font16.draw(batch, mSetup.monsterName, 68, 119, 57, 1, false);
        font16.draw(batch, String.valueOf(mSetup.critter.level), 78, 95);
        font16.draw(batch, String.valueOf(mSetup.critter.strLvl), 98, 95, 26, 1, false);
        font16.draw(batch, String.valueOf(mSetup.critter.conLvl), 68, 73, 26, 1, false);
        font16.draw(batch, String.valueOf(mSetup.critter.aglLvl), 98, 73, 26, 1, false);
        font16.draw(batch, String.valueOf(mSetup.critter.currentFood), 68, 51, 26, 1, false);
        //Closest Layer
        
        //font.draw(batch, String.valueOf(mSetup.critter.maxHunger), 70, 30);
                //Done drawing after this****
	batch.end();
        testing.begin(ShapeRenderer.ShapeType.Line);
        testing.setProjectionMatrix(cam.combined);
        testing.setColor(Color.WHITE);
        
        

        testing.end();
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
    
}
