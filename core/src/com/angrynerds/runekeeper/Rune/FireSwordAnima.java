/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.angrynerds.runekeeper.Rune;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 *
 * @author qzhao
 */
public class FireSwordAnima {

    private static final int FIRE_FRAME_COLS = 5;
    private static final int FIRE_FRAME_ROW = 8;
    
    float stateTime;
    TextureRegion currentFrame; 
    Animation UpAnima;


    Animation upIdling;

    Texture attackingSheet;

    TextureRegion[] attackingFrames;

    Animation currentAnimation;

    //  System.out.println("I am attacking");
    //}
    public FireSwordAnima() {
        createAnimations();
        //currentAnimation = downIdling;
    }

    private void createAnimations() {

        attackingSheet = new Texture(Gdx.files.internal("FireSword.png"));

        TextureRegion[][] tmp = TextureRegion.split(attackingSheet, attackingSheet.getWidth() / (FIRE_FRAME_COLS),
                attackingSheet.getHeight() / FIRE_FRAME_ROW); 
    
        attackingFrames = new TextureRegion[FIRE_FRAME_COLS * FIRE_FRAME_ROW];

 
        int index = 0;
        for (int i = 0; i < FIRE_FRAME_ROW; i++) {
            for (int j = 0; j < FIRE_FRAME_COLS; j++) {
                attackingFrames[index++] = tmp[i][j];
            }
        }
        //index = 0;
        upIdling = new Animation(0.040f, attackingFrames);
        
        stateTime = 0f;

    }
    
    public void showFireAnimation(SpriteBatch spriteBatch, int x, int y){
        stateTime += Gdx.graphics.getDeltaTime();          
        currentFrame = upIdling.getKeyFrame(stateTime, true);  // #16
        spriteBatch.draw(currentFrame, x, y, Gdx.graphics.getWidth() / 3, Gdx.graphics.getHeight() / 3);             // #17
    }

}

