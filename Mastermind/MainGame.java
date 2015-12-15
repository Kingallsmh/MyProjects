package com.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainGame extends Game {
	public SpriteBatch batch;
        public OrthographicCamera cam;
	
	@Override
	public void create () {
            batch = new SpriteBatch();
            cam = new OrthographicCamera();
            cam.setToOrtho(false, Configurations.cameraWidth, Configurations.cameraHeight);
            this.setScreen(new BoardScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
}
