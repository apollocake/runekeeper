/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.angrynerds.runekeeper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author qzhao
 */
public class RuneFire implements iRune{
    Animation fireIdling;
    private String runeName;
    private Vector2 pos = new Vector2();
    private Texture texture;
    //private SpriteBatch batch;
    public RuneFire() {
        //batch = new SpriteBatch();
        
    }

    @Override
    public void showRune(SpriteBatch batch) {
        texture = new Texture(Gdx.files.internal("Rune_Fire.png"));
//        batch.begin();
        //Gdx.gl.glActiveTexture(GL20.GL_TEXTURE0);
        batch.draw(texture, pos.x+19, pos.y+50,Gdx.graphics.getWidth()/22,Gdx.graphics.getHeight()/20);
  //      batch.end();
       System.out.println("Fire Rune");
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setLocation(float x, float y) {
        pos.x = x;
        pos.y = y;
    }
    
}
