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
    
    private float health;
    public final float MAXHEALTH = 100;
    
    public ProgressBar healthBar;
    Skin skin = new Skin();
    Pixmap pixmap = new Pixmap(10, 10, Format.RGBA8888);
  
     public HealthBar()  {         
        health = 100;
         
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("white", new Texture(pixmap));
        TextureRegionDrawable textureBar = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("barGreen_horizontalMid.png"))));
        ProgressBarStyle barStyle = new ProgressBarStyle(skin.newDrawable("white", Color.DARK_GRAY), textureBar);
        barStyle.knobBefore = barStyle.knob;
        this.healthBar = new ProgressBar(0, health, .5f, false, barStyle);
        this.healthBar.setValue(MAXHEALTH);
        this.healthBar.setPosition(20,450);
        this.healthBar.setVisible(true);
        this.healthBar.validate(); 
    }  
    
        
    
    public void setHealth(float health){
        this.health = health;
        this.healthBar.setValue(this.health);
        
    }
    
    public float getHealth(){
        return health;
    }
}
    

