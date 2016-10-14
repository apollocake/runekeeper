/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.angrynerds.runekeeper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author qzhao
 */
public class Glove {
    Animation fireIdling;
    private String runeName;
    private Vector2 pos = new Vector2();
    private Texture texture;
    //private SpriteBatch batch;
    public Glove() {
        //batch = new SpriteBatch();
        
    }

    
    public void draw(SpriteBatch batch) {
        texture = new Texture(Gdx.files.internal("glove.png"));
//        batch.begin();
        //Gdx.gl.glActiveTexture(GL20.GL_TEXTURE0);
        batch.draw(texture, 2, 2,Gdx.graphics.getWidth()/20,Gdx.graphics.getHeight()/14);
  //      batch.end();
       //System.out.println("Fire Rune");
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
