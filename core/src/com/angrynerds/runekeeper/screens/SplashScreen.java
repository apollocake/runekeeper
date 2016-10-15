package com.angrynerds.runekeeper.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class SplashScreen extends RunekeeperScreen {

    Texture texture;
    Image splashImage;
    Stage stage;

    public SplashScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        texture = new Texture("intro_swords1.png");
        splashImage = new Image(texture);
        stage = new Stage();

        stage.addActor(splashImage);
        splashImage.addAction(Actions.sequence(Actions.alpha(0),
                Actions.fadeIn(0.75f), Actions.delay(1.75f), Actions.run(new Runnable() {
            @Override
            public void run() {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new StartScreen(game));
            }
        })));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(255, 255, 255, 255);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

}
