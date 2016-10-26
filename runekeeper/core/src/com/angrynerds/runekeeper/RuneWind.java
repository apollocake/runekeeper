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
public class RuneWind implements iRune {

    Animation fireIdling;
    private String runeName;
    private Vector2 pos = new Vector2();
    private Texture texture;

    //private SpriteBatch batch;
    public RuneWind() {
        //batch = new SpriteBatch();

    }

    @Override
    public void showRune(SpriteBatch batch, int x, int y) {
        texture = new Texture(Gdx.files.internal("Rune_Wind.png"));
        batch.draw(texture, x, y, Gdx.graphics.getWidth() / 20, Gdx.graphics.getHeight() / 15);
//       System.out.println("Fire Rune");

    }

    @Override
    public void setLocation(float x, float y) {
        pos.x = x;
        pos.y = y;
    }

}
