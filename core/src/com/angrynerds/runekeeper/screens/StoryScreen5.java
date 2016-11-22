package com.angrynerds.runekeeper.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;

public class StoryScreen5 extends RunekeeperScreen
    {
    Texture texture1, texture2, texture3, texture4, texture5;
    Image storyImage;
    Stage stage;

    public StoryScreen5(Game game)
        {
        super(game);   
        }
    
    @Override
    public void show()
        {
        texture1 = new Texture("meteor 02.png");
        texture2 = new Texture("asteroid 04.png");
        texture3 = new Texture("mutant 04.png");
        texture4 = new Texture("glove sword 03.png");
        texture5 = new Texture("duty 04.png");
       
        storyImage = new Image(texture5);
        stage = new Stage();
        storyImage.setPosition(this.stage.getWidth() / 2, this.stage.getHeight() / 2, Align.center);

        stage.addActor(storyImage);
        storyImage.addAction(Actions.sequence(Actions.alpha(0),
                Actions.fadeIn(0.75f), Actions.delay(2.75f), Actions.run(new Runnable()
                    {
                    @Override
                    public void run()
                        {
                        //game.setScreen(new StoryScreen5(game));
                        ((Game) Gdx.app.getApplicationListener()).setScreen(new NewGameScreen(game));
                        }
                    })));
        }

    @Override
    public void render(float delta)
        {
        Gdx.gl.glClearColor(255, 255, 255, 255);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
        }
}
