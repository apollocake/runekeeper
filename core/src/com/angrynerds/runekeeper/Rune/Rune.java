/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.angrynerds.runekeeper.Rune;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 * @author qzhao
 */
public class Rune {

    private iRune rune;

    public void setRune(iRune rune) {
        this.rune = rune;
    }

    public iRune getRune() {
        return rune;
    }

    public void use(SpriteBatch batch, int x, int y) {
        rune.showRune(batch, x, y);
    }

    public void update(float x, float y) {
        rune.setLocation(x, y);
    }
}
