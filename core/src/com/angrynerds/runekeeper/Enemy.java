/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.angrynerds.runekeeper;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 *
 * @author Noah
 */
public class Enemy implements Entity{
    private Sprite enemySprite;
    private String enemyName;

    public Enemy(Sprite newSprite, String newName) {
        this.enemySprite = newSprite;
        this.enemyName = newName;
    }

    @Override
    public Sprite getSprite() {
        return this.enemySprite;
    }

    @Override
    public String getName() {
        return this.enemyName;
    }

}
