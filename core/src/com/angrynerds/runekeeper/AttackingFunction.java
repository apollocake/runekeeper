
package com.angrynerds.runekeeper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class AttackingFunction {
   // public void attacking(){
    
        private static final int ATTACKING_FRAME_COLS= 6;
        private static final int ATTACKING_UP_FRAME_ROW = 0; 
        private static final int ATTACKING_LEFT_FRAME_ROW = 1;        
        private static final int ATTACKING_DOWN_FRAME_ROW = 2;        
        private static final int ATTACKING_RIGHT_FRAME_ROW = 3;  
        
        Animation attackingDownAnima; 
        Animation attackingUpAnima;          
        Animation attackingLeftAnima;          
        Animation attackingRightAnima; 
        
        Animation upIdling;
        Animation downIdling;
        Animation leftIdling;
        Animation rightIdling;
        
        Texture   attackingSheet;             
        TextureRegion[] attackingUpFrames;             
        TextureRegion[] attackingDownFrames;             
        TextureRegion[] attackingLeftFrames;             
        TextureRegion[] attackingRightFrames; 
        
        Animation currentAnimation;
        
        
      //  System.out.println("I am attacking");
    //}
        public AttackingFunction() {
            createAnimations();
        //currentAnimation = downIdling;
        }
    private void createAnimations() {
                
        attackingSheet = new Texture(Gdx.files.internal("playerSlash.png"));
        
        TextureRegion[][] tmp = TextureRegion.split(attackingSheet, attackingSheet.getWidth()/(ATTACKING_FRAME_COLS), 
                attackingSheet.getHeight()/4); //There are 4 rows.
       // TextureRegion[][] tmp = TextureRegion.split(attackingSheet, attackingSheet.getWidth()/(ATTACKING_FRAME_COLS), 
        //        attackingSheet.getHeight()/4); //There are 4 rows.

        
       // attackingUpFrames = new TextureRegion[ATTACKING_FRAME_COLS-1];
       attackingUpFrames = new TextureRegion[ATTACKING_FRAME_COLS-1];
        attackingDownFrames = new TextureRegion[ATTACKING_FRAME_COLS-1];
        attackingLeftFrames = new TextureRegion[ATTACKING_FRAME_COLS-1];
        attackingRightFrames = new TextureRegion[ATTACKING_FRAME_COLS-1];
        
                
        //For when the character is in Idle                           
        upIdling  = new Animation(0.0025f, tmp[ATTACKING_UP_FRAME_ROW][0]);
        downIdling  = new Animation(0.0025f, tmp[ATTACKING_DOWN_FRAME_ROW][0]);
        leftIdling  = new Animation(0.025f, tmp[ATTACKING_LEFT_FRAME_ROW][0]);
        rightIdling  = new Animation(0.025f, tmp[ATTACKING_RIGHT_FRAME_ROW][0]);
        
        //For the walking animations
        
        //Up
        for (int i = 0, j = 1; j < ATTACKING_FRAME_COLS; j++) {
            attackingUpFrames[i++] = tmp[ATTACKING_UP_FRAME_ROW][j];  
            System.out.println("UP:  " + tmp[ATTACKING_UP_FRAME_ROW][j]);
        }        
        attackingUpAnima = new Animation(0.075f, attackingUpFrames);
                
        
        //Left
        for (int i = 0, j = 1; j < ATTACKING_FRAME_COLS; j++) {
            attackingLeftFrames[i++] = tmp[ATTACKING_LEFT_FRAME_ROW][j];  
        }        
        attackingLeftAnima = new Animation(0.075f, attackingLeftFrames);
        
        
        //Down
        for (int i = 0, j = 1; j < ATTACKING_FRAME_COLS; j++) {
            attackingDownFrames[i++] = tmp[ATTACKING_DOWN_FRAME_ROW][j];
            System.out.println("DOWN:  " + tmp[ATTACKING_DOWN_FRAME_ROW][j]);
        }        
        attackingDownAnima = new Animation(0.075f, attackingDownFrames);
        

        //Right
        for (int i = 0, j = 1; j < ATTACKING_FRAME_COLS; j++) {
            attackingRightFrames[i++] = tmp[ATTACKING_RIGHT_FRAME_ROW][j];
            System.out.println("RIGHT:  " + tmp[ATTACKING_RIGHT_FRAME_ROW][j]);
        }
        attackingRightAnima = new Animation(0.075f, attackingRightFrames);
    }
        
}
