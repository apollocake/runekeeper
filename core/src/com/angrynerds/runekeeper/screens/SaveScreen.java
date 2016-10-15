package com.angrynerds.runekeeper.screens;

import com.angrynerds.runekeeper.screens.RunekeeperScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class SaveScreen extends RunekeeperScreen {

    Stage stage;

    public SaveScreen(Game gam) {
        super(gam);
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
        TextureRegion upRegion = skin.getRegion("default-slider-knob");
        TextureRegion downRegion = skin.getRegion("default-slider-knob");
        BitmapFont buttonFont = skin.getFont("default-font");

        Table titleTable = new Table();
        Label titleLabel = new Label("Save Menu", skin);

        Table buttonsTable = new Table();
        TextButton saveButton = new TextButton("Save", skin);
        TextButton loadButton = new TextButton("Load", skin);

        TextButton cancelButton = new TextButton("Cancel", skin);

        titleTable.add(titleLabel);
        titleTable.left().top();

        buttonsTable.add(saveButton);
        buttonsTable.add(loadButton);

        buttonsTable.row();
        buttonsTable.add(cancelButton);

        stage.addActor(buttonsTable);
        stage.addActor(titleTable);

        /*draws a debug rectange around table and actors inside*/
        buttonsTable.debug();
        saveButton.addListener(new ChangeListener() {
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                System.out.println("Save Pressed!");
            }
        });

        loadButton.addListener(new ChangeListener() {
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                System.out.println("Load Pressed!");
            }
        });

        cancelButton.addListener(new ChangeListener() {
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                System.out.println("Cancel Pressed!");
            }
        });
        titleTable.setFillParent(true);
        buttonsTable.setFillParent(true);

    }

    @Override

    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override

    public void hide() {
        this.dispose();
    }

    @Override

    public void dispose() {
        stage.dispose();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //stage.act(Gdx.graphics.getDeltaTime());
        stage.act(delta);
        stage.draw();
    }

}
