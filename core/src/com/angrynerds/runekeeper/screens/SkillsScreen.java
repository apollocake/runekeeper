package com.angrynerds.runekeeper.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class SkillsScreen extends RunekeeperScreen {

    Stage stage;
    public int x = 0;

    public SkillsScreen(Game gams) {
        super(gams);
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
        TextureRegion upRegion = skin.getRegion("default-slider-knob");
        TextureRegion downRegion = skin.getRegion("default-slider-knob");
        BitmapFont buttonFont = skin.getFont("default-font");

        Table titleTable = new Table();
        Label titleLabel = new Label("Skills Point", skin);

        Table buttonsTable = new Table();
        TextButton addButton = new TextButton("+", skin);
        TextButton minusButton = new TextButton("-", skin);
        
        Table labelTable = new Table();
        Label valueLabel = new Label("" + x, skin);

        titleTable.add(titleLabel);
        titleTable.left().top();
        
        labelTable.add(valueLabel);
        labelTable.left().bottom();
        
        buttonsTable.add(addButton);
        buttonsTable.add(minusButton);
        buttonsTable.right().bottom();

        stage.addActor(labelTable);
        stage.addActor(buttonsTable);
        stage.addActor(titleTable);

        /*draws a debug rectange around table and actors inside*/
        buttonsTable.debug();
        addButton.addListener(new ChangeListener() {
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                System.out.println("Add Points!");
            }
        });

        minusButton.addListener(new ChangeListener() {
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                System.out.println("Minus Points!");
            }
        });
        
        labelTable.setFillParent(true);
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
