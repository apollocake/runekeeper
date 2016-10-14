
package com.angrynerds.runekeeper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;


public class PlayerAnimation{
    
    //Based off the playerWlaking.png in the assets folder
    private static final int WALKING_FRAME_COLS= 9;
    private static final int WALKING_UP_FRAME_ROW = 0; 
    private static final int WALKING_LEFT_FRAME_ROW = 1;        
    private static final int WALKING_DOWN_FRAME_ROW = 2;        
    private static final int WALKING_RIGHT_FRAME_ROW = 3;        


    Animation walkingDownAnima; 
    Animation walkingUpAnima;          
    Animation walkingLeftAnima;          
    Animation walkingRightAnima; 
        
    Animation upIdling;
    Animation downIdling;
    Animation leftIdling;
    Animation rightIdling;
    //Animations for player damage
    Animation dupIdling;
    Animation ddownIdling;
    Animation dleftIdling;
    Animation drightIdling;

    Texture   walkingSheet;  
    Texture   walkingSheet2;
    
    TextureRegion[] walkingUpFrames;             
    TextureRegion[] walkingDownFrames;             
    TextureRegion[] walkingLeftFrames;             
    TextureRegion[] walkingRightFrames; 

   
    Animation currentAnimation;

    

    
    public PlayerAnimation(float x, float y) {
        createAnimations();
        currentAnimation = downIdling;

    }
    
    private void createAnimations() {
                
        walkingSheet = new Texture(Gdx.files.internal("playerWalking.png"));
        walkingSheet2 = new Texture(Gdx.files.internal("playerWalking3.png"));

        TextureRegion[][] tmp2 = TextureRegion.split(walkingSheet2, walkingSheet2.getWidth()/WALKING_FRAME_COLS, 
                walkingSheet2.getHeight()/4); //There are 4 rows.
        
        
        TextureRegion[][] tmp = TextureRegion.split(walkingSheet, walkingSheet.getWidth()/WALKING_FRAME_COLS, 
                walkingSheet.getHeight()/4); //There are 4 rows.

        
        walkingUpFrames = new TextureRegion[WALKING_FRAME_COLS-1];
        walkingDownFrames = new TextureRegion[WALKING_FRAME_COLS-1];
        walkingLeftFrames = new TextureRegion[WALKING_FRAME_COLS-1];
        walkingRightFrames = new TextureRegion[WALKING_FRAME_COLS-1];
        
                
        //For when the character is in Idle                           
        upIdling  = new Animation(0.025f, tmp[WALKING_UP_FRAME_ROW][0]);
        downIdling  = new Animation(0.025f, tmp[WALKING_DOWN_FRAME_ROW][0]);
        leftIdling  = new Animation(0.025f, tmp[WALKING_LEFT_FRAME_ROW][0]);
        rightIdling  = new Animation(0.025f, tmp[WALKING_RIGHT_FRAME_ROW][0]);
        
        
        dupIdling  = new Animation(0.025f, tmp2[WALKING_UP_FRAME_ROW][0]);
        ddownIdling  = new Animation(0.025f, tmp2[WALKING_DOWN_FRAME_ROW][0]);
        dleftIdling  = new Animation(0.025f, tmp2[WALKING_LEFT_FRAME_ROW][0]);
        drightIdling  = new Animation(0.025f, tmp2[WALKING_RIGHT_FRAME_ROW][0]);
        
        //For the walking animations
        
        //Up
        for (int i = 0, j = 1; j < WALKING_FRAME_COLS; j++) {
            walkingUpFrames[i++] = tmp[WALKING_UP_FRAME_ROW][j];  
            //System.out.println("UP:  " + tmp[WALKING_UP_FRAME_ROW][j]);
        }        
        walkingUpAnima = new Animation(0.075f, walkingUpFrames);
                
        
        //Left
        for (int i = 0, j = 1; j < WALKING_FRAME_COLS; j++) {
            walkingLeftFrames[i++] = tmp[WALKING_LEFT_FRAME_ROW][j];  
        }        
        walkingLeftAnima = new Animation(0.075f, walkingLeftFrames);
        
        
        //Down
        for (int i = 0, j = 1; j < WALKING_FRAME_COLS; j++) {
            walkingDownFrames[i++] = tmp[WALKING_DOWN_FRAME_ROW][j];
            //System.out.println("DOWN:  " + tmp[WALKING_DOWN_FRAME_ROW][j]);
        }        
        walkingDownAnima = new Animation(0.075f, walkingDownFrames);
        

        //Right
        for (int i = 0, j = 1; j < WALKING_FRAME_COLS; j++) {
            walkingRightFrames[i++] = tmp[WALKING_RIGHT_FRAME_ROW][j];
            //System.out.println("RIGHT:  " + tmp[WALKING_RIGHT_FRAME_ROW][j]);
        }
        walkingRightAnima = new Animation(0.075f, walkingRightFrames);
    }


    public int getWidth(){
        return walkingSheet.getWidth()/WALKING_FRAME_COLS;
    }
    public int getHeight(){
        return walkingSheet.getHeight()/4;
    }
    
 
    
}
