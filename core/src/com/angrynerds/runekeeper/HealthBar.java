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
    
    float damagetaken;
    float maxhealth;
    
    final float HEALTH = 100;
    public ProgressBar health;
    Skin skin = new Skin();
    Pixmap pixmap = new Pixmap(10, 10, Format.RGBA8888);
  
     public HealthBar()  {
       pixmap.setColor(Color.WHITE);
       pixmap.fill();
       skin.add("white", new Texture(pixmap));
       TextureRegionDrawable textureBar = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("barGreen_horizontalMid.png"))));
       ProgressBarStyle barStyle = new ProgressBarStyle(skin.newDrawable("white", Color.DARK_GRAY), textureBar);
       
        this.health = new ProgressBar(0, HEALTH, .5f, false, barStyle);
        barStyle.knobBefore = barStyle.knob;
        this.health.setValue(50);
        this.health.setPosition(100,100);
        this.health.setVisible(true);
        this.health.validate();  
        damagetaken = 0;
        maxhealth = HEALTH;
    }  
    
    //method to subtract health from the healthbar
    public void damage(float i){
        float temp = 0;
        if((i+damagetaken) >= HEALTH)
        {
           System.out.println("Your dead"); 
           this.health.setVisible(false);
        }
        else
        {
          damagetaken += i;
          temp = maxhealth - damagetaken;
          this.health.setValue(temp);
        }
         
    }
    
    // method to add health to the healthbar
    public void addhealth(float i){
         float temp = 0;
          if((i+damagetaken)>= HEALTH)
          {
              this.health.setValue(HEALTH);
          }
          else
          {
             temp = (HEALTH - damagetaken) + i;
             this.health.setValue(temp);
              if(damagetaken - i <= 0)
               {
                  damagetaken = 0;
               }
               else
                  damagetaken = damagetaken - i;
          }     
    }    
}
    

