
package com.angrynerds.runekeeper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;


class EnemyPatrol{
    
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
    Animation patrol;

    Texture   walkingSheet;             
    TextureRegion[] walkingUpFrames;             
    TextureRegion[] walkingDownFrames;             
    TextureRegion[] walkingLeftFrames;             
    TextureRegion[] walkingRightFrames; 
    
    public Vector2 pos = new Vector2();
    Animation currentAnimation;
    

    
    public EnemyPatrol(float x, float y) {
        createAnimations();
        currentAnimation = patrol;
    }
    
    private void createAnimations() {
                
        walkingSheet = new Texture(Gdx.files.internal("playerWalking.png"));
        

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
        
        //For the walking animations
        
        //Up
        for (int i = 0, j = 1; j < WALKING_FRAME_COLS; j++) {
            walkingUpFrames[i++] = tmp[WALKING_UP_FRAME_ROW][j];  
            System.out.println("UP:  " + tmp[WALKING_UP_FRAME_ROW][j]);
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
            System.out.println("DOWN:  " + tmp[WALKING_DOWN_FRAME_ROW][j]);
        }        
        walkingDownAnima = new Animation(0.075f, walkingDownFrames);
        

        //Right
        for (int i = 0, j = 1; j < WALKING_FRAME_COLS; j++) {
            walkingRightFrames[i++] = tmp[WALKING_RIGHT_FRAME_ROW][j];
            System.out.println("RIGHT:  " + tmp[WALKING_RIGHT_FRAME_ROW][j]);
        }
        walkingRightAnima = new Animation(0.075f, walkingRightFrames);
    }

    void setLocation(float x, float y) {
        pos.x = x;
        pos.y = y;
    }
    
 
    
}
