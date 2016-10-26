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
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 *
 * @author qzhao
 */
public class WaterGloveAnima {
        private static final int FIRE_RIGHT_FRAME_COLS = 4;
    private static final int FIRE_RIGHT_FRAME_ROW = 4;
    
    float stateTime;
    TextureRegion currentFrame; 

    Animation RightAnima;

    Animation rightIdling;

    Texture attackingSheet;
    TextureRegion[] attackingRightFrames;

    Animation currentAnimation;

    //  System.out.println("I am attacking");
    //}
    public WaterGloveAnima() {
        createAnimations();
        //currentAnimation = downIdling;
    }

    private void createAnimations() {

        attackingSheet = new Texture(Gdx.files.internal("WaterGlove.png"));

        TextureRegion[][] tmp = TextureRegion.split(attackingSheet, attackingSheet.getWidth() / (FIRE_RIGHT_FRAME_COLS),
                attackingSheet.getHeight() / FIRE_RIGHT_FRAME_ROW); 
    
        attackingRightFrames = new TextureRegion[FIRE_RIGHT_FRAME_COLS * FIRE_RIGHT_FRAME_ROW];

 
        int index = 0;
        for (int i = 0; i < FIRE_RIGHT_FRAME_ROW; i++) {
            for (int j = 0; j < FIRE_RIGHT_FRAME_COLS; j++) {
                attackingRightFrames[index++] = tmp[i][j];
            }
        }
        //index = 0;
        rightIdling = new Animation(0.05f, attackingRightFrames);
        
        stateTime = 0f;

    }
    
    public void showFireAnimation(SpriteBatch spriteBatch, int x, int y){
        stateTime += Gdx.graphics.getDeltaTime() ;          
        currentFrame = rightIdling.getKeyFrame(stateTime, true);  // #16
        spriteBatch.draw(currentFrame, x, y);             // #17
    }

}
