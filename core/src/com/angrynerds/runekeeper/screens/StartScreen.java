
package com.angrynerds.runekeeper.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class StartScreen extends RunekeeperScreen {
	TextureRegion title;
	SpriteBatch batch;
	float time = 0;

	public StartScreen (Game game) {
		super(game);
	}

	@Override
	public void show () {
		//title = new TextureRegion(new Texture("startscreen.png"), 0, 0, 480, 320);
		title = new TextureRegion(new Texture("startscreen.png"), 0, 0, 240, 160);
		batch = new SpriteBatch();
		//batch.getProjectionMatrix().setToOrtho2D(0, 0, 480, 320);
		batch.getProjectionMatrix().setToOrtho2D(0, 0, 240, 160);
	}

	@Override
	public void render (float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(title, 0, 0);
		batch.end();

		time += delta;
		if (time > 1) {
			if (Gdx.input.isKeyPressed(Keys.ANY_KEY) || Gdx.input.justTouched()) {
                                //move to a different game screen
				//game.setScreen(new GameScreen(game));
			}
		}
	}

	@Override
	public void hide () {
		Gdx.app.debug("Runekeeper", "dispose start screen");
		batch.dispose();
		title.getTexture().dispose();
	}
}
