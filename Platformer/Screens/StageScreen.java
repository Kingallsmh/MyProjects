/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.World.MiningCamp;
import com.mygdx.game.objects.Monster.BasicCreature;
import com.mygdx.game.objects.Monster.BodyParts.Head;
import com.mygdx.game.objects.Monster.BodyParts.library.MainLibrary;
import com.mygdx.game.objects.Monster.SmallCreature;
import com.mygdx.game.objects.Player;
import com.mygdx.game.objects.Player2;

/**
 *
 * @author Kyle
 */
public class StageScreen implements Screen{
    float initX;
    float initY;
    public SpriteBatch batch;
    ShapeRenderer testing;
    BitmapFont font;
    public OrthographicCamera cam;
    MainLibrary mainLib;
    //For testing
    SmallCreature test;
    
//    public MiningCamp mCamp;
    
    public StageScreen(MyGdxGame game){
        testing = new ShapeRenderer();
        font = new BitmapFont();
        batch = game.batch;
        cam = game.cam;
        mainLib = new MainLibrary();
        //For testing
        test = new SmallCreature(mainLib.GetTorso(2));
        test.headList.add(mainLib.GetHead(1));
        test.AttachHead();
        test.armsList.add(mainLib.GetRArm(2));
        test.AttachArms(0);
        test.armsList.add(mainLib.GetLArm(2));
        test.AttachArms(1);
        test.legsList.add(mainLib.GetRLeg(0));
        test.AttachLegs(0);
        test.legsList.add(mainLib.GetLLeg(0));
        test.AttachLegs(1);
        
//        mCamp = new MiningCamp();
    }
    
    public void UpdateCamera(){
        //cam.position.x = p1.CB.x;
        cam.update();
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.1f, 1);
	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        cam.update();
	batch.begin();
	batch.setProjectionMatrix(cam.combined);
        
        //UpdateCamera();
                //Draw here*********
        //Farthest Layer
//        mCamp.drawStage(batch);
        
        //Middle Layer
        test.Render(batch);
        
        
        
        
        
        
        //Closest Layer
//        mCamp.p1.Input();
//        mCamp.p1.Draw(batch);
//        mCamp.p2.Input();
//        mCamp.p2.Draw(batch);
        font.setColor(Color.RED);
        //font.draw(batch, String.valueOf(p1.balloon), 10, 10);
                //Done drawing after this****
	batch.end();
//        testing.begin(ShapeRenderer.ShapeType.Line);
//        testing.setProjectionMatrix(cam.combined);
//        testing.setColor(Color.WHITE);
//        testing.rect(p1.CB.x, p1.CB.y, p1.CB.width, p1.CB.height);
//        testing.rect(p1.NB.x, p1.NB.y, p1.NB.width, p1.NB.height);
//        testing.rect(p1.EB.x, p1.EB.y, p1.EB.width, p1.EB.height);
//        testing.rect(p1.SB.x, p1.SB.y, p1.SB.width, p1.SB.height);
//        testing.rect(p1.WB.x, p1.WB.y, p1.WB.width, p1.WB.height);
//        for(Rectangle colArray : mCamp.collisionList){
//            testing.rect(colArray.x, colArray.y, colArray.width, colArray.height);
//        }
//        testing.end();
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
