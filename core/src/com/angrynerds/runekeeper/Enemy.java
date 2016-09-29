/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.angrynerds.runekeeper;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author Noah
 */
public class Enemy implements Entity{
    private EntityAnimation animation;
    private String enemyName;

    private Vector2 pos = new Vector2();

    public Enemy(EntityAnimation newAnimation, String newName, float x, float y) {
        this.pos.x = x;
        this.pos.y = y;
        this.animation = newAnimation;
        this.enemyName = newName;
    }

    @Override
    public String getName() {
        return this.enemyName;
    }

    @Override
    public void update() {
        animation.setLocation(pos.x, pos.y);
    }

    @Override
    public EntityAnimation getAnimation() {
        return this.animation;
    }

    @Override
    public Vector2 getPosition() {
        return this.pos;
    }

}
