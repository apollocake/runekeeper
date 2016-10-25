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
public interface iRune {

    void showRune(SpriteBatch batch, int x, int y);

    void setLocation(float x, float y);
}
