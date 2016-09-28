/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.angrynerds.runekeeper;

import javax.swing.JFrame;
import javax.swing.JProgressBar;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar.ProgressBarStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 *
 * @author Trevor
 */
public class HealthBar {
    
    public ProgressBar health;
    Skin skin = new Skin();
    Pixmap pixmap = new Pixmap(10, 10, Format.RGBA8888);
   // public Texture progress_bar = new Texture(Gdx.files.internal("assets/health.jpg"));
   // public Texture pb_knob = new Texture(Gdx.files.internal("assets/bat_knob.jpg"));
   // public ProgressBar.ProgressBarStyle pbs = new ProgressBar.ProgressBarStyle();  

    public HealthBar() {
     pixmap.setColor(Color.WHITE);
     pixmap.fill();
     skin.add("white", new Texture(pixmap));
     TextureRegionDrawable textureBar = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("barGreen_horizontalMid.png"))));
       ProgressBarStyle barStyle = new ProgressBarStyle(skin.newDrawable("white", Color.DARK_GRAY), textureBar);
       
      // pbs.background = new TextureRegionDrawable(new TextureRegion(progress_bar));
      //  pbs.knob = new TextureRegionDrawable(new TextureRegion(pb_knob));

        health = new ProgressBar(0, 100, .5f, false, barStyle);
        barStyle.knobBefore = barStyle.knob;
        health.setValue(100);
        health.setPosition(10, 10);
        health.setVisible(true);
        health.setSize(10f, 100f);
        health.validate();
        
    }  
}
    

