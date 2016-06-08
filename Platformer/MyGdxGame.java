package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Screens.StageScreen;
import com.mygdx.game.objects.Player;

public class MyGdxGame extends Game {
	public SpriteBatch batch;
        public OrthographicCamera cam;
        
	
	@Override
	public void create () {
            cam = new OrthographicCamera();
            batch = new SpriteBatch();
            cam.setToOrtho(false, Configurations.cameraWidth, Configurations.cameraHeight);
            this.setScreen(new StageScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
}
