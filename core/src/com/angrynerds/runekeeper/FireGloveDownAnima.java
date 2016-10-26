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
public class FireGloveDownAnima {
        private static final int FIRE_RIGHT_FRAME_COLS = 4;
    private static final int FIRE_RIGHT_FRAME_ROW = 4;
    
    float stateTime;
    TextureRegion currentFrame; 
    //Animation attackingDownAnima;
    //Animation attackingUpAnima;
    //Animation attackingLeftAnima;
    Animation DownAnima;

    //Animation upIdling;
    //Animation downIdling;
    //Animation leftIdling;
    Animation downIdling;

    Texture attackingSheet;
    //TextureRegion[] attackingUpFrames;
    //TextureRegion[] attackingDownFrames;
    //TextureRegion[] attackingLeftFrames;
    TextureRegion[] attackingDownFrames;

    Animation currentAnimation;

    //  System.out.println("I am attacking");
    //}
    public FireGloveDownAnima() {
        createAnimations();
        //currentAnimation = downIdling;
    }

    private void createAnimations() {

        attackingSheet = new Texture(Gdx.files.internal("FireGloveDown.png"));

        TextureRegion[][] tmp = TextureRegion.split(attackingSheet, attackingSheet.getWidth() / (FIRE_RIGHT_FRAME_COLS),
                attackingSheet.getHeight() / FIRE_RIGHT_FRAME_ROW); 
    
        attackingDownFrames = new TextureRegion[FIRE_RIGHT_FRAME_COLS * FIRE_RIGHT_FRAME_ROW];

 
        int index = 0;
        for (int i = 0; i < FIRE_RIGHT_FRAME_ROW; i++) {
            for (int j = 0; j < FIRE_RIGHT_FRAME_COLS; j++) {
                attackingDownFrames[index++] = tmp[i][j];
            }
        }
        //index = 0;
        downIdling = new Animation(0.025f, attackingDownFrames);
        
        stateTime = 0f;

    }
    
    public void showFireAnimation(SpriteBatch spriteBatch, int x, int y){
        stateTime += Gdx.graphics.getDeltaTime();          
        currentFrame = downIdling.getKeyFrame(stateTime, true);  // #16
        spriteBatch.draw(currentFrame, x, y);             // #17
    }

}
