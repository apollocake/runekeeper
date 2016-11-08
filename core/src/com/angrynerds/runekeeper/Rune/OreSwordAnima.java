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
public class OreSwordAnima {
    
    private static final int FIRE_FRAME_COLS = 5;
    private static final int FIRE_FRAME_ROW = 6;
    private boolean isAnimationDone = false;

    public boolean isIsAnimationDone() {
        return isAnimationDone;
    }

    public void setIsAnimationDone(boolean isAnimationDone) {
        this.isAnimationDone = isAnimationDone;
    }
    
    float stateTime;
    TextureRegion currentFrame; 
    Animation UpAnima;


    Animation upIdling;

    Texture attackingSheet;

    TextureRegion[] attackingFrames;

    Animation currentAnimation;

    //  System.out.println("I am attacking");
    //}
    public OreSwordAnima() {
        createAnimations();
        //currentAnimation = downIdling;
    }

    private void createAnimations() {

        attackingSheet = new Texture(Gdx.files.internal("OreSword2.png"));

        TextureRegion[][] tmp = TextureRegion.split(attackingSheet, attackingSheet.getWidth() / (FIRE_FRAME_COLS),
                attackingSheet.getHeight() / FIRE_FRAME_ROW); 
    
        attackingFrames = new TextureRegion[FIRE_FRAME_COLS * FIRE_FRAME_ROW];

 
        int index = 0;
        for (int i = 0; i < FIRE_FRAME_ROW; i++) {
            for (int j = 0; j < FIRE_FRAME_COLS; j++) {
                attackingFrames[index++] = tmp[i][j];
                System.out.println("index " + index);
            }
            if(index == 20){
                //isAnimationDone = true;
            }
        }
        isAnimationDone = false;
        //index = 0;
        upIdling = new Animation(0.045f, attackingFrames);
        
        stateTime = 0f;

    }
    
    public void showFireAnimation(SpriteBatch spriteBatch, int x, int y, float w, float h){
        stateTime += Gdx.graphics.getDeltaTime();          
        currentFrame = upIdling.getKeyFrame(stateTime);  // #16
        
        //System.out.println("isAnimationFinished " + upIdling.isAnimationFinished(stateTime));
        //if(upIdling.isAnimationFinished(stateTime) == true){
         //   isAnimationDone = true;
          //  stateTime = 0;
        //}
        //if (direction.equalsIgnoreCase("RIGHT_ATTACKING")){
        spriteBatch.draw(currentFrame, x, y, w, h);             // #17
        //}
    }
    
    public boolean getisAnimaFinished(){
         if(upIdling.isAnimationFinished(stateTime) == true){
            stateTime = 0;
            //System.out.println("isAnimationFinished " + upIdling.isAnimationFinished(stateTime));
            return true;
            
        }
         else
             return false;
         
        
    }
}
