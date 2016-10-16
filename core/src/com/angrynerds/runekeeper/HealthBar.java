/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.angrynerds.runekeeper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar.ProgressBarStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 *
 * @author Trevor
 */
public class HealthBar {


    //public final float MAXHEALTH = 100;

    boolean isEmpty;

    public ProgressBar healthBar;

    Skin skin = new Skin();
    Pixmap pixmap = new Pixmap(10, 10, Format.RGBA8888);

    public HealthBar(float health) {


        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("white", new Texture(pixmap));
        TextureRegionDrawable textureBar = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("barGreen_horizontalMid.png"))));
        ProgressBarStyle barStyle = new ProgressBarStyle(skin.newDrawable("white", Color.DARK_GRAY), textureBar);
        barStyle.knobBefore = barStyle.knob;

        this.healthBar = new ProgressBar(0, health, .5f, false, barStyle);
        this.healthBar.setValue(health);
        this.healthBar.setPosition(100, 100);
        this.healthBar.setVisible(true);
        this.healthBar.validate();

        isEmpty = false;
    }

    public void setHealth(float health) {
        this.healthBar.setValue(health);

    }

    public float getHealth() {
        return healthBar.getValue();
    }

    //method to subtract health from the healthbar
    public void removeBar(float i) {
            this.healthBar.setVisible(false);
    }
}
